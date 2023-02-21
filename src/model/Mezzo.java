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
import javax.persistence.Table;

@Entity
@Table(name = "mezzo")
public class Mezzo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	
	@Column
	private Tipo tipo;
	@Column
	private int capienza;
	@Column
	private Stato stato;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "mezzo")
	private List<ConvalidaTessera> convalide;
	//convenience method to manage convalide
	public void add(ConvalidaTessera c) {
		if(convalide==null) convalide=new ArrayList<ConvalidaTessera>();
		convalide.add(c);
	}
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "mezzo")
	private List<StatoMezzo> stati_mezzo;
	//convenience method to manage stati_mezzo
	public void add(StatoMezzo s) {
		if(stati_mezzo==null) stati_mezzo=new ArrayList<StatoMezzo>();
		stati_mezzo.add(s);
	}


	public Mezzo() {}
	public Mezzo(Tipo tipo, int capienza, Stato stato) {
		super();
		this.tipo = tipo;
		this.capienza = capienza;
		this.stato = stato;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public Tipo getTipo() {
		return tipo;
	}
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	public int getCapienza() {
		return capienza;
	}
	public void setCapienza(int capienza) {
		this.capienza = capienza;
	}
	public Stato getStato() {
		return stato;
	}
	public void setStato(Stato stato) {
		this.stato = stato;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Mezzo [id=");
		builder.append(id);
		builder.append(", tipo=");
		builder.append(tipo);
		builder.append(", capienza=");
		builder.append(capienza);
		builder.append(", stato=");
		builder.append(stato);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
}
