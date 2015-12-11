package swe2.shared.model;

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
		
		//TODO load all sessions and find combustion
	}
	
	@Test
	public void testCombustion() {
		
	}
}
