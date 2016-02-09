package edu.sga.apex.client;

import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.util.Constants;

/**
 * The Class SCJobRunner.
 * 
 * @author Gourav Shenoy
 */
public class SCJobRunner {

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {

		if ( args.length < 1 ) {
			System.out.println("The argument should be either "
					+ Constants.SUBMITJOB + " or "
					+ Constants.MONITOR);
			return;
		}

		if ( args[0].equals(Constants.SUBMITJOB) ) {
			KarstSCImpl kimpl = new KarstSCImpl();
			kimpl.submitJob();
			//System.out.println("Job submitted successfuly.");
		}
		else if ( args[0].equals(Constants.MONITOR) ) {
			if( args.length < 2 ) {
				System.out.println("Please provide the job name.");
				return;
			}

			String jobName = args[1];

			KarstSCImpl kimpl = new KarstSCImpl();
			kimpl.monitorJob(jobName);
			System.out.println("Request to monitor job " + jobName
					+ " submitted successfuly.");

		}
	}

}
