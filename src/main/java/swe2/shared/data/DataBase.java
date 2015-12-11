package swe2.shared.data;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.EntityManagerUtil;
import swe2.shared.model.Combustion;
import swe2.shared.model.Deliverer;
import swe2.shared.model.Delivery;
import swe2.shared.model.Operator;
import swe2.shared.model.User;
import swe2.shared.model.WasteStorage;

public class DataBase implements DataAccess {

	private Logger logger = LoggerFactory.getLogger(this.getClass());
	
	private EntityManager em;
	private Session session;
	
	public DataBase() {
		em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		session = em.unwrap(Session.class);
		
		String pwHashed = BCrypt.hashpw("root", BCrypt.gensalt());
		Operator operator = new Operator("operator", pwHashed);
		addUser(operator);
		
		Deliverer deliverer = new Deliverer("deliverer", pwHashed);
		addUser(deliverer);
	}
	
	private boolean addUser(User user) {
		try {
			save(user);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving combustion '" + user + "' failed!", e);
			return false;
		}
	}
	
	@Override
	public Collection<Combustion> getCombustions() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addCombustion(Combustion combustion) {
		try {
			save(combustion);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving combustion '" + combustion + "' failed!", e);
			return false;
		}
	}

	@Override
	public Collection<Delivery> getDeliveries() {
		throw new UnsupportedOperationException();
	}

	@Override
	public boolean addDelivery(Delivery d) {
		throw new UnsupportedOperationException();
	}

	@Override
	public WasteStorage getStorage() {
		throw new UnsupportedOperationException();
	}

	@Override
	public <T extends User> T authenticate(String userId, String password, Class<T> clazz) {
		if (!(clazz.equals(Operator.class) || clazz.equals(Deliverer.class)))
			throw new IllegalArgumentException("Only Operator/Deliverer class argument allowed!");
		try {
			final T user = get(clazz, userId);
			if (user != null && !BCrypt.checkpw(password, user.getPasswordHash())) {
				return null;
			}
			return user;
		} catch(HibernateException e) {
			logger.error("Getting user failed!", e);
			return null;
		}
	}

	/**
	 * @see {@link org.hibernate.Session#get(Class, Serializable)}
	 */
	private <T> T get(Class<T> clazz, Serializable id) {
		Transaction transaction = session.beginTransaction();
		T result;
		try {
			result = session.get(clazz, id);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			result = null;
		}
		return result;
	}
	
	/**
	 * @see {@link org.hibernate.Session#save(Object)}
	 */
	private void save(Object o) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		try {
			session.save(o);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
		}
	}
}
