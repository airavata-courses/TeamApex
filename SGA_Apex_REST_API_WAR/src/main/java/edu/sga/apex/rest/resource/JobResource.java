package edu.sga.apex.rest.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;

import org.json.JSONObject;

@Path("/job")
public class JobResource {
	
	@POST
	@Path("/submit/{jobName}")
	@Produces(MediaType.APPLICATION_JSON)
	@Consumes(MediaType.APPLICATION_JSON)
	public Response submitJob(@PathParam("jobName") String jobName, JSONObject inputObj) {
		
		ResponseBuilder builder = null;
		
		String result = "{\"status\": \"OK\"}";
		
		System.out.println(inputObj);
		
		builder = Response.ok(result);
		return builder.build();
	}
}
