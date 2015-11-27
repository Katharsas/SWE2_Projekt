package swe2.shared.model;

import java.math.BigDecimal;

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
}