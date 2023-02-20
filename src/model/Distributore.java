package model;

import javax.persistence.Entity;

import org.hibernate.annotations.Type;

@Entity
public class Distributore extends Emittente {

	
	
	
	
	@Type(type = "utils.EnumTypePostgreSql")
	private Attivo attivo;
	
	public Distributore() {}

	public Distributore(Attivo attivo) {
		super();
		this.attivo = attivo;
	}

	public Attivo getAttivo() {
		return attivo;
	}

	public void setAttivo(Attivo attivo) {
		this.attivo = attivo;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Distributore [attivo=");
		builder.append(attivo);
		builder.append("]");
		return super.toString()+builder.toString();
	}
	
	
	
}