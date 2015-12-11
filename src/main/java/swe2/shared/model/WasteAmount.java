package swe2.shared.model;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * @author jmothes
 */

@SuppressWarnings("serial")
@Embeddable
public class WasteAmount implements Serializable {

	@Deprecated
	protected WasteAmount() {this(0);}
	
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
