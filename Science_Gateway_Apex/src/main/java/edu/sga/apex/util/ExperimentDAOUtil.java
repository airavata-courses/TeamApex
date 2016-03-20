package edu.sga.apex.util;

import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Experiment;

/**
 * The Class ExperimentDAOUtil.
 * 
 * @author Gourav Shenoy
 */
public class ExperimentDAOUtil {
	
	/**
	 * Gets the experiments for user.
	 *
	 * @param userName the user name
	 * @return the experiments for user
	 * @throws Exception the exception
	 */
	public static List<Experiment> getExperimentsForUser(String userName) throws Exception {
		try {
			EntityDAO dao = new EntityDAOImpl();
			return dao.getExperiments(userName);
		} catch(Exception ex) {
			System.err.println("Exception in ExperimentDAOUtil: " + ex);
			throw ex;
		}
	}
	
	/**
	 * Gets the completed experiments for user.
	 *
	 * @param userName the user name
	 * @return the completed experiments for user
	 * @throws Exception the exception
	 */
	public static List<Experiment> getCompletedExperimentsForUser(String userName) throws Exception {
		try {
			EntityDAO dao = new EntityDAOImpl();
			return dao.getCompleteExperiments(userName);
		} catch(Exception ex) {
			System.err.println("Exception in ExperimentDAOUtil: " + ex);
			throw ex;
		}
	}

}
