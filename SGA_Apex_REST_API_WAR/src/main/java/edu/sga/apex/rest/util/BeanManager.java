package edu.sga.apex.rest.util;

import org.json.JSONObject;

import edu.sga.apex.bean.SubmitJobRequestBean;

public class BeanManager {

	public static SubmitJobRequestBean getSubmitJobReqBean(JSONObject json) {
		SubmitJobRequestBean bean = new SubmitJobRequestBean();
		if (json != null) {
			bean.setJobName(json.getString("jobName"));
			bean.setEmailId(json.getString("emailId"));
			bean.setNumCores((json.getInt("numCores") == 0) ? 2 : json.getInt("numCores"));
			bean.setNumProcessors((json.getInt("numProcessors") == 0) ? 6 : json.getInt("numProcessors"));
			bean.setWallTime((json.getString("wallTime") == null) ? "30:00" : json.getString("wallTime"));
		}
		return bean;
	}
}
