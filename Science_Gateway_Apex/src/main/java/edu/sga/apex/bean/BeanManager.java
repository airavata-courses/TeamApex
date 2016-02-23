package edu.sga.apex.bean;

import java.util.Arrays;

import edu.sga.apex.util.Constants;

/**
 * The Class BeanManager.
 * 
 * @author Gourav Shenoy
 */
public class BeanManager {

	/**
	 * Creates the job bean.
	 *
	 * @param qstatResult the qstat result
	 * @return the job bean
	 * @throws Exception the exception
	 */
	public static JobBean createJobBean(String qstatResult) throws Exception {
		JobBean bean = new JobBean();
		if (qstatResult != null) {
			String[] jobInfo = qstatResult.split(" ");
			System.out.println(Arrays.toString(jobInfo));
			bean.setJobId(jobInfo[0].trim());
			bean.setUserName(jobInfo[1].trim());
			bean.setQueue(jobInfo[2].trim());
			bean.setJobName(jobInfo[3].trim());
			bean.setNumNodes(jobInfo[5].trim());
			bean.setNumProcessors(jobInfo[6].trim());
			bean.setRequiredTime(jobInfo[8].trim());
			bean.setStatus(Constants.JOB_STATUS
					.get(jobInfo[9].trim().charAt(0)));
		} else {
			throw new Exception("Qstat command returned empty response.");
		}

		System.out.println("Returning JobBean: " + bean);
		return bean;
	}

}
