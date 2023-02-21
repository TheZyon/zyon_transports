package model;

import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name = "stato_mezzo")
public class StatoMezzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@Column
	private Stato stato;
	@Column
	private Date data_inizio;
	@Column
	private Date data_fine;
	
	public StatoMezzo() {}
	
	public StatoMezzo(Mezzo mezzo, Stato stato, Date data_inizio, Date data_fine) {
		super();
		this.mezzo = mezzo;
		this.stato = stato;
		this.data_inizio = data_inizio;
		this.data_fine = data_fine;
	}

	public int getId() {
		return id;
	}

	public Mezzo getMezzo() {
		return mezzo;
	}

	public Stato getStato() {
		return stato;
	}

	public Date getData_inizio() {
		return data_inizio;
	}

	public Date getData_fine() {
		return data_fine;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public void setData_inizio(Date data_inizio) {
		this.data_inizio = data_inizio;
	}

	public void setData_fine(Date data_fine) {
		this.data_fine = data_fine;
	}

	@Override
	public String toString() {
		return "StatoMezzo [id=" + id + ", mezzo=" + mezzo + ", stato=" + stato + ", data_inizio=" + data_inizio
				+ ", data_fine=" + data_fine + "]";
	}

}
