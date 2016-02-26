package edu.sga.apex.util;

import com.jcraft.jsch.Channel;
import com.jcraft.jsch.ChannelSftp;
import com.jcraft.jsch.JSch;
import com.jcraft.jsch.Session;
import com.jcraft.jsch.SftpException;

import edu.sga.apex.bean.SCPRequestBean;

/**
 * The Class SCPUtil.
 * 
 * @author Gourav Shenoy
 */
public class SFTPUtil {

	/** The jsch. */
	private JSch jsch = null;
	
	/** The session. */
	private Session session = null;
	
	/** The channel. */
	private Channel channel = null;
	
	/** The sftp channel. */
	private ChannelSftp sftpChannel = null;
	
	/** The request bean. */
	private SCPRequestBean requestBean;
	
	/**
	 * Instantiates a new SCP util.
	 *
	 * @param requestBean the request bean
	 */
	public SFTPUtil(SCPRequestBean requestBean) {
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
	 * Connect to channel.
	 *
	 * @return the channel sftp
	 */
	private ChannelSftp connectToChannel() {
		try {
			this.connectToSession();
			
			channel = session.openChannel(Constants.SFTP_CHANNEL);
			channel.connect();
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		
		return (ChannelSftp) channel;
	}
	
	/**
	 * Disconnect.
	 */
	private void disconnect() {
		sftpChannel.disconnect();
		session.disconnect();
	}
	
	/**
	 * Send to server.
	 */
	public void sendToServer() {
		sftpChannel = this.connectToChannel();
		
		System.out.println("[SFTP] Request Bean: " + this.requestBean);
		
		try {
			System.out.println("Starting File Upload...");
			sftpChannel.put(requestBean.getSourceFilePath(), requestBean.getDestFilePath());
			System.out.println("File upload succeeded!");
		}
		catch(Exception ex) {
			ex.printStackTrace();
		}
		finally {
			disconnect();
		}
	}
	
	/**
	 * Mk dir.
	 *
	 * @param path the path
	 */
	public void mkDir(String path) {
		sftpChannel = this.connectToChannel();
		
		System.out.println("[SFTP] Request Bean: " + this.requestBean);
		
		try {
			String[] folders = path.split("/");
			for (String folder : folders) {
			    if (folder.length() > 0) {
			        try {
			            sftpChannel.cd(folder);
			        }
			        catch ( SftpException e ) {
						System.out.println("Making dir: " + folder);
			        	sftpChannel.mkdir(folder);
			        	sftpChannel.cd(folder);
			        }
			    }
			}
			
			System.out.println("MkDir succeeded!");
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
		SCPRequestBean bean = new SCPRequestBean();
		bean.setDestFilePath("/N/u/goshenoy/Karst/sftp_test/file.txt");
		bean.setSourceFilePath("C:\\folder1\\ex1.txt");
		bean.setHostName("karst.uits.iu.edu");
		bean.setSshPort(Constants.SSH_PORT);
		bean.setUserName("goshenoy");
		bean.setPassPhrase("aq1sw2de3");
		bean.setPrivateKeyFilePath("C:\\Users\\Gaurav-PC\\.ssh\\id_rsa");
		bean.setKnownHostsFilePath("C:\\Users\\Gaurav-PC\\.ssh\\known_hosts");
		
		SFTPUtil util = new SFTPUtil(bean);
		util.sendToServer();
	}
}
