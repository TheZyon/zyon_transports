package model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = "composizione")
@NamedQuery(name="getComposizioniByTratta", query="SELECT c FROM Composizione c WHERE c.tratta=:tratta")
public class Composizione {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tratta")
	private Tratta tratta;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tappa")
	private Tappa tappa;
	
	@Column
	private int posizione_nella_tratta;

	//constructor
	public Composizione() {}

	public Composizione(Tratta tratta, Tappa tappa, int posizione_nella_tratta) {
		super();
		this.tratta = tratta;
		this.tappa = tappa;
		this.posizione_nella_tratta=posizione_nella_tratta;
	}


	
	
	//getters&setter
	
	
	public Tratta getTratta() {
		return tratta;
	}

	public void setTratta(Tratta tratta) {
		this.tratta = tratta;
	}

	public Tappa getTappa() {
		return tappa;
	}

	public void setTappa(Tappa tappa) {
		this.tappa = tappa;
	}

	public int getPosizione_nella_tratta() {
		return posizione_nella_tratta;
	}

	public void setPosizione_nella_tratta(int posizione_nella_tratta) {
		this.posizione_nella_tratta = posizione_nella_tratta;
	}

	public int getId() {
		return id;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Composizione [id=");
		builder.append(id);
		builder.append(", tratta=");
		builder.append(tratta);
		builder.append(", tappa=");
		builder.append(tappa);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
