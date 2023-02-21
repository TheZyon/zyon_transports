package model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="arrivo")
public class Arrivo {

	@Id
	@GeneratedValue
	private int id;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tappa")
	private Mezzo tappa;
	@Column
	private Date data;
	
	public Arrivo() {}

	public Arrivo(Mezzo mezzo, Mezzo tappa, Date data) {
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

	public Mezzo getTappa() {
		return tappa;
	}

	public void setTappa(Mezzo tappa) {
		this.tappa = tappa;
	}

	public Date getData() {
		return data;
	}

	public void setData(Date data) {
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
