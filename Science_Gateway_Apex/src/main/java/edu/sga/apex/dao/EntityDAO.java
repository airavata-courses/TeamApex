package edu.sga.apex.dao;

import java.util.List;

import edu.sga.apex.entity.Experiment;

/**
 * Interface for Entity Data Access Object.
 * @author mangirish_wagle
 *
 */
public interface EntityDAO {

	/**
	 * Persists entity object to DB.
	 * @param entity
	 */
	public void saveEntity(Object entity);

	
	public void updateExperiment(Experiment expt);

	public List<Experiment> getCompleteExperiments();

	public List<Experiment> getInProgressExperiments();
}
