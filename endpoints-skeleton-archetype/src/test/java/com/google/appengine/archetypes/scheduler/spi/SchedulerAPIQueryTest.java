package com.google.appengine.archetypes.scheduler.spi;

import static com.google.appengine.archetypes.scheduler.service.OfyService.ofy;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.entities.Room;
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.Type;
import com.google.appengine.archetypes.scheduler.forms.RoomForm;
import com.google.appengine.archetypes.scheduler.forms.ServiceForm;
import com.google.appengine.archetypes.scheduler.forms.TypeForm;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class SchedulerAPIQueryTest {

	
private SchedulerApi schedulerApi;
    
	private static final String EMAIL = "example@gmail.com";

	private static final String USER_ID = "123456789";
	
	private static final long NEWPRODUCTID1 = 4434556;
	private static final String NEWNAME1 = "chest";
	private static final double NEWPRICE1 = 33.05;
	
	private static final boolean REQUIRESCLEARANCE1 = false;
	private static final int DEFAULTLENGTH1 = 15;

	private static final long NEWPRODUCTID2 = 4234256;
	private static final String NEWNAME2 = "arm";
	private static final double NEWPRICE2 = 23.76;
	
	private static final boolean REQUIRESCLEARANCE2 = true;
	private static final int DEFAULTLENGTH2 = 45;
	
	private static final long NEWPRODUCTID3 = 41241246;
	private static final String NEWNAME3 = "leg";
	private static final double NEWPRICE3 = 77.55;

	private static final boolean REQUIRESCLEARANCE3 = false;
	private static final int DEFAULTLENGTH3 = 30;
	
	private static final long NEWTYPEID = 293487;
	private static final String TYPE = "Laser";
	private static final boolean ISSERVICE = true;
	
	private static final long ROOMID = 423894;
	private static final int NUMBER = 5;
	private static final String CALENDARID = "ajhfbg9823bskdf";

    private Type type;
	private Service service1, service2, service3;
	private Room room;
	
    private TypeForm typeForm;
	private ServiceForm serviceForm1, serviceForm2, serviceForm3;
	private RoomForm roomForm;
	
	private List<Long> serviceIds;
	
	private User user;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	@Before
    public void setUp() throws Exception {
		testHelper.setUp();
        user = new User(EMAIL, "gmail.com", USER_ID);
        schedulerApi = new SchedulerApi();
        
        typeForm = new TypeForm(ISSERVICE, TYPE);
        type = new Type(typeForm.getIsService(), typeForm.getTypeName(), NEWTYPEID);
 
        serviceForm1 = new ServiceForm(DEFAULTLENGTH1,NEWNAME1,type.getTypeId(),NEWPRICE1, REQUIRESCLEARANCE1); 
        serviceForm2 = new ServiceForm(DEFAULTLENGTH2,NEWNAME2,type.getTypeId(),NEWPRICE2, REQUIRESCLEARANCE2); 
        serviceForm3 = new ServiceForm(DEFAULTLENGTH3,NEWNAME3,type.getTypeId(),NEWPRICE3, REQUIRESCLEARANCE3); 

        service1 = new Service(serviceForm1.getDefaultLength(), serviceForm1.getClearanceRequired(), NEWPRODUCTID1, serviceForm1.getName(), serviceForm1.getTypeId(), serviceForm1.getPrice());
        service2 = new Service(serviceForm2.getDefaultLength(), serviceForm2.getClearanceRequired(), NEWPRODUCTID2, serviceForm2.getName(), serviceForm2.getTypeId(), serviceForm2.getPrice());
        service3 = new Service(serviceForm3.getDefaultLength(), serviceForm3.getClearanceRequired(), NEWPRODUCTID3, serviceForm3.getName(), serviceForm3.getTypeId(), serviceForm3.getPrice());
               
        roomForm = new RoomForm(NUMBER,serviceIds);
        room = new Room(roomForm.getNumber(), roomForm.getServiceIds(), CALENDARID, ROOMID);

        ofy().save().entities(room, type, service1, service2, service3).now();
        
	}

	//After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
    	 ofy().clear();
    	testHelper.tearDown();
	}
    

    @Test
    public void testAllServices() throws Exception {
    	
        List<Service> services = schedulerApi.getAllServices(user);
        assertEquals(3, services.size());
        assertTrue("The result should contain service1.", services.contains(service1));
        assertTrue("The result should contain service2.", services.contains(service2));
        assertTrue("The result should contain service3.", services.contains(service3));
    
        assertEquals(service2, services.get(0));
        assertEquals(service1, services.get(1));
        assertEquals(service3, services.get(2));
    }
	
}
