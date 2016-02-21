package edu.sga.apex.rest.resource;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.xml.datatype.Duration;
import javax.xml.datatype.XMLGregorianCalendar;
import javax.xml.namespace.QName;

import org.eclipse.jetty.util.Scanner.BulkListener;
import org.json.JSONObject;

import edu.sga.apex.bean.SubmitJobRequestBean;
import edu.sga.apex.impl.KarstSCImpl;
import edu.sga.apex.interfaces.SCInterface;
import edu.sga.apex.rest.jaxb.Author;
import edu.sga.apex.rest.jaxb.Book;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.util.APIUtil;
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
			String response = "{\"jobId\": \"" + jobId + "\", \"status\":\"Submitted\"}";
			builder = Response.ok(response);
		} else {
			builder = Response.status(Status.BAD_REQUEST);
			builder.entity("{\"error\":\"Invalid JSON input\"}");
		}
		return builder.build();
	}
	
	@GET
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getAuthor() {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		Author author = factory.createAuthor();
		author.setFirstName("Gourav");
		author.setLastName("Shenoy");
		
		builder = Response.ok(author);
		return builder.build();
	}
	
	@POST
	@Produces({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	@Consumes({MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML})
	public Response getBook(Author authorIp) {
		ResponseBuilder builder = null;
		
		System.out.println(authorIp);
		
		ObjectFactory factory = new ObjectFactory();
		Book book = factory.createBook();
		book.setAuthor(authorIp);
		book.setPages(569);
		book.setTitle("Living your Dream... A Journey!");
		book.setPublicationDate(APIUtil.getXMLGregorianCalendar());
		
		builder = Response.ok(book);
		return builder.build();
	}
}
