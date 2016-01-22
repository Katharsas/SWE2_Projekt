package swe2.shared.model;

import javax.persistence.Entity;

/**
 * @author akolb
 */

@SuppressWarnings("serial")
@Entity
public class Deliverer extends User {
	
	public static final Deliverer EMPTY = new Deliverer("", "");

	@Deprecated
	protected Deliverer() {this(EMPTY.getId(), EMPTY.getPasswordHash());}
	
	public Deliverer(String id, String passwordHash) {
		super(id, passwordHash);
	}
}