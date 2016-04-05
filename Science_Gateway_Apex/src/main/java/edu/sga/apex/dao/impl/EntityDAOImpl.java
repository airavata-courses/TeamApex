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
import edu.sga.apex.entity.ExperimentPK;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.Role;
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
				+ "WHERE e.user.username='" + username +"'");
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
				+ "WHERE e.status='" + ExperimentStatus.Complete.toString() + "'");
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
				+ "WHERE e.status='" + ExperimentStatus.Queued.toString() + "'");
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

	@SuppressWarnings("unchecked")
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

		if( machines == null || machines.size() == 0 )
			return null;
		
		return machines.get(0);
	}

	@Override
	public Machine getMachineById(String machineId) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Machine machine = em.find(Machine.class, machineId);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return machine;
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

	@SuppressWarnings("unchecked")
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
		
		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		if( apps == null || apps.size() == 0 )
			return null;
		
		return apps.get(0);
	}

	@Override
	public Application getApplicationById(String appId) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Application app = em.find(Application.class, appId);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return app;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getUserByName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public User getUserByName(String userName) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT u FROM User u "
				+ "WHERE u.username='" + userName + "'");
		List<User> users = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		if( users == null || users.size() == 0 )
			return null;
		
		return users.get(0);
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getExperimentByID(java.lang.String, java.lang.String)
	 */
	@Override
	public Experiment getExperimentByID(String jobID, String machineID) {
		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		// create primary key for experiment
		ExperimentPK pk = new ExperimentPK();
		pk.setJobId(jobID);
		pk.setMachine(machineID);

		Experiment experiment = em.find(Experiment.class, pk);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		return experiment;
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.dao.EntityDAO#getRoleByRoleName(java.lang.String)
	 */
	@SuppressWarnings("unchecked")
	@Override
	public Role getRoleByRoleName(String roleName) {
		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-sga");

		EntityManager em = emf.createEntityManager();

		// Creating a new transaction.
		EntityTransaction tx = em.getTransaction();

		tx.begin();

		Query query = em.createQuery("SELECT r FROM Role r "
				+ "WHERE r.role='" + roleName + "'");
		List<Role> roles = query.getResultList();

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();

		if( roles == null || roles.size() == 0 )
			return null;
		
		return roles.get(0);
	}
}
