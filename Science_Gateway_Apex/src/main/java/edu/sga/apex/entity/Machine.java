package edu.sga.apex.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity class for Machine.
 * @author mangirish_wagle
 *
 */
@Entity
public class Machine {

	@Id
	String machineId;
	String machineName;
	String hostname;
	String portNum;
	String working_dir;

	public String getMachineName() {
		return machineName;
	}
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	public String getHostname() {
		return hostname;
	}
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	public String getPortNum() {
		return portNum;
	}
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	public String getWorking_dir() {
		return working_dir;
	}
	public void setWorking_dir(String working_dir) {
		this.working_dir = working_dir;
	}
}
