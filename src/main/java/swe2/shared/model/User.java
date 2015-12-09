package swe2.shared.model;

import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

/**
 * @author akolb
 */

@Entity
@Table(name="USERS")
@Inheritance(strategy=InheritanceType.SINGLE_TABLE)
public abstract class User {
	
	@Id
	private final String id;
	private String passwordHash;
	
	public User(String id, String passwordHash) {
		Objects.requireNonNull(id);
		this.id = id;
		this.setPasswordHash(passwordHash);
	}
	
	//--------------Methoden------------------
	
	@Override
	public boolean equals(Object o) {
		if(this == o) return true;
		if(o == null ) return false;
		if(this.getClass() != o.getClass()) return false; 
		return this.getId().equals(((User) o).getId());
	}
	
	@Override
	public int hashCode() {
		return this.getId().hashCode();
	}

	//------------Getter & Setter ---------------------------
	
	public String getId() {
		return this.id;
	}
	
	public String getPasswordHash() {
		return this.passwordHash;
	}
	
	public void setPasswordHash(String passwordHash) {
		Objects.requireNonNull(passwordHash);
		this.passwordHash = passwordHash;
	}
}