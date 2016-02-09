package edu.sga.apex.bean;

/**
 * The Class SCPRequestBean.
 * 
 * @author Gourav Shenoy
 */
public class SCPRequestBean {
	
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
	
	/** The source file path. */
	private String sourceFilePath;
	
	/** The dest file path. */
	private String destFilePath;
	
	/** The ssh port. */
	private int sshPort;
	
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
	 * Gets the source file path.
	 *
	 * @return the source file path
	 */
	public String getSourceFilePath() {
		return sourceFilePath;
	}

	/**
	 * Sets the source file path.
	 *
	 * @param sourceFilePath the new source file path
	 */
	public void setSourceFilePath(String sourceFilePath) {
		this.sourceFilePath = sourceFilePath;
	}

	/**
	 * Gets the dest file path.
	 *
	 * @return the dest file path
	 */
	public String getDestFilePath() {
		return destFilePath;
	}

	/**
	 * Sets the dest file path.
	 *
	 * @param destFilePath the new dest file path
	 */
	public void setDestFilePath(String destFilePath) {
		this.destFilePath = destFilePath;
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

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "SCPRequestBean [userName=" + userName + ", hostName="
				+ hostName + ", passPhrase=" + passPhrase
				+ ", privateKeyFilePath=" + privateKeyFilePath
				+ ", KnownHostsFilePath=" + KnownHostsFilePath
				+ ", sourceFilePath=" + sourceFilePath + ", destFilePath="
				+ destFilePath + ", sshPort=" + sshPort + "]";
	}

}
