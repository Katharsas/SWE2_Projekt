package swe2.shared.model;
import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.Transaction;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.Assert;

import swe2.EntityManagerUtil;
import swe2.shared.model.Operator;

public class UserTest {

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
	public void testUserPersistance() {
		String op1_id = "op1_test";
		Operator op1 = new Operator(op1_id, "");
		
		Session session = em.unwrap(Session.class);
		Transaction tSave = session.beginTransaction();
		session.save(op1);
		tSave.commit();
		
		Transaction tLoad = session.beginTransaction();
		Operator opResult = session.get(Operator.class, op1_id);
		tLoad.commit();
		
		Assert.assertEquals(op1.getId(), opResult.getId());
		Assert.assertEquals(op1.getPasswordHash(), opResult.getPasswordHash());
		
		Transaction tLoadWrong = session.beginTransaction();
		Operator opResultWrong = session.get(Operator.class, op1_id + "wrong");
		tLoadWrong.commit();
		Assert.assertNull(opResultWrong);
		
		Transaction tDelete = session.beginTransaction();
		session.delete(opResult);
		tDelete.commit();
		
		Transaction tCheck = session.beginTransaction();
		Assert.assertNull(session.get(Operator.class, op1_id));
		tCheck.commit();
	}
	
	@Test
	public void testUser() {
		Operator user1 = new Operator("user1", "user1_hash");
		Assert.assertEquals("user1", user1.getId());
		Assert.assertEquals("user1_hash", user1.getPasswordHash());
		
		try {
			new Operator(null, "");
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
		try {
			new Operator("", null);
			Assert.assertTrue(false);
		} catch(NullPointerException e) {}
		
		Operator user2 = new Operator("user2", "user2_hash");
		Assert.assertNotEquals(user1, user2);
		
		Operator user1Clone = new Operator("user1", "user1Clone_hash");
		Assert.assertEquals(user1, user1Clone);
	}
}
