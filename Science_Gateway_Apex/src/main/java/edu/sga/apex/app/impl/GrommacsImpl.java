package edu.sga.apex.app.impl;
import edu.sga.apex.app.AppInterface;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Application;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Machine;
import edu.sga.apex.entity.User;
import edu.sga.apex.impl.BigRed2SCImpl;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.AppRefNames;
import edu.sga.apex.util.ExperimentStatus;
import edu.sga.apex.util.MachineRefNames;

/**
 * Grommacs application Impl.
 * @author mangirish_wagle
 *
 */
public class GrommacsImpl implements AppInterface {

	@Override
	public String submitRemoteJob(SubmitJobRequestBean requestBean) {

		try {
			//TODO: Get this from the request bean
			String application = AppRefNames.GROMMACS.toString();

			//TODO: Get this from the request bean
			String machine = MachineRefNames.KARST.toString();

			String jobId = "";
			SCInterface scIntf = null;

			if( machine.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
			}
			else if( machine.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
			}

			jobId = scIntf.submitRemoteJob(requestBean);

			//Add DB entry.
			EntityDAO dao = new EntityDAOImpl();

			// TODO: Get Logged in user from context
			User user =  new User();
			user.setUsername("admin");
			user.setPassword("apex123");

			Machine machineObj = dao.getMachineByName(machine);

			Application app = dao.getApplicationByName(application);

			Experiment expt = new Experiment();

			expt.setJobId(jobId);
			expt.setMachine(machineObj);

			expt.setApplication(app);
			expt.setEmail(requestBean.getEmailId());
			expt.setJobName(requestBean.getJobName());
			expt.setNumOfNodes(requestBean.getNumNodes());
			expt.setProcPerNode(requestBean.getNumProcessors());
			expt.setStatus(ExperimentStatus.QUEUED.toString());
			expt.setUserName(user);
			expt.setWallTime(requestBean.getWallTime());

			dao.saveEntity(expt);

			return jobId;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
