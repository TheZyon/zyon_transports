package controller;

import java.sql.Time;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.DAO;
import model.Mezzo;
import model.Stato;
import model.Tipo;
import model.Tratta;



public class MainProject {
	

	public static Logger log = LoggerFactory.getLogger(MainProject.class);

	public static DAO dao = new DAO();
	public static void main(String[] args) {

		
		try {
			
//		1.	######  TESTS #####
			
//		1.1	### create UTENTE, TESSERA, EMITTENTE, BIGLIETTO ---> ok ###
//			dao.create(new Utente("Carlo", "Rossi", "ghjkl"));
//			Emittente e = new Rivenditore();
//			dao.create(new Tessera(Date.valueOf("2020-12-25"), Date.valueOf("2021-02-02"), e , new Utente("Carmnelino", "c", "asdfghjkl")));	
//			Emittente r= (Rivenditore)dao.getById("Rivenditore",1);
//			Utente u= (Utente)dao.getById("Utente", 1);
//			dao.create(new Biglietto(r, u, Date.valueOf("2022-03-03"), false));
		
//		1.2	### create MEZZO, TRATTA ---->ok ###
			
			var trenino = new Mezzo(Tipo.TRAM, 10, Stato.IN_SERVIZIO);
			
			var tratta1 = new Tratta("A1", "A2", Time.valueOf("06:22:22"), 2);
//			var tratta2= new Tratta("B1", "B3", Time.valueOf("23:33:33"), 3);
//			var tratta3= new Tratta("C1", "C4", Time.valueOf("07:11:11"),4);
//	
//			trenino.addTratta(tratta1);
//			trenino.addTratta(tratta2);
//			trenino.addTratta(tratta3);
//			dao.create(trenino); 
//			var tram = (Mezzo) dao.getById("Mezzo", 1); //---->ok
			
//			STATI MEZZO----------------------
			
			
			
//			tram.getStati_mezzo().forEach(s->log.info(s.toString()));
//			tram.addStatoMezzo(new StatoMezzo(tram, Stato.IN_MANUTENZIONE, Date.valueOf("1990-01-01"), Date.valueOf("2000-02-01") ));
//			dao.merge(tram);
			
			
//		2.	POPOLAMENTO DATABASE
			/*
			 
			  dao.create(new Utente("Carlo", "Rossi", "ghjkl"));
			  Emittente e = new Rivenditore();
			  dao.create(new Tessera(Date.valueOf("2020-12-25"), Date.valueOf("2021-02-02"), e , new Utente("Carmnelino", "c", "asdfghjkl")));	
			  var trenino = new Mezzo(Tipo.TRAM, 10, Stato.IN_SERVIZIO);
			
			  var tratta1 = new Tratta("A1", "A2", Time.valueOf("06:22:22"), 2);
			  var tratta2= new Tratta("B1", "B3", Time.valueOf("23:33:33"), 3);
			  var tratta3= new Tratta("C1", "C4", Time.valueOf("07:11:11"),4);
	
			  trenino.addTratta(tratta1);
			  trenino.addTratta(tratta2);
			  trenino.addTratta(tratta3);
			  dao.create(trenino); 
			  
			 
			  
			 */
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		finally {dao.closeAll();}
		
		
	}

	
}
