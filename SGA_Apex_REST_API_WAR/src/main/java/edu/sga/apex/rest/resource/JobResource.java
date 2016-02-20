package edu.sga.apex.rest.resource;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

@Path("/job")
public class JobResource {
	
	@GET
	@Path("/submit/{jobName}")
	@Produces(MediaType.TEXT_PLAIN)
	public Response submitJob(@PathParam("jobName") String jobName) {
		
		ResponseBuilder builder = null;
		
		String result = "Hello World!";
		
		builder = Response.ok(result);
		return builder.build();
	}
}
