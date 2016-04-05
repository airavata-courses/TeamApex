package edu.sga.apex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity for User Roles.
 * @author mangirish_wagle
 *
 */
@Entity
public class Role {

	/** The Id. */
	@Id
	public Integer Id;

	/** The role. */
	@Column
	String role;

	/**
	 * Gets the id.
	 *
	 * @return the id
	 */
	public Integer getId() {
		return Id;
	}

	/**
	 * Sets the id.
	 *
	 * @param id the new id
	 */
	public void setId(Integer id) {
		Id = id;
	}

	/**
	 * Gets the role.
	 *
	 * @return the role
	 */
	public String getRole() {
		return role;
	}

	/**
	 * Sets the role.
	 *
	 * @param role the new role
	 */
	public void setRole(String role) {
		this.role = role;
	}
}
