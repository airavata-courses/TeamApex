package edu.sga.apex.dao;

import java.util.List;

import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Application;
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

	/**
	 * Updates the experiment with the new data.
	 * @param expt
	 */
	public void updateExperiment(Experiment expt);

	/**
	 * Get list of all experiments for user.
	 * @param username
	 * @return
	 */
	public List<Experiment> getExperiments(String username);

	/**
	 * Get a list of completed experiments for user.
	 * @param username
	 * @return List of Experiments.
	 */
	public List<Experiment> getCompleteExperiments(String username);

	/**
	 * Get a list of In Progress experiments for user.
	 * @param username
	 * @return
	 */
	public List<Experiment> getInQueuedExperiments(String username);

	/**
	 * Get inputs for an application.
	 * @param appId
	 * @return List of inputs.
	 */
	public List<AppInput> getInputsForApp(String appId);

	/**
	 * Get list of available applications.
	 * @return List of applications.
	 */
	public List<Application> getApplications();
}
