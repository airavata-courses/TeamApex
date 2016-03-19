package edu.sga.apex.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.entity.Experiment;

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
		em.persist(entity);

		// Committing transaction.
		tx.commit();

		// Closing connection.
		em.close();
		emf.close();
	}

	@Override
	public void updateExperiment(Experiment expt) {
		// TODO Auto-generated method stub

	}

	@Override
	public List<Experiment> getCompleteExperiments() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public List<Experiment> getInProgressExperiments() {
		// TODO Auto-generated method stub
		return null;
	}
}
