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
	protected Combustion() {this(MixedWaste.EMPTY, Operator.EMPTY);}
	
	@Id @GeneratedValue
	private Long id;
	
	private final Co2Amount co2;
	@Lob
	private final LocalDateTime dateTime;
	@ManyToOne
	private final Operator operator;
	private final MixedWaste waste;
	private final Money co2Cost;
	
	public Combustion(MixedWaste waste, Operator operator) {
		Objects.requireNonNull(waste);
		Objects.requireNonNull(operator);
		this.waste = waste;
		this.operator = operator;
		this.co2 = waste.calculateCo2Emission();
		this.co2Cost = co2.calculateTaxCost();
		this.dateTime = LocalDateTime.now();
	}
	
	@Override
	public int hashCode() {
		return id.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		if (id == null) throw new IllegalStateException("ID has not been"
				+ "generated by DB yet. Save object to DB or compare manually.");
		return id.equals(((Combustion)obj).id);
	}

	//----- Getter & Setter ---------------
	
	public Co2Amount getCo2() {
		return this.co2;
	}
	
	public Money getCo2Cost() {
		return this.co2Cost;
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