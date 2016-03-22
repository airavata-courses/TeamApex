package edu.sga.apex.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;

/**
 * JPA Entity class for Experiment.
 * @author mangirish_wagle
 *
 */
@Entity
@IdClass(ExperimentPK.class)
public class Experiment {

	@Id
	private String jobId;

	@Id
	@ManyToOne( fetch = FetchType.EAGER, cascade=CascadeType.ALL )
	@JoinColumn(name="machineId")
	private Machine machine;

	@Column
	String jobName;

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name="user")
	User user;

	@Column
	String status;

	//Meta
	@Column
	Integer numOfNodes;

	@Column
	Integer procPerNode;

	@Column
	String wallTime;

	@Column
	String email;

	@ManyToOne( fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	@JoinColumn(name="appId")
	Application application;

	//Empty Constructor
	public Experiment() {}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public Machine getMachine() {
		return machine;
	}

	public void setMachine(Machine machine) {
		this.machine = machine;
	}

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public User getUserName() {
		return user;
	}

	public void setUserName(User userName) {
		this.user = userName;
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
