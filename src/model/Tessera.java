package model;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
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

	@Column
	private Date data_erogazione;
	@Column
	private Date data_scadenza;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_utente")
	private Utente utente;
	
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_emittente")
	private Emittente emittente;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "tessera")
	private List<ConvalidaTessera> convalide;
	
	//convenience method to manage convalide
	public void add(ConvalidaTessera c) {
			if(convalide==null) convalide=new ArrayList<ConvalidaTessera>();
			convalide.add(c);
		}
	
	public Tessera() {}

	public Tessera(Date data_erogazione, Date data_scadenza,Emittente emittente,  Utente utente) {
		super();
		this.data_erogazione = data_erogazione;
		this.data_scadenza = data_scadenza;
		this.utente = utente;
		this.emittente=emittente;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Date getData_erogazione() {
		return data_erogazione;
	}

	public void setData_erogazione(Date data_erogazione) {
		this.data_erogazione = data_erogazione;
	}

	public Date getData_scadenza() {
		return data_scadenza;
	}

	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Emittente getEmittente() {
		return emittente;
	}

	public void setEmittente(Emittente emittente) {
		this.emittente = emittente;
	}

	public List<ConvalidaTessera> getConvalide() {
		return convalide;
	}

	public void setConvalide(List<ConvalidaTessera> convalide) {
		this.convalide = convalide;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tessera [id=");
		builder.append(id);
		builder.append(", data_erogazione=");
		builder.append(data_erogazione);
		builder.append(", data_scadenza=");
		builder.append(data_scadenza);
		builder.append(", utente=");
		builder.append(utente);
		builder.append(", emittente=");
		builder.append(emittente);
		builder.append(", convalide=");
		builder.append(convalide);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
