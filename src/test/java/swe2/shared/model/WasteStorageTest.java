package swe2.shared.model;

import java.lang.reflect.Field;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import swe2.EntityManagerUtil;

public class WasteStorageTest {
	
	private EntityManager em;
	
	@Before
	public void beforeEach() {
		em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
	}

	@After
	public void afterEach() {
		em.close();
	}
	
	@Test
	public void testWasteStoragePersistance() {
		Session session = em.unwrap(Session.class);
		
		WasteStorage storage = new WasteStorage();
		storage.addWaste(WasteType.PAPER, new WasteAmount(1));
		storage.addWaste(WasteType.PLASTIC, new WasteAmount(5));
		
		try {
			Field idField = storage.getClass().getDeclaredField("id");
			idField.setAccessible(true);
			idField.set(storage, new Long(-1l));
		} catch (NoSuchFieldException | SecurityException | IllegalArgumentException | IllegalAccessException e) {
			throw new IllegalStateException(e);
		}
		
		Transaction tSave = session.beginTransaction();
		session.save(storage);
		tSave.commit();
		
		Transaction tGet = session.beginTransaction();
		WasteStorage result = session.get(WasteStorage.class, -1l);
		tGet.commit();
		
		Assert.assertEquals(new WasteAmount(1), result.get(WasteType.PAPER));
		Assert.assertEquals(new WasteAmount(5), result.get(WasteType.PLASTIC));
		Assert.assertEquals(new WasteAmount(0), result.get(WasteType.RESIDUAL));
		
		Transaction tDelete = session.beginTransaction();
		session.delete(storage);
		tDelete.commit();
	}
}
