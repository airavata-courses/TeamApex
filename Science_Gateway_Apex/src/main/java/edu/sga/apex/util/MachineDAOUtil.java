package edu.sga.apex.util;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;

/**
 * The class MachineDAOUtil.
 * @author mangirish_wagle
 *
 */
public class MachineDAOUtil {

	public static String getMachineNameById(String machineId) {

		EntityDAO edao = new EntityDAOImpl();
		return edao.getMachineById(machineId).getMachineName();
	}

}
