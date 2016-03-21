package edu.sga.apex.app;

import edu.sga.apex.bean.SubmitJobRequestBean;

/**
 * Interface for application methods.
 * @author mangirish_wagle
 *
 */
public interface AppInterface {

	/**
	 * Method that handles application related job submission actions for a given machine.
	 * @param requestBean
	 * @param appName
	 * @param machineName
	 * @return
	 */
	public String submitRemoteJob(SubmitJobRequestBean requestBean, String appName, String machineName);
}
