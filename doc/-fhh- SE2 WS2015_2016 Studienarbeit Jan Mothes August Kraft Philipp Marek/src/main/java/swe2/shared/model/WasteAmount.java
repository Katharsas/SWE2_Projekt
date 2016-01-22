package swe2.shared.model;

import java.io.Serializable;
import java.text.DecimalFormat;

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
	
	@Override
	public int hashCode() {
		return Double.valueOf(weight).hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null ||getClass() != obj.getClass()) return false;
		return weight == ((WasteAmount)obj).weight;
	}

	@Override
	public String toString() {
		return new DecimalFormat("#.##").format(weight);
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
