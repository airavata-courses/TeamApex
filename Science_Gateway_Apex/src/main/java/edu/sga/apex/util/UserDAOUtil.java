package edu.sga.apex.util;

import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.User;

/**
 * The Class UserDAOUtil.
 * 
 * @author Gourav Shenoy
 */
public class UserDAOUtil {

	/**
	 * Checks if is user exists.
	 *
	 * @param userName the user name
	 * @return true, if is user exists
	 */
	public static boolean isUserExists(String userName) {
		boolean isExists = false;
		try {
			EntityDAO dao = new EntityDAOImpl();
			
			User user = dao.getUserByName(userName);
			if(user != null) {
				isExists = true;
			}			
		} catch(Exception ex) {
			System.err.println("Exception in UserDAOUtil: " + ex);
			throw ex;
		}
		return isExists;
	}
	
	/**
	 * Gets the user list.
	 *
	 * @return the user list
	 */
	public static List<User> getUserList() {
		try {
			EntityDAO dao = new EntityDAOImpl();
			return dao.getUsers();
		} catch(Exception ex) {
			System.err.println("Exception in UserDAOUtil: " + ex);
			throw ex;
		}
	}
	
	/**
	 * Creates the user.
	 *
	 * @param user the user
	 * @return true, if successful
	 */
	public static boolean createUser(User user) {
		boolean success = false;
		try {
			EntityDAO dao = new EntityDAOImpl();
			dao.saveEntity(user);
			success = true;
		} catch(Exception ex) {
			System.err.println("Exception in UserDAOUtil: " + ex);
			throw ex;
		}
		return success;
	}
}
