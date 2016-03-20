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
import edu.sga.apex.util.ExperimentStatus;

public class JPATestImpl {
	
	public void testGetApplication(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Application> apps = dao.getApplications();
		
		for(Application app : apps){
			System.out.println(app.getAppName());
		}
	}
	
	public void testGetCompleteExpts(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getCompleteExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	public void testGetQueuedExpts(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getInQueuedExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	public void testGetExperiments(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	public void testGetInputsForApp(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<AppInput> inputs = dao.getInputsForApp("gro01");
		
		for(AppInput input : inputs){
			System.out.println(input.getInput());
		}
	}
	
	public void testSaveExpt() {
		
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

		//dao.saveEntity(machine);

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

		expt.setJobId("1111");
		expt.setMachine(machine);

		expt.setApplication(app);
		expt.setEmail("sdf@sf.com");
		expt.setJobName("dummy");
		expt.setNumOfNodes(2);
		expt.setProcPerNode(2);
		expt.setStatus(ExperimentStatus.COMPLETE.toString());
		expt.setUserName(user);
		expt.setWallTime("30");

		dao.saveEntity(expt);
		
		Experiment expt2 = new Experiment();

		expt2.setJobId("2222");
		expt2.setMachine(machine);

		expt2.setApplication(app);
		expt2.setEmail("sdf@sf.com");
		expt2.setJobName("dummy2");
		expt2.setNumOfNodes(2);
		expt2.setProcPerNode(2);
		expt2.setStatus(ExperimentStatus.QUEUED.toString());
		expt2.setUserName(user);
		expt2.setWallTime("30");

		dao.saveEntity(expt2);
		
		//System.out.println(ExperimentStatus.COMPLETE);
		
	}

	public static void main(String[] args) {

		JPATestImpl jpatest = new JPATestImpl();
		jpatest.testSaveExpt();

		System.out.println("--------Testing Get Applications--------");
		jpatest.testGetApplication();
		System.out.println("--------Testing Get Complete Experiments--------");
		jpatest.testGetCompleteExpts();
		System.out.println("--------Testing Get Experiments--------");
		jpatest.testGetExperiments();
		System.out.println("--------Testing Get Queued Experiments--------");
		jpatest.testGetQueuedExpts();
		System.out.println("--------Testing Get App Inputs--------");
		jpatest.testGetInputsForApp();
	}
}
