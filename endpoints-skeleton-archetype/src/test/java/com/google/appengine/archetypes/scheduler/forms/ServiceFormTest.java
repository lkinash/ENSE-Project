package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ServiceFormTest {
	
	//Data for Service Form class fields
	private String NAME = "chemical peel";
	private boolean CLEARANCEREQ = false;
	private double PRICE = 132.00;
	private long TYPEID = 888989;
	private int DEFAULTLENGTH = 5;
	
	private ServiceForm serviceForm;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 serviceForm = new ServiceForm(DEFAULTLENGTH,NAME,TYPEID,PRICE,CLEARANCEREQ);	 
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(DEFAULTLENGTH,serviceForm.getDefaultLength());
		assertEquals(NAME,serviceForm.getName());
		assertEquals(TYPEID,serviceForm.getTypeId());
		assertEquals(PRICE,serviceForm.getPrice(),0.001);
		assertEquals(CLEARANCEREQ,serviceForm.getClearanceRequired());	
	}
}
