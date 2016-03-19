package edu.sga.apex.dao;

/**
 * Interface for Entity Data Access Object.
 * @author mangirish
 *
 */
public interface EntityDAO {

	/**
	 * Persists entity object to DB.
	 * @param entity
	 */
	public void saveEntity(Object entity);

}
