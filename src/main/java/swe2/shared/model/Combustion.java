package swe2.shared.model;

/**
 *@author akolb
 *
 *TABLE_OF_CONTENTS
 *Constructor
 *Getter & Setter
 */


import java.io.Serializable;
import java.time.LocalDateTime;

public class Combustion implements Serializable {

	private final Co2Amount co2;
	private final LocalDateTime dateTime;
	private final Operator operator;
	private final MixedWaste waste;
	
	public Combustion( MixedWaste waste, Operator operator ){
	
		this.waste = waste;
		this.operator = operator;
		this.co2 = waste.calculateCo2Emission();
		this.dateTime = LocalDateTime.now();
	
	}
	
	
	
	//----- Getter 6 Setter ---------------
	
	public Co2Amount getCo2(){
		return this.co2;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
	
	public Operator getOperator(){
		return this.operator;
	}
	
	public MixedWaste getWaste(){
		return this.waste;
	}

}