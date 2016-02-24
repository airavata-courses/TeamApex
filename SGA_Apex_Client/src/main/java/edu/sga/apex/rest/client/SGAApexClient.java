package edu.sga.apex.rest.client;

import java.util.Scanner;

import org.apache.wink.client.ClientResponse;
import org.json.JSONObject;

/**
 * The Class SGAApexClient.
 */
public class SGAApexClient {
	
	/**
	 * Submit job.
	 *
	 * @param input the input
	 * @return the client response
	 */
	public static ClientResponse submitJob(Scanner input){
		JSONObject obj = new JSONObject();
		JSONObject request = new JSONObject();
		
		System.out.println("Please enter the number of processors.");
		String numProcessors = input.next();
		if(numProcessors.equals("") || numProcessors.isEmpty()){
			numProcessors = "6";
		}
		obj.put("numProcessors", numProcessors);
		
		System.out.println("Please enter the email id");
		String emailId = input.next();
		obj.put("emailId", emailId);
		
		System.out.println("Please enter the number of nodes.");
		String numNodes = input.next();
		if(numNodes.equals("") || numNodes.isEmpty()){
			numNodes = "2";
		}
		obj.put("numNodes", numNodes);
		
		System.out.println("Please enter the wall time");
		String wallTime = input.next();
		if(wallTime.equals("") || wallTime.isEmpty()){
			wallTime = "30:00";
		}
		obj.put("wallTime", wallTime);
		
		System.out.println("Please enter the job name");
		String jobName = input.next();
		obj.put("jobName", jobName);
		
		request.put("submitJobRequest", obj);
		
		return APIUtil.doPost(request);
	}
	
	/**
	 * Monitor job.
	 *
	 * @param input the input
	 * @return the client response
	 */
	public static ClientResponse monitorJob(Scanner input){
		System.out.println("Please enter the job id");
		String jobId = input.next();
		return APIUtil.doMonitor(jobId);
	}
	
	/**
	 * Delete job.
	 *
	 * @param input the input
	 * @return the client response
	 */
	public static ClientResponse deleteJob(Scanner input){
		System.out.println("Please enter the job id");
		String jobId = input.next();
		return APIUtil.doDelete(jobId);
	}
	
	/**
	 * Gets the status.
	 *
	 * @param input the input
	 * @return the status
	 */
	public static ClientResponse getStatus(Scanner input){
		System.out.println("Please enter the job id");
		String jobId = input.next();
		return APIUtil.doGetStatus(jobId);	
	}

	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		int choice;
		ClientResponse response;
		Scanner input = new Scanner(System.in);
		do
		{	
			System.out.println("Please enter your choice: \n 1 - Submit job \n 2 - Monitor job \n 3 - Cancel job \n 4 - Get job status\n 5 - exit\n");
			choice = input.nextInt();
			switch(choice){
			case 1:
				response = SGAApexClient.submitJob(input);
				System.out.println("Status code "+ response.getStatusCode());
				System.out.println(response.getEntity(String.class));
				break;
			case 2:
				response = SGAApexClient.monitorJob(input);
				System.out.println("Status code "+response.getStatusCode());
				System.out.println(response.getEntity(String.class));
				break;
			case 3:
				response = SGAApexClient.deleteJob(input);
				System.out.println("Status code "+response.getStatusCode());
				System.out.println(response.getEntity(String.class));
				break;
			case 4:
				response = SGAApexClient.getStatus(input);
				System.out.println("Status code "+response.getStatusCode());
				System.out.println(response.getEntity(String.class));
				break;
			case 5:
				break;
			default:
				System.out.println("Incorrect input - Must enter 1 - Submit job, 2 - Monitor job, 3 - Cancel job and 4 - exit");
				break;
			}	
		}while(choice != 5);
		input.close();
	}

}
