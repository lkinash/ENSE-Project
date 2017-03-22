package com.google.appengine.archetypes.scheduler.spi;

import static com.google.appengine.archetypes.scheduler.service.OfyService.ofy;
import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.Type;
import com.google.appengine.archetypes.scheduler.forms.TypeForm;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;
import com.googlecode.objectify.Key;

public class SchedulerAPITest {

	
	//Data for Room class fields
	private static final boolean REQUIRESCLEARANCE = false;
	private static final int DEFAULTLENGTH = 5;
	
	private static final long NEWPRODUCTID = 4478956;
	private static final String NEWNAME = "leg wax";
	private static final long NEWTYPEID = 3325623;
	private static final double NEWPRICE = 33.05;

	//Data for Type class fields
	private static final String TYPE = "Laser";
	private static final long TYPEID = 100000;
	private static final boolean ISSERVICE = true;
		
    private static final String EMAIL = "example@gmail.com";

    private static final String USER_ID = "123456789";
	
    private User user;
    
	private SchedulerApi schedulerApi;
    
    private Type type;
	private Service service;
	
	
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
    
    @Test(expected = UnauthorizedException.class)
    public void testGetAllAdminWithoutUser() throws Exception {
    	schedulerApi.getAllAdmins(null);
    }
		
    @Test
	public void testSaveType() throws Exception{
    	
    	type = schedulerApi.addType(user, new TypeForm(true, TYPE));
    	
    	long typeId = type.getTypeId();
    	
    	assertEquals(TYPE,type.getTypeName());
    	assertEquals(ISSERVICE,type.getIsService());
    	
    	// Fetch the type via Objectify.
        type = ofy().load().key(Key.create(Type.class, typeId)).now();
    	
    	assertEquals(TYPE,type.getTypeName());
    	assertEquals(ISSERVICE,type.getIsService());
    	
    }	
	
	
}
