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
			
			if(json.has("numNodes")){
				bean.setNumNodes(json.getInt("numNodes"));
			}else{
				bean.setNumNodes(Constants.DEFAULT_NUM_NODES);
			}
			
			if(json.has("numProcessors")){
				bean.setNumProcessors(json.getInt("numProcessors"));
			}else{
				bean.setNumProcessors(Constants.DEFAULT_NUM_PROCESSORS);
			}

			if(json.has("wallTime")){
				bean.setWallTime(json.getString("wallTime"));
			}else{
				bean.setWallTime(Constants.DEFAULT_WALLTIME);
			}
		}
		return bean;
	}
}
