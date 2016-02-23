package edu.sga.apex.rest.util;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;

/**
 * The Class BeanManager.
 * 
 * @author Gourav Shenoy
 */
public class BeanManager {

	/**
	 * Gets the submit job request bean.
	 * 
	 * @param request
	 *            the request
	 * @return the submit job request bean
	 * @throws Exception
	 */
	public static SubmitJobRequestBean getSubmitJobRequestBean(
			SubmitJobRequest request) throws Exception {
		
		SubmitJobRequestBean bean = new SubmitJobRequestBean();
		
		/* throw error if required field not specified */
		if (request != null) {
			/* throw error if required field not specified */
			if (request.getJobName() == null) {
				throw new Exception(
						"jobName parameter not specified. Please pass this field in the request JSON.");
			}
			bean.setJobName(request.getJobName());

			/* throw error if required field not specified */
			if (request.getEmailId() == null) {
				throw new Exception(
						"emailId parameter not specified. Please pass this field in the request JSON.");
			}
			bean.setEmailId(request.getEmailId());

			/* if walltime not provided, use default */
			if (request.getWallTime() == null) {
				bean.setWallTime(Constants.DEFAULT_WALLTIME);
			} else {
				bean.setWallTime(request.getWallTime());
			}

			/* if num of nodes not provided, use default */
			if (request.getNumNodes() == null) {
				bean.setNumNodes(Constants.DEFAULT_NUM_NODES);
			} else {
				bean.setNumNodes(request.getNumNodes());
			}

			/* if num of processors not provided, use default */
			if (request.getNumProcessors() == null) {
				bean.setNumProcessors(Constants.DEFAULT_NUM_PROCESSORS);
			} else {
				bean.setNumProcessors(request.getNumProcessors());
			}
		}
		return bean;
	}
}
