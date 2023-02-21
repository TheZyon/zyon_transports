package model;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name="emittente")
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
public abstract class Emittente {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int id;
	
	

	@OneToMany(cascade = {CascadeType.DETACH, CascadeType.MERGE, CascadeType.PERSIST, CascadeType.REFRESH}, mappedBy = "emittente")
	private List<Biglietto> biglietti;
	
	//convenience method to manage biglietti
	public void add(Biglietto b) {
			if(biglietti==null) biglietti=new ArrayList<Biglietto>();
			biglietti.add(b);
		}
		
	public Emittente() {}

	public Emittente(int id) {
		super();
		this.id = id;
	}
		
	
	
	
	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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
		builder.append("Emittente [id=");
		builder.append(id);
		builder.append("]");
		return builder.toString();
	}

	
	
	
	
	
	
}
