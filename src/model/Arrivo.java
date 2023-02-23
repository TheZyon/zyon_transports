package model;

import java.sql.Time;

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
@Table(name="arrivo")
@NamedQuery(name="getArriviByMezzo", query = "SELECT a FROM Arrivo a WHERE a.mezzo= :mezzino  ORDER BY a.data")

public class Arrivo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tappa")
	private Tappa tappa;
	@Column
	private Time data;
	
	public Arrivo() {}

	public Arrivo(Mezzo mezzo, Tappa tappa, Time data) {
		super();
		this.mezzo = mezzo;
		this.tappa = tappa;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public Tappa getTappa() {
		return tappa;
	}

	public void setTappa(Tappa tappa) {
		this.tappa = tappa;
	}

	public Time getData() {
		return data;
	}

	public void setData(Time data) {
		this.data = data;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Arrivo [id=");
		builder.append(id);
		builder.append(", mezzo=");
		builder.append(mezzo);
		builder.append(", tappa=");
		builder.append(tappa);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
