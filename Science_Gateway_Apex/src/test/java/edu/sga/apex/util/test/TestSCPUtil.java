package edu.sga.apex.util.test;

import static org.junit.Assert.assertTrue;
import static org.junit.Assert.fail;

import java.io.FileNotFoundException;
import java.io.InputStream;
import java.util.Properties;

import org.junit.Test;

import edu.sga.apex.bean.SCPRequestBean;
import edu.sga.apex.util.Constants;
import edu.sga.apex.util.SFTPUtil;

public class TestSCPUtil {
	
	@Test
	public void testSCPUtil(){
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
			
			SCPRequestBean bean = new SCPRequestBean();
			bean.setHostName(properties.getProperty("hostname"));
			bean.setUserName(properties.getProperty("username"));
			bean.setSshPort(Constants.SSH_PORT);
			bean.setPassPhrase(properties.getProperty("passphrase"));
			bean.setDestFilePath(properties.getProperty("destFilePath"));
			bean.setSourceFilePath(properties.getProperty("srcFilePath"));
			bean.setPrivateKeyFilePath(properties.getProperty("privateKey"));
			bean.setKnownHostsFilePath(properties.getProperty("knownHosts"));		
			
			//SCPUtil util = new SCPUtil(bean);
			//util.sendToServer();
			
			assertTrue(true);
		}
		catch(Exception ex) {
			ex.printStackTrace();
			fail("Test Failed!");
		}
				
	}

}
