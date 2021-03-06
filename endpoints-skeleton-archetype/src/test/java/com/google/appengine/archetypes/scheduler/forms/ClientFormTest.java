package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/*
 * @author Archana
 * Tests for Clearances
 */
public class ClientFormTest {
	
	//Data for Client Form class fields
	private String FIRSTNAME = "Jennifer";
	private String LASTNAME = "Lopez";
	private String EMAIL = "jennyfromtheblock@hip.com";
	private long PHONENUM = 5556978;
	
	private TimeBlockForm BIRTHDAY;
	
	private AdminClearances CLEARANCE;
	private ClientForm clientForm;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 clientForm = new ClientForm(FIRSTNAME,LASTNAME,PHONENUM,BIRTHDAY,EMAIL); 
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(FIRSTNAME,clientForm.getFirstName());
		assertEquals(LASTNAME,clientForm.getLastName());
		assertEquals(PHONENUM,clientForm.getPhoneNumber());
		assertEquals(EMAIL,clientForm.getEmail());
	}
}
