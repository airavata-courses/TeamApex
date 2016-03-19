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

	String jobId;
	String machine;

	public ExperimentPK() {}

	public String getJobId() {
		return jobId;
	}

	public void setJobId(String jobId) {
		this.jobId = jobId;
	}

	public String getMachine() {
		return machine;
	}

	public void setMachine(String machine) {
		this.machine = machine;
	}

	@Override
	public int hashCode() {
		return this.jobId.hashCode() + this.machine.hashCode();
	}

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
