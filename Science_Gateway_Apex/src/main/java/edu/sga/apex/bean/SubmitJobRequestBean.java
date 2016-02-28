package edu.sga.apex.bean;

/**
 * The Class SubmitJobRequestBean.
 * 
 * @author Gourav Shenoy
 */
public class SubmitJobRequestBean {
	
	/** The job name. */
	private String jobName;
	
	/** The email id. */
	private String emailId;
	
	/** The wall time. */
	private String wallTime;
	
	/** The num nodes. */
	private Integer numNodes;
	
	/** The num processors. */
	private Integer numProcessors;

	/**
	 * Gets the job name.
	 *
	 * @return the job name
	 */
	public String getJobName() {
		return jobName;
	}

	/**
	 * Sets the job name.
	 *
	 * @param jobName the new job name
	 */
	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	/**
	 * Gets the email id.
	 *
	 * @return the email id
	 */
	public String getEmailId() {
		return emailId;
	}

	/**
	 * Sets the email id.
	 *
	 * @param emailId the new email id
	 */
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	/**
	 * Gets the wall time.
	 *
	 * @return the wall time
	 */
	public String getWallTime() {
		return wallTime;
	}

	/**
	 * Sets the wall time.
	 *
	 * @param wallTime the new wall time
	 */
	public void setWallTime(String wallTime) {
		this.wallTime = wallTime;
	}

	/**
	 * Gets the num nodes.
	 *
	 * @return the num nodes
	 */
	public Integer getNumNodes() {
		return numNodes;
	}

	/**
	 * Sets the num nodes.
	 *
	 * @param numNodes the new num nodes
	 */
	public void setNumNodes(Integer numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * Gets the num processors.
	 *
	 * @return the num processors
	 */
	public Integer getNumProcessors() {
		return numProcessors;
	}

	/**
	 * Sets the num processors.
	 *
	 * @param numProcessors the new num processors
	 */
	public void setNumProcessors(Integer numProcessors) {
		this.numProcessors = numProcessors;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SubmitJobRequestBean [jobName=" + jobName + ", emailId=" + emailId + ", wallTime=" + wallTime
				+ ", numCores=" + numNodes + ", numProcessors=" + numProcessors + "]";
	}

}
