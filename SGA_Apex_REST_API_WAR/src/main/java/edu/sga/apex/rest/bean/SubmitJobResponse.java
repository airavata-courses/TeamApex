package edu.sga.apex.rest.bean;

// TODO: Auto-generated Javadoc
/**
 * The Class SubmitJobResponse.
 */
public class SubmitJobResponse {
	
	/** The job id. */
	private String jobId;
	
	/** The status. */
	private String status;
	
	/**
	 * Instantiates a new submit job response.
	 *
	 * @param jobId the job id
	 * @param status the status
	 */
	public SubmitJobResponse(String jobId, String status) {
		this.setJobId(jobId);
		this.setStatus(status);
	}

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	public String getJobId() {
		return jobId;
	}

	/**
	 * Sets the job id.
	 *
	 * @param jobId the new job id
	 */
	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}

}
