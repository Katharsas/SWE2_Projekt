package swe2.shared.model;

import java.io.Serializable;
import java.util.Objects;

import javax.persistence.Embeddable;

/**
 * @author akolb
 *
 * INHALT
 * Konstrukter
 * Money calculateDeliveryCost
 * Getter & Setter
 */

@SuppressWarnings("serial")
@Embeddable
public class UniformWaste implements Serializable {

	public final static UniformWaste EMPTY =
			new UniformWaste(WasteType.PLASTIC, new WasteAmount(0));
	
	private final WasteType wasteType;
	private final WasteAmount wasteAmount;
	
	@Deprecated
	protected UniformWaste() {this(EMPTY.wasteType, EMPTY.wasteAmount);}
	
	public UniformWaste(WasteType wasteType, WasteAmount wasteAmount) {
		Objects.requireNonNull(wasteType);
		Objects.requireNonNull(wasteAmount);
		this.wasteAmount = wasteAmount;
		this.wasteType = wasteType;
	}
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((wasteAmount == null) ? 0 : wasteAmount.hashCode());
		result = prime * result + ((wasteType == null) ? 0 : wasteType.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		UniformWaste other = (UniformWaste) obj;
		return wasteType == other.wasteType && wasteAmount.equals(other.wasteAmount);
	}

	public Money calculateDeliveryCost() {
		return wasteType.getDeliveryCost(wasteAmount);
	}
	
	//--------- Getter & Setter ----------------
	
	public WasteAmount getWasteAmount() {
		return this.wasteAmount;
	}
}