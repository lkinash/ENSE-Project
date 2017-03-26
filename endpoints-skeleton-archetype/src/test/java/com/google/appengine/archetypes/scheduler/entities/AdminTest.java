package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import java.util.List;

/*
 * @author Archana
 * Tests for Admin
 */
public class AdminTest {
	
	//Data for Admin class fields
	private static final long ADMINID = 5599666;
	private static final String USERID = "sdds659565";
	private static final AdminClearances CLEARANCE = AdminClearances.admin;
	private static final String EMAIL = "example@example.com";
	
	private Admin admin;
	
	
	 private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 admin = new Admin(CLEARANCE, USERID,ADMINID, EMAIL);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(ADMINID,admin.getAdminId());
		assertEquals(USERID,admin.getUserId());
		assertEquals(EMAIL,admin.getEmail());
		assertEquals(CLEARANCE,admin.getAdminClearance());
	}
}
