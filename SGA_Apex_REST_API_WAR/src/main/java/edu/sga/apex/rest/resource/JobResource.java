package edu.sga.apex.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.json.JSONObject;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.rest.util.BeanManager;

/**
 * The Class JobResource.
 * 
 * @author Gourav Shenoy
 */
@Path("/job")
public class JobResource {

	/**
	 * Submit job.
	 *
	 * @param inputObj            the input obj
	 * @return the response
	 */
	@POST
	@Path("/submit")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitJob(JSONObject inputObj) {
		ResponseBuilder builder = null;
		if (inputObj != null) {
			SubmitJobRequestBean bean = BeanManager.getSubmitJobReqBean(inputObj);
			SCInterface scInterface = new KarstSCImpl();
			String jobId = scInterface.submitRemoteJob(bean);
			String response = "{\"jobId\": " + jobId + ", \"status\":\"Submitted\"}";
			builder = Response.ok(response);
		} else {
			builder = Response.status(Status.BAD_REQUEST);
			builder.entity("{\"error\":\"Invalid JSON input\"}");
		}
		return builder.build();
	}
}
