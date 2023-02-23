package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.Type;

@Entity
@Table(name = "mezzo")
public class Mezzo {

	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	
	@Enumerated(EnumType.STRING)
	@Type(type = "utils.EnumTypePostgreSql")
	@Column(name="tipo")
	private Tipo tipo;
	@Column
	private int capienza;
	@Enumerated(EnumType.STRING)
	@Type(type = "utils.EnumTypePostgreSql")
	@Column(name="stato")
	private Stato stato;
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "mezzo")
	private List<ConvalidaTessera> convalide;
	//convenience method to manage convalide
	public void add(ConvalidaTessera c) {
		if(convalide==null) convalide=new ArrayList<ConvalidaTessera>();
		convalide.add(c);
	}
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="mezzo")
	private List<Arrivo> arrivi;
	
	//mapping *-* con tratta
	@ManyToMany(cascade= {CascadeType.PERSIST, CascadeType.MERGE, CascadeType.DETACH, CascadeType.REFRESH})
	@JoinTable(
			name = "appartenenza",
			joinColumns = @JoinColumn(name="id_mezzo"),
			inverseJoinColumns = @JoinColumn(name="id_tratta")
	)
	
	private List<Tratta> tratte;
	
	//add a method for the fibre
	public void addTratta(Tratta t) {
		if(tratte==null) tratte= new ArrayList<Tratta>();
		tratte.add(t);
	}
	
	//mapping con vidimazione
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "mezzo")
	private List<Vidimazione> vidimazioni;
	
	//mapping con stato_mezzo
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "mezzo")
	private List<StatoMezzo> stati_mezzo;
	//convenience method to manage stati_mezzo
	public void addStatoMezzo(StatoMezzo s) {
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


	public List<StatoMezzo> getStati_mezzo() {
		return stati_mezzo;
	}


	public List<ConvalidaTessera> getConvalide() {
		return convalide;
	}


	public List<Arrivo> getArrivi() {
		return arrivi;
	}


	public List<Tratta> getTratte() {
		return tratte;
	}


	public List<Vidimazione> getVidimazioni() {
		return vidimazioni;
	}
	
	
	
	
}
