package edu.sga.apex.util;

/**
 * Enum for Machine Reference Names.
 * @author mangirish_wagle
 *
 */
public enum MachineRefNames {

	KARST("karst"),
	BIGRED2("bigred2");

	String refName;

	private MachineRefNames(String name) {
		this.refName = name;
	}

	public String getRefName() {
		return refName;
	}

	public void setRefName(String refName) {
		this.refName = refName;
	}

	@Override
	public String toString() {
		return this.getRefName();
	}
}
