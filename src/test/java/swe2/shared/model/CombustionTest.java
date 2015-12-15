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

public class CombustionTest {

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
		
		Operator operator = data.authenticate("operator", "root", Operator.class);
		MixedWaste waste = new MixedWaste();
		Combustion combustion = new Combustion(waste, operator);
		
		Transaction tMerge = session.beginTransaction();
		session.merge(operator);
		tMerge.commit();
		
		Transaction tSave = session.beginTransaction();
		session.save(combustion);
		tSave.commit();
		
		Transaction tGet = session.beginTransaction();
		@SuppressWarnings("unchecked")
		Collection<Combustion> results =
				session.createCriteria(Combustion.class).list();
		tGet.commit();
		
		Assert.assertTrue(results.contains(combustion));
		for(Combustion c : results) {
			if (c.equals(combustion)) {
				Assert.assertEquals(combustion.getDateTime(), c.getDateTime());
				Assert.assertEquals(combustion.getWaste(), c.getWaste());
				Assert.assertEquals(combustion.getOperator(), c.getOperator());
				Assert.assertEquals(combustion.getCo2(), c.getCo2());
			}
		}
		
		Transaction tDelete = session.beginTransaction();
		session.delete(combustion);
		tDelete.commit();
	}
	
	@Test
	public void testCombustion() {
		Operator operator = new Operator("operator", "op_hash");
		Operator operatorClone = new Operator("operator", "op_hash");
		
		MixedWaste waste = new MixedWaste();
		waste.addWaste(WasteType.PAPER, new WasteAmount(2));
		waste.addWaste(WasteType.RESIDUAL, new WasteAmount(1));
		
		MixedWaste wasteClone = new MixedWaste();
		wasteClone.addWaste(WasteType.PAPER, new WasteAmount(2));
		wasteClone.addWaste(WasteType.RESIDUAL, new WasteAmount(1));
		
		Combustion combustion = new Combustion(waste, operator);

		Assert.assertEquals(operatorClone, combustion.getOperator());
		Assert.assertEquals(wasteClone, combustion.getWaste());
		
		try {
			new Combustion(null, operator);
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
		try {
			new Combustion(waste, null);
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
	}
}
