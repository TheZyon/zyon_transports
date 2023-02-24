package model;

import java.sql.Timestamp;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "stato_mezzo")
@NamedQuery(name="getStatiMezzoByMezzo", query = "SELECT s FROM StatoMezzo s WHERE s.mezzo = :m")
public class StatoMezzo {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "utils.EnumTypePostgreSql")
	@Column(name="stato")
	private Stato stato;
	
	
	@Column
	private Timestamp data_inizio;
	@Column
	private Timestamp data_fine;
	
	public StatoMezzo() {}
	
	public StatoMezzo(Mezzo mezzo, Stato stato, Timestamp data_inizio, Timestamp data_fine) {
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

	public Timestamp getData_inizio() {
		return data_inizio;
	}

	public Timestamp getData_fine() {
		return data_fine;
	}

	public void setMezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public void setStato(Stato stato) {
		this.stato = stato;
	}

	public void setData_inizio(Timestamp data_inizio) {
		this.data_inizio = data_inizio;
	}

	public void setData_fine(Timestamp data_fine) {
		this.data_fine = data_fine;
	}

	@Override
	public String toString() {
		return "StatoMezzo [id=" + id + ", mezzo=" + mezzo + ", stato=" + stato + ", data_inizio=" + data_inizio
				+ ", data_fine=" + data_fine + "]";
	}

}
