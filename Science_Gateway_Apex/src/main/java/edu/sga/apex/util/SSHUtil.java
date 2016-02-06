package edu.sga.apex.util;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelExec;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;

import edu.sga.apex.bean.SSHRequestBean;

/**
 * The Class SSHUtil.
 * 
 * @author Gourav Shenoy
 */
public class SSHUtil {

	/** The jsch. */
	private JSch jsch = null;
	
	/** The session. */
	private Session session = null;
	
	/** The channel. */
	private Channel channel = null;
	
	/** The exec channel. */
	private ChannelExec execChannel = null;
	
	/** The request bean. */
	private SSHRequestBean requestBean = null;
	
	/**
	 * Instantiates a new SSH util.
	 *
	 * @param requestBean the request bean
	 */
	public SSHUtil(SSHRequestBean requestBean) {
		this.requestBean = requestBean;
		this.jsch = new JSch();
	}
	
	/**
	 * Connect to session.
	 */
	private void connectToSession() {
		try {
			session = jsch.getSession(requestBean.getUserName(), requestBean.getHostName(), requestBean.getSshPort());
			
			jsch.setKnownHosts(requestBean.getKnownHostsFilePath());
			jsch.addIdentity(requestBean.getPrivateKeyFilePath(), requestBean.getPassPhrase());
			
			session.connect();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
	}
	
	/**
	 * Open channel.
	 *
	 * @return the channel exec
	 */
	private ChannelExec openChannel() {
		try {
			this.connectToSession();
			channel = session.openChannel(Constants.EXEC_CHANNEL);
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return (ChannelExec) channel;
	}
	
	/**
	 * Disconnect.
	 */
	private void disconnect() {
		execChannel.disconnect();
		session.disconnect();
	}
	
	/**
	 * Execute commands.
	 */
	public void executeCommands() {
		execChannel = this.openChannel();
		execChannel.setErrStream(System.err);
		
		System.out.println("Request Bean: " + this.requestBean);
		
		try {
			System.out.println("Starting command execution...");
			execChannel.setCommand(this.requestBean.getCommands());
			execChannel.connect();
			System.out.println("Execution completed!");
			
			BufferedReader reader = new BufferedReader(new InputStreamReader(execChannel.getInputStream()));
		    String line;
		    while ((line = reader.readLine()) != null) {
		      System.out.println(line);
		    }
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		SSHRequestBean bean = new SSHRequestBean();
		bean.setHostName("karst.uits.iu.edu");
		bean.setSshPort(Constants.SSH_PORT);
		bean.setUserName("goshenoy");
		bean.setPassPhrase("aq1sw2de3");
		bean.setPrivateKeyFilePath("C:\\Users\\Gaurav-PC\\.ssh\\id_rsa");
		bean.setKnownHostsFilePath("C:\\Users\\Gaurav-PC\\.ssh\\known_hosts");
		bean.setCommands("pwd;");
		
		SSHUtil util = new SSHUtil(bean);
		util.executeCommands();
	}
}
