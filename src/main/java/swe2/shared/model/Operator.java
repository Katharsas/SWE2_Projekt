package swe2.shared.model;

import javax.persistence.Entity;

/**
 * @author akolb
 */

@Entity
public class Operator extends User{
	
	@SuppressWarnings("unused")
	private Operator() {this(null,null);}
	
	public Operator(String id, String passwordHash) {
		super(id, passwordHash);
	}
}