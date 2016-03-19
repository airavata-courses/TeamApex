package edu.sga.apex.impl;

import java.util.LinkedList;
import java.util.List;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.AppInput;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.User;

public class JPATestImpl {

	public static void main(String[] args) {

		EntityDAO dao = new EntityDAOImpl();

		User user =  new User();
		user.setUsername("admin");
		user.setPassword("apex123");

		//dao.saveEntity(user);

		Machine machine = new Machine();
		machine.setMachineId("karst01");
		machine.setHostname("karst.iu.edu");
		machine.setMachineName("KARST");
		machine.setPortNum("22");
		machine.setWorking_dir("Karst");

		Application app = new Application();
		app.setAppId("gro01");
		app.setAppName("GROMMACS");
		app.setScript_path("/path");
		List<AppInput> inputList = new LinkedList<AppInput>();

		AppInput input1 = new AppInput();
		input1.setInput("xyz.tpr");
		input1.setDescription("Topology file");

		AppInput input2 = new AppInput();
		input2.setInput("xyz.gro");
		input2.setDescription("grommac file");

		inputList.add(input1);
		inputList.add(input2);

		app.setInputList(inputList);

		Experiment expt = new Experiment();
		expt.setApplication(app);
		expt.setEmail("sdf@sf.com");
		expt.setJobId("2222");
		expt.setJobName("dummy");
		expt.setMachine(machine);
		expt.setNumOfNodes(2);
		expt.setProcPerNode(2);
		expt.setStatus("");
		expt.setUserName(user);
		expt.setWallTime("30");

		dao.saveEntity(expt);
	}
}
