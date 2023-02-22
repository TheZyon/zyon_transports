package model;

import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "tratta")
public class Tratta {

	@Id
	@GeneratedValue (strategy = GenerationType.IDENTITY)
	private int id;
	
	@Column
	private String partenza;
	@Column
	private String capolinea;
	@Column
	private Time tempo_medio; //vedere la giusta Classe
	@Column
	private int ordine;

	
	//mapping con Mezzo
	@ManyToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH})
	@JoinTable(
			name="appartenenza",
			joinColumns = @JoinColumn(name = "id_mezzo"),
			inverseJoinColumns = @JoinColumn(name = "id_tratta"))
	private List<Mezzo> mezzi;
	public void addMezzo(Mezzo m) {
		if(mezzi==null) mezzi=new ArrayList<Mezzo>();
		mezzi.add(m);
	}
	
	//mapping con Composizione ---> TODO convenience method a cosa può servire?
	
	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy="tratta")
	private List<Composizione> tappe_indicizzate;
	
	
	
	//Constructors
	public Tratta() {}
	public Tratta(String partenza, String capolinea, Time tempo_medio, int ordine) {
		super();
		this.partenza = partenza;
		this.capolinea = capolinea;
		this.tempo_medio = tempo_medio;
		this.ordine = ordine;
	}

	//getters&Setters
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getPartenza() {
		return partenza;
	}
	public void setPartenza(String partenza) {
		this.partenza = partenza;
	}
	public String getCapolinea() {
		return capolinea;
	}
	public void setCapolinea(String capolinea) {
		this.capolinea = capolinea;
	}
	public Time getTempo_medio() {
		return tempo_medio;
	}
	public void setTempo_medio(Time tempo_medio) {
		this.tempo_medio = tempo_medio;
	}
	public int getOrdine() {
		return ordine;
	}
	public void setOrdine(int ordine) {
		this.ordine = ordine;
	}
	
	public List<Mezzo> getMezzi() {
		return mezzi;
	}

	public void setMezzi(List<Mezzo> mezzi) {
		this.mezzi = mezzi;
	}
	
	
	//toString

	public List<Composizione> getTappe_indicizzate() {
		return tappe_indicizzate;
	}
	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Tratta [id=");
		builder.append(id);
		builder.append(", partenza=");
		builder.append(partenza);
		builder.append(", capolinea=");
		builder.append(capolinea);
		builder.append(", tempo_medio=");
		builder.append(tempo_medio);
		builder.append(", ordine=");
		builder.append(ordine);
		builder.append("]");
		return builder.toString();
	}
	
	
	
	
	
	
	
}
