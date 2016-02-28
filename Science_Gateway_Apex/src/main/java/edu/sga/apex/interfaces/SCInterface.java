package edu.sga.apex.interfaces;

import edu.sga.apex.bean.JobBean;
import edu.sga.apex.bean.SubmitJobRequestBean;


/**
 * The Interface SCInterface.
 *
 * @author erikadsouza
 */
public interface SCInterface {
	
	
	/**
	 * Copy files.
	 *
	 * @param srcFile the src file
	 * @param destFile the dest file
	 * @return the string
	 */
	public String copyFiles(String srcFile, String destFile);
	
	/**
	 * Function is used to get an explicit status update of a job submitted.
	 *
	 * @param jobId the job name
	 * @throws Exception the exception
	 */
	public void monitorJob(String jobId) throws Exception;
	
	/**
	 * Delete job.
	 *
	 * @param jobId the job id
	 * @throws Exception the exception
	 */
	public void deleteJob(String jobId) throws Exception;
	
	/**
	 * Submit remote jon.
	 *
	 * @param requestBean the request bean
	 * @return the string
	 */
	public String submitRemoteJob(SubmitJobRequestBean requestBean);
	
	/**
	 * Gets the job status.
	 *
	 * @param jobId the job id
	 * @return the job status
	 * @throws Exception the exception
	 */
	public JobBean getJobStatus(String jobId) throws Exception;
	
	/**
	 * Download job output file.
	 *
	 * @param jobName the job name
	 * @return the string
	 * @throws Exception the exception
	 */
	public String downloadJobOutputFile(String jobName) throws Exception;
	
	/**
	 * Submit job.
	 *
	 * @return the string
	 */
	@Deprecated
	public String submitJob();
	
}
