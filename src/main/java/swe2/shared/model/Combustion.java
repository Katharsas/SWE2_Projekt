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
import java.util.Objects;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@SuppressWarnings("serial")
@Entity
@Table(name="COMBUSTIONS")
public class Combustion implements Serializable {

	@Deprecated
	protected Combustion() {this(MixedWaste.EMPTY, null);}
	
	@Id @GeneratedValue
	private Long id;
	
	private final Co2Amount co2;
	@Lob
	private final LocalDateTime dateTime;
	@ManyToOne
	private final Operator operator;
	private final MixedWaste waste;
	
	public Combustion(MixedWaste waste, Operator operator) {
		Objects.requireNonNull(waste);
		this.waste = waste;
		this.operator = operator;
		this.co2 = waste.calculateCo2Emission();
		this.dateTime = LocalDateTime.now();
	}
	
	
	//----- Getter 6 Setter ---------------
	
	public Co2Amount getCo2() {
		return this.co2;
	}
	
	public LocalDateTime getDateTime() {
		return this.dateTime;
	}
	
	public Operator getOperator() {
		return this.operator;
	}
	
	public MixedWaste getWaste() {
		return this.waste;
	}

}