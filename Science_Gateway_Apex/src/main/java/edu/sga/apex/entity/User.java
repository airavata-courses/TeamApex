package edu.sga.apex.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity class for Users.
 * @author mangirish_wagle
 *
 */
@Entity
public class User {

	@Id
	public String username;

	@Column
	public String password;

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
