package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name="utente")
public class Utente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String nome;
	@Column
	private String cognome;
	@Column
	private String CF;
	
	@OneToOne(cascade={CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	private Tessera tessera;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "biglietto")
	List<Biglietto> biglietti;
	
	//convenience method to manage biglietti
	public void add(Biglietto b) {
		if(biglietti==null) biglietti=new ArrayList<Biglietto>();
		biglietti.add(b);
	}
	
	
	public Utente() {}


	public Utente(String nome, String cognome, String cF) {
		super();
		this.nome = nome;
		this.cognome = cognome;
		CF = cF;
	}


	public String getNome() {
		return nome;
	}


	public void setNome(String nome) {
		this.nome = nome;
	}


	public String getCognome() {
		return cognome;
	}


	public void setCognome(String cognome) {
		this.cognome = cognome;
	}


	public String getCF() {
		return CF;
	}


	public void setCF(String cF) {
		CF = cF;
	}


	public List<Biglietto> getBiglietti() {
		return biglietti;
	}


	public void setBiglietti(List<Biglietto> biglietti) {
		this.biglietti = biglietti;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Utente [id=");
		builder.append(id);
		builder.append(", nome=");
		builder.append(nome);
		builder.append(", cognome=");
		builder.append(cognome);
		builder.append(", CF=");
		builder.append(CF);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
