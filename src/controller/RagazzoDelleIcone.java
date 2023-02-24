package controller;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import DAO.DAO;
import model.Abbonamento;
import model.Biglietto;
import model.ConvalidaTessera;
import model.Distributore;
import model.Emittente;
import model.Mezzo;
import model.Stato;
import model.StatoMezzo;
import model.Tessera;
import model.Tipo;
import model.Utente;
import model.Vidimazione;
import utils.NoSuchElementInDBException;
//funziona tutto della parte iniziale dei mezzi serve solo una mano con la parte della lista segnata con TODO
//fare test su tessera poi finito tutto
public class RagazzoDelleIcone {

	public static DAO dao = new DAO();
	static Biglietto biglietto = new Biglietto();
	static Emittente emittente = new Distributore();
	static Utente utente = new Utente();
	static Mezzo trenino = new Mezzo(Tipo.TRAM, 10, Stato.IN_SERVIZIO);

	
	
	public static void main(String[] args) throws Exception {
		//MainProject.popolaDB();
		//creaMezzo(mezzo);
		//modificaStatoMezzo(1);
		
		//creaBiglietto();
		//vidimaBiglietto(11, 2);
		
		vidimatiSuMezzo(mezzo,Timestamp.valueOf("2023-02-24 04:00:35.805"));

		
		
	}

	//nuovo mezzo
		//nuovo stato mezzo
		static Mezzo mezzo = new Mezzo(Tipo.AUTOBUS, 45, Stato.IN_SERVIZIO);
		static StatoMezzo statoMezzo = new StatoMezzo();
		
		//metodo per creare mezzo che lo aggiunge alla lista mezzi ed al database
		@SuppressWarnings("unchecked")
		public static void creaMezzo(Mezzo mezzo) {
			dao.create(mezzo);

		};

		//metodo modifica stato mezzo che prima li salva nella lista e poi nel db
		public static void modificaStatoMezzo(int id) throws NoSuchElementInDBException {
			
			mezzo = (Mezzo) dao.getById("Mezzo", id);

			//impostgazione mezzo dello stato
			statoMezzo.setMezzo(mezzo);
			
			//impostazione stato dello stato mezzo
			statoMezzo.setStato(mezzo.getStato());
			
			if(mezzo.getStato() == Stato.IN_SERVIZIO) {
				mezzo.setStato(Stato.IN_MANUTENZIONE);
			}else {
				mezzo.setStato(Stato.IN_SERVIZIO);
			
			}
			
			//impostazione della data iniziale (cicla tutti gli stati in cerca di uno con l'id uguale al mezzo di cui stiamo cambiando lo stato)
			//List<StatoMezzo> statoMezzi = dao.getAll("StatoMezzo"); TODO perchè mi a problema con la lista 
			
			//imposta data inizio statoMezzo
			Timestamp dataInizio = new Timestamp(System.currentTimeMillis());
			statoMezzo.setData_inizio(dataInizio);		
			//impostazione della data finale (il momento in cui si istanzia il metodo) quindi data istantanea in formato Long (es* January 12, 1952)
			Timestamp dataFine = new Timestamp(System.currentTimeMillis());
			statoMezzo.setData_fine(dataFine);
			
			

		
			dao.create(statoMezzo);
			dao.merge(mezzo);
			
		}
		
		//parte vidimazione
		//check del biglietto pasasto con update del biglietto in lista e db
		public static void vidimaBiglietto(int biglietto, int m) throws NoSuchElementInDBException {
			Biglietto b = (Biglietto) dao.getById("Biglietto", biglietto);
				if (b.isVidimato()) {
					System.out.println("il biglietto è già stato vidimato, non fare il tirchio e spulcia i soldi per comprarne uno nuovo");
				}else {
					b.setVidimato(true);
					System.out.println("biglietto accettato");
							
							//set del biglietto su vidimato sia in lista biglietti che su db
							b.setVidimato(true);
							dao.merge(b);
							
							//set della nuova vidimazione
							Vidimazione bigliettoVidimato = new Vidimazione();
							bigliettoVidimato.setBiglietto(b);
							Timestamp dataVidimazione = new Timestamp(System.currentTimeMillis());
							bigliettoVidimato.setData(dataVidimazione);
							mezzo = (Mezzo) dao.getById("Mezzo", m);
							bigliettoVidimato.setMezzo(mezzo);
							
							//set del biglietto nella lista vidimazioni e nel db
							dao.create(bigliettoVidimato);
						}		
				}
				
		
		
		//metodo per ottenere quante vidimazioni sono avvenute su determinato mezzo
		public static int vidimatiSuMezzo(Mezzo m, Timestamp d) {
			List <Vidimazione> vidimazioni = dao.getAll("Vidimazione");
			int i = 0;
			for (Vidimazione v : vidimazioni) {
				if (v.getMezzo() == m) {
					if (v.getData() == d) {
						i++;
					}
				}
				
			}
			System.out.println(i);
			return i;
		}
		
		
			

		
	
}
