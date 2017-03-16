package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/*
 * @author Archana
 * Tests for Changes
 */
public class ChangesTest {
	
	//Data for Changes class fields
	private String TIMESTAMP = "2005-10-30 T 10:45 UTC";
	private String USERID = "478956opo";
	private long CHANGEID = 111447;
	private String CHANGE = "laser machine type has been changed";
	
	private Changes changes;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        changes = new Changes(TIMESTAMP,USERID,CHANGEID,CHANGE);
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }
	
	public void getGetters() throws Exception{
		assertEquals(TIMESTAMP,changes.getTimeStamp());
		assertEquals(USERID,changes.getUserId());
		assertEquals(CHANGEID,changes.getChangeId());
		assertEquals(CHANGE,changes.getChange());
	}

}
