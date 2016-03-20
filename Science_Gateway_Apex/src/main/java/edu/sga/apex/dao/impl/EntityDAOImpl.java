package edu.sga.apex.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import javax.persistence.Query;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.User;
import edu.sga.apex.util.ExperimentStatus;

/**
 * Entity Data Access Object Implementation.
 * @author mangirish_wagle
 *
 */
public class EntityDAOImpl implements EntityDAO {

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#saveEntity(java.lang.Object)
	 */
	public void saveEntity(Object entity) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		// Persisting the entity object.
		//if( ! em.contains(entity) );
		em.merge(entity);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#updateExperiment(edu.sga.apex.entity.Experiment)
	 */
	@Override
	public void updateExperiment(Experiment expt) {
		this.saveEntity(expt);
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getExperiments(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Experiment> getExperiments(String username) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT e FROM Experiment e "
				+ "WHERE e.userName.username='" + username +"'");
		List<Experiment> expts = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return expts;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getCompleteExperiments(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Experiment> getCompleteExperiments(String username) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT e FROM Experiment e "
				+ "WHERE e.status='" + ExperimentStatus.COMPLETE.toString() + "'");
		List<Experiment> expts = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return expts;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getInQueuedExperiments(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Experiment> getInQueuedExperiments(String username) {
		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT e FROM Experiment e "
				+ "WHERE e.status='" + ExperimentStatus.QUEUED.toString() + "'");
		List<Experiment> expts = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return expts;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getApplications()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<Application> getApplications() {
		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT a FROM Application a");
		List<Application> apps = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return apps;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getInputsForApp(java.lang.String)
	 */
	@Override
	public List<AppInput> getInputsForApp(String appId) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Application app = em.find(Application.class, appId);
		List<AppInput> inputList = app.getInputList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return inputList;
	}

	@Override
	public Machine getMachineByName(String machineName) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT m FROM Machine m "
				+ "WHERE m.machineName='" + machineName + "'");
		List<Machine> machines = query.getResultList();

		return machines.get(0);
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getUsers()
	 */
	@SuppressWarnings("unchecked")
	@Override
	public List<User> getUsers() {
		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT u FROM User u");
		List<User> users = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return users;
	}

	@Override
	public Application getApplicationByName(String appName) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT a FROM Application a "
				+ "WHERE a.appName='" + appName + "'");
		List<Application> apps = query.getResultList();

		return apps.get(0);
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getUserByName(java.lang.String)
	 */
	@Override
	public User getUserByName(String userName) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		User user = em.find(User.class, userName);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return user;
	}
}
