package edu.sga.apex.util;

import java.util.List;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.User;

/**
 * The Class ExperimentDAOUtil.
 * 
 * @author Gourav Shenoy
 */
public class ExperimentDAOUtil {

	/** The dao. */
	private static EntityDAO dao = new EntityDAOImpl();

	/**
	 * Gets the experiments for user.
	 *
	 * @param userName the user name
	 * @return the experiments for user
	 * @throws Exception the exception
	 */
	public static List<Experiment> getExperimentsForUser(String userName) throws Exception {
		try {
			return dao.getExperiments(userName);
		} catch(Exception ex) {
			System.err.println("Exception in ExperimentDAOUtil: " + ex);
			throw ex;
		}
	}

	/**
	 * Gets the completed experiments for user.
	 *
	 * @param userName the user name
	 * @return the completed experiments for user
	 * @throws Exception the exception
	 */
	public static List<Experiment> getCompletedExperimentsForUser(String userName) throws Exception {
		try {
			return dao.getCompleteExperiments(userName);
		} catch(Exception ex) {
			System.err.println("Exception in ExperimentDAOUtil: " + ex);
			throw ex;
		}
	}

	/**
	 * Gets the experiment by job id and machine id.
	 *
	 * @param jobID the job id
	 * @param machineID the machine id
	 * @return the experiment by job id and machine id
	 * @throws Exception the exception
	 */
	public static Experiment getExperimentByJobIDAndMachineID(String jobID, String machineID) throws Exception {
		try {
			return dao.getExperimentByID(jobID, machineID);
		} catch(Exception ex) {
			System.err.println("Exception in ExperimentDAOUtil: " + ex);
			throw ex;
		}
	}

	/**
	 * Save experiment.
	 *
	 * @param requestBean the request bean
	 * @param jobId the job id
	 * @param appName the app name
	 * @param machineName the machine name
	 */
	public static void saveExperiment(SubmitJobRequestBean requestBean, String jobId,
			String appName, String machineName ) {
		System.out.println("Entered..");
		User user = dao.getUserByName( requestBean.getUserName() );

		Machine machineObj = dao.getMachineByName(machineName);

		Application app = dao.getApplicationByName(appName);

		Experiment expt = new Experiment();

		expt.setJobId(jobId);
		expt.setMachine(machineObj);

		expt.setApplication(app);
		expt.setEmail(requestBean.getEmailId());
		expt.setJobName(requestBean.getJobName());
		expt.setNumOfNodes(requestBean.getNumNodes());
		expt.setProcPerNode(requestBean.getNumProcessors());
		expt.setStatus(ExperimentStatus.Submitted.toString());
		expt.setUserName(user);
		expt.setWallTime(requestBean.getWallTime());

		dao.saveEntity(expt);
		System.out.println("saved: " + expt);
	}

	/**
	 * Update experiment status.
	 *
	 * @param exptId the expt id
	 * @param machineId the machine id
	 * @param status the status
	 */
	public static void updateExperimentStatus( String exptId, String machineId, String status ) {

		Experiment exptToUpdate = dao.getExperimentByID(exptId, machineId);
		exptToUpdate.setStatus(status);
		dao.updateExperiment(exptToUpdate);

	}
}
