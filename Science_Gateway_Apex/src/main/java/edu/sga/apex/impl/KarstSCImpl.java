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
import java.net.URISyntaxException;
import java.net.URL;
import java.util.Enumeration;
import java.util.Properties;
import java.util.Scanner;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

import sun.misc.Launcher;
import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.bean.SSHRequestBean;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.SFTPUtil;
import edu.sga.apex.util.SSHUtil;


/**
 * 
 */
public class KarstSCImpl implements SCInterface{

	private static final String script_path = "template/karst_job.script";

	private Properties properties;

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
					System.out.println("Please enter the number of nodes");
					String nodes = input.nextLine();
					line = line.replace("$nodes", nodes);
				}
				if(line.contains("$processors_per_node")){
					System.out.println("Please enter the processors per node");
					String ppn = input.nextLine();
					line = line.replace("$processors_per_node", ppn);

				}
				if(line.contains("$emailId")){
					System.out.println("Please enter the email id");
					String emailId = input.nextLine();
					line = line.replace("$emailId", emailId);
				}
				if(line.contains("$job_name") && job_name == null){
					System.out.println("Please enter the job");
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

	@Override
	public String copyFiles(String srcFile, String destFile) {

		try {
			String loginUser = properties.getProperty("loginUser");
			String loginKey = properties.getProperty("loginKey");
			String knownHosts = properties.getProperty("knownHosts");
			String hostName = properties.getProperty("hostName");
			Integer portName = Constants.SSH_PORT;
			String passPhrase = properties.getProperty("passPhrase");

			SCPRequestBean bean = new SCPRequestBean();
			bean.setHostName(hostName);
			bean.setSshPort(portName);
			bean.setUserName(loginUser);
			bean.setPassPhrase(passPhrase);
			bean.setPrivateKeyFilePath(loginKey);
			bean.setKnownHostsFilePath(knownHosts);

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

	@Override
	public String monitorJob(String jobName) {

		String loginUser = properties.getProperty("loginUser");

		// Copy Email send script.
		String srcFileEmail = properties.getProperty("srcFileEmail");
		String destFileEmail = properties.getProperty("destFileEmail");
		String srcFileEmailPath = this.createTempFile(srcFileEmail, "sendEmail", ".sh");
		this.copyFiles(srcFileEmailPath, destFileEmail);
		//this.copyFiles(srcFileEmail, destFileEmail);

		// Copy Email Properties Script.
		String srcFileEmailProp = properties.getProperty("srcFileEmailProp");
		String destFileEmailProp = properties.getProperty("destFileEmailProp");
		String srcFileEmailPropPath = this.createTempFile(srcFileEmailProp, "sendmail", ".properties");
		this.copyFiles(srcFileEmailPropPath, destFileEmailProp);
//		this.copyFiles(srcFileEmailProp, destFileEmailProp);

		// Calling the email send
		String sendEmailcommand = "source " + destFileEmail + " "+ jobName + " " + loginUser;

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
		return null;
	}

	@Override
	public String deleteJob(String jobId) {
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
			return "Request to delete job sent successfully";
		}catch(Exception ex){
			return "Failed to request delete job";
		}
	}
	
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
            
            return file.getAbsolutePath();
		}
		catch(Exception ex) {
			ex.printStackTrace();
			return null;
		}
		
	}
	
}
