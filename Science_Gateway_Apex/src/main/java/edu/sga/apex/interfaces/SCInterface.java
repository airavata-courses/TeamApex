package edu.sga.apex.interfaces;


/**
 *
 * @author erikadsouza
 *
 */
public interface SCInterface {
	
	/**
	 * This interface first creates a job script where some of the values
	 * are taken from the user interactively. It then submits the script 
	 * to the remote computing resource.
	 * 
	 * @param SCPRequestBean
	 * @return a status saying its submitted successfully or failed
	 */
	public String submitJob();
	
	/**
	 * TODO
	 * @return 
	 */
	public String copyFiles(String srcFile, String destFile);
	
	/**
	 * Function is used to get an explicit status update of a job submitted
	 * @param jobId
	 * @return
	 */
	public String monitorJob(String jobName);
	
	public String deleteJob(String jobId);
	
	
}
