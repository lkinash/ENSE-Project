package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class RemoveServiceFormTest {
	//Data for Remove Service Form class fields
	private String NAME = "Bikini laser";
	private boolean CHANGEDCLEAR = false;
	private double PRICE = 142.00;
	private long TYPEID = 5556;
	private int DEFAULTLENGTH = 5;	
	private long SERVICEID = 2236556;
	
	private RemoveServiceForm removeService;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 removeService = new RemoveServiceForm(DEFAULTLENGTH,NAME,TYPEID,PRICE,CHANGEDCLEAR,SERVICEID);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(DEFAULTLENGTH,removeService.getDefaultLength());
		assertEquals(NAME,removeService.getName());
		assertEquals(TYPEID,removeService.getTypeId());
		assertEquals(PRICE,removeService.getPrice(),0.001);
		assertEquals(CHANGEDCLEAR,removeService.getClearanceRequired());
		assertEquals(SERVICEID,removeService.getServiceId());	
	}
}
