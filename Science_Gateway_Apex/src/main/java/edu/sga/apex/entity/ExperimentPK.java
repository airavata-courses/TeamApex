package edu.sga.apex.entity;

import java.io.Serializable;

import javax.persistence.Embeddable;

/**
 * Composite Primary Key class for Experiment.
 * @author mangirish_wagle
 *
 */
@Embeddable
public class ExperimentPK implements Serializable {

	/**
	 * serialVersionUID required by Serializable.
	 */
	private static final long serialVersionUID = 1L;

	/** The job id. */
	String jobId;
	
	/** The machine. */
	String machine;

	/**
	 * Instantiates a new experiment pk.
	 */
	public ExperimentPK() {}

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
	public String getMachine() {
		return machine;
	}

	/**
	 * Sets the machine.
	 *
	 * @param machine the new machine
	 */
	public void setMachine(String machine) {
		this.machine = machine;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#hashCode()
	 */
	@Override
	public int hashCode() {
		return this.jobId.hashCode() + this.machine.hashCode();
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#equals(java.lang.Object)
	 */
	@Override
	public boolean equals(Object obj) {
		if (obj == null) {
			return false;
		}
		if (obj == this) {
			return true;
		}
		if (!(obj instanceof ExperimentPK)) {
			return false;
		}
		ExperimentPK pk = (ExperimentPK) obj;
		return pk.jobId == this.jobId 
				&& pk.getMachine().equals(this.getMachine());
	}
}
