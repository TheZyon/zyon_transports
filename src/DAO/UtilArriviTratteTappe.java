package DAO;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;

import controller.MainProject;
import model.Arrivo;
import model.Composizione;
import model.Mezzo;
import model.Tappa;
import model.Tratta;
import utils.SuccessivoMod;

public class UtilArriviTratteTappe extends DAO<Arrivo> {


	static Logger log= MainProject.log; 

	@SuppressWarnings("unchecked")
	public  List<Arrivo> getArriviByMezzo(Mezzo m){ //ritorna lista arrivi mezzo ordinata cronologicamente
		List<Arrivo> list= null ;
		var em= super.getEntityManager();
		//1. prendo tutti gli arrivi del mezzo
		list = (List<Arrivo>) em.createNamedQuery("getArriviByMezzo").setParameter("mezzino", m).getResultList();
		return list;
		
	}
	
	public List<Arrivo> getArriviByMezzoAndTimeConstraint(Mezzo m, Timestamp start, Timestamp end){
		List<Arrivo> list=null;
		var em=super.getEntityManager();
		em.getTransaction().begin();
		
		list=em.createNamedQuery("getArriviByMezzo&TimeInterval")
				.setParameter("mezzino", m)
				.setParameter("data1", start)
				.setParameter("data2", end)
				.getResultList();
		
		em.getTransaction().commit();
		return list;
		
	}
	
	
	/*
	 * 1. verifica se l'ultima tappa raggiunta dal mezzo m fa parte della tratta , se sì crea arrivo a tappa successiva, altrimenti
	 * 2. verifica se l'ultima tappa era una tappa conclusiva di qualche tratta, se sì aggiunge la prima tappa della nuova tratta, altrimenti 
	 * 3. lancia eccezione 
	 * */
	public void createArrivo(Mezzo m, int numero_tratta, Timestamp t) throws Exception {
		
		Tratta tr= m.getTratte()
				.stream()
				.sorted(Comparator.comparingInt(Tratta::getId))
				.collect(Collectors.toList())
				.get(numero_tratta-1);
		
		//prendo ultima tappa raggiunta dal tale mezzo
		var arrivi=getArriviByMezzo(m);
		
		if(arrivi.size()==0) {//il mezzo non è mai arrivato da nessuna parte, aggiungo la prima tappa della tratta
			var tappaInizialeTratta=tr.getTappe_indicizzate().get(0).getTappa();
			super.merge(new Arrivo(m,tappaInizialeTratta, t));
		}
		else { //c'è qualche arrivo
		Tappa ultimaTappaRaggiunta= arrivi.get(arrivi.size()-1).getTappa();
	
		//verifica compatibilità: se l'ultima tappa raggiunta sta nella tratta
		int order_tappa_in_tratta =orderTappaInTratta(ultimaTappaRaggiunta, tr);
		
		if(order_tappa_in_tratta>0) {//la tale tappa sta nella tratta---> aggiungo arrivo alla tappa successiva nel db
			log.info("L'ultima tappa fa parte della tratta immessa....");
			//prendo la tappa successiva nella tratta
			List<Composizione> tappeTratta=tr.getTappe_indicizzate();
			//TODO ---> controllare come mai si aggiunge un 1 di troppo
			int indice_successivo= SuccessivoMod.successivo(tr.getOrdine(), order_tappa_in_tratta)-1; //indice tappa successiva nella tratta
			
			super.merge(new Arrivo(m, tappeTratta.get(indice_successivo).getTappa(), t));
			
			
		}
		else{//l'ultima tappa raggiunta non fa parte della tratta passata nei parametri ---> verifico se era una tappa finale di qualche tratta del mezzo
			log.info("L'ultima tappa non fa parte della tratta immessa.....");
			log.info("L'ultima tappa: "+ultimaTappaRaggiunta);
			int nTratteMezzoCheFiniscono_con_ultimaTratta=m.getTratte().stream()
					.filter(tra->{ //filtro le tratte su quelle che hanno ultimaTappaRaggiunta come prima-ultima tappa
			
					Tappa primaTappaTratta=	tra.getTappe_indicizzate().stream().sorted(Comparator.comparingInt(Composizione::getPosizione_nella_tratta))
						.collect(Collectors.toList()).get(0).getTappa();
			
					log.info(primaTappaTratta.toString());
			
			
					if(primaTappaTratta.toString().equals(ultimaTappaRaggiunta.toString())){ log.info("coincidono!!!!"); return true;}		
					else {return false;}
			
					})
					.collect(Collectors.toList()).size();
			
			if(nTratteMezzoCheFiniscono_con_ultimaTratta>0) {//verifico se l'ultima tappa raggiunta ha posizione 1 in qualche tratta del mezzo
				var tappaInizialeTratta=tr.getTappe_indicizzate().get(0).getTappa();
				super.merge(new Arrivo(m,tappaInizialeTratta, t));
			}
			
			else throw new Exception("l'ultima tappa non faceva parte di questa tratta, e non concludeva nessuna tratta...non usare i mezzi aziendali per scappare nei boschi!");
			
			
		} 
		
		
		}
		
	}

	public int orderTappaInTratta(Tappa t, Tratta tr) { //returns positive index specifiyng the position of true, odtherwise retirns 0
	
	var tappeIndicizzate= tr.getTappe_indicizzate().stream().sorted(Comparator.comparingInt(Composizione::getPosizione_nella_tratta))
							.collect(Collectors.toList());
	
	int res=0;
	
	for(Composizione ti : tappeIndicizzate) {

		if(ti.getTappa().getId()==t.getId()) res=ti.getPosizione_nella_tratta();		
	}
	
	return res;
	
	}
	
		
	/* 
	 * Ritorna lista delle liste arrivi che compongono le tappe percorse, cioè una lista dell'output contiene gli arrivi di una tratta percorsa 
	 * 1. verifico se la tratta è assegnata al mezzo 
	 * 2. prendo lista id delle tappe della tratta del mezzo, ordinate secondo la posizione nella tratta 
	 * 3. prendo lista arrivi del mezzo, ordinati per orario, prendo id 
	 * 4. comparo sequenzialmente le sottoliste degli arrivi di ordine pari alla tratta, 
	 * 	  con la lista delle tappe della tratta per verificare corrispondenza (comparo gli id)
	 * 	  e aggiungo le sottoliste che corrispondono alla lista ritornata
	 * */
	public List<List<Arrivo>> getPercorsiEffettiviMezzoTratta(Mezzo m, Tratta t) throws Exception{
		
		List<List<Arrivo>> res= new ArrayList<List<Arrivo>>();
		
		//1.
		if(m.getTratte().contains(t)) {
			//log.info("La tratta appartiene al mezzo");
			//2.
			List<Integer> idsTappe=t.getTappe_indicizzate().stream()
							.sorted(Comparator.comparingInt(s->s.getPosizione_nella_tratta()))
							.map(ti-> ti.getTappa().getId())
							.collect(Collectors.toList()); //lista degli id delle tappe della tratta, ordinati secondo la posizione nella tratta
			
			int ordine= t.getOrdine();
			//log.info("la tratta ha ordine "+ ordine);
			
			//3.
			List<Arrivo> arriviMezzo=m.getArrivi().stream()
					.sorted(Comparator.comparingLong(a->a.getData().getTime()))
					.collect(Collectors.toList());
			
			//log.info("Gli arrivi del mezzo ordinati: "+arriviMezzo.toString());
			
			//4.
			for(int i = 0; i<arriviMezzo.size() - ordine +1; i++) {
				
			  List<Arrivo> sottoArrivi= arriviMezzo.subList(i, i+ordine);
			  List<Integer> idsSottoArrivi= sottoArrivi.stream()
												.map(a->a.getTappa().getId())
														.collect(Collectors.toList());
			   
//			  System.out.println("ids sottolista arrivi: "+ idsSottoArrivi);
			   if(idsSottoArrivi.equals(idsTappe)) {//questa sottolista di arrivi corrisponde ad una percorrenza della tratta
				   //log.info("sottolista coincide! Aggiungo a percorsi fatti...");
				   res.add(sottoArrivi);
				   i+=ordine-1;
			   }
			   else {//log.info("sottolista non coincide");
				   }
			   
			   }
			log.info("I percorsi effettivi sono: "+ res.toString());
				return res;
			}
			else { throw new Exception("Il mezzo non ha assegnata questa tratta!");}
	}
	
	/*
	 * rispetto al metodo precedente l'unica differenza è data 
	 * dal vincolo temporale e si esaurisce nel punto 3.
	 * 
	 * */
	public List<List<Arrivo>> getPercorsiEffettiviMezzoTratta(Mezzo m, Tratta t,Timestamp start, Timestamp end) throws Exception{
		
		List<List<Arrivo>> res= new ArrayList<List<Arrivo>>();
		
		//1.
		if(m.getTratte().contains(t)) {
			//log.info("La tratta appartiene al mezzo");
			//2.
			List<Integer> idsTappe=t.getTappe_indicizzate().stream()
					.sorted(Comparator.comparingInt(s->s.getPosizione_nella_tratta()))
					.map(ti-> ti.getTappa().getId())
					.collect(Collectors.toList()); //lista degli id delle tappe della tratta, ordinati secondo la posizione nella tratta
			
			int ordine= t.getOrdine();
			//log.info("la tratta ha ordine "+ ordine);
			
			//3.
			List<Arrivo> arriviMezzo=getArriviByMezzoAndTimeConstraint(m, start, end).stream()
					.sorted(Comparator.comparingLong(a->a.getData().getTime()))
					.collect(Collectors.toList());
			
			//log.info("Gli arrivi del mezzo ordinati: "+arriviMezzo.toString());
			
			//4.
			for(int i = 0; i<arriviMezzo.size() - ordine +1; i++) {
				
				List<Arrivo> sottoArrivi= arriviMezzo.subList(i, i+ordine);
				List<Integer> idsSottoArrivi= sottoArrivi.stream()
						.map(a->a.getTappa().getId())
						.collect(Collectors.toList());
				
//			  System.out.println("ids sottolista arrivi: "+ idsSottoArrivi);
				if(idsSottoArrivi.equals(idsTappe)) {//questa sottolista di arrivi corrisponde ad una percorrenza della tratta
					//log.info("sottolista coincide! Aggiungo a percorsi fatti...");
					res.add(sottoArrivi);
					i+=ordine-1;
				}
				else {//log.info("sottolista non coincide");
				}
				
			}
			log.info("I percorsi effettivi sono: "+ res.toString());
			return res;
		}
		else { throw new Exception("Il mezzo non ha assegnata questa tratta!");}
	}

	/*calcola il tempo percorso per una lista di arrivi che compongono una Tratta che è stata percorsa*/
	public Long tempoPercorso(List<Arrivo> trattaPercorsa) { 
		
		Long sum=(long) 0;
		List<Long> istantiArrivi= trattaPercorsa.stream()
				.sorted(Comparator.comparingLong(a->a.getData().getTime()))
				.map(arrivo->arrivo.getData().getTime())
				.collect(Collectors.toList());
		for(int i=1; i<istantiArrivi.size(); i++) {
			var in= Long.sum(istantiArrivi.get(i), -istantiArrivi.get(i-1));
			sum=Long.sum(sum, in);
		}
		
		return sum;
	}


}
	
