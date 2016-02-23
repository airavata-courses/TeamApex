package edu.sga.apex.rest.util;

import java.util.Date;
import java.util.GregorianCalendar;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

/**
 * The Class APIUtil.
 * 
 * @author Gourav Shenoy
 */
public class APIUtil {

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

}
