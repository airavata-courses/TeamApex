package edu.sga.apex.rest.util;

/**
 * The Class Constants.
 * 
 * @author Gourav Shenoy
 */
public class Constants {

	/** The Constant DEFAULT_WALLTIME. */
	public static final String DEFAULT_WALLTIME = "30:00";
	
	/** The Constant DEFAULT_NUM_PROCESSORS. */
	public static final int DEFAULT_NUM_PROCESSORS = 6;
	
	/** The Constant DEFAULT_NUM_NODES. */
	public static final int DEFAULT_NUM_NODES = 2;
	
	/** The Constant STATUS_SUBMITTED. */
	public static final String STATUS_SUBMITTED = "Submitted";
	
	/** The Constant TEMP_DIR. */
	public static final String TEMP_DIR = System.getProperty("java.io.tmpdir");
	
	/** The Constant MISSING_FIELD_EXCEPTION. */
	public static final String MISSING_FIELD_EXCEPTION = "%s parameter not specified. Please pass this field in the request JSON.";
}
