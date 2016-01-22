package swe2.shared.model;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import javax.persistence.ElementCollection;
import javax.persistence.Embeddable;
import javax.persistence.EnumType;
import javax.persistence.FetchType;
import javax.persistence.MapKeyEnumerated;

@SuppressWarnings("serial")
@Embeddable
public class MixedWaste implements Serializable {
	
	public static final MixedWaste EMPTY = new MixedWaste();
	
	@ElementCollection(fetch = FetchType.EAGER)
	@MapKeyEnumerated(EnumType.STRING)
	private final Map<WasteType, WasteAmount> typeToAmount = new HashMap<>();
	
	public MixedWaste() {
		for (final WasteType type : WasteType.values()) {
			typeToAmount.put(type, new WasteAmount(0));
		}
	}
	
	@Override
	public int hashCode() {
		return typeToAmount.hashCode();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == null || getClass() != obj.getClass()) return false;
		return typeToAmount.equals(((MixedWaste)obj).typeToAmount);
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
