package edu.sga.apex.util;

import java.util.LinkedList;
import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.User;

/**
 * Executable class to pre- populate database for initial application use.
 * @author mangirish_wagle
 *
 */
public class PrepopulateDB {

	public static void main(String[] args) {

		EntityDAO dao = new EntityDAOImpl();

		User user =  new User();
		user.setUsername("admin");
		user.setPassword("apex123");

		dao.saveEntity(user);

		Machine machine1 = new Machine();
		machine1.setMachineId("karst01");
		machine1.setHostname("karst.uits.iu.edu");
		machine1.setMachineName(MachineRefNames.KARST.toString());
		machine1.setPortNum("22");
		machine1.setWorking_dir("Karst");

		dao.saveEntity(machine1);

		Machine machine2 = new Machine();
		machine2.setMachineId("bigred201");
		machine2.setHostname("bigred2.uits.iu.edu");
		machine2.setMachineName(MachineRefNames.BIGRED2.toString());
		machine2.setPortNum("22");
		machine2.setWorking_dir("BigRed2");

		dao.saveEntity(machine2);

		Application app = new Application();
		app.setAppId("gro01");
		app.setAppName("GROMMACS");
		app.setScript_path("/tmp");
		List<AppInput> inputList = new LinkedList<AppInput>();

		AppInput input1 = new AppInput();
		input1.setInput("pdb1y6l-EM-vacuum.tpr");
		input1.setDescription("Topology file");

		AppInput input2 = new AppInput();
		input2.setInput("pdb1y6l-EM-vacuum.gro");
		input2.setDescription("Grommacs file");

		inputList.add(input1);
		inputList.add(input2);

		app.setInputList(inputList);

		dao.saveEntity(app);

	}

}
