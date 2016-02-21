package edu.sga.apex.rest.util;

import org.json.JSONObject;

import edu.sga.apex.bean.SubmitJobRequestBean;

// TODO: Auto-generated Javadoc
/**
 * The Class BeanManager.
 */
public class BeanManager {

	/**
	 * Gets the submit job req bean.
	 *
	 * @param json the json
	 * @return the submit job req bean
	 */
	public static SubmitJobRequestBean getSubmitJobReqBean(JSONObject json) {
		SubmitJobRequestBean bean = new SubmitJobRequestBean();
		if (json != null) {
			bean.setJobName(json.getString("jobName"));
			bean.setEmailId(json.getString("emailId"));
			bean.setNumNodes((json.getInt("numNodes") == 0) ? Constants.DEFAULT_NUM_NODES
					: json.getInt("numNodes"));
			bean.setNumProcessors((json.getInt("numProcessors") == 0) ? Constants.DEFAULT_NUM_PROCESSORS
					: json.getInt("numProcessors"));
			bean.setWallTime((json.getString("wallTime") == null) ? Constants.DEFAULT_WALLTIME
					: json.getString("wallTime"));
		}
		return bean;
	}
}
