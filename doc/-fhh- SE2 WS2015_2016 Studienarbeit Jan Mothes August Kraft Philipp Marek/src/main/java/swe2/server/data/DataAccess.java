package swe2.server.data;

import java.util.Collection;

import swe2.shared.model.Combustion;
import swe2.shared.model.Delivery;
import swe2.shared.model.User;
import swe2.shared.model.WasteStorage;

public interface DataAccess {
	
	public Collection<Combustion> getCombustions();
	public boolean addCombustion(Combustion c);
	
	public Collection<Delivery> getDeliveries();
	public boolean addDelivery(Delivery d);
	
	public WasteStorage getStorage();
	public boolean saveStorage(WasteStorage storage);
        
	public <T extends User> T authenticate(String userId, String password,
			Class<T> accountType);
}

