package model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;

import org.hibernate.annotations.Type;

@Entity
public class Distributore extends Emittente {

	
	
	
	@Enumerated(EnumType.STRING)
	@Type(type = "utils.EnumTypePostgreSql")
	@Column(name="stato_emittente")
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
