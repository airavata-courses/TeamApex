package edu.sga.apex.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.rest.jaxb.ApiErrorResponse;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;
import edu.sga.apex.rest.jaxb.SubmitJobResponse;
import edu.sga.apex.rest.util.BeanManager;
import edu.sga.apex.rest.util.Constants;

/**
 * The Class JobResource.
 * 
 * @author Gourav Shenoy
 */
@Path("/job")
public class JobResource {
	
	/* 
	 * API Request JSON
	    {
	        "submitJobRequest":
	        {
	            "numProcessors": 6,
	            "emailId": "goshenoy@indiana.edu",
	            "numNodes": 2,
	            "wallTime": "30:00",
	            "jobName": "goshenoy-01"
	        }
	    }
	 * 
	 * */
	@POST
	@Path("/submit")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response submitRemoteJob(SubmitJobRequest request) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {
			if (request != null) {
				SubmitJobRequestBean bean = BeanManager
						.getSubmitJobRequestBean(request);
				SCInterface scInterface = new KarstSCImpl();
				String jobId = scInterface.submitRemoteJob(bean);

				SubmitJobResponse response = factory.createSubmitJobResponse();
				response.setJobId(jobId);
				response.setStatus(Constants.STATUS_SUBMITTED);
				builder = Response.ok(response);
			} else {
				throw new Exception("Invalid API Request (Empty)");
			}
		} catch (Exception ex) {
			System.out.println(request);
			ApiErrorResponse errResponse = factory.createApiErrorResponse();
			errResponse.setMessage(ex.getMessage());
			errResponse.setStatus(Status.BAD_REQUEST.getStatusCode());

			builder = Response.status(Response.Status.BAD_REQUEST)
					.entity(errResponse);
		}

		return builder.build();
	}
}
