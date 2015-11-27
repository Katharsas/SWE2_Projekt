package swe2.shared.model;

/**
 * @author jmothes
 */

public class WasteAmount {

	private final double weight;

	public WasteAmount(double kilogram) {
		if (kilogram < 0) throw negative;
		this.weight = kilogram;
	}

	public double inKilogram() {
		return weight;
	}
	
	public WasteAmount add(WasteAmount amount) {
		return new WasteAmount(this.weight + amount.weight);
	}
	
	public WasteAmount sub(WasteAmount amount) {
		if (this.weight < amount.weight) throw negative;
		return new WasteAmount(this.weight - amount.weight);
	}
	
	private static IllegalArgumentException negative =
			new IllegalArgumentException("Waste amount cannot be negative!");
}
