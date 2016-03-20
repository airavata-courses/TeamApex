
package edu.sga.apex.rest.resource;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import edu.sga.apex.bean.JobBean;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.rest.jaxb.JobResponse;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SimpleAPIResponse;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;
import edu.sga.apex.rest.jaxb.SubmitJobResponse;
import edu.sga.apex.rest.util.JAXBManager;
import edu.sga.apex.util.AppRefNames;
import edu.sga.apex.rest.util.Constants;
import edu.sga.apex.rest.util.ExceptionUtil;

/**
 * The API Class JobResource.
 * 
 * @author Gourav Shenoy
 */
@Path("job")
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
	            "jobName": "goshenoy-01",
	            "inputFiles": [
	    			{
						"fileType": "T1",
						"fileName": "test.in"
	    			},
	    			{
						"fileType": "T2",
						"fileName": "test2.in"
	    			}
  				],
	        }
	    }
	 * 
	 * */
	/**
	 * Submit remote job.
	 *
	 * @param request the request
	 * @return the response
	 */
	@POST
	@Path("submit")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response submitRemoteJob(SubmitJobRequest request) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {
			if (request != null) {
				
				//TODO: Get this from the request
				String application = AppRefNames.GROMMACS.toString();
				
				/* Convert request jaxb to middleware req bean */
				SubmitJobRequestBean bean = JAXBManager
						.getSubmitJobRequestBean(request);
				
				/* Get Karst impl */
				SCInterface scInterface = new KarstSCImpl(application);
				/* Submit job to Karst */
				String jobId = scInterface.submitRemoteJob(bean);
				
				/* Construct response jaxb entity */
				SubmitJobResponse response = factory.createSubmitJobResponse();
				response.setJobId(jobId);
				response.setStatus(Constants.STATUS_SUBMITTED);
				
				/* Build the response */
				builder = Response.ok(response);
				builder.status(Status.ACCEPTED);
			} else {
				throw new Exception("Invalid API Request (Empty)");
			}
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Delete job.
	 *
	 * @param jobId the job id
	 * @return the response
	 */
	@DELETE
	@Path("{jobId}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteJob(@PathParam("jobId") String jobId) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {
			
			//TODO: Get this from the request
			String application = AppRefNames.GROMMACS.toString();
			
			/* Get Karst impl */
			SCInterface scInterface = new KarstSCImpl(application);
			/* Delete job oo Karst */
			scInterface.deleteJob(jobId);
			
			/* Construct response jaxb entity */
			SimpleAPIResponse response = factory.createSimpleAPIResponse();
			response.setMessage("Submitted request to delete job [" + jobId + "].");
			response.setStatus(Status.ACCEPTED.getStatusCode());
			
			/* Build the response */
			builder = Response.ok(response);
			builder.status(Status.ACCEPTED);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Monitor job.
	 *
	 * @param jobId the job id
	 * @return the response
	 */
	@GET
	@Path("{jobId}/monitor")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response monitorJob(@PathParam("jobId") String jobId) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {
			
			//TODO: Get this from the request
			String application = AppRefNames.GROMMACS.toString();
			
			/* Get Karst impl */
			SCInterface scInterface = new KarstSCImpl(application);
			/* Delete job oo Karst */
			scInterface.monitorJob(jobId);
			
			/* Construct response jaxb entity */
			SimpleAPIResponse response = factory.createSimpleAPIResponse();
			response.setMessage("Submitted request to monitor job [" + jobId + "]. "
					+ "You should now receive emails providing you more information about the status of your submitted job.");
			response.setStatus(Status.ACCEPTED.getStatusCode());
			
			/* Build the response */
			builder = Response.ok(response);
			builder.status(Status.ACCEPTED);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Gets the job status.
	 *
	 * @param jobId the job id
	 * @return the job status
	 */
	@GET
	@Path("{jobId}/status")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getJobStatus(@PathParam("jobId") String jobId) {
		ResponseBuilder builder = null;
		try {
			
			//TODO: Get this from the request
			String application = AppRefNames.GROMMACS.toString();
			
			/* Get Karst impl */
			SCInterface scInterface = new KarstSCImpl(application);
			/* Get job status from Karst */
			JobBean bean = scInterface.getJobStatus(jobId);
			
			/* Construct response jaxb entity */
			JobResponse response = JAXBManager.getJobResponse(bean);
			
			/* Build the response */
			builder = Response.ok(response);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Gets the job output file.
	 *
	 * @param jobName the job name
	 * @return the job output file
	 */
	@GET
	@Path("{jobName}/output")
	public Response getJobOutputFile(@PathParam("jobName") String jobName) {
		ResponseBuilder builder = null;
		try {
			
			//TODO: Get this from the request
			String application = AppRefNames.GROMMACS.toString();
			
			/* Get Karst impl */
			SCInterface scInterface = new KarstSCImpl(application);
			/* Get job output file path */
			String filePath = scInterface.downloadJobOutputFile(jobName);
			
			/* Create a file object */
			File response = new File(filePath);
			
			/* Build the response */
			builder = Response.ok(response).header("Content-Disposition", "attachment; filename=" + response.getName());
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
}
