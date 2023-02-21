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
@Table(name = "convalida_tessera")
public class ConvalidaTessera {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tessera")
	private Tessera tessera;
	
	@ManyToOne(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_mezzo")
	private Mezzo mezzo;
	
	@Column
	private Date data;
	
	public ConvalidaTessera() {}

	public ConvalidaTessera(Tessera tessera, Mezzo mezzo, Date data) {
		super();
		this.tessera = tessera;
		this.mezzo = mezzo;
		this.data = data;
	}

	public int getId() {
		return id;
	}

	public Tessera getId_tessera() {
		return tessera;
	}

	public Mezzo getId_mezzo() {
		return mezzo;
	}

	public Date getData() {
		return data;
	}

	public void setId_tessera(Tessera tessera) {
		this.tessera = tessera;
	}

	public void setId_mezzo(Mezzo mezzo) {
		this.mezzo = mezzo;
	}

	public void setData(Date data) {
		this.data = data;
	}

	@Override
	public String toString() {
		return "ConvalidaTessera [id=" + id + ", id_tessera=" + tessera + ", id_mezzo=" + mezzo + ", data=" + data
				+ "]";
	}

}
