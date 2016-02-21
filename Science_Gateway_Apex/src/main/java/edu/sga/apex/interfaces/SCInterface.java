package edu.sga.apex.interfaces;

import edu.sga.apex.bean.SubmitJobRequestBean;


/**
 * The Interface SCInterface.
 *
 * @author erikadsouza
 */
public interface SCInterface {
	
	/**
	 * This interface first creates a job script where some of the values
	 * are taken from the user interactively. It then submits the script 
	 * to the remote computing resource.
	 *
	 * @return a status saying its submitted successfully or failed
	 */
	public String submitJob();
	
	/**
	 * TODO.
	 *
	 * @param srcFile the src file
	 * @param destFile the dest file
	 * @return the string
	 */
	public String copyFiles(String srcFile, String destFile);
	
	/**
	 * Function is used to get an explicit status update of a job submitted.
	 *
	 * @param jobName the job name
	 * @return the string
	 */
	public String monitorJob(String jobName);
	
	/**
	 * Delete job.
	 *
	 * @param jobId the job id
	 * @return the string
	 */
	public String deleteJob(String jobId);
	
	/**
	 * Submit remote jon.
	 *
	 * @param requestBean the request bean
	 * @return the string
	 */
	public String submitRemoteJob(SubmitJobRequestBean requestBean);
	
}
