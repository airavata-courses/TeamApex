package edu.sga.apex.rest.resource;

import java.util.List;

import javax.ws.rs.GET;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import edu.sga.apex.entity.Application;
import edu.sga.apex.rest.jaxb.ApplicationListResponse;
import edu.sga.apex.rest.util.ExceptionUtil;
import edu.sga.apex.rest.util.JAXBManager;
import edu.sga.apex.util.ApplicationDAOUtil;

/**
 * The Class ApplicationResource.
 * 
 * @author Gourav Shenoy
 */
@Path("application")
public class ApplicationResource {

	/**
	 * Gets the applications.
	 *
	 * @return the applications
	 */
	@GET
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getApplications() {
		ResponseBuilder builder = null;
		try {
			/* get application list from db */
			List<Application> applicationDAOList = ApplicationDAOUtil.getApplications();
			
			/* construct jaxb response from dao */
			ApplicationListResponse appListResponse = JAXBManager.getApplicationListResponse(applicationDAOList);
			
			/* construct the response object */
			builder = Response.ok(appListResponse);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Allow options.
	 *
	 * @return the response
	 */
	@OPTIONS
	public Response allowOPTIONS() {
		ResponseBuilder builder = Response.ok()
				.header("Access-Control-Allow-Origin", "*")
				.header("Access-Control-Allow-Headers", "X-Requested-With,Content-Type,Accept,Origin,Authorization");
		return builder.build();
	}
}
