package swe2.server.data;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	public static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("my_persistence_unit");
		return emf;
	}
}
