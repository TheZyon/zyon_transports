package controller;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.commons.lang3.tuple.Pair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.DAO;
import DAO.UtilArriviTratteTappe;
import model.Abbonamento;
import model.Arrivo;
import model.Attivo;
import model.Biglietto;
import model.Composizione;
import model.Distributore;
import model.Emittente;
import model.Mezzo;
import model.Periodo;
import model.Rivenditore;
import model.Stato;
import model.Tappa;
import model.Tessera;
import model.Tipo;
import model.Tratta;
import model.Utente;
import utils.NoSuchElementInDBException;
import utils.TimeUtil;



public class MainProject {
	

	public static Logger log = LoggerFactory.getLogger(MainProject.class);

	public static DAO dao = new DAO();
	public static UtilArriviTratteTappe utilArrivi= new UtilArriviTratteTappe();
	public static void main(String[] args) {

		
		try {

			
		

			
//			 PRESENTAZIONE FUNZIONALITA'
//			 
//				
//			 	var tr= (Tratta) dao.getById("Tratta", 2);
//			 	var m=(Mezzo)dao.getById("Mezzo", 1);
//			 
//			 
//			 
//			 	TERZO PARAGRAFO
//			 
//				numero passaggi per tappa
//			 	log.info("passaggi mezzo 1 per tappa a1: "+ passaggiPerTappa(m, (Tappa)dao.getById("Tappa", 1)));
//				
//			 	numero passaggi per tappa in intervallo di tempo
//			 	log.info(""+passaggiPerTappa(m,(Tappa)dao.getById("Tappa", 1) , Timestamp.valueOf("1995-04-09 13:00:00"),Timestamp.valueOf("1995-04-09 13:40:00") ));
//			 
//			 	durate delle percorrenze effettive delle tratte tra una data start e una data end
//			 	tempoEffettivoMezzoTratta(m, tr,Timestamp.valueOf("1995-04-09 13:00:00"), Timestamp.valueOf("1995-04-09 14:13:00") )
//			 	.forEach(p->log.info( p.getKey()+". tempo: "+ p.getValue().toString()));;
			 
			 
		} catch (Exception e) {e.printStackTrace();}
		finally {dao.closeAll();}
		
		
	}
	public static void popolaDB() throws NoSuchElementInDBException, Exception{ //fa quello che dice il suo nome
		

//		creazioni utenti, tessere, rivenditori, biglietti	 
		
			  dao.create(new Utente("Carlo", "Rossi", "ghjkl"));
			  dao.create(new Rivenditore());
				dao.create(new Distributore(Attivo.FUORI_SERVIZIO));
				dao.create(new Distributore(Attivo.IN_SERVIZIO));
				
				dao.create(new Utente("Pina", "Pipina", "pipipi"));
				dao.create(new Utente("Carmelo", "Koko", "kokokokoko"));
				dao.create(new Utente("Uomo", "Cunigghio", "BUNNYMEN2023"));
				
				dao.create(new Tessera(Timestamp.valueOf("2022-12-25 00:00:00"), Timestamp.valueOf("2022-12-25 00:00:00"),(Emittente) dao.getById("Emittente", 1), (Utente) dao.getById("Utente", 1) ));
			
				dao.create(new Biglietto((Emittente) dao.getById("Emittente", 2), (Utente)dao.getById("Utente", 5), Timestamp.valueOf("2023-01-15 11:11:11"), false));
				dao.create(new Biglietto((Emittente) dao.getById("Emittente", 2), (Utente)dao.getById("Utente", 5), Timestamp.valueOf("2023-01-15 11:20:11"), false));
			  Emittente e = new Rivenditore();
			  dao.create(new Tessera(Timestamp.valueOf("2020-12-25 10:00:00"), Timestamp.valueOf("2021-12-25 10:00:00"), e , new Utente("Carmnelino", "c", "asdfghjkl")));	
			  
			  //altri biglietti
			  for(int i=0; i<4; i++) {
					dao.create(new Biglietto((Emittente) dao.getById("Emittente", 2), (Utente)dao.getById("Utente", 4), Timestamp.valueOf("2023-0"+(i+1)+"-0"+(i+5) +" 11:20:11"), false));
					dao.create(new Biglietto((Emittente) dao.getById("Emittente", 2), (Utente)dao.getById("Utente", 3), Timestamp.valueOf("2023-0"+(i+1)+"-0"+(i+5) +" 11:20:11"), false));
					dao.create(new Biglietto((Emittente) dao.getById("Emittente", 1), (Utente)dao.getById("Utente", 2), Timestamp.valueOf("2023-0"+(i+1)+"-0"+(i+5) +" 06:06:06"), false));
					
				}
				dao.create(new Abbonamento(Timestamp.valueOf("2021-12-25 10:00:00"), (Emittente)dao.getById("Emittente", 1), (Tessera) dao.getById("Tessera", 1), Periodo.MENSILE,Timestamp.valueOf("2022-01-25 10:00:00") ));
			  
			  //creazione mezzo 1 con tre tratte
			  var trenino = new Mezzo(Tipo.TRAM, 10, Stato.IN_SERVIZIO);
			
			  var tratta1 = new Tratta("A1", "A2", Time.valueOf("00:32:00"), 2);
			  var tratta2= new Tratta("B1", "B3", Time.valueOf("00:08:00"), 3);
			  var tratta3= new Tratta("C1", "C4", Time.valueOf("00:30:00"),4);
	
			  trenino.addTratta(tratta1);
			  trenino.addTratta(tratta2);
			  trenino.addTratta(tratta3);
			  dao.create(trenino); 
			  
			 
			 // creazione delle tappe della tratte
			  
			 Tappa[] tratte1= {new Tappa("a1"), new Tappa("a2")};
			
			 Tappa[] tratte2= {new Tappa("b1"), new Tappa("b2"), new Tappa("b3")};
			 
			 Tappa[] tratte3 = {new Tappa("c1"),new Tappa("c2"),new Tappa("c3"), new Tappa("c4")};
			 Tappa[][] t= {tratte1, tratte2, tratte3};
			  
			 for(int i=0; i<3; i++) aggiungiTappeATratta((Tratta)dao.getById("Tratta", i+1), t[i]);
			  
			 
			 
				
	}
	
public static void	popolaDBArrivi() throws Exception {
	//creazione arrivi mezzo 1 
	 String a = "1995-04-09";
	 var m=(Mezzo)dao.getById("Mezzo", 1);
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 10:00:00"));
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 10:30:00"));
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 10:40:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 11:00:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 11:05:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 11:10:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 11:15:00"));
		utilArrivi.createArrivo(m,3,Timestamp.valueOf(a+" 12:00:00"));
		utilArrivi.createArrivo(m,3,Timestamp.valueOf(a+" 12:10:00"));
		utilArrivi.createArrivo(m,3,Timestamp.valueOf(a+" 12:20:00"));
		utilArrivi.createArrivo(m,3,Timestamp.valueOf(a+" 12:32:00"));
		utilArrivi.createArrivo(m,3,Timestamp.valueOf(a+" 12:40:00"));
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 13:00:00"));
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 13:31:00"));
		utilArrivi.createArrivo(m,1,Timestamp.valueOf(a+" 13:40:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:00:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:06:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:08:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:10:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:12:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:20:00"));
		utilArrivi.createArrivo(m,2,Timestamp.valueOf(a+" 14:30:00"));
}	
	//task ---> crea biglietto controllando abbonamento tessera (validità tessera è annuale)
	
	//task ---> ottenere biglietti emessi in intervallo temporale e per punto di emissione
	
	//task ---> verifica validità abbonamenti dato il mnumero di tessera utente
	
	
	
	
	
	
	
	//task ---> quante volte un mezzo passa per una tappa
	public static int passaggiPerTappa(Mezzo m, Tappa t) {//tutti i passaggi di m per t
		return m.getArrivi().stream()
				.filter(a->a.getTappa().getId()==t.getId())
				.collect(Collectors.toList())
				.size();	
	}
	public static int passaggiPerTappa(Mezzo m, Tappa t, Timestamp start, Timestamp end) { 
		//passaggiPerTappa tra inizio e fine
		return utilArrivi.getArriviByMezzoAndTimeConstraint(m, start, end).stream()
				.filter(a->a.getTappa().getId()==t.getId())
				.collect(Collectors.toList())
				.size();	
	}

	//task ---> tempo effettivo percorrenza di una tratta

	/* ritorna la lista di coppie (indice_percorso, tempo_impiegato) associata all'attività del mezzo m per la tratta t
	 * 1. uso metodo  percorsiEffettiviTratta(Mezzo m, Tratta t) per ottenere "List<List<Arrivo>> percorsi", in cui ogni lista elenca gli arrivi di una percorrenza della tratta
	 * 2. uso percorsi per ritornare la lista voluta
	 * */
	public static List<Pair<Integer, Time>> tempoEffettivoMezzoTratta(Mezzo m, Tratta t) throws Exception {
		
		//1. 
		List<List<Arrivo>> percorsi=utilArrivi.getPercorsiEffettiviMezzoTratta(m,t);
		//2.
		return percorsi.stream()
		.map(percorso->Pair.of(percorsi.indexOf(percorso)+1, TimeUtil.millisToTime(utilArrivi.tempoPercorso(percorso))))
		.collect(Collectors.toList());
		
	}
	public static List<Pair<Integer, Time>> tempoEffettivoMezzoTratta(Mezzo m, Tratta t,Timestamp start, Timestamp end) throws Exception {
		
		//1. 
		List<List<Arrivo>> percorsi=utilArrivi.getPercorsiEffettiviMezzoTratta(m,t, start, end);
		//2.
		return percorsi.stream()
				.map(percorso->Pair.of(percorsi.indexOf(percorso)+1, TimeUtil.millisToTime(utilArrivi.tempoPercorso(percorso))))
				.collect(Collectors.toList());
		
	}
	public static void aggiungiTappeATratta(Tratta tr, Tappa[] t) { //crea nuove tappe e le aggiunge a una tratta
		
		for(int i=0; i<t.length;  i++) {
			
			dao.create(t[i]);
			var tappe= (ArrayList<Tappa>) dao.getAll("Tappa").stream().sorted(Comparator.comparingInt(Tappa::getId)).collect(Collectors.toList());
			var tappa=tappe.get(tappe.size()-1); //l'ultima tappa inserita
			dao.create(new Composizione(tr, tappa, i+1));
			
		}
		
		
	}
}
