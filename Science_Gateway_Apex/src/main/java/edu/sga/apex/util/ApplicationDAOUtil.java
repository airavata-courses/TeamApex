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
	 * @throws Exception 
	 */
	public static String getAppNameById(String appId) throws Exception {
		try {
			Application application = dao.getApplicationById(appId);
			
			/* null check */
			if(application == null) {
				throw new Exception("Error fetching application. Possibly invalid ID or non existing application in DB.");
			}
			
			return application.getAppName();
		} catch(Exception ex) {
			System.err.println("Exception in ApplicationDAOUtil: " + ex);
			throw ex;
		}
	}
}
