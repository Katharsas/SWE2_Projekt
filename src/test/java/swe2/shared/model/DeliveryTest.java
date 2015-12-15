package swe2.shared.model;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import swe2.EntityManagerUtil;
import swe2.shared.data.DataAccess;
import swe2.shared.data.DataBase;

public class DeliveryTest {
	
	private EntityManager em;
	private DataAccess data = new DataBase();
	
	@Before
	public void beforeEach() {
		em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
	}

	@After
	public void afterEach() {
		em.close();
	}
	
	@Test
	public void testCombustionPersistance() {
		Session session = em.unwrap(Session.class);
		
		Deliverer deliverer =
				data.authenticate("deliverer", "root", Deliverer.class);
		UniformWaste waste =
				new UniformWaste(WasteType.PAPER, new WasteAmount(2));
		Delivery delivery = new Delivery(waste, deliverer);
		
		Transaction tMerge = session.beginTransaction();
		session.merge(deliverer);
		tMerge.commit();
		
		Transaction tSave = session.beginTransaction();
		session.save(delivery);
		tSave.commit();
		
		Transaction tGet = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Collection<Delivery> results =
				session.createCriteria(Delivery.class).list();
		tGet.commit();
		
		Assert.assertTrue(results.contains(delivery));
		for(Delivery d : results) {
			if (d.equals(delivery)) {
				Assert.assertEquals(delivery.getDateTime(), d.getDateTime());
				Assert.assertEquals(delivery.getCost(), d.getCost());
				Assert.assertEquals(delivery.getDeliverer(), d.getDeliverer());
				Assert.assertEquals(delivery.getWaste(), d.getWaste());
			}
		}
		
		Transaction tDelete = session.beginTransaction();
		session.delete(delivery);
		tDelete.commit();
	}
	
	@Test
	public void testCombustion() {
		Deliverer deliverer = new Deliverer("deliverer", "dl_hash");
		Deliverer delivererClone = new Deliverer("deliverer", "dl_hash");
		
		UniformWaste waste =
				new UniformWaste(WasteType.PAPER, new WasteAmount(2));
		UniformWaste wasteClone =
				new UniformWaste(WasteType.PAPER, new WasteAmount(2));
		
		Delivery delivery = new Delivery(waste, deliverer);
		Assert.assertEquals(delivererClone, delivery.getDeliverer());
		Assert.assertEquals(wasteClone, delivery.getWaste());
		
		try {
			new Delivery(null, deliverer);
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
		try {
			new Delivery(waste, null);
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
	}
}
