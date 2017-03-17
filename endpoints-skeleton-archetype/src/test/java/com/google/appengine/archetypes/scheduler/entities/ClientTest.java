package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.Date;
import java.util.List;

/*
 * @author Archana
 * Tests for Client
 */
public class ClientTest {
	
	//Data for Client class fields
	private String FIRSTNAME = "Jennifer";
	private String LASTNAME = "Anniston";
	private int PHONENUM = 5465568; //can't add the area code
	private String CALENDARID = "jhj5686";
	//private List<Long> clearanceIds;
	//private List<Long> appointmentIds;
	private Date BIRTHDAY = new Date(2005,06,19);
	private AdminClearances CLEARANCE;
	private long CLIENTID = 115587877;
	private String USERID = "kjkj589";
	
	private Client client;
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(FIRSTNAME,client.getFirstName());
		assertEquals(LASTNAME,client.getLastName());
		assertEquals(PHONENUM,client.getPhoneNumber());
		assertEquals(CALENDARID,client.getCalendarId());
		assertEquals(CLIENTID,client.getClientId());
		assertEquals(USERID,client.getUserId());
		assertEquals(BIRTHDAY,client.getBirthday());
	}
}