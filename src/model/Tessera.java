package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;


/* Lorenzo
 * 1. Completare Tessera con mapping *-1 su Emittente (attenzione a completare Emittente con la lista delle tessere) 
 * 2. creare Abbonamento e fare i sue mapping *-1 (su Tessera e su Emittente)
 * */





@Entity
@Table(name="tessera")
public class Tessera {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;

	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_utente")
	private Utente utente;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "tessera")
	private List<ConvalidaTessera> convalide;
	
	//convenience method to manage convalide
	public void add(ConvalidaTessera c) {
			if(convalide==null) convalide=new ArrayList<ConvalidaTessera>();
			convalide.add(c);
		}
	
	
}
