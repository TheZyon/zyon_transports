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

import utils.DateUtil;

@Entity
@Table(name="abbonamento")
@NamedQuery(name="abbonamentoPerEmittente&TempoEmissione",
query = "SELECT a FROM Abbonamento a WHERE a.emittente = :e AND a.data_erogazione BETWEEN :d1 AND :d2 ORDER BY a.data_erogazione")
public class Abbonamento {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private Timestamp data_erogazione;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_emittente")
	private Emittente emittente;
	
	@ManyToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinColumn(name="id_tessera")
	private Tessera tessera;
	
	@Enumerated(EnumType.STRING)
	@Type(type = "utils.EnumTypePostgreSql")
	@Column
	private Periodo periodo;
	
	@Column
	private Timestamp data_scadenza;
	
	
	public Abbonamento() {}


	public Abbonamento(Timestamp data_erogazione, Emittente emittente, Tessera tessera, Periodo periodo) {
		super();
		this.data_erogazione = data_erogazione;
		this.emittente = emittente;
		this.tessera = tessera;
		this.periodo = periodo;
		
		if(periodo.equals(Periodo.SETTIMANALE)) {this.data_scadenza = DateUtil.addDaysTimestamp(data_erogazione, 7);}
		else if(periodo.equals(Periodo.MENSILE)) {this.data_scadenza =DateUtil.addDaysTimestamp(data_erogazione, 30); }
		
	}


	public int getId() {
		return id;
	}


	public void setId(int id) {
		this.id = id;
	}


	public Timestamp getData_erogazione() {
		return data_erogazione;
	}


	public void setData_erogazione(Timestamp data_erogazione) {
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


	public Timestamp getData_scadenza() {
		return data_scadenza;
	}


	public void setData_scadenza(Timestamp data_scadenza) {
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
