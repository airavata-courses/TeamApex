package edu.sga.apex.rest.util;

import java.text.MessageFormat;
import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import edu.sga.apex.rest.jaxb.User;

/**
 * The Class APIUtil.
 * 
 * @author Gourav Shenoy
 */
public class APIUtil {
	
	/** The Constant MISSING_FIELD_MSG. */
	private static final String MISSING_FIELD_MSG = "Missing field {0} in request object.";

	/**
	 * Gets the XML gregorian calendar.
	 *
	 * @return the XML gregorian calendar
	 */
	@SuppressWarnings("finally")
	public static XMLGregorianCalendar getXMLGregorianCalendar() {
		XMLGregorianCalendar date = null ;
		try {
			GregorianCalendar c = new GregorianCalendar();
			c.setTime(new Date());
			date = DatatypeFactory.newInstance().newXMLGregorianCalendar(c);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		finally {
			return date;
		}
	}

	/**
	 * Validate jaxb.
	 *
	 * @param jaxbObject the jaxb object
	 * @throws Exception the exception
	 */
	public static void validateJAXB(Object jaxbObject) throws Exception {
		if(jaxbObject instanceof User) {
			validateUserJAXB((User) jaxbObject);
		}
	}
	
	/**
	 * Validate user jaxb.
	 *
	 * @param user the user
	 * @throws Exception the exception
	 */
	private static void validateUserJAXB(User user) throws Exception {
		if(user.getUserName() == null) {
			throw new Exception(MessageFormat.format(MISSING_FIELD_MSG, "userName"));		
		}
		else if(user.getPassword() == null) {
			throw new Exception(MessageFormat.format(MISSING_FIELD_MSG, "password"));	
		}
	}
	
}
