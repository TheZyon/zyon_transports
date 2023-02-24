package DAO;

import java.sql.Timestamp;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.persistence.EntityManager;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;

import controller.MainProject;
import model.Abbonamento;
import model.Biglietto;
import model.Emittente;
import model.Tessera;

public class DAOBigliettiAbbonamenti <T> extends DAO<T> {

	Logger log = MainProject.log;
	
	
	@SuppressWarnings("unchecked")
	public List<Biglietto> getBigliettiByEmittenteAndTimeInterval(Emittente e, Timestamp start, Timestamp end){ //ritorna lista biglietti emanati da emittente e, tra start ed end
		List<Biglietto> list=null;
		EntityManager em = super.getEntityManager();
		em.getTransaction().begin();
		
		list = em.createNamedQuery("bigliettiPerEmittente&TempoEmissione")
				.setParameter("e", e)
				.setParameter("d1", start)
				.setParameter("d2", end)
				.getResultList();
		
		
		em.getTransaction().commit();
		return list;
	}
	
	@SuppressWarnings("unchecked")
	public List<Abbonamento> getAbbonamentiByEmittenteAndTimeInterval(Emittente e, Timestamp start, Timestamp end){
		
		List<Abbonamento> list=null;
		EntityManager em = super.getEntityManager();
		em.getTransaction().begin();
		
		list = em.createNamedQuery("abbonamentoPerEmittente&TempoEmissione")
				.setParameter("e", e)
				.setParameter("d1", start)
				.setParameter("d2", end)
				.getResultList();
		
		
		em.getTransaction().commit();
		return list;

	}
	
	public List<Pair<Integer, Boolean>> validitaAbbonamentiTessera(Tessera t){ 
		//ritorna lista di coppie per gli abbonamenti della tessera t in cui Integer è l'id dell'abbonamneto e il Boolean dice se è attivo
		
	return	t.getAbbonamenti().stream()
		.map(a->{
			
			
		var ora = new Timestamp(System.currentTimeMillis());
			
		if(a.getData_scadenza().before(ora)) {//abbonamento scaduto
			return Pair.of(a.getId(), false);
		}
		else return Pair.of(a.getId(), true);
	
		})
		.sorted(Comparator.comparingInt(p->p.getKey()))
		.collect(Collectors.toList());
		
	}
		

}
