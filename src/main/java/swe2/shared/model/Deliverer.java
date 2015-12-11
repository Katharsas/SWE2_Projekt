package swe2.shared.model;

import javax.persistence.Entity;

/**
 * @author akolb
 */

@SuppressWarnings("serial")
@Entity
public class Deliverer extends User {
	
	@Deprecated
	protected Deliverer() {this("","");}
	
	public Deliverer(String id, String passwordHash) {
		super(id, passwordHash);
	}
}