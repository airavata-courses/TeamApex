package edu.sga.apex.entity;

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * JPA Entity class for Experiment.
 * @author mangirish_wagle
 *
 */
@Entity
public class Experiment {

	@Id
	String jobId;
	String jobName;

	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	User userName;

	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	Machine machine;

	String status;
	
	//Meta
	Integer numOfNodes;
	Integer procPerNode;
	String wallTime;
	String email;

	@ManyToOne( fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	Application application;

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public User getUserName() {
		return userName;
	}

	public void setUserName(User userName) {
		this.userName = userName;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getNumOfNodes() {
		return numOfNodes;
	}

	public void setNumOfNodes(Integer numOfNodes) {
		this.numOfNodes = numOfNodes;
	}

	public Integer getProcPerNode() {
		return procPerNode;
	}

	public void setProcPerNode(Integer procPerNode) {
		this.procPerNode = procPerNode;
	}

	public String getWallTime() {
		return wallTime;
	}

	public void setWallTime(String wallTime) {
		this.wallTime = wallTime;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Application getApplication() {
		return application;
	}

	public void setApplication(Application application) {
		this.application = application;
	}
}
