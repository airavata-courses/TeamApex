package edu.sga.apex.app.impl;
import edu.sga.apex.app.AppInterface;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.BigRed2SCImpl;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.AppRefNames;
import edu.sga.apex.util.ExperimentDAOUtil;
import edu.sga.apex.util.MachineRefNames;

/**
 * Grommacs application Impl.
 * @author mangirish_wagle
 *
 */
public class GrommacsImpl implements AppInterface {

	@Override
	public String submitRemoteJob(SubmitJobRequestBean requestBean, String appName, String machineName) {

		try {

			String jobId = "";
			SCInterface scIntf = null;

			if( machineName.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
			}
			else if( machineName.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
			}

			jobId = scIntf.submitRemoteJob(requestBean);

			//Add DB entry.
			ExperimentDAOUtil.saveExperiment(requestBean, jobId, appName, machineName);

			return jobId;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
