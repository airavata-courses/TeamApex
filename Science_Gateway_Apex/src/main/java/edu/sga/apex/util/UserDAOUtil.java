package edu.sga.apex.util;

import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Role;
import edu.sga.apex.entity.User;

/**
 * The Class UserDAOUtil.
 * 
 * @author Gourav Shenoy
 */
public class UserDAOUtil {
	
	/** The dao. */
	private static EntityDAO dao = new EntityDAOImpl();

	/**
	 * Checks if is user exists.
	 *
	 * @param userName the user name
	 * @return true, if is user exists
	 */
	public static boolean isUserExists(String userName) throws Exception {
		boolean isExists = false;
		try {
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
	public static List<User> getUserList() throws Exception {
		try {
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
	public static boolean createUser(User user) throws Exception {
		boolean success = false;
		try {
			dao.saveEntity(user);
			success = true;
		} catch(Exception ex) {
			System.err.println("Exception in UserDAOUtil: " + ex);
			throw ex;
		}
		return success;
	}
	
	/**
	 * Gets the role by role name.
	 *
	 * @param roleName the role name
	 * @return the role by role name
	 * @throws Exception the exception
	 */
	public static Role getRoleByRoleName(String roleName) throws Exception {
		try {
			return dao.getRoleByRoleName(roleName);
		} catch(Exception ex) {
			System.err.println("Exception in UserDAOUtil: " + ex);
			throw ex;
		}
	}
}
