package swe2.server.data;

import java.io.Serializable;
import java.util.Collection;

import javax.persistence.EntityManager;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.mindrot.jbcrypt.BCrypt;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import swe2.server.data.EntityManagerUtil;
import swe2.shared.model.Combustion;
import swe2.shared.model.Deliverer;
import swe2.shared.model.Delivery;
import swe2.shared.model.Operator;
import swe2.shared.model.User;
import swe2.shared.model.WasteStorage;

public class DataBase implements DataAccess {

	private static final Logger logger = LoggerFactory.getLogger(DataBase.class);
	
	//Singleton pattern
	private static final DataBase instance = new DataBase();
	public static DataBase getInstance() {return instance;}
	
	private EntityManager em;
	private Session session;
	
	public DataBase() {
		em = EntityManagerUtil.getEntityManagerFactory().createEntityManager();
		session = em.unwrap(Session.class);
		initDefaultUsers();
	}
	
	private void initDefaultUsers() {
		String pwHashed = BCrypt.hashpw("root", BCrypt.gensalt());
		
		Operator operator = get(Operator.class, "operator");
		if (operator == null) {
			operator = new Operator("operator", pwHashed);
			saveUser(operator);
		}
		Deliverer deliverer = get(Deliverer.class, "deliverer");
		if (deliverer == null) {
			deliverer = new Deliverer("deliverer", pwHashed);
			saveUser(deliverer);
		}
	}
	
	private boolean saveUser(User user) {
		try {
			save(user);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving user '" + user + "' failed!", e);
			return false;
		}
	}
	
	@Override
	public synchronized Collection<Combustion> getCombustions() {
		try {
			return getAll(Combustion.class);
		} catch(HibernateException e) {
			throw new HibernateException("Getting all combustions failed!", e);
		}
	}

	@Override
	public boolean addCombustion(Combustion combustion) {
		try {
			merge(combustion.getOperator());
			save(combustion);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving combustion '" + combustion + "' failed!", e);
			return false;
		}
	}

	@Override
	public synchronized Collection<Delivery> getDeliveries() {
		try {
			return getAll(Delivery.class);
		} catch(HibernateException e) {
			throw new HibernateException("Getting all deliveries failed!", e);
		}
	}

	@Override
	public boolean addDelivery(Delivery delivery) {
		try {
			merge(delivery.getDeliverer());
			save(delivery);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving delivery '" + delivery + "' failed!", e);
			return false;
		}
	}

	@Override
	public synchronized WasteStorage getStorage() {
		try {
			return get(WasteStorage.class, WasteStorage.getId());
		} catch(HibernateException e) {
			throw new HibernateException("Getting WasteStorage failed!", e);
		}
	}
	
	@Override
	public boolean saveStorage(WasteStorage storage) {
		try {
			merge(storage);
			return true;
		} catch(HibernateException e) {
			logger.error("Saving WasteStorage failed!", e);
			return false;
		}
	}

	/**
	 * @see {@link BCrypt#checkpw(String, String)}
	 * 
	 * @param userId - User name/id
	 * @param password - The cleartext password the user entered to login.
	 * @param clazz - The user account type: Deliverer or Operator
	 * @return Null if user does not exist or wrong password, User object otherwise.
	 */
	@Override
	public <T extends User> T authenticate(String userId, String password, Class<T> clazz) {
		if (!(clazz.equals(Operator.class) || clazz.equals(Deliverer.class)))
			throw new IllegalArgumentException("Only Operator/Deliverer class argument allowed!");
		logger.debug("Trying to get " + clazz.getSimpleName() + " from db.");
		try {
			final T user = get(clazz, userId);
			if (user == null) logger.debug("Wrong userId: " + userId);
			if (user != null && !BCrypt.checkpw(password, user.getPasswordHash())) {
				logger.debug("Wrong password:" + password);
				return null;
			}
			logger.debug("Returning " + user + " from db.");
			return user;
		} catch(HibernateException e) {
			throw new HibernateException("Getting user failed!", e);
		}
	}

	/**
	 * @see {@link Session#get(Class, Serializable)}
	 */
	private synchronized <T> T get(Class<T> clazz, Serializable id) {
		Transaction transaction = session.beginTransaction();
		T result;
		try {
			result = session.get(clazz, id);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		}
		return result;
	}
	
	/**
	 * Return a list of all Entities of given type.
	 * @see {@link Session#createCriteria(Class)}
	 */
	@SuppressWarnings("unchecked")
	private synchronized <T> Collection<T> getAll(Class<T> clazz) {
		Transaction transaction = session.beginTransaction();
		Collection<T> result;
		try {
			result = session.createCriteria(clazz).list();
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		}
		return result;
	}
	
	/**
	 * @see {@link Session#save(Object)}
	 */
	private synchronized void save(Object o) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		try {
			session.save(o);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	/**
	 * @see {@link Session#merge(Object)}
	 */
	private synchronized void merge(Object o) throws HibernateException {
		Transaction transaction = session.beginTransaction();
		try {
			session.merge(o);
			transaction.commit();
		} catch (HibernateException e) {
			transaction.rollback();
			throw e;
		}
	}
	
	@Override
	protected void finalize() throws Throwable {
		em.close();
	}
}
