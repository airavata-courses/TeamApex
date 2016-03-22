
package edu.sga.apex.rest.resource;

import java.io.File;

import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.SecurityContext;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import edu.sga.apex.app.AppInterface;
import edu.sga.apex.app.impl.GrommacsImpl;
import edu.sga.apex.bean.JobBean;
import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.impl.BigRed2SCImpl;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.rest.jaxb.JobResponse;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SimpleAPIResponse;
import edu.sga.apex.rest.jaxb.SubmitJobRequest;
import edu.sga.apex.rest.jaxb.SubmitJobResponse;
import edu.sga.apex.rest.util.Constants;
import edu.sga.apex.rest.util.ExceptionUtil;
import edu.sga.apex.rest.util.JAXBManager;
import edu.sga.apex.util.AppRefNames;
import edu.sga.apex.util.ApplicationDAOUtil;
import edu.sga.apex.util.ExperimentDAOUtil;
import edu.sga.apex.util.MachineDAOUtil;
import edu.sga.apex.util.MachineRefNames;

/**
 * The API Class JobResource.
 * 
 * @author Gourav Shenoy
 */
@Path("job")
public class JobResource {
	
	/** The context. */
	@Context
	private SecurityContext context;

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
	            "machineID": "bigred201",
	            "applicationID": "gro01",
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
				/* get the logged in user */
				String userName = context.getUserPrincipal().getName();

				/* Convert request jaxb to middleware req bean */
				SubmitJobRequestBean bean = JAXBManager
						.getSubmitJobRequestBean(request, userName);

				//System.out.println("App ID: " + bean.getApplicationID());
				//System.out.println("Machine ID: " + bean.getMachineID());

				// TODO: Uncomment the following once the params are passed in req.
				ApplicationDAOUtil appUtil = new ApplicationDAOUtil();
				String application = appUtil.getAppNameById(bean.getApplicationID());
				//String application = AppRefNames.GROMMACS.toString();

				// TODO: Uncomment the following once the params are passed in req.
				MachineDAOUtil machineUtil = new MachineDAOUtil();
				String machine = machineUtil.getMachineNameById(bean.getMachineID());
				//String machine = MachineRefNames.KARST.toString();

				/* Get Karst impl */
				//SCInterface scInterface = new KarstSCImpl(application);

				AppInterface appIntf = null;
				if(application.equals(AppRefNames.GROMMACS.toString())) {
					appIntf = new GrommacsImpl();
				}
				// else add cases for other apps here

				if( appIntf != null ) {
					/* Submit job to Karst */
					String jobId = appIntf.submitRemoteJob(bean, application, machine);
					
					/* Construct response jaxb entity */
					SubmitJobResponse response = factory.createSubmitJobResponse();
					response.setJobId(jobId);
					response.setStatus(Constants.STATUS_SUBMITTED);

					/* Build the response */
					builder = Response.ok(response);
					builder.status(Status.ACCEPTED);

				}
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
	 * @param machineID the machine id
	 * @param jobId the job id
	 * @return the response
	 */
	@DELETE
	@Path("{machineID}/{jobID}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response deleteJob(@PathParam("machineID") String machineID, @PathParam("jobID") String jobId) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {
			// TODO: Uncomment the following once the params are passed in req.
			MachineDAOUtil machineUtil = new MachineDAOUtil();
			String machineName = machineUtil.getMachineNameById(machineID);
			//String machine = MachineRefNames.KARST.toString();

			SCInterface scIntf = null;

			if( machineName.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
			}
			else if( machineName.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
			}

			/* Delete job on Karst */
			scIntf.deleteJob(jobId);

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
	 * @param jobID the job id
	 * @param machineID the machine id
	 * @return the job output file
	 */
	@GET
	@Path("{machineID}/{jobID}/output")
	public Response getJobOutputFile(@PathParam("jobID") String jobID, @PathParam("machineID") String machineID) {
		ResponseBuilder builder = null;
		try {
			// TODO: Uncomment the following once the params are passed in req.
			MachineDAOUtil machineUtil = new MachineDAOUtil();
			String machineName = machineUtil.getMachineNameById(machineID);

			SCInterface scIntf = null;

			if( machineName.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
			}
			else if( machineName.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
			}
			
			/* Get job output file path */
			String filePath = scIntf.downloadJobOutputFile(jobID, machineID);

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

	/**
	 * Gets the experiment by machine and job id.
	 *
	 * @param machineID the machine id
	 * @param jobID the job id
	 * @return the experiment by machine and job id
	 */
	@GET
	@Path("{machineID}/{jobID}")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExperimentByMachineAndJobID(@PathParam("machineID") String machineID, @PathParam("jobID") String jobID) {
		ResponseBuilder builder = null;
		try {

			// TODO: Uncomment the following once the params are passed in req.
			MachineDAOUtil machineUtil = new MachineDAOUtil();
			String machineName = machineUtil.getMachineNameById(machineID);
			//String machine = MachineRefNames.KARST.toString();

			SCInterface scIntf = null;

			if( machineName.equals(MachineRefNames.BIGRED2.toString()) ) {
				scIntf = new BigRed2SCImpl(AppRefNames.GROMMACS.toString());
			}
			else if( machineName.equals(MachineRefNames.KARST.toString()) ) {
				scIntf = new KarstSCImpl(AppRefNames.GROMMACS.toString());
			}

			JobBean bean = scIntf.getJobStatus(jobID);

			//Update Experiment in DB.
			ExperimentDAOUtil.updateExperimentStatus(jobID, machineID, bean.getStatus());

			/* get experiment entity from db */
			Experiment experimentDAO = ExperimentDAOUtil.getExperimentByJobIDAndMachineID(jobID, machineID);

			/* check if present in db */
			if(experimentDAO == null) {
				throw new Exception("Experiment with jobID: [" + jobID + "] on machine: [" + machineID + "] not found!");
			}

			/* construct jaxb from dao */
			edu.sga.apex.rest.jaxb.Experiment experimentResponse = JAXBManager.getExperimentJAXB(experimentDAO);

			/* constuct jaxb response */
			builder = Response.ok(experimentResponse);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
}
