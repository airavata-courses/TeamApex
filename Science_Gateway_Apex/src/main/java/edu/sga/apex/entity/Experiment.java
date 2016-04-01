package edu.sga.apex.entity;

import java.util.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;

// TODO: Auto-generated Javadoc
/**
 * JPA Entity class for Experiment.
 * 
 * @author mangirish_wagle
 * 
 */
@Entity
@IdClass(ExperimentPK.class)
public class Experiment {

	/** The job id. */
	@Id
	private String jobId;

	/** The machine. */
	@Id
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "machineId")
	private Machine machine;

	/** The job name. */
	@Column
	String jobName;

	/** The user. */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "user")
	User user;

	/** The status. */
	@Column
	String status;

	/** The num of nodes. */
	// Meta
	@Column
	Integer numOfNodes;

	/** The proc per node. */
	@Column
	Integer procPerNode;

	/** The wall time. */
	@Column
	String wallTime;

	/** The email. */
	@Column
	String email;

	/** The created at. */
	@Column
	Date createdAt;

	/** The updated at. */
	@Column
	Date updatedAt;

	/** The application. */
	@ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
	@JoinColumn(name = "appId")
	Application application;

	/**
	 * Instantiates a new experiment.
	 */
	// Empty Constructor
	public Experiment() {
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
	 * Gets the machine.
	 *
	 * @return the machine
	 */
	public Machine getMachine() {
		return machine;
	}

	/**
	 * Sets the machine.
	 *
	 * @param machine the new machine
	 */
	public void setMachine(Machine machine) {
		this.machine = machine;
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
	public User getUserName() {
		return user;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(User userName) {
		this.user = userName;
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

	/**
	 * Gets the num of nodes.
	 *
	 * @return the num of nodes
	 */
	public Integer getNumOfNodes() {
		return numOfNodes;
	}

	/**
	 * Sets the num of nodes.
	 *
	 * @param numOfNodes the new num of nodes
	 */
	public void setNumOfNodes(Integer numOfNodes) {
		this.numOfNodes = numOfNodes;
	}

	/**
	 * Gets the proc per node.
	 *
	 * @return the proc per node
	 */
	public Integer getProcPerNode() {
		return procPerNode;
	}

	/**
	 * Sets the proc per node.
	 *
	 * @param procPerNode the new proc per node
	 */
	public void setProcPerNode(Integer procPerNode) {
		this.procPerNode = procPerNode;
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
	 * Gets the email.
	 *
	 * @return the email
	 */
	public String getEmail() {
		return email;
	}

	/**
	 * Sets the email.
	 *
	 * @param email the new email
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * Gets the application.
	 *
	 * @return the application
	 */
	public Application getApplication() {
		return application;
	}

	/**
	 * Sets the application.
	 *
	 * @param application the new application
	 */
	public void setApplication(Application application) {
		this.application = application;
	}

	/**
	 * Gets the user.
	 *
	 * @return the user
	 */
	public User getUser() {
		return user;
	}

	/**
	 * Sets the user.
	 *
	 * @param user the new user
	 */
	public void setUser(User user) {
		this.user = user;
	}

	/**
	 * Gets the created at.
	 *
	 * @return the created at
	 */
	public Date getCreatedAt() {
		return createdAt;
	}

	/**
	 * Sets the created at.
	 *
	 * @param createdAt the new created at
	 */
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	/**
	 * Gets the updated at.
	 *
	 * @return the updated at
	 */
	public Date getUpdatedAt() {
		return updatedAt;
	}

	/**
	 * Sets the updated at.
	 *
	 * @param updatedAt the new updated at
	 */
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}

	/**
	 * Created at.
	 */
	@PrePersist
	void createdAt() {
		this.createdAt = this.updatedAt = new Date();
	}

	/**
	 * Updated at.
	 */
	@PreUpdate
	void updatedAt() {
		this.updatedAt = new Date();
	}
}
