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
import java.time.format.DateTimeFormatter;
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class Delivery implements Serializable {
	
	@Deprecated
	protected Delivery() {this(UniformWaste.EMPTY, Deliverer.EMPTY);}
	
	@Id @GeneratedValue
	private Long id;
	
	private final Money cost;
	private final LocalDateTime dateTime;
	private final Deliverer deliverer;
	private final UniformWaste waste;
	
	public Delivery(UniformWaste waste, Deliverer deliverer) {
		Objects.requireNonNull(waste);
		Objects.requireNonNull(deliverer);
		this.waste = waste;
		this.deliverer = deliverer;
		this.cost = waste.calculateDeliveryCost();
		dateTime = LocalDateTime.now();
	}
	
	@Override
	public String toString() {
		return "Delivery["+waste+" Time:"+dateTime.format(DateTimeFormatter.ISO_LOCAL_TIME)+"]";
	}
	
	//------- Getter & Setter -------------
	
	public Money getCost() {
		return this.cost;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
	public Deliverer getDeliverer() {
		return this.deliverer;
	}
	
	public UniformWaste getWaste() {
		return this.waste;
	}
}