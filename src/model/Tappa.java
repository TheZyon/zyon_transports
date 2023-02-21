package model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="tappa")
public class Tappa {

	@Id
	@GeneratedValue
	private int id;
	
	@Column
	private String nome;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="tappa")
	private List<Composizione> composizioni;

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="tappa")
	private List<Arrivo>arrivi;
	
	
	//constructor
	public Tappa() {}

	public Tappa(String nome) {
		super();
		this.nome = nome;
	}


	public Tappa(int id, String nome) {
		super();
		this.id = id;
		this.nome = nome;
	}


	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tappa [id=");
		builder.append(id);
		builder.append(", name=");
		builder.append(nome);
		builder.append("]");
		return builder.toString();
	}
	
	
	
}
