package edu.sga.apex.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;

/**
 * JPA Entity class for Application.
 * @author mangirish_wagle
 *
 */
@Entity
public class Application {

	/** The app id. */
	@Id
	String appId;

	/** The app name. */
	@Column
	String appName;

	/** The script_path. */
	@Column
	String script_path;

	/** The input list. */
	@OneToMany( targetEntity=AppInput.class, fetch = FetchType.EAGER, cascade = CascadeType.ALL )
	private List<AppInput> inputList;

	/**
	 * Gets the app id.
	 *
	 * @return the app id
	 */
	public String getAppId() {
		return appId;
	}

	/**
	 * Sets the app id.
	 *
	 * @param appId the new app id
	 */
	public void setAppId(String appId) {
		this.appId = appId;
	}

	/**
	 * Gets the app name.
	 *
	 * @return the app name
	 */
	public String getAppName() {
		return appName;
	}

	/**
	 * Sets the app name.
	 *
	 * @param appName the new app name
	 */
	public void setAppName(String appName) {
		this.appName = appName;
	}

	/**
	 * Gets the script_path.
	 *
	 * @return the script_path
	 */
	public String getScript_path() {
		return script_path;
	}

	/**
	 * Sets the script_path.
	 *
	 * @param script_path the new script_path
	 */
	public void setScript_path(String script_path) {
		this.script_path = script_path;
	}

	/**
	 * Gets the input list.
	 *
	 * @return the input list
	 */
	public List<AppInput> getInputList() {
		return inputList;
	}

	/**
	 * Sets the input list.
	 *
	 * @param inputList the new input list
	 */
	public void setInputList(List<AppInput> inputList) {
		this.inputList = inputList;
	}
}
