package swe2.shared.model;

import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.EntityManagerUtil;
import swe2.shared.data.DataAccess;
import swe2.shared.data.DataBase;

public class CombustionTest {

private EntityManager em;

	Logger logger = LoggerFactory.getLogger(this.getClass());
	DataAccess data = new DataBase();
	
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
	}
	
	@Test
	public void testCombustion() {
		Operator operator = new Operator("operator", "op_hash");
		
		MixedWaste waste = new MixedWaste();
		waste.addWaste(WasteType.PAPER, new WasteAmount(2));
		waste.addWaste(WasteType.RESIDUAL, new WasteAmount(1));
		
		Combustion combustion = new Combustion(waste, operator);
		Assert.assertEquals(operator, combustion.getOperator());
		Assert.assertEquals(waste, combustion.getWaste());
		
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
