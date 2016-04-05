package edu.sga.apex.entity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * JPA Entity class for Users.
 * @author mangirish_wagle
 *
 */
@Entity
public class User {

	/** The Id. */
	@Id
	public Integer Id;

	/** The username. */
	@Column
	public String username;

	/** The password. */
	@Column
	public String password;

	/** The roles. */
	@OneToMany( targetEntity=Role.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	List<Role> roles;

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
	 * Gets the username.
	 *
	 * @return the username
	 */
	public String getUsername() {
		return username;
	}

	/**
	 * Sets the username.
	 *
	 * @param username the new username
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * Gets the password.
	 *
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Sets the password.
	 *
	 * @param password the new password
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Gets the roles.
	 *
	 * @return the roles
	 */
	public List<Role> getRoles() {
		if(roles == null) {
			roles = new ArrayList<Role>();
		}
		return roles;
	}

	/**
	 * Sets the roles.
	 *
	 * @param roles the new roles
	 */
	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
