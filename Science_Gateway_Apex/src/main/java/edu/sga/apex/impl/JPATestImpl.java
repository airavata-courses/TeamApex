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

/**
 * The Class JPATestImpl.
 */
public class JPATestImpl {
	
	/**
	 * Test get application.
	 */
	public void testGetApplication(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Application> apps = dao.getApplications();
		
		for(Application app : apps){
			System.out.println(app.getAppName());
		}
	}
	
	/**
	 * Test get complete expts.
	 */
	public void testGetCompleteExpts(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getCompleteExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	/**
	 * Test get queued expts.
	 */
	public void testGetQueuedExpts(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getInQueuedExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	/**
	 * Test get experiments.
	 */
	public void testGetExperiments(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<Experiment> expts = dao.getExperiments("admin");
		
		for(Experiment expt : expts){
			System.out.println(expt.getJobId());
		}
	}
	
	/**
	 * Test get inputs for app.
	 */
	public void testGetInputsForApp(){
		EntityDAO dao = new EntityDAOImpl();
		
		List<AppInput> inputs = dao.getInputsForApp("gro01");
		
		for(AppInput input : inputs){
			System.out.println(input.getInput());
		}
	}
	
	/**
	 * Test get users.
	 */
	public void testGetUsers() {
		EntityDAO dao = new EntityDAOImpl();
		
		List<User> users = dao.getUsers();
		for(User user : users) {
			System.out.println("Username: " + user.getUsername());
		}
	}
	
	/**
	 * Test get user by name.
	 *
	 * @param userName the user name
	 */
	public void testGetUserByName(String userName) {
		EntityDAO dao = new EntityDAOImpl();
		
		User user = dao.getUserByName(userName);
		if(user != null) {
			System.out.println("User found: " + user.getUsername());
		}
		else {
			System.err.println("User [" + userName + "] not found!");
		}
	}
	
	public void testGetExperimentByID(String jobID, String machineID) {
		EntityDAO dao = new EntityDAOImpl();
		
		Experiment exp = dao.getExperimentByID(jobID, machineID);
		if(exp != null) {
			System.out.println("Experiment found: " + exp.getJobName());
		}
		else {
			System.err.println("Experiment [" + jobID + "] not found!");
		}
	}
	
	/**
	 * Test save expt.
	 */
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
		expt.setStatus(ExperimentStatus.Submitted.toString());
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
		expt2.setStatus(ExperimentStatus.Submitted.toString());
		expt2.setUserName(user);
		expt2.setWallTime("30");

		dao.saveEntity(expt2);
		
		//System.out.println(ExperimentStatus.COMPLETE);
		
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
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
		System.out.println("--------Testing Get Users--------");
		jpatest.testGetUsers();
		System.out.println("--------Testing Get User by Name--------");
		jpatest.testGetUserByName("admin");
		jpatest.testGetUserByName("nonUser");
		System.out.println("--------Testing Get Experiment by ID--------");
		jpatest.testGetExperimentByID("2222", "karst01");
		jpatest.testGetExperimentByID("nonExistingID", "karst01");
	}
}
