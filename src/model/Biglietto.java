package model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="biglietto")
public class Biglietto {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_emittente")
	private Emittente emittente;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_utente")
	private Utente utente;
	
	@Column
	private Timestamp data_erogazione;
	
	@Column
	private boolean vidimato;
	
	@OneToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "biglietto")
	private Vidimazione vidimazione;
	public Biglietto() {}

	public Biglietto(Emittente emittente, Utente utente, Timestamp data_erogazione, boolean vidimato) {
		super();
		this.emittente = emittente;
		this.utente = utente;
		this.data_erogazione = data_erogazione;
		this.vidimato = vidimato;
	}

	public Emittente getEmittente() {
		return emittente;
	}

	public void setEmittente(Emittente emittente) {
		this.emittente = emittente;
	}

	public Utente getUtente() {
		return utente;
	}

	public void setUtente(Utente utente) {
		this.utente = utente;
	}

	public Timestamp getData_erogazione() {
		return data_erogazione;
	}

	public void setData_erogazione(Timestamp data_erogazione) {
		this.data_erogazione = data_erogazione;
	}

	public boolean isVidimato() {
		return vidimato;
	}

	public void setVidimato(boolean vidimato) {
		this.vidimato = vidimato;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Biglietto [id=");
		builder.append(id);
		builder.append(", emittente=");
		builder.append(emittente);
		builder.append(", utente=");
		builder.append(utente);
		builder.append(", data_erogazione=");
		builder.append(data_erogazione);
		builder.append(", vidimato=");
		builder.append(vidimato);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
