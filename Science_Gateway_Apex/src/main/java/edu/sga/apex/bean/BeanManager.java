package edu.sga.apex.bean;

import com.ancientprogramming.fixedformat4j.format.FixedFormatManager;
import com.ancientprogramming.fixedformat4j.format.impl.FixedFormatManagerImpl;

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
	 * @param qstatResult
	 *            the qstat result
	 * @return the job bean
	 * @throws Exception
	 *             the exception
	 */
	public static JobBean createJobBean(String qstatResult) throws Exception {
		/* JobBean to be returned */
		JobBean bean = new JobBean();
		/* Initialize FixedFormatManager */
		FixedFormatManager manager = new FixedFormatManagerImpl();
		
		/* Check if qstat string is valid */ 
		if (qstatResult != null && !qstatResult.isEmpty()) {
			try {
				/* Convert qstat result to job bean */
				bean = manager.load(JobBean.class, qstatResult);
				
				/* Replace status */
				if(Constants.JOB_STATUS.containsKey(bean.getStatus())) {
					bean.setStatus(Constants.JOB_STATUS.get(bean.getStatus()));
				}
			} catch (Exception ex) {
				throw ex;
			}
		} else {
			throw new Exception("Qstat command returned empty response.");
		}

		System.out.println("Returning: " + bean);
		return bean;
	}
}
