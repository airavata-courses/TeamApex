package edu.sga.apex.rest.resource;

import java.net.URI;
import java.util.LinkedList;
import java.util.List;

import javax.persistence.EntityExistsException;
import javax.servlet.http.HttpSession;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.ResponseBuilder;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.core.SecurityContext;

import edu.sga.apex.dao.EntityDAO;
import edu.sga.apex.dao.impl.EntityDAOImpl;
import edu.sga.apex.entity.Experiment;
import edu.sga.apex.entity.Role;
import edu.sga.apex.rest.jaxb.ExperimentListResponse;
import edu.sga.apex.rest.jaxb.ObjectFactory;
import edu.sga.apex.rest.jaxb.SimpleAPIResponse;
import edu.sga.apex.rest.jaxb.User;
import edu.sga.apex.rest.util.APIUtil;
import edu.sga.apex.rest.util.ExceptionUtil;
import edu.sga.apex.rest.util.JAXBManager;
import edu.sga.apex.util.ExperimentDAOUtil;
import edu.sga.apex.util.UserDAOUtil;
import edu.sga.apex.util.UserRoles;

/**
 * The Class UserResource.
 * 
 * @author Gourav Shenoy
 */
@Path("user")
public class UserResource {
	
	/** The context. */
	@Context
	private SecurityContext context;
	
	@Context
	private HttpSession session;

	/* 
	 * API Request JSON
	    {
	        "user":
	        {
	            "userName": "admin",
	            "password": "apex123"
	        }
	    }
	 * 
	 * */
	/**
	 * Register user.
	 *
	 * @param user the user
	 * @return the response
	 */
	@POST
	@Path("register")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	@Consumes({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response registerUser(User user) {
		ResponseBuilder builder = null;
		ObjectFactory factory = new ObjectFactory();
		try {	
			/* validate user jaxb */
			APIUtil.validateJAXB(user);
			
			/* check if user already exists */
			if(UserDAOUtil.isUserExists(user.getUserName())) {
				throw new EntityExistsException("User with Username: [" + user.getUserName() + "] already exists!");
			}
			else {
				/* create a new user in db */
				edu.sga.apex.entity.User userDAO = JAXBManager.getUserDAOEntity(user);
				boolean success = UserDAOUtil.createUser(userDAO);
				if(success) {
					/* Construct response jaxb entity */
					SimpleAPIResponse response = factory.createSimpleAPIResponse();
					response.setMessage("Created new user: [" + user.getUserName() + "].");
					response.setStatus(Status.CREATED.getStatusCode());
					
					/* Build the response */
					builder = Response.ok(response);
					builder.status(Status.CREATED);
				}
				else {
					/* failed to create new user */
					throw new Exception("Failed to create new user: [" + user.getUserName() + "].");
				}
			}
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * Gets the experiments for user.
	 *
	 * @return the experiments for user
	 */
	@GET
	@Path("jobs")
	@Produces({ MediaType.APPLICATION_JSON, MediaType.APPLICATION_XML })
	public Response getExperimentsForUser() {
		ResponseBuilder builder = null;
		try {
			/* get the logged in user */
			String userName = context.getUserPrincipal().getName();
			
			/* get experiment list from db */
			List<Experiment> experimentList = ExperimentDAOUtil.getExperimentsForUser(userName);
			
			/* construct jaxb response */
			ExperimentListResponse expListResponse = JAXBManager.getExperimentListResponse(experimentList);
			
			/* Build the response */
			builder = Response.ok(expListResponse);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * User logout.
	 *
	 * @return the response
	 */
	@GET
	@Path("logout")
	@Produces({ MediaType.TEXT_HTML })
	public Response userLogout() {
		ResponseBuilder builder = null;
		try {
			/* Invalidate the session to logout */
			session.invalidate();
			
			/* Home page*/
			URI response = new URI("/");
			
			/* Send redirect */
			builder = Response.temporaryRedirect(response);
		} catch (Exception ex) {
			/* handle exception and return response */
			return ExceptionUtil.handleException(ex);
		}

		/* Return the response */
		return builder.build();
	}
	
	/**
	 * The main method.
	 *
	 * @param args the arguments
	 */
	public static void main(String[] args) {
		
		try {
			edu.sga.apex.entity.User user = new edu.sga.apex.entity.User();
			
			user.setPassword("rocks");
			user.setUsername("mango");
			
			List<Role> roles = new LinkedList<Role>();
			Role role = UserDAOUtil.getRoleByRoleName(UserRoles.USER.toString());
			
			roles.add(role);
			user.setRoles(roles);
			
			EntityDAO dao = new EntityDAOImpl();
			dao.saveEntity(user);
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
