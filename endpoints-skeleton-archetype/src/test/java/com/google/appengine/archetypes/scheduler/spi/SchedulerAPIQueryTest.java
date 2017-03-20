package com.google.appengine.archetypes.scheduler.spi;

import static com.google.appengine.archetypes.scheduler.service.OfyService.ofy;

import org.junit.After;
import org.junit.Before;

import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.Type;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class SchedulerAPIQueryTest {

	
private SchedulerApi schedulerApi;
    
	private static final String EMAIL = "example@gmail.com";

	private static final String USER_ID = "123456789";


    private Type type;
	private Service service;
    
	private User user;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	@Before
    public void setUp() throws Exception {
		testHelper.setUp();
        user = new User(EMAIL, "gmail.com", USER_ID);
        schedulerApi = new SchedulerApi();
	}

	//After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
    	 ofy().clear();
    	testHelper.tearDown();
	}
	
}
