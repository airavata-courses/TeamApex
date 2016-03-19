package edu.sga.apex.dao.impl;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;

import edu.sga.apex.dao.EntityDAO;

public class EntityDAOImpl implements EntityDAO {

	public void saveEntity(Object entity) {

		// Connection details loaded from persistence.xml to create EntityManagerFactory.
		EntityManagerFactory emf = Persistence.createEntityManagerFactory("jpa-test");

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
}
