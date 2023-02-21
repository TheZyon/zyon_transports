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

import org.hibernate.annotations.Type;

@Entity
@Table(name="abbonamento")
public class Abbonamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Date data_erogazione;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_emittente")
	private Emittente emittente;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tessera")
	private Tessera tessera;
	
	@Type(type = "utils.EnumTypePostgreSql")
	@Column
	private Periodo periodo;
	
	@Column
	private Date data_scadenza;
	
	
	public Abbonamento() {}


	public Abbonamento(Date data_erogazione, Emittente emittente, Tessera tessera, Periodo periodo,
			Date data_scadenza) {
		super();
		this.data_erogazione = data_erogazione;
		this.emittente = emittente;
		this.tessera = tessera;
		this.periodo = periodo;
		this.data_scadenza = data_scadenza;
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


	public Emittente getEmittente() {
		return emittente;
	}


	public void setEmittente(Emittente emittente) {
		this.emittente = emittente;
	}


	public Tessera getTessera() {
		return tessera;
	}


	public void setTessera(Tessera tessera) {
		this.tessera = tessera;
	}


	public Periodo getPeriodo() {
		return periodo;
	}


	public void setPeriodo(Periodo periodo) {
		this.periodo = periodo;
	}


	public Date getData_scadenza() {
		return data_scadenza;
	}


	public void setData_scadenza(Date data_scadenza) {
		this.data_scadenza = data_scadenza;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Abbonamento [id=");
		builder.append(id);
		builder.append(", data_erogazione=");
		builder.append(data_erogazione);
		builder.append(", emittente=");
		builder.append(emittente);
		builder.append(", tessera=");
		builder.append(tessera);
		builder.append(", periodo=");
		builder.append(periodo);
		builder.append(", data_scadenza=");
		builder.append(data_scadenza);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
	
	
}
