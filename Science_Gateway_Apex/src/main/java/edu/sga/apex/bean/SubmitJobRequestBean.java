package edu.sga.apex.bean;

public class SubmitJobRequestBean {
	
	private String jobName;
	
	private String emailId;
	
	private String wallTime;
	
	private Integer numCores;
	
	private Integer numProcessors;

	public String getJobName() {
		return jobName;
	}

	public void setJobName(String jobName) {
		this.jobName = jobName;
	}

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	public String getWallTime() {
		return wallTime;
	}

	public void setWallTime(String wallTime) {
		this.wallTime = wallTime;
	}

	public Integer getNumCores() {
		return numCores;
	}

	public void setNumCores(Integer numCores) {
		this.numCores = numCores;
	}

	public Integer getNumProcessors() {
		return numProcessors;
	}

	public void setNumProcessors(Integer numProcessors) {
		this.numProcessors = numProcessors;
	}

	@Override
	public String toString() {
		return "SubmitJobRequestBean [jobName=" + jobName + ", emailId=" + emailId + ", wallTime=" + wallTime
				+ ", numCores=" + numCores + ", numProcessors=" + numProcessors + "]";
	}

}
