package swe2.shared.model;

import java.io.Serializable;

import javax.persistence.Embeddable;


/**
 * @author jmothes
 */

@SuppressWarnings("serial")
@Embeddable
public class Co2Amount implements Serializable {

	@Deprecated
	protected Co2Amount() {this(0);}
	
	private final double amount;
	private final static Money taxCostPerUnit = new Money("0.1");

	public Co2Amount(double kilogram) {
		this.amount = kilogram;
	}
	
	@Override
	public int hashCode() {
		return Double.valueOf(amount).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		return amount == ((Co2Amount)obj).amount;
	}

	public Money calculateTaxCost() {
		return taxCostPerUnit.multiply(new Money(amount));
	}
	
	public Co2Amount add(Co2Amount amount) {
		return new Co2Amount(this.amount + amount.amount);
	}
}
