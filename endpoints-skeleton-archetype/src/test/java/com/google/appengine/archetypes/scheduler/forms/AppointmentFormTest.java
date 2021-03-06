package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class AppointmentFormTest {
	//Data for Appointment Form class fields
	private long TYPEID = 5569325;
	private long SERVICEID = 777785;
	private long CLIENTID = 222236;
	private long EMPLOYEEID = 111454;
	private long ROOMID = 498535;
	private int HOUR = 12;
	private int MINUTE = 30;
	private int LENGTH = 30;
	
	private String TYPENAME = "laser";
	private String EMPLOYEENAME = "Joe";
	private String SERVICENAME = "leg";
	
	private TimeBlockForm DATE;
	
	private AppointmentForm appointment;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 appointment = new AppointmentForm(EMPLOYEEID, EMPLOYEENAME, TYPEID,TYPENAME, SERVICEID, SERVICENAME ,CLIENTID,ROOMID, HOUR, MINUTE, DATE, LENGTH);

	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		
		assertEquals(TYPEID,appointment.getTypeId());
		assertEquals(SERVICEID,appointment.getServiceId());
		assertEquals(CLIENTID,appointment.getClientId());
		assertEquals(ROOMID,appointment.getRoomId());
		assertEquals(HOUR,appointment.getHour());
		assertEquals(MINUTE,appointment.getMinute());
		assertEquals(LENGTH,appointment.getLength());
		assertEquals(EMPLOYEEID,appointment.getEmployeeId());
		
	}

}
