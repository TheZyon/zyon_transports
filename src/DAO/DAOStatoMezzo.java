package DAO;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import model.Mezzo;
import model.Stato;
import model.StatoMezzo;
import utils.NoSuchElementInDBException;

public class DAOStatoMezzo {

	DAO dao = new DAO();
	
	/*
	 * 1.creo nuova istanza di statoMezzo con lo stato precedente del mezzo
	 * 2.cambio lo stato del mezzo
	 * 3. setto la data di fine dello stato precedente come la data in cui viene effettuato il cambio di stato
	 * 4. setto la data di inizio dello stato come unix epoch se non ci stanmno stati precedenti registrati, 
	 * 		altrimenti come la data di fine dell'ultimo stato registrato
	 * */
	public void modificaStatoMezzo(int id) throws NoSuchElementInDBException {
		
		var mezzo =(Mezzo) dao.getById("Mezzo", id);
		
		
		//1.
		StatoMezzo statoMezzo = new StatoMezzo();
		statoMezzo.setMezzo(mezzo);
		statoMezzo.setStato(mezzo.getStato());
		
		//2.
		if(mezzo.getStato().equals(Stato.IN_SERVIZIO)) {
			
			mezzo.setStato(Stato.IN_MANUTENZIONE);
		}else {
			mezzo.setStato(Stato.IN_SERVIZIO);
		
		}
		
		//impostazione della data iniziale (cicla tutti gli stati in cerca di uno con l'id uguale al mezzo di cui stiamo cambiando lo stato)
		//List<StatoMezzo> statoMezzi = dao.getAll("StatoMezzo"); TODO perchè mi a problema con la lista 
		
		//imposta data fine statoMezzo
		Timestamp dataInizio = new Timestamp(System.currentTimeMillis());
		statoMezzo.setData_fine(dataInizio);		
		//impostazione della data finale (il momento in cui si istanzia il metodo) quindi data istantanea in formato Long (es* January 12, 1952)
		
		
		//public StatoMezzo(Mezzo mezzo, Stato stato, Timestamp data_inizio, Timestamp data_fine)
		
		//imposta data inizio
		var em= dao.getEntityManager();
		List<StatoMezzo> list=null;
		em.getTransaction().begin();
		
		
		 list=em.createNamedQuery("getStatiMezzoByMezzo")
						.setParameter("m", mezzo)
								.getResultList();
		
		
		em.getTransaction().commit();
		
		if(list.size()==0) {//il mezzo non aveva salvati stati
			statoMezzo.setData_inizio(new Timestamp(0));			
		}
		else { //ci sono degli stati
			var v= list.stream() //stati del mezzo ordinati per la data di fine
			.sorted(Comparator.comparingLong(sm->sm.getData_fine().getTime()))
			.collect(Collectors.toList());
			
			//la data di inizio dello precedente coinciderà con 
			//la data di fine dell'ultimo stato che era stato registrato
			statoMezzo.setData_inizio(v.get(v.size()-1).getData_fine()); 
			
		}
	
		dao.create(statoMezzo);
		dao.merge(mezzo);
		
	}
	
	
	
}
