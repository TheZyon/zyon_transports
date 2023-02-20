package model;

import javax.persistence.Entity;

@Entity
public class Rivenditore extends Emittente {

	public Rivenditore(){}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder();
		builder.append("Rivenditore []");
		return super.toString()+builder.toString();
	}
	
	
	
}
