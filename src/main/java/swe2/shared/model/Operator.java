package swe2.shared.model;

import javax.persistence.Entity;

/**
 * @author akolb
 */

@SuppressWarnings("serial")
@Entity
public class Operator extends User {
	
	@Deprecated
	protected Operator() {this("","");}
	
	public Operator(String id, String passwordHash) {
		super(id, passwordHash);
	}
}