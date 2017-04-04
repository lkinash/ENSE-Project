package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
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
	private static final String FIRSTNAME = "Jennifer";
	private static final String LASTNAME = "Anniston";
	private static final String EMAIL = "email@email.com";
	private static final long PHONENUM = 5465568; //can't add the area code
	private static final String CALENDARID = "jhj5686";
	private static final long CLIENTID = 115587877;
	private static final String USERID = "kjkj589";
	private static final AdminClearances CLEARANCE = AdminClearances.admin;
	private static final long BIRTHDAYID = 345434;
	
	private List<Long> clearanceIds;
	private List<Long> appointmentIds;
	
	private Client client;
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 client = new Client(FIRSTNAME,LASTNAME,PHONENUM,BIRTHDAYID,appointmentIds,clearanceIds,CALENDARID,USERID,CLIENTID, EMAIL);
	 }


	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(FIRSTNAME,client.getFirstName());
		assertEquals(LASTNAME,client.getLastName());
		assertEquals(PHONENUM,client.getPhoneNumber());
		assertEquals(CALENDARID,client.getCalendarId());
		assertEquals(CLIENTID,client.getClientId());
		assertEquals(USERID,client.getUserId());
		assertEquals(BIRTHDAYID,client.getBirthday());
	}
}
