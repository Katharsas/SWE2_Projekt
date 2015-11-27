package swe2.shared.model;

/**
 * @author jmothes
 */

public class WasteAmount {

	private final double weight;

	public WasteAmount(double kilogram) {
		if (kilogram < 0) throw new IllegalArgumentException("Waste amount cannot be negative!");
		this.weight = kilogram;
	}

	public double inKilogram() {
		return weight;
	}
	
	public WasteAmount add(WasteAmount amount) {
		return new WasteAmount(this.weight + amount.weight);
	}
	
	public WasteAmount sub(WasteAmount amount) {
		return new WasteAmount(this.weight - amount.weight);
	}		
}
