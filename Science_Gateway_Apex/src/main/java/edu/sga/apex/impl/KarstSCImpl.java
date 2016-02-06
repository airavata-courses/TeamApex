package edu.sga.apex.impl;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.Properties;
import java.util.Scanner;

import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.SFTPUtil;


/**
 * 
 */
public class KarstSCImpl implements SCInterface{

	private static final String script_path = "template/karst_job.script";
	private static final String local_output_path = System.getProperty("user.home") + "/temp.script";

	public void createJobScript(){
		Scanner input = new Scanner(System.in);

		File tempFile = new File(local_output_path);

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
	public String submitJob(SCPRequestBean SCPRequestBean) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String copyFiles() {

		try {
			Properties properties = new Properties();
			String propFileName = "config.properties";

			InputStream inputStream = getClass().getClassLoader()
					.getResourceAsStream(propFileName);

			if(inputStream != null) {
				properties.load(inputStream);
			}
			else {
				throw new FileNotFoundException("property file: " + propFileName + " not found!");
			}

			String loginUser = properties.getProperty("loginUser");
			String loginKey = properties.getProperty("loginKey");
			String knownHosts = properties.getProperty("knownHosts");
			String srcFile = properties.getProperty("srcFile");
			String srcFileProp = properties.getProperty("srcFileProp");
			String destFile = properties.getProperty("destFile");
			String destFileProp = properties.getProperty("destFileProp");
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

			SFTPUtil util = new SFTPUtil(bean);

			// Copy Email send script.
			bean.setSourceFilePath(srcFile);
			bean.setDestFilePath(destFile);
			util.sendToServer();

			// Copy email send properties.
			bean.setSourceFilePath(srcFileProp);
			bean.setDestFilePath(destFileProp);
			util.sendToServer();

			System.out.println("SUCCESS");

		}
		catch(Exception ex) {
			ex.printStackTrace();
		}

		return null;

	}

	@Override
	public String monitorJob(String jobId) {

		this.copyFiles();

		return null;
	}
}
