package edu.sga.apex.util;

import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Application;

/**
 * The Class ApplicationDAOUtil.
 * 
 * @author Gourav Shenoy
 */
public class ApplicationDAOUtil {

	/** The dao. */
	private static EntityDAO dao = new EntityDAOImpl();

	/**
	 * Gets the applications.
	 *
	 * @return the applications
	 * @throws Exception the exception
	 */
	public static List<Application> getApplications() throws Exception {
		try {
			return dao.getApplications();
		} catch(Exception ex) {
			System.err.println("Exception in ApplicationDAOUtil: " + ex);
			throw ex;
		}
	}

	/**
	 * Gets the application name by querying on ID.
	 * @param appId Application ID.
	 * @return Application Name.
	 */
	public String getAppNameById(String appId) {

		EntityDAO edao = new EntityDAOImpl();
		return edao.getApplicationById(appId).getAppName();
	}
}
