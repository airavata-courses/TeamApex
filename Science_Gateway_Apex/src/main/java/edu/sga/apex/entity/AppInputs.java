package edu.sga.apex.entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * JPA Entity class for Application Inputs.
 * @author mangirish_wagle
 *
 */
@Entity
public class AppInputs {
	
	@Id
	String input;
	String application;
	String description;

	public String getInput() {
		return input;
	}
	public void setInput(String input) {
		this.input = input;
	}
	public String getApplication() {
		return application;
	}
	public void setApplication(String application) {
		this.application = application;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
}
