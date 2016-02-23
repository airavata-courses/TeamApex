package edu.sga.apex.bean;

import com.ancientprogramming.fixedformat4j.annotation.Field;
import com.ancientprogramming.fixedformat4j.annotation.Record;

/**
 * The Class JobBean.
 * 
 * @author Gourav Shenoy
 */
@Record
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
	
	private String requiredMemory;
	
	/** The required time. */
	private String requiredTime;
	
	/** The status. */
	private String status;
	
	private String elapsedTime;

	/**
	 * Gets the job id.
	 *
	 * @return the job id
	 */
	@Field(offset = 1, length = 24)
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
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	@Field(offset = 25, length = 12)
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
	@Field(offset = 37, length = 9)
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
	 * Gets the job name.
	 *
	 * @return the job name
	 */
	@Field(offset = 46, length = 17)
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
	 * Gets the num nodes.
	 *
	 * @return the num nodes
	 */
	@Field(offset = 70, length = 6)
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
	@Field(offset = 76, length = 7)
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

	@Field(offset = 83, length = 7)
	public String getRequiredMemory() {
		return requiredMemory;
	}

	public void setRequiredMemory(String requiredMemory) {
		this.requiredMemory = requiredMemory;
	}

	@Field(offset = 90, length = 10)
	public String getRequiredTime() {
		return requiredTime;
	}

	public void setRequiredTime(String requiredTime) {
		this.requiredTime = requiredTime;
	}

	@Field(offset = 100, length = 2)
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Field(offset = 102, length = 9)
	public String getElapsedTime() {
		return elapsedTime;
	}

	public void setElapsedTime(String elapsedTime) {
		this.elapsedTime = elapsedTime;
	}

	@Override
	public String toString() {
		return "JobBean [jobId=" + jobId + ", jobName=" + jobName
				+ ", userName=" + userName + ", queue=" + queue + ", numNodes="
				+ numNodes + ", numProcessors=" + numProcessors
				+ ", requiredMemory=" + requiredMemory + ", requiredTime="
				+ requiredTime + ", status=" + status + ", elapsedTime="
				+ elapsedTime + "]";
	}
	
}
