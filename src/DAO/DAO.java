package DAO;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import utils.JPAUtil;
import utils.NoSuchElementInDBException;

public class DAO <T> {
	private EntityManagerFactory emf;
	private EntityManager em;
	private Logger log = LoggerFactory.getLogger(DAO.class);
	public DAO() {
		emf= JPAUtil.getEntityManagerFactory();
		em=emf.createEntityManager();
	}
	
	public void closeAll() {
		em.close();
		emf.close();
	}
	
	
	
	//operazioni CRUD
	
	//create
	public void create(T t) {
		em.getTransaction().begin();
		
		em.persist(t);
		
		em.getTransaction().commit();
		
		log.info("CREATED IN DB: "+ t.toString());

	}
	
	
	//retrieve

	public T getById(String className, int id) throws NoSuchElementInDBException {
			List<T> list=null;
		
			em.getTransaction().begin();
			
		
			list= em.createQuery("SELECT i FROM "+ className+" i" +" WHERE id="+id).getResultList();
			em.getTransaction().commit();
			
			if(list.size()>0) return (T) list.get(0);
			else throw new NoSuchElementInDBException();
			
			
	}

	public List<T> getAll(String className){
		em.getTransaction().begin();
		
		List<T> t = (List<T>) em.createQuery("SELECT c FROM "+ className + " c" ).getResultList();
		
		em.getTransaction().commit();
		return t;
	} 
	
	
	//update 
	
	public void merge(T t) {
			em.getTransaction().begin();
			em.merge(t);
			em.getTransaction().commit();
		}


	//delete
	public void delete(T t) throws Exception {
			
			
			em.getTransaction().begin();
			
			em.remove(t);
			
			em.getTransaction().commit();
		}
		
	public EntityManager getEntityManager() {
		return em;
	}
	
}
