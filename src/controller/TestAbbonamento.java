package controller;

import java.sql.Date;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import DAO.DAO;
import model.Abbonamento;
import model.Distributore;
import model.Emittente;
import model.Periodo;
import model.Rivenditore;
import model.Tessera;
import model.Utente;

public class TestAbbonamento {
	
	static EntityManagerFactory emf = Persistence.createEntityManagerFactory("zyon_transports");
	static Logger log = LoggerFactory.getLogger(DAO.class);
	static Date today = Date.valueOf("2023-1-1");
	
	public static void main(String[] args) {
		Distributore d1 = new Distributore();
		Rivenditore r1 = new Rivenditore();
		Rivenditore r2 = new Rivenditore();
		Utente u1 = new Utente("Mario", "Rossi", "RSSMRA98B22H501K");
		Utente u2 = new Utente("Luigi", "Verdi", "VRDLGU98B22F205J");
		Utente u3 = new Utente("Anna", "Bianchi", "BNCNNA98B62A662N");
		Tessera t1 = new Tessera(Date.valueOf("2021-12-25"), r1,  u1);
		Tessera t2 = new Tessera(Date.valueOf("2022-1-15"), r2,  u2);
		Tessera t3 = new Tessera(Date.valueOf("2021-11-20"), d1,  u3);
		
		
//		DAO<Utente> u = new DAO<Utente>();
//		DAO<Tessera> d = new DAO<Tessera>();
//		u.create(u1);
//		d.create(t3);
		
//		rinnovaTesseraById(2);
		
//		createAbbonamento(Date.valueOf("2022-2-25"), d1, t2, Periodo.MENSILE);

	}
	
	
	public static void createAbbonamento(Date dataErogazione, Emittente e, Tessera t, Periodo periodo) {
		EntityManager em = emf.createEntityManager();
		
		if (today.after(t.getData_scadenza())) {
			log.info("ERRORE: Tessera scaduta. Impossibile creare un abbonamento; rinnovare la tessera per registrare un nuovo abbonamento.");
			log.info("ID tessera scaduta: " + t.getId());
			return;
		}
		Abbonamento a = new Abbonamento(dataErogazione, e, t, periodo);
		
		em.getTransaction().begin();
		em.persist(a);
		em.getTransaction().commit();
		log.info("CREATED IN DB: "+ a.toString());
	}
	
	public static void rinnovaTesseraById(int id) {
		EntityManager em = emf.createEntityManager();
		
		em.getTransaction().begin();
		Tessera t = em.find(Tessera.class, id);
		t.setData_erogazione(today);
		t.setData_scadenza(Date.valueOf(today.toLocalDate().plusYears(1)));
		em.merge(t);
		em.getTransaction().commit();
		log.info("RENEWED IN DB: "+ t.toString());
	}

}
