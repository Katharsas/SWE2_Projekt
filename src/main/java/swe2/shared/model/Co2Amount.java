package swe2.shared.model;


/**
 * @author jmothes
 */

public class Co2Amount {

	private final double amount;
	private final static Money taxCostPerUnit = new Money("0.1");

	public Co2Amount(double kilogram) {
		this.amount = kilogram;
	}

	public Money calculateTaxCost() {
		return taxCostPerUnit.multiply(new Money(amount));
	}
	
	public Co2Amount add(Co2Amount amount) {
		return new Co2Amount(this.amount + amount.amount);
	}
}
