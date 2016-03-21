package edu.sga.apex.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * JPA Entity class for Users.
 * @author mangirish_wagle
 *
 */
@Entity
public class User {

	@Id
	public Integer Id;

	@Column
	public String username;

	@Column
	public String password;

	@OneToMany( targetEntity=Role.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	List<Role> roles;

	public Integer getId() {
		return Id;
	}

	public void setId(Integer id) {
		Id = id;
	}

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

	public List<Role> getRoles() {
		return roles;
	}

	public void setRoles(List<Role> roles) {
		this.roles = roles;
	}
}
