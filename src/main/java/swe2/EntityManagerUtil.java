package swe2;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class EntityManagerUtil {
	public static EntityManagerFactory getEntityManagerFactory() {
		EntityManagerFactory emf =
				Persistence.createEntityManagerFactory("my_persistance_unit");
		return emf;
	}
}
