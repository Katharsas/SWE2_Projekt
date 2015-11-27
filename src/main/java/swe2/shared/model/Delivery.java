package swe2.shared.model;

/**
 *@author akolb
 *
 *TABLE_OF_CONTENTS
 *Constructor
 *Getter & Setter
 */


import java.time.LocalDateTime;

public class Delivery{

	private final Money cost;
	private final LocalDateTime dateTime;
	private final Deliverer deliverer;
	private final UniformWaste waste;
	
	public Delivery( UniformWaste waste, Deliverer deliverer ){
	
		this.waste = waste;
		this.deliverer = deliverer;
		this.cost = waste.calculateDeliveryCost();
		dateTime = LocalDateTime.now();
	
	}
	
	//------- Getter & Setter -------------
	
	public Money getCost(){
		return this.cost;
	}
	
	public LocalDateTime getDateTime(){
		return this.dateTime;
	}
	
	public Deliverer getDeliverer(){
		return this.deliverer;
	}
	
	public UniformWaste getWaste(){
		return this.waste;
	}

}