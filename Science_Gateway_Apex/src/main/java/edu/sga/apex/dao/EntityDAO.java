package edu.sga.apex.dao;

import java.util.List;

import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.Role;
import edu.sga.apex.entity.User;

/**
 * Interface for Entity Data Access Object.
 * @author mangirish_wagle
 *
 */
public interface EntityDAO {

	/**
	 * Persists entity object to DB.
	 *
	 * @param entity the entity
	 */
	public void saveEntity(Object entity);

	/**
	 * Updates the experiment with the new data.
	 *
	 * @param expt the expt
	 */
	public void updateExperiment(Experiment expt);

	/**
	 * Get list of all experiments for user.
	 *
	 * @param username the username
	 * @return the experiments
	 */
	public List<Experiment> getExperiments(String username);

	/**
	 * Gets the experiment by id.
	 *
	 * @param jobID the job id
	 * @param machineID the machine id
	 * @return the experiment by id
	 */
	public Experiment getExperimentByID(String jobID, String machineID);

	/**
	 * Get a list of completed experiments for user.
	 *
	 * @param username the username
	 * @return List of Experiments.
	 */
	public List<Experiment> getCompleteExperiments(String username);

	/**
	 * Get a list of In Progress experiments for user.
	 *
	 * @param username the username
	 * @return the in queued experiments
	 */
	public List<Experiment> getInQueuedExperiments(String username);

	/**
	 * Get inputs for an application.
	 *
	 * @param appId the app id
	 * @return List of inputs.
	 */
	public List<AppInput> getInputsForApp(String appId);

	/**
	 * Get list of available applications.
	 * @return List of applications.
	 */
	public List<Application> getApplications();

	/**
	 * Gets the machine object from DB by name.
	 *
	 * @param machineName the machine name
	 * @return the machine by name
	 */
	public Machine getMachineByName(String machineName);

	/**
	 * Gets the machine object from DB by ID.
	 *
	 * @param machineId the machine ID.
	 * @return Machine entity.
	 */
	public Machine getMachineById(String machineId);

	/**
	 * Gets the application object from DB by name.
	 *
	 * @param appName the app name
	 * @return the application by name
	 */
	public Application getApplicationByName(String appName);

	/**
	 * Get Application by ID.
	 * @param appId
	 * @return Application entity.
	 */
	public Application getApplicationById(String appId);

	/**
	 * Gets the users.
	 *
	 * @return the users
	 */
	public List<User> getUsers();

	/**
	 * Gets the user by name.
	 *
	 * @param userName the user name
	 * @return the user by name
	 */
	public User getUserByName(String userName);
	
	/**
	 * Gets the role by role name.
	 *
	 * @param roleName the role name
	 * @return the role by role name
	 */
	public Role getRoleByRoleName(String roleName);
}
