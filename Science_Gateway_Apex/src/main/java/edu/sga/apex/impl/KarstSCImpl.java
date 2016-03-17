package edu.sga.apex.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.List;
import java.util.Properties;
import java.util.Scanner;

import edu.sga.apex.bean.BeanManager;
import edu.sga.apex.bean.InputFileBean;
import edu.sga.apex.bean.JobBean;
import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.bean.SSHRequestBean;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.SFTPUtil;
import edu.sga.apex.util.SSHUtil;


/**
 * The Class KarstSCImpl.
 * 
 * @author Gourav Shenoy
 */
public class KarstSCImpl implements SCInterface {

	/** The Constant script_path. */
	private static final String script_path = "template/karst_grommacs.script";

	/** The properties. */
	private Properties properties;

	/**
	 * Instantiates a new karst sc impl.
	 */
	public KarstSCImpl() {
		try
		{
			this.properties = new Properties();
			String propFileName = "config.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(propFileName);

			if(inputStream != null) {
				properties.load(inputStream);
			}
			else {
				throw new FileNotFoundException("property file: " + propFileName + " not found!");
			}
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/**
	 * Creates the job script.
	 */
	@Deprecated
	public void createJobScript(){
		Scanner input = new Scanner(System.in);

		String path = properties.getProperty("tempJobScript");
		if (path.startsWith("~" + File.separator)) {
			path = System.getProperty("user.home") + path.substring(1);
		}
		File tempFile = new File(path);

		try 
		{
			InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(script_path));
			BufferedReader br = new BufferedReader(reader);

			tempFile.getParentFile().mkdirs();
			PrintWriter pw = new PrintWriter(tempFile);

			String line = null, job_name = null;
			while((line = br.readLine()) != null){
				if(line.contains("$nodes")){
					System.out.print("Please enter the number of nodes: ");
					String nodes = input.nextLine();
					line = line.replace("$nodes", nodes);
				}
				if(line.contains("$processors_per_node")){
					System.out.print("Please enter the processors per node: ");
					String ppn = input.nextLine();
					line = line.replace("$processors_per_node", ppn);

				}
				if(line.contains("$emailId")){
					System.out.print("Please enter the email id: ");
					String emailId = input.nextLine();
					line = line.replace("$emailId", emailId);
				}
				if(line.contains("$job_name") && job_name == null){
					System.out.print("Please enter the job name: ");
					job_name = input.nextLine();
					line = line.replace("$job_name", job_name);
				}else if(line.contains("$job_name")){
					line = line.replace("$job_name", job_name);
				}
				pw.println(line);
				pw.flush();
			}
			pw.close();
			br.close();
		} 
		catch (FileNotFoundException e) {
			e.printStackTrace();
		}catch(IOException e){
			e.printStackTrace();
		}
		input.close();
	}
	
	/**
	 * Creates the job script.
	 *
	 * @param bean the bean
	 * @return the string
	 */
	private String createJobScript(SubmitJobRequestBean bean){
		try {
			File file = File.createTempFile("temp", "script");
			PrintWriter pw = new PrintWriter(file);
			
			InputStreamReader reader = new InputStreamReader(getClass().getClassLoader().getResourceAsStream(script_path));
			BufferedReader br = new BufferedReader(reader);
			
			String line = null;
			while((line = br.readLine()) != null){
				if(line.contains("$nodes")){
					line = line.replace("$nodes", bean.getNumNodes().toString());
				}
				if(line.contains("$processors_per_node")){
					line = line.replace("$processors_per_node", bean.getNumProcessors().toString());
				}
				if(line.contains("$walltime")){
					line = line.replace("$walltime", bean.getWallTime());
				}
				if(line.contains("$emailId")){
					line = line.replace("$emailId", bean.getEmailId());
				}
				if(line.contains("$job_name")){
					line = line.replace("$job_name", bean.getJobName());
				}
				if(line.contains("$nodesProc")){
					Integer nodesProc = bean.getNumNodes()*bean.getNumProcessors();
					line = line.replace("$nodesProc", nodesProc.toString());
				}
				if(line.contains("$tpr_file")){
					List<InputFileBean> inputFiles = bean.getInputFiles();
					String tpr_file = null, gro_file = null;
					for(InputFileBean ifbean: inputFiles){
						if(ifbean.getFileType().equals("Coordinate-File")){
							tpr_file = ifbean.getFileName();
						}else if(ifbean.getFileType().equals("Portable-Input-Binary-File")){
							gro_file = ifbean.getFileName();
						}
					}
					line = line.replace("$tpr_file", tpr_file);
					line = line.replace("$gro_file", gro_file);
				}
				pw.println(line);
				pw.flush();
			}
			pw.close();
			br.close();
			
			return file.getAbsolutePath();
			
		} catch (IOException e) {
			e.printStackTrace();
			return null;
		}
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#copyFiles(java.lang.String, java.lang.String)
	 */
	@Override
	public String copyFiles(String srcFile, String destFile) {

		try {
			SCPRequestBean bean = new SCPRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));

			bean.setSourceFilePath(srcFile);
			bean.setDestFilePath(destFile);

			SFTPUtil util = new SFTPUtil(bean);
			util.sendToServer();
			return "File copied successfully";
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return "Failed to copy file";
		}
	}
	
	/**
	 * Make dir.
	 *
	 * @param directory the directory
	 */
	public void makeDir(String directory) {
		System.out.println("Making directory on remote: " + directory);
		try {
			SCPRequestBean bean = new SCPRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));
			
			SFTPUtil util = new SFTPUtil(bean);
			util.mkDir(directory);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#monitorJob(java.lang.String)
	 */
	@Override
	public void monitorJob(String jobId) throws Exception {

		try {
			String loginUser = properties.getProperty("loginUser");

			// Copy Email send script.
			String srcFileEmail = properties.getProperty("srcFileEmail");
			String destFileEmail = properties.getProperty("destFileEmail");
			String srcFileEmailPath = this.createTempFile(srcFileEmail, "sendEmail", ".sh");
			this.copyFiles(srcFileEmailPath, destFileEmail);

			// Copy Email Properties Script.
			String srcFileEmailProp = properties.getProperty("srcFileEmailProp");
			String destFileEmailProp = properties.getProperty("destFileEmailProp");
			String srcFileEmailPropPath = this.createTempFile(srcFileEmailProp, "sendmail", ".properties");
			this.copyFiles(srcFileEmailPropPath, destFileEmailProp);

			// Calling the email send
			String sendEmailcommand = "source " + destFileEmail + " "+ jobId + " " + loginUser;

			String loginKey = properties.getProperty("loginKey");
			String knownHosts = properties.getProperty("knownHosts");
			String hostName = properties.getProperty("hostName");
			String passPhrase = properties.getProperty("passPhrase");

			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(hostName);
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(loginUser);
			bean.setPassPhrase(passPhrase);
			bean.setPrivateKeyFilePath(loginKey);
			bean.setKnownHostsFilePath(knownHosts);
			bean.setCommands(sendEmailcommand);

			SSHUtil util = new SSHUtil(bean);
			util.executeCommands();
			System.out.println("Command Executed: " + sendEmailcommand);
		}
		catch (Exception ex) {
			throw new Exception("Something went wrong while executing monitorJob command, "
					+ "reason: " + ex.getMessage());
		}
		
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#deleteJob(java.lang.String)
	 */
	@Override
	public void deleteJob(String jobId) throws Exception {
		try{
			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));
			bean.setCommands("qdel " + jobId);

			SSHUtil util = new SSHUtil(bean);
			util.executeCommands();
		}catch(Exception ex){
			throw new Exception("Something went wrong while executing monitorJob command, "
					+ "reason: " + ex.getMessage());
		}
	}

	/**
	 * Creates the temp file.
	 *
	 * @param path the path
	 * @param prefix the prefix
	 * @param suffix the suffix
	 * @return the string
	 */
	private String createTempFile(String path, String prefix, String suffix) {
		try {
			InputStream is = this.getClass().getClassLoader().getResourceAsStream(path);
			File file = File.createTempFile(prefix, suffix);

			OutputStream out = new FileOutputStream(file);
			int read;
			byte[] bytes = new byte[1024];

			while ((read = is.read(bytes)) != -1) {
				out.write(bytes, 0, read);
			}

			out.close();
			return file.getAbsolutePath();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}

	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#submitJob()
	 */
	@Deprecated
	@Override
	public String submitJob() {
		try{
			// create the job script file
			createJobScript();

			// copy the job script
			String tempJobScript = properties.getProperty("tempJobScript");
			String destJobScript = properties.getProperty("destJobScript");
			this.copyFiles(tempJobScript, destJobScript);

			// Copy Email send script.
			String srcFileEmail = properties.getProperty("srcFileEmail");
			String destFileEmail = properties.getProperty("destFileEmail");
			String srcFileEmailPath = this.createTempFile(srcFileEmail, "sendEmail", ".sh");
			this.copyFiles(srcFileEmailPath, destFileEmail);

			// Copy Email Properties Script.
			String srcFileEmailProp = properties.getProperty("srcFileEmailProp");
			String destFileEmailProp = properties.getProperty("destFileEmailProp");
			String srcFileEmailPropPath = this.createTempFile(srcFileEmailProp, "sendmail", ".properties");
			this.copyFiles(srcFileEmailPropPath, destFileEmailProp);

			// submit the job
			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));
			bean.setCommands("qsub temp.script");

			SSHUtil util = new SSHUtil(bean);
			util.executeCommands();
			return "Job submitted successfully";
		}catch(Exception e){
			e.printStackTrace();
			return "Job failed to submit";
		}
	}
	
	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#submitRemoteJon(edu.sga.apex.bean.SubmitJobRequestBean)
	 */
	@Override
	public String submitRemoteJob(SubmitJobRequestBean requestBean) {
		try{
			// create the job script file
			String pbsScriptPath = createJobScript(requestBean);
			
			if(pbsScriptPath == null) {
				throw new RuntimeException("Failed to create pbs job script");
			}

			// copy the job script
			String destJobScript = String.format(properties.getProperty("destJobScript"), 
													properties.getProperty("loginUser"));
			this.copyFiles(pbsScriptPath, destJobScript);
			
			// handle dos2unix for script
			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));
			bean.setCommands("dos2unix " + destJobScript);

			SSHUtil util = new SSHUtil(bean);
			util.executeCommands();

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
			
			String destScriptPath = String.format(properties.getProperty("destScript"), 
					properties.getProperty("loginUser"));
			
			// copy trp file
			File file = new File(System.getProperty(Constants.TEMP_DIR_PROP), tpr_file);	
			this.copyFiles(file.getAbsolutePath(), destScriptPath);
			
			// copy gro file
			file = new File(System.getProperty(Constants.TEMP_DIR_PROP), gro_file);	
			this.copyFiles(file.getAbsolutePath(), destScriptPath);
			
			/**String srcScript = properties.getProperty("srcScript");
			String destScriptPath = String.format(properties.getProperty("destScript"), 
													properties.getProperty("loginUser"));
			String srcScriptPath = this.createTempFile(srcScript, "hello", ".sh");
			
			// Make dest directory & copy binary
			this.makeDir("apex_scripts");
			this.copyFiles(srcScriptPath, destScriptPath);
			
			// handle dos2unix
			bean.setCommands("dos2unix " + destScriptPath);
			util = new SSHUtil(bean);
			util.executeCommands();
			
			// give it exe permissions
			bean.setCommands("chmod +x " + destScriptPath);
			util = new SSHUtil(bean);
			util.executeCommands();**/
			
			// Copy Email send script.
			String srcFileEmail = properties.getProperty("srcFileEmail");
			String destFileEmail = String.format(properties.getProperty("destFileEmail"), 
													properties.getProperty("loginUser"));
			String srcFileEmailPath = this.createTempFile(srcFileEmail, "sendEmail", ".sh");
			this.copyFiles(srcFileEmailPath, destFileEmail);

			// Copy Email Properties Script.
			String srcFileEmailProp = properties.getProperty("srcFileEmailProp");
			String destFileEmailProp = String.format(properties.getProperty("destFileEmailProp"), 
														properties.getProperty("loginUser"));
			String srcFileEmailPropPath = this.createTempFile(srcFileEmailProp, "sendmail", ".properties");
			this.copyFiles(srcFileEmailPropPath, destFileEmailProp);

			// submit the job
			bean.setCommands("qsub " + destJobScript);
			util = new SSHUtil(bean);
			String jobId = util.executeCommands();
			
			return jobId;
		}catch(Exception e){
			e.printStackTrace();
			return "Job failed to submit";
		}
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#getJobStatus(java.lang.String)
	 */
	@Override
	public JobBean getJobStatus(String jobId) throws Exception {
		try {
			String loginUser = properties.getProperty("loginUser");
			 
			// Calling the qstat command
			String qStatCommand = "qstat -u " + loginUser + " | grep "+ jobId;

			String loginKey = properties.getProperty("loginKey");
			String knownHosts = properties.getProperty("knownHosts");
			String hostName = properties.getProperty("hostName");
			String passPhrase = properties.getProperty("passPhrase");

			SSHRequestBean bean = new SSHRequestBean();
			bean.setHostName(hostName);
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(loginUser);
			bean.setPassPhrase(passPhrase);
			bean.setPrivateKeyFilePath(loginKey);
			bean.setKnownHostsFilePath(knownHosts);
			bean.setCommands(qStatCommand);

			SSHUtil util = new SSHUtil(bean);
			String response = util.executeCommands();
			System.out.println("Command Executed: " + qStatCommand);
			System.out.println("Response: " + response);
			JobBean job = BeanManager.createJobBean(response);
			
			return job;
		}
		catch (Exception ex) {
			throw new Exception("Something went wrong while executing qstat command, "
					+ "reason: " + ex);
		}
	}

	/* (non-Javadoc)
	 * @see edu.sga.apex.interfaces.SCInterface#downloadJobOutputFile(java.lang.String)
	 */
	@Override
	public String downloadJobOutputFile(String jobName) throws Exception {
		String downloadedFile = null;
		
		try {
			SCPRequestBean bean = new SCPRequestBean();
			bean.setHostName(properties.getProperty("hostName"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setUserName(properties.getProperty("loginUser"));
			bean.setPassPhrase(properties.getProperty("passPhrase"));
			bean.setPrivateKeyFilePath(properties.getProperty("loginKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));

			SFTPUtil util = new SFTPUtil(bean);
			downloadedFile = util.getFromServer(jobName + ".out");
			if(downloadedFile == null) {
				throw new FileNotFoundException("Output file does not exist on remote machine!");
			}
		}
		catch(Exception ex) {
			System.err.println("Failed to download job output file, reason: " + ex);
		}
		
		// return the path
		return downloadedFile;
	}
}
