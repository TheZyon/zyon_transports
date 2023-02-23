package model;

import java.sql.Timestamp;

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
@Table(name="vidimazione")
public class Vidimazione {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@OneToOne()
	@JoinColumn(name="id_biglietto")
	private Biglietto biglietto;
	
	@ManyToOne
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@Column
	private Timestamp data;
	
	
	public Vidimazione() {}


	public Vidimazione(Biglietto biglietto, Mezzo mezzo, Timestamp data) {
		super();
		this.biglietto = biglietto;
		this.mezzo = mezzo;
		this.data = data;
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Biglietto getBiglietto() {
		return biglietto;
	}


	public void setBiglietto(Biglietto biglietto) {
		this.biglietto = biglietto;
	}


	public Mezzo getMezzo() {
		return mezzo;
	}


	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}


	public Timestamp getData() {
		return data;
	}


	public void setData(Timestamp data) {
		this.data = data;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Vidimazione [id=");
		builder.append(id);
		builder.append(", biglietto=");
		builder.append(biglietto);
		builder.append(", mezzo=");
		builder.append(mezzo);
		builder.append(", data=");
		builder.append(data);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
