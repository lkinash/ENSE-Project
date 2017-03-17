package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;
import org.junit.After;
import org.junit.Before;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

import java.util.List;
import java.util.Date;

/*
 * @author Archana
 * Tests for Clearances
 */
public class ClearancesTest {
	/*
	//Data for Clearances class fields
	private Service SERVICE;
	private long CLEARANCEID = 22556 ; 
	private Date RENEWALDATE = new Date(1993,6,24);
    
    
    private Clearances clearance;
    
    private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 clearance = new Clearances(SERVICE,RENEWALDATE,CLEARANCEID);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }
    
    @Test
    public void testGetters() throws Exception{
    	assertEquals(CLEARANCEID,clearance.getClearanceId());
    	assertEquals(RENEWALDATE,clearance.getRenewalDate());
    }
*/
}
