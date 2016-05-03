package edu.sga.apex.rest.resource;

import java.io.IOException;

import javax.ws.rs.Consumes;
import javax.ws.rs.OPTIONS;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;

import org.apache.wink.common.model.multipart.InMultiPart;
import org.apache.wink.common.model.multipart.InPart;

import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SimpleAPIResponse;
import edu.sga.apex.rest.util.ExceptionUtil;
import edu.sga.apex.rest.util.FileUtil;

/**
 * The Class FileResource.
 * 
 * @author Gourav Shenoy
 */
@Path("file")
public class FileResource {

	/**
	 * Upload file.
	 *
	 * @param inMultiPart the in multi part
	 * @return the response
	 */
	@Path("upload")
	@POST
	@Consumes({ MediaType.MULTIPART_FORM_DATA })
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response uploadFile(InMultiPart inMultiPart) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		String tempFileName = null;
		
		try {
			/* read each part from multipart */
			while (inMultiPart.hasNext()) {
				InPart part = inMultiPart.next();
				String fileName = null;
				/* get header to read filename */
				String[] contentDispositionHeader = part.getHeaders()
						.getFirst("Content-Disposition").split(";");

				/* fetch filename from upload */
				for (String name : contentDispositionHeader) {
					if ((name.trim().startsWith("filename"))) {
						String[] tmp = name.split("=");
						fileName = tmp[1].trim().replaceAll("\"", "");
						System.out.println(fileName);
					}
				}

				/* save uploaded file in temp */
				tempFileName = FileUtil.createTempFile(part.getInputStream(),
						fileName);
				
				/* temp-file null check */
				if(tempFileName == null) {
					throw new IOException("Failed to create temp file!");
				}
				
				/* Construct response jaxb entity */
				SimpleAPIResponse response = factory.createSimpleAPIResponse();
				response.setMessage("File Upload Succeeded!");
				response.setContentUrl(tempFileName);
				response.setStatus(Status.ACCEPTED.getStatusCode());
				
				/* Build the response */
				builder = Response.ok(response);
				builder.status(Status.OK);
			}
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
