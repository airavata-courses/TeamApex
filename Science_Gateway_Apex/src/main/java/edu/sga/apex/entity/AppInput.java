package edu.sga.apex.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity class for Application Inputs.
 * @author mangirish_wagle
 *
 */
@Entity
public class AppInput {
	
	@Id
	String input;
	String description;

	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
