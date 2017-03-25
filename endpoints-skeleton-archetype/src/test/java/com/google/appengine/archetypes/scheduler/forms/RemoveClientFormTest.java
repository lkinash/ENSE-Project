package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import java.util.Date;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class RemoveClientFormTest {
	
	//Data for Remove Client Form class fields
	private String EMAIL = "alyssam@example.com";
	private String PASSWORD = "lalala";
	private String FIRSTNAME = "Alyssa";
	private String LASTNAME = "Milano";
	private Date BIRTHDAY = new Date(05,06,1985);
	private int PHONENUM = 3369985;
	
	private AdminClearances CLEARANCE = AdminClearances.admin;
	private long CLIENTID = 999999;
	
	private RemoveClientForm removeClientForm;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 removeClientForm = new RemoveClientForm(CLIENTID,FIRSTNAME,LASTNAME,PHONENUM,BIRTHDAY,PASSWORD,EMAIL);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(CLIENTID,removeClientForm.getClientId());
		assertEquals(FIRSTNAME,removeClientForm.getFirstName());
		assertEquals(LASTNAME,removeClientForm.getLastName());
		assertEquals(PHONENUM,removeClientForm.getPhoneNumber());
		assertEquals(BIRTHDAY,removeClientForm.getBirthday());
		assertEquals(PASSWORD,removeClientForm.getPassword());
		assertEquals(EMAIL,removeClientForm.getEmail());
	}
}
