package edu.sga.apex.util;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Machine;

/**
 * The class MachineDAOUtil.
 * @author mangirish_wagle
 *
 */
public class MachineDAOUtil {
	
	/** The dao. */
	private static EntityDAO dao = new EntityDAOImpl();

	/**
	 * Gets the machine name by id.
	 *
	 * @param machineId the machine id
	 * @return the machine name by id
	 * @throws Exception the exception
	 */
	public static String getMachineNameById(String machineId) throws Exception {
		try {
			Machine machine = dao.getMachineById(machineId);
			
			/* null check */
			if(machine == null) {
				throw new Exception("Error fetching machine. Possibly invalid ID or non existing machine in DB.");
			}
			
			return machine.getMachineName();
		} catch(Exception ex) {
			System.err.println("Exception in ApplicationDAOUtil: " + ex);
			throw ex;
		}
	}

}
