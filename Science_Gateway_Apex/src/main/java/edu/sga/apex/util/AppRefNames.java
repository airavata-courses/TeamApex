package edu.sga.apex.util;

/**
 * Enum for Application reference names. 
 * @author mangirish_wagle
 *
 */
public enum AppRefNames {

	GROMMACS("Grommacs");

	String refName;

	private AppRefNames(String name) {
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
