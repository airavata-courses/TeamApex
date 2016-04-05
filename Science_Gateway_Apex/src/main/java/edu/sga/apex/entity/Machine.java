package edu.sga.apex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity class for Machine.
 * @author mangirish_wagle
 *
 */
@Entity
public class Machine {

	/** The machine id. */
	@Id
	String machineId;

	/** The machine name. */
	@Column
	String machineName;

	/** The hostname. */
	@Column
	String hostname;

	/** The port num. */
	@Column
	String portNum;

	/** The working_dir. */
	@Column
	String working_dir;

	/**
	 * Gets the machine id.
	 *
	 * @return the machine id
	 */
	public String getMachineId() {
		return machineId;
	}
	
	/**
	 * Sets the machine id.
	 *
	 * @param machineId the new machine id
	 */
	public void setMachineId(String machineId) {
		this.machineId = machineId;
	}
	
	/**
	 * Gets the machine name.
	 *
	 * @return the machine name
	 */
	public String getMachineName() {
		return machineName;
	}
	
	/**
	 * Sets the machine name.
	 *
	 * @param machineName the new machine name
	 */
	public void setMachineName(String machineName) {
		this.machineName = machineName;
	}
	
	/**
	 * Gets the hostname.
	 *
	 * @return the hostname
	 */
	public String getHostname() {
		return hostname;
	}
	
	/**
	 * Sets the hostname.
	 *
	 * @param hostname the new hostname
	 */
	public void setHostname(String hostname) {
		this.hostname = hostname;
	}
	
	/**
	 * Gets the port num.
	 *
	 * @return the port num
	 */
	public String getPortNum() {
		return portNum;
	}
	
	/**
	 * Sets the port num.
	 *
	 * @param portNum the new port num
	 */
	public void setPortNum(String portNum) {
		this.portNum = portNum;
	}
	
	/**
	 * Gets the working_dir.
	 *
	 * @return the working_dir
	 */
	public String getWorking_dir() {
		return working_dir;
	}
	
	/**
	 * Sets the working_dir.
	 *
	 * @param working_dir the new working_dir
	 */
	public void setWorking_dir(String working_dir) {
		this.working_dir = working_dir;
	}
}
