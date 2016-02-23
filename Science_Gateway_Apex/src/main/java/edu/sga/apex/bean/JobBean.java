package edu.sga.apex.bean;

/**
 * The Class JobBean.
 * 
 * @author Gourav Shenoy
 */
public class JobBean {

	/** The job id. */
	private String jobId;
	
	/** The job name. */
	private String jobName;
	
	/** The user name. */
	private String userName;
	
	/** The queue. */
	private String queue;
	
	/** The num nodes. */
	private String numNodes;
	
	/** The num processors. */
	private String numProcessors;
	
	/** The required time. */
	private String requiredTime;
	
	/** The status. */
	private String status;

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
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the queue.
	 *
	 * @return the queue
	 */
	public String getQueue() {
		return queue;
	}

	/**
	 * Sets the queue.
	 *
	 * @param queue the new queue
	 */
	public void setQueue(String queue) {
		this.queue = queue;
	}

	/**
	 * Gets the num nodes.
	 *
	 * @return the num nodes
	 */
	public String getNumNodes() {
		return numNodes;
	}

	/**
	 * Sets the num nodes.
	 *
	 * @param numNodes the new num nodes
	 */
	public void setNumNodes(String numNodes) {
		this.numNodes = numNodes;
	}

	/**
	 * Gets the num processors.
	 *
	 * @return the num processors
	 */
	public String getNumProcessors() {
		return numProcessors;
	}

	/**
	 * Sets the num processors.
	 *
	 * @param numProcessors the new num processors
	 */
	public void setNumProcessors(String numProcessors) {
		this.numProcessors = numProcessors;
	}

	/**
	 * Gets the required time.
	 *
	 * @return the required time
	 */
	public String getRequiredTime() {
		return requiredTime;
	}

	/**
	 * Sets the required time.
	 *
	 * @param requiredTime the new required time
	 */
	public void setRequiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
	}

	/**
	 * Sets the status.
	 *
	 * @param status the new status
	 */
	public void setStatus(String status) {
		this.status = status;
	}
	
	/**
	 * Gets the status.
	 *
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "JobBean [jobId=" + jobId + ", jobName=" + jobName
				+ ", userName=" + userName + ", queue=" + queue + ", numNodes="
				+ numNodes + ", numProcessors=" + numProcessors
				+ ", requiredTime=" + requiredTime + ", status=" + status + "]";
	}
	
}
