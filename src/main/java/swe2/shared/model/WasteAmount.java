package swe2.shared.model;

public class WasteAmount {

	private final double weight;
	
	public WasteAmount(double kilogram) {
		this.weight = kilogram;
	}
	
	public double inKilogram() {
		return weight;
	}
}
