package edu.sga.apex.rest.util;

import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import edu.sga.apex.rest.jaxb.ApiErrorResponse;
import edu.sga.apex.rest.jaxb.ObjectFactory;

/**
 * The Class ExceptionUtil.
 * 
 * @author Gourav Shenoy
 */
public class ExceptionUtil {

	/**
	 * Handle exception.
	 *
	 * @param exception the exception
	 * @return the response
	 */
	public static Response handleException(Exception exception) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();

		/* Construct error response jaxb */
		ApiErrorResponse errResponse = factory.createApiErrorResponse();
		errResponse.setMessage(exception.getMessage());
		errResponse.setStatus(Status.INTERNAL_SERVER_ERROR.getStatusCode());

		/* Build the error response */
		builder = Response.status(Response.Status.INTERNAL_SERVER_ERROR)
				.entity(errResponse);

		/* Return the response */
		return builder.build();
	}

}
