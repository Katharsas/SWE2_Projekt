package swe2.shared.model;

/**
 * @author akolb
 *
 * INHALT
 * Konstrukter
 * Money calculateDeliveryCost
 * Getter & Setter
 */

public class UniformWaste{

	private final WasteAmount wasteAmount;
	private final WasteType wasteType;
	
	public UniformWaste( WasteAmount wasteAmount, WasteType wasteType ){
		this.wasteAmount = wasteAmount;
		this.wasteType = wasteType;
	}
	
	//------ Methods -----
	
	public Money calculateDeliveryCost(){
	
		return wasteType.getDeliveryCost( wasteAmount );
	
	}
	
	
	
	//--------- Getter && Setter ----------------
	public WasteAmount getWasteAmount(){
		return this.wasteAmount;
	}

}