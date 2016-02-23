package edu.sga.apex.rest.util;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;

/**
 * The Class BeanManager.
 * 
 * @author Gourav Shenoy
 */
public class BeanManager {
	
	public static SubmitJobRequestBean getSubmitJobRequestBean(SubmitJobRequest request) {
		SubmitJobRequestBean bean = new SubmitJobRequestBean();
		if (request != null) {
			bean.setJobName(request.getJobName());
			bean.setEmailId(request.getEmailId());
			
			/* if walltime not provided, use default */
			if(bean.getWallTime() == null) {
				bean.setWallTime(Constants.DEFAULT_WALLTIME);
			}
			else {
				bean.setWallTime(request.getWallTime());
			}
			
			/* if num of nodes not provided, use default */
			if(request.getNumNodes() == null) {
				bean.setNumNodes(Constants.DEFAULT_NUM_NODES);
			}
			else {
				bean.setNumNodes(request.getNumNodes());
			}
			
			/* if num of processors not provided, use default */
			if(request.getNumProcessors() == null) {
				bean.setNumProcessors(Constants.DEFAULT_NUM_PROCESSORS);
			}
			else {
				bean.setNumProcessors(request.getNumProcessors());
			}
		}
		return bean;
	}
}
