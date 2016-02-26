package edu.sga.apex.rest.client;

import javax.ws.rs.core.MediaType;

import org.apache.wink.client.ClientConfig;
import org.apache.wink.client.ClientResponse;
import org.apache.wink.client.Resource;
import org.apache.wink.client.RestClient;
import org.json.JSONObject;

/**
 * The Class APIUtil.
 */
public class APIUtil {

	/** The client. */
	private static RestClient client;
	
	/** The client config. */
	private static ClientConfig clientConfig;
	
	/** The Constant URL. */
	public static String URL = "http://%s:8080/SGA_Apex/sga/rest/job/";

	static {
		// client config
		clientConfig = new org.apache.wink.client.ClientConfig();
		clientConfig.connectTimeout(30000);
		clientConfig.readTimeout(30000);

		// initialize client
		client = new org.apache.wink.client.RestClient(clientConfig);
	}

	/**
	 * Do post.
	 *
	 * @param json the json
	 * @return the client response
	 */
	public static ClientResponse doPost(JSONObject json) {
		Resource resource = client.resource(URL + "submit");
		ClientResponse response = resource.contentType(MediaType.APPLICATION_JSON).accept(MediaType.APPLICATION_JSON)
				.post(json.toString());
		return response;
	}

	/**
	 * Do delete.
	 *
	 * @param jobId the job id
	 * @return the client response
	 */
	public static ClientResponse doDelete(String jobId) {
		Resource resource = client
				.resource(URL + jobId);
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).delete();
		return response;
	}
	
	/**
	 * Do get status.
	 *
	 * @param jobId the job id
	 * @return the client response
	 */
	public static ClientResponse doGetStatus(String jobId){
		Resource resource = client.resource(URL + jobId + "/status");
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get();
		return response;
	}
	
	/**
	 * Do monitor.
	 *
	 * @param jobId the job id
	 * @return the client response
	 */
	public static ClientResponse doMonitor(String jobId){
		Resource resource = client.resource(URL + jobId + "/monitor");
		ClientResponse response = resource.accept(MediaType.APPLICATION_JSON).get();
		return response;
	}
}
