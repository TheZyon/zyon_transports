package DAO;

import java.sql.Time;
import java.util.List;

import model.Arrivo;
import model.Composizione;
import model.Mezzo;
import model.Tappa;
import model.Tratta;
import utils.SuccessivoMod;

public class DAOArrivi extends DAO<Arrivo> {

	
	public  List<Arrivo> getArriviByMezzo(Mezzo m){
		List<Arrivo> list= null ;
		var em= super.getEntityManager();
		//1. prendo tutti gli arrivi del mezzo
		list = em.createNamedQuery("getArriviByMezzo").setParameter("mezzino", m).getResultList();
		return list;
		
	}

	
	
	
	/*
	 * 1. verifica se l'ultima tappa raggiunta dal mezzo m fa parte della tratta 
	 * 2. se no lancia eccezione, se sì cera arrivo con tappa successiva della tratta
	 * 
	 * */
	public void createArrivo(Mezzo m, int numero_tratta, Time t) throws Exception {
		
		Tratta tr= m.getTratte().get(numero_tratta-1);
		
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
		
		if(order_tappa_in_tratta>0) {//la tale tappa sta nella tratta
			
			//prendo la tappa successiva nella tratta
			List<Composizione> tappeTratta=tr.getTappe_indicizzate();
			//TODO ---> controllare come mai si aggiunge un 1 di troppo
			int indice_successivo= SuccessivoMod.successivo(tr.getOrdine(), order_tappa_in_tratta)-1; //indice tappa successiva nella tratta
			
			super.merge(new Arrivo(m, tappeTratta.get(indice_successivo).getTappa(), t));
			
			
		}
		else {throw new Exception("l'ultima tappa non faceva parte di questa tratta!");}
		}
		
	}
		
		

	public int orderTappaInTratta(Tappa t, Tratta tr) { //returns positive index specifiyng the position of true, odtherwise retirns 0
	
	var tappeIndicizzate= tr.getTappe_indicizzate();
	
	int res=0;
	
	for(Composizione ti : tappeIndicizzate) {

		if(ti.getTappa().getId()==t.getId()) res=ti.getPosizione_nella_tratta();		
	}
	
	return res;
	
	}
	
}
	
