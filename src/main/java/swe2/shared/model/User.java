package swe2.shared.model;

/*
Konstrukter

bool quals
int hashCode
Getter & Setter
*/


public abstract class User{
	
	private final String id;
	private String passwordHash;
	
	public User( String id, String passwordHash ){
		this.id = id;
		this.setPasswordHash( passwordHash );
	}
	
	//--------------Methoden------------------
	
	@Override
	public boolean equals( Object o ){
		if( this == o) return true;
		if( o == null ) return false;
		if( this.getClass() != o.getClass() ) return false; 
		return (this.getId().equals( ( (User) o).getId() ) );
	}
	
	@Override
	public int hashCode(){
		return this.getId().hashCode();
	}
	
	

	//------------Getter & Setter ---------------------------
	public String getId(){
		return this.id;
	}
	
	public String getPasswordHash(){
		return this.passwordHash;
	}
	
	public void setPasswordHash( String passwordHash ){
		this.passwordHash = passwordHash;
	}

}