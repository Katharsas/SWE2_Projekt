import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import swe2.EntityManagerUtil;
import swe2.shared.model.Operator;

public class HibernateDemoTest {

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
		EntityTransaction transaction = em.getTransaction();
		transaction.begin();
		
		Operator op1 = new Operator("op1", "op1_pw");
		em.persist(op1);
		
		transaction.commit();
	}
}
