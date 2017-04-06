package com.google.appengine.archetypes.scheduler.entities;

import static com.google.appengine.archetypes.scheduler.service.OfyService.factory;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.scheduler.list.Status;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.List;

/*
 * @author Archana
 * Tests for Appointment
 */
public class AppointmentTest{
	
	//Data for Appointment class fields
    private Status STATUS = Status.booked;
	private String EVENTID = "5584712";
	private long APPOINTMENTID = 44556;
	private long TYPEID = 93485;
	private long SERVICEID = 239842;
	private long CLIENTID = 55522;
	private long ROOMID = 52932;
	private long EMPLOYEEID = 298352;
	
	private Appointment appointment;

	
	 private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 appointment = new Appointment(STATUS, EVENTID, APPOINTMENTID, SERVICEID,  TYPEID, CLIENTID, ROOMID, EMPLOYEEID);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }	
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(EVENTID,appointment.getEventId());
		assertEquals(APPOINTMENTID,appointment.getAppointmentId());
		assertEquals(CLIENTID,appointment.getClientId());
		assertEquals(STATUS, appointment.getStatus());
		assertEquals(TYPEID,appointment.getTypeId());
		assertEquals(ROOMID,appointment.getRoomId());
		assertEquals(EMPLOYEEID,appointment.getEmployeeId());
		assertEquals(SERVICEID,appointment.getServiceId());
	}
	
}