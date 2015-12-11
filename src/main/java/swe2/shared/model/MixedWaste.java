package swe2.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.CollectionTable;
import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.Inheritance;
import javax.persistence.MapKeyColumn;
import javax.persistence.MapKeyEnumerated;
import javax.persistence.MapKeyJoinColumn;
import javax.persistence.OneToMany;

@SuppressWarnings("serial")
@Embeddable
@Inheritance
public class MixedWaste implements Serializable {
	
	public static MixedWaste EMPTY = new MixedWaste();
	
	@ElementCollection
	@MapKeyEnumerated(EnumType.STRING)
	private final Map<WasteType, WasteAmount> typeToAmount = new HashMap<>();
	
	public MixedWaste() {
		for (final WasteType type : WasteType.values()) {
			typeToAmount.put(type, new WasteAmount(0));
		}
	}
	
	public void addWaste(WasteType type, WasteAmount waste) {
		final WasteAmount newAmount = typeToAmount.get(type).add(waste);
		typeToAmount.put(type, newAmount);
	}
	
	public void removeWaste(WasteType type, WasteAmount waste) {
		final WasteAmount newAmount = typeToAmount.get(type).sub(waste);
		typeToAmount.put(type, newAmount);
	}
	
	public WasteAmount get(WasteType type) {
		return typeToAmount.get(type);
	}
	
	public Set<WasteType> keySet() {
		return typeToAmount.keySet();
	}
	
	public Co2Amount calculateCo2Emission() {
		Co2Amount result = new Co2Amount(0);
		for (final Entry<WasteType, WasteAmount> entry : typeToAmount.entrySet()) {
			result = result.add(entry.getKey().getCo2Emission(entry.getValue()));
		}
		return result;
	}
}
