package swe2.shared.model;

import java.lang.reflect.Field;
import java.util.Map;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.PostLoad;

@SuppressWarnings("serial")
@Entity
public class WasteStorage extends MixedWaste {
	
	// #########################################################################
	// JPA/Hibernate Fix: Entities cannot extend Embeddables, so just fake a member
	private MixedWaste storage = this;
	
	@PostLoad
	private void assignStorageToSelf() {
		// When loaded from db, storage != this -> copy over data from storage
		// and then set storage = this (reverse process of 'storage = this')
		try {
			Field typeToAmount = MixedWaste.class.getDeclaredField("typeToAmount");
			typeToAmount.setAccessible(true);
			Map<?,?> itsWaste = (Map<?,?>) typeToAmount.get(storage);
			typeToAmount.set(this, itsWaste);
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		storage = this;
	}
	// JPA/Hibernate Fix end
	// #########################################################################
	
	@Id
	public final Long id = WasteStorage.getId();
	
	public WasteStorage() {
		super();
	}
	
	public static long getId() {
		return 1l;
	}
}
