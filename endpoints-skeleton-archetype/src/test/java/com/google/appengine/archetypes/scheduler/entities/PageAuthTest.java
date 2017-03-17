package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import java.util.Set;

public class PageAuthTest {
	
	//Data for Page Auth class fields
	private long PAGEAUTHID = 22569;
	private AdminClearances CLEARANCE = AdminClearances.admin;
	private Set<Integer> VIEWONLY = null;
	private Set<Integer> VIEWANDEDIT = null;
	
	private PageAuth pageauth;
    
    private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 pageauth = new PageAuth(PAGEAUTHID,CLEARANCE);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }
    
    @Test
    public void testGetters() throws Exception{
    	assertEquals(PAGEAUTHID,pageauth.getPageAuthId());
    	assertEquals(CLEARANCE,pageauth.getClearance());
    }
}
