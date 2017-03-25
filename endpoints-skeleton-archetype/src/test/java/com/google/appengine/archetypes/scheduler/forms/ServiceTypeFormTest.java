package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ServiceTypeFormTest {
	
	//Data for Service Type Form class fields
	private String NAME = "Leg Wax";
	private boolean CLEARANCEREQ = false;
	private double PRICE = 52.35;
	private long TYPEID = 555656;
	private int DEFAULTLEN = 5;
	private String TYPENAME = "Wax";
	private boolean ISSERVICE = true;	
	
	private ServiceTypeForm serviceType;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 serviceType = new ServiceTypeForm(DEFAULTLEN,NAME,TYPEID,PRICE,CLEARANCEREQ,TYPENAME);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(DEFAULTLEN,serviceType.getDefaultLength());
		assertEquals(NAME,serviceType.getName());
		assertEquals(TYPEID,serviceType.getTypeId());
		assertEquals(PRICE,serviceType.getPrice(),0.001);
		assertEquals(CLEARANCEREQ,serviceType.getClearanceRequired());
		assertEquals(TYPENAME,serviceType.getTypeName());
	}
}
