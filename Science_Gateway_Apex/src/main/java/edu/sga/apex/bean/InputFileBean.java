package edu.sga.apex.bean;

/**
 * The Class InputFileBean.
 * 
 * @author Gourav Shenoy
 */
public class InputFileBean {
	
	/** The file type. */
	private String fileType;
	
	/** The file name. */
	private String fileName;

	/**
	 * Gets the file type.
	 *
	 * @return the file type
	 */
	public String getFileType() {
		return fileType;
	}

	/**
	 * Sets the file type.
	 *
	 * @param fileType the new file type
	 */
	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	/**
	 * Gets the file name.
	 *
	 * @return the file name
	 */
	public String getFileName() {
		return fileName;
	}

	/**
	 * Sets the file name.
	 *
	 * @param fileName the new file name
	 */
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	/* (non-Javadoc)
	 * @see java.lang.Object#toString()
	 */
	@Override
	public String toString() {
		return "InputFileBean [fileType=" + fileType + ", fileName=" + fileName
				+ "]";
	}
	
}
