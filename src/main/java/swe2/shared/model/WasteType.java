package swe2.shared.model;

import java.util.Objects;

/**
 * @author jmothes
 */

public enum WasteType {

	RESIDUAL(0.6, new Money("0.47")),
	PLASTIC(0.8, new Money("0.42")),
	PAPER(0.2, new Money("0.21"));

	private final double co2EmissionPerKilo;
	private final Money deliveryCostPerKilo;

	private WasteType(double co2EmissionPerKilo, Money deliveryCostPerKilo) {
		Objects.requireNonNull(co2EmissionPerKilo);
		Objects.requireNonNull(deliveryCostPerKilo);
		this.co2EmissionPerKilo = co2EmissionPerKilo;
		this.deliveryCostPerKilo = deliveryCostPerKilo;
	}

	public Co2Amount getCo2Emission(WasteAmount amount) {
		Objects.requireNonNull(amount);
		return new Co2Amount(amount.inKilogram() * co2EmissionPerKilo);
	}

	public Money getDeliveryCost(WasteAmount amount) {
		Objects.requireNonNull(amount);
		return deliveryCostPerKilo.multiply(new Money(amount.inKilogram()));
	}
}
