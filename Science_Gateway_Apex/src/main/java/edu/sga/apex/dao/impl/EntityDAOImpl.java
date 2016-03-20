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
import edu.sga.apex.util.ExperimentStatus;

/**
 * Entity Data Access Object Implementation.
 * @author mangirish_wagle
 *
 */
public class EntityDAOImpl implements EntityDAO {

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

	@Override
	public void updateExperiment(Experiment expt) {
		this.saveEntity(expt);
	}

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

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();
		
		return machines.get(0);
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

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();
		
		return apps.get(0);
	}
}
