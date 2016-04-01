package edu.sga.apex.app.impl;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.List;
import java.util.Properties;

import edu.sga.apex.app.AppInterface;
import edu.sga.apex.bean.InputFileBean;
import edu.sga.apex.bean.SSHRequestBean;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.BigRed2SCImpl;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.AppRefNames;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.ExperimentDAOUtil;
import edu.sga.apex.util.MachineRefNames;
import edu.sga.apex.util.SSHUtil;

/**
 * Grommacs application Impl.
 * @author mangirish_wagle
 *
 */
public class GrommacsImpl implements AppInterface {

	/** The properties. */
	private Properties properties;

	SCInterface scIntf = null;

	String propFileName = null;

	public GrommacsImpl(String machineName) {

		try{
			if( machineName.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
				propFileName = "bigred2_config.properties";
			}
			else if( machineName.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
				propFileName = "karst_config.properties";
			}

			this.properties = new Properties();

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(propFileName);

			if(inputStream != null) {
				this.properties.load(inputStream);
			}
			else {
				throw new FileNotFoundException("property file: " + propFileName + " not found!");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	@Override
	public String submitRemoteJob(SubmitJobRequestBean requestBean, String appName, String machineName) {
		try {
			// create exclusive job directory for user
			scIntf.makeDir(String.format(Constants.USER_JOB_DIR, requestBean.getUserName(), requestBean.getJobName()));

			// Copy binary
			List<InputFileBean> inputFiles = requestBean.getInputFiles();
			String tpr_file = null, gro_file = null;
			for(InputFileBean ifbean: inputFiles){
				if(ifbean.getFileType().equals("Coordinate-File")){
					gro_file = ifbean.getFileName();
				}else if(ifbean.getFileType().equals("Portable-Input-Binary-File")){
					tpr_file = ifbean.getFileName();
				}
			}

			String destScriptPath = String.format(properties.getProperty("jobDir"), 
					properties.getProperty("loginUser"), requestBean.getUserName(), requestBean.getJobName());

			// copy trp file
			File file = new File(System.getProperty(Constants.TEMP_DIR_PROP), tpr_file);	
			scIntf.copyFiles(file.getAbsolutePath(), destScriptPath);

			// copy gro file
			file = new File(System.getProperty(Constants.TEMP_DIR_PROP), gro_file);	
			scIntf.copyFiles(file.getAbsolutePath(), destScriptPath);

			//Submit Job
			String jobId = scIntf.submitRemoteJob(requestBean);
			System.out.println("JobID: " + jobId);
			
			// Check job submission
			if(jobId == null || 
					jobId.isEmpty() || 
					jobId.length() > Constants.MAX_JOBID_LEN) {
				throw new Exception("Something went wrong while submitting remote job! "
						+ "Please try again later.");
			}
				
			//Add DB entry.
			ExperimentDAOUtil.saveExperiment(requestBean, jobId, appName, machineName);

			return jobId;
		}
		catch(Exception ex) {
			System.err.println("Exception in GrommacsImpl: " + ex);
			return null;
		}
	}

	@Override
	public String downloadJobOutputFile(String userName, String jobName) {
		/* Get Job Name from JobID */
		try{
			//Experiment expt = ExperimentDAOUtil.getExperimentByJobIDAndMachineID(jobID, machineID);
			//String jobName = expt.getJobName();

			String jobDir = String.format(properties.getProperty("jobDir"), 
					properties.getProperty("loginUser"), userName, jobName);

			// Build Tar.
			String tarCommand = "tar -cf " 
					+ jobDir + File.separator + jobName + ".tar "
					+ jobDir + File.separator + "*.out "
					+ jobDir + File.separator + "*.edr "
					+ jobDir + File.separator + "*.log "
					+ jobDir + File.separator + "*.trr";

			String loginKey = properties.getProperty("loginKey");
			String knownHosts = properties.getProperty("knownHosts");
			String hostName = properties.getProperty("hostName");
			String passPhrase = properties.getProperty("passPhrase");
			String loginUser = properties.getProperty("loginUser");

			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(hostName);
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(loginUser);
			bean.setPassPhrase(passPhrase);
			bean.setPrivateKeyFilePath(loginKey);
			bean.setKnownHostsFilePath(knownHosts);
			bean.setCommands(tarCommand);

			SSHUtil util = new SSHUtil(bean);
			String response = util.executeCommands();
			System.out.println("Command Executed: " + tarCommand);
			System.out.println("Response: " + response);

			// Download the Tar.
			String downloadedFile = scIntf.downloadJobOutputFile(jobDir + File.separator + jobName + ".tar ");
			return downloadedFile;
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
	}
}
