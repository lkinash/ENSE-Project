package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/*
 * @author Archana
 * Tests for Clearances
 */
public class CancelAppointmentFormTest {
	
	//Data for Cancel Appointment Form class field
	private Long APPOINTMENTID = 0L;
	private String CANCELREASON = "hit by deer" ;
	
	private CancelAppointmentForm cancelAppointment;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 cancelAppointment = new CancelAppointmentForm(APPOINTMENTID,CANCELREASON);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(APPOINTMENTID,cancelAppointment.getAppointmentId());
		assertEquals(CANCELREASON,cancelAppointment.getReasonForCancellation());
	}
}
