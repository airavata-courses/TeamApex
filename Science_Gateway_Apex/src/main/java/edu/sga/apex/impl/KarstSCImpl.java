package edu.sga.apex.impl;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;
import java.util.Scanner;

import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.SFTPUtil;

public class KarstSCImpl implements SCInterface {

	/**
	 * 
	 */
	public void createJobScript(){
		Scanner input = new Scanner(System.in);
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

	public static void main(String[] args) {
		KarstSCImpl kimpl = new KarstSCImpl();
		kimpl.monitorJob("12345");
	}

}
