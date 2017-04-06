package com.google.appengine.archetypes.scheduler.service;

import static org.junit.Assert.assertEquals;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.forms.TypeForm;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.google.api.client.util.DateTime;

public class DateTimeConverterTest {

		private int year = 2017;
		private int month = 4;
	 	private int day = 28;
	 	private int hour = 9;
	 	private int minute = 0;
	
	 	DateTime dateTime = new DateTime("2017-04-28T09:00:00-06:00");
	 	
		private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));
	    
		 //Before the test is run set up the test data store helper and create a new instance of the 
		 @Before
		 public void setUp() throws Exception {
			 testHelper.setUp();
		 }

		 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
		 @After
		 public void tearDown() throws Exception {
			 testHelper.tearDown();
		 }

		@Test
		public void testGetters() throws Exception{
			assertEquals(dateTime, DateTimeConverter.convert(year, month, day, hour, minute));
		}

	
	
}

