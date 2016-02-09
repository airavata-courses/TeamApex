package edu.sga.apex.bean;

/**
 * The Class SSHRequestBean.
 * 
 * @author Gourav Shenoy
 */
public class SSHRequestBean {
	
	/** The user name. */
	private String userName;
	
	/** The host name. */
	private String hostName;
	
	/** The pass phrase. */
	private String passPhrase;
	
	/** The private key file path. */
	private String privateKeyFilePath;
	
	/** The Known hosts file path. */
	private String KnownHostsFilePath;
	
	/** The ssh port. */
	private int sshPort;
	
	/** The command. */
	private String commands;

	/**
	 * Gets the user name.
	 *
	 * @return the user name
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * Sets the user name.
	 *
	 * @param userName the new user name
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * Gets the host name.
	 *
	 * @return the host name
	 */
	public String getHostName() {
		return hostName;
	}

	/**
	 * Sets the host name.
	 *
	 * @param hostName the new host name
	 */
	public void setHostName(String hostName) {
		this.hostName = hostName;
	}

	/**
	 * Gets the pass phrase.
	 *
	 * @return the pass phrase
	 */
	public String getPassPhrase() {
		return passPhrase;
	}

	/**
	 * Sets the pass phrase.
	 *
	 * @param passPhrase the new pass phrase
	 */
	public void setPassPhrase(String passPhrase) {
		this.passPhrase = passPhrase;
	}

	/**
	 * Gets the private key file path.
	 *
	 * @return the private key file path
	 */
	public String getPrivateKeyFilePath() {
		return privateKeyFilePath;
	}

	/**
	 * Sets the private key file path.
	 *
	 * @param privateKeyFilePath the new private key file path
	 */
	public void setPrivateKeyFilePath(String privateKeyFilePath) {
		this.privateKeyFilePath = privateKeyFilePath;
	}

	/**
	 * Gets the known hosts file path.
	 *
	 * @return the known hosts file path
	 */
	public String getKnownHostsFilePath() {
		return KnownHostsFilePath;
	}

	/**
	 * Sets the known hosts file path.
	 *
	 * @param knownHostsFilePath the new known hosts file path
	 */
	public void setKnownHostsFilePath(String knownHostsFilePath) {
		KnownHostsFilePath = knownHostsFilePath;
	}

	/**
	 * Gets the ssh port.
	 *
	 * @return the ssh port
	 */
	public int getSshPort() {
		return sshPort;
	}

	/**
	 * Sets the ssh port.
	 *
	 * @param sshPort the new ssh port
	 */
	public void setSshPort(int sshPort) {
		this.sshPort = sshPort;
	}

	/**
	 * Gets the command.
	 *
	 * @return the command
	 */
	public String getCommands() {
		return commands;
	}

	/**
	 * Sets the command.
	 *
	 * @param command the new command
	 */
	public void setCommands(String command) {
		this.commands = command;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SSHRequestBean [userName=" + userName + ", hostName="
				+ hostName + ", passPhrase=" + passPhrase
				+ ", privateKeyFilePath=" + privateKeyFilePath
				+ ", KnownHostsFilePath=" + KnownHostsFilePath + ", sshPort="
				+ sshPort + ", command=" + commands + "]";
	}

}
