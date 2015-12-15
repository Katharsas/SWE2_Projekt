package swe2.shared.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@SuppressWarnings("serial")
@Entity
public class WasteStorage extends MixedWaste {
	
	@Id
	public final Long id = WasteStorage.getId();
	
	public WasteStorage() {
		super();
	}
	
	public static long getId() {
		return 1l;
	}
}
