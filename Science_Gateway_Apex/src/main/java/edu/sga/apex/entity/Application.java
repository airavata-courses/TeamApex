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

	@Id
	String appId;

	@Column
	String appName;

	@Column
	String script_path;

	@OneToMany( targetEntity=AppInput.class, fetch = FetchType.LAZY, cascade = CascadeType.ALL )
	private List<AppInput> inputList;

	public String getAppId() {
		return appId;
	}

	public void setAppId(String appId) {
		this.appId = appId;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getScript_path() {
		return script_path;
	}

	public void setScript_path(String script_path) {
		this.script_path = script_path;
	}

	public List<AppInput> getInputList() {
		return inputList;
	}

	public void setInputList(List<AppInput> inputList) {
		this.inputList = inputList;
	}
}
