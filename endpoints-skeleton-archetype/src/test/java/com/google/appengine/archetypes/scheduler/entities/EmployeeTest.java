package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import java.util.List;

/*
 * @author Archana
 * Tests for Employee
 */
public class EmployeeTest {
	
	//Data for Employee class fields
	private static final String CALENDARID = "12345678999";
	private static final String FIRSTNAME = "User";
	private static final String LASTNAME = "Test";
	private static final String USERID = "123458956";
	private static final String EMAIL = "example@example.com";
	private static final long EMPLOYEEID = 6665559;
	
	private Employee employee;

	
	 private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	   
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
	     employee = new Employee(CALENDARID, FIRSTNAME, LASTNAME, USERID, EMAIL, null,  EMPLOYEEID, null, null );
			
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }	
	
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(CALENDARID, employee.getCalendarId());
		assertEquals(FIRSTNAME, employee.getFirstName());
		assertEquals(LASTNAME, employee.getLastName());
		assertEquals(EMAIL,employee.getEmail());
		assertEquals(USERID, employee.getUserId());
		assertEquals(EMPLOYEEID, employee.getEmployeeId());
	}
	
}
