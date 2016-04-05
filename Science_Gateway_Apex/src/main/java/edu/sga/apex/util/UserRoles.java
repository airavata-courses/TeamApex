package edu.sga.apex.util;

public enum UserRoles {

	ADMIN("admin"),
	USER("user");

	String role;

	private UserRoles(String roleName) {
		this.role = roleName;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	@Override
	public String toString() {
		return this.getRole();
	}
}
