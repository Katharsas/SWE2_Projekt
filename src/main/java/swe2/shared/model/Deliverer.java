package swe2.shared.model;

import javax.persistence.Entity;

/**
 * @author akolb
 */

@Entity
public class Deliverer extends User{
	
	@SuppressWarnings("unused")
	private Deliverer() {this(null,null);}
	
	public Deliverer(String id, String passwordHash) {
		super(id, passwordHash);
	}
}