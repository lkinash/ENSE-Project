package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class RemoveAdminFormTest {
	
	//Data for Remove Admin Form class fields
	private String PASSWORD  = "hellokitty7";
	private AdminClearances CLEARANCE = AdminClearances.admin;
	private String EMAIL = "blabjh@exam.com";
	private long ADMINID = 5556663;
	
	private RemoveAdminForm removeAdminForm;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 removeAdminForm = new RemoveAdminForm(CLEARANCE,EMAIL, ADMINID);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(ADMINID,removeAdminForm.getAdminId());
		assertEquals(CLEARANCE,removeAdminForm.getClearance());
		assertEquals(EMAIL,removeAdminForm.getEmail());
	}
}
