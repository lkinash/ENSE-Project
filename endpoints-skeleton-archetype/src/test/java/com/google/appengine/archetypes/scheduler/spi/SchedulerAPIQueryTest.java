package com.google.appengine.archetypes.scheduler.spi;

import static com.google.appengine.archetypes.scheduler.service.OfyService.ofy;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.entities.Admin;
import com.google.appengine.archetypes.scheduler.entities.Changes;
import com.google.appengine.archetypes.scheduler.entities.Client;
import com.google.appengine.archetypes.scheduler.entities.Employee;
import com.google.appengine.archetypes.scheduler.entities.PageAuth;
import com.google.appengine.archetypes.scheduler.entities.Product;
import com.google.appengine.archetypes.scheduler.entities.Room;
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.Type;
import com.google.appengine.archetypes.scheduler.forms.AdminForm;
import com.google.appengine.archetypes.scheduler.forms.ClientForm;
import com.google.appengine.archetypes.scheduler.forms.DayTimeBlocksForm;
import com.google.appengine.archetypes.scheduler.forms.EmployeeForm;
import com.google.appengine.archetypes.scheduler.forms.HolidayTimeBlockListForm;
import com.google.appengine.archetypes.scheduler.forms.PageAuthForm;
import com.google.appengine.archetypes.scheduler.forms.ProductForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveAdminForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveEmployeeForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveServiceForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveTypeForm;
import com.google.appengine.archetypes.scheduler.forms.RoomForm;
import com.google.appengine.archetypes.scheduler.forms.ServiceForm;
import com.google.appengine.archetypes.scheduler.forms.TimeBlockForm;
import com.google.appengine.archetypes.scheduler.forms.TimeBlockListForm;
import com.google.appengine.archetypes.scheduler.forms.TypeForm;
import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.archetypes.scheduler.wrappers.WrappedBoolean;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class SchedulerAPIQueryTest {

	
private SchedulerApi schedulerApi;
    
	private static final String EMAIL = "example@gmail.com";

	private static final String USER_ID = "123456789";
	private static final long EMPLOYEE_ID = 5555656;
	private static final long CLIENT_ID = 6666;
	
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
	
	private static final String CALENDARID2 = "kljjfklsjdlk";
	private static final String USER_ID2 = "6565686569";
	private static final long CLIENT_ID2 = 6668856;
	private static final long PAGEAUTHID = 56565656;
	
	//Data for Employee form
	private static final AdminClearances CLEARANCE = AdminClearances.admin;
	
	//Data for Client form 1
	private static final int PHONENUM = 5335656;
	private static final String FIRSTNAME = "Phoebe";
	private static final String LASTNAME = "Halliwell";
	private static final String EMAIL1 = "ph@gmail.com";
	private static final TimeBlockForm NEWBIRTHDAY = new TimeBlockForm(05,24,1993);
	private static final long BIRTHDAYID = 5556;
	
	//Data for Client Form 2
	private static final int PHONENUM2 = 5551212;
	private static final String FIRSTNAME2 = "Pipper";
	private static final String LASTNAME2 = "Halliwell";
	private static final String EMAIL2 = "pipperh@gmail.com";
	private static final TimeBlockForm NEWBIRTHDAY2 = new TimeBlockForm(06,24,1985);
	private static final long BIRTHDAYID2 = 555356;
	
	//Data for EMPLOYEE Form 2
	private static final int EMPLOYEENUM = 5551212;
	private static final String EMPLOYEEFIRSTNAME = "Pipper";
	private static final String EMPLOYEELASTNAME = "Halliwell";
	private static final String EMPLOYEEEMAIL = "pipperh@gmail.com";

	//Data for Admin Form 1
	private static final String ADMINFIRSTNAME1 = "Prue";
	private static final String ADMINLASTNAME1 = "Halliwell";
	private static final String ADMINEMAIL1 = "prueH@gmail.com";
	private static final String ADMINUSERID1 = "565dsdsdsd";
	private static final long ADMINID1 = 555555996;
	
	//Data for Admin Form 2
	private static final String ADMINFIRSTNAME2 = "Archie";
	private static final String ADMINLASTNAME2 = "Jughead";
	private static final String ADMINEMAIL2 = "AJ@gmail.com";
	private static final String ADMINUSERID2 = "899skjdks";
	private static final long ADMINID2 = 54121212;

	//Data for Product Form 1
	private static final long PRODUCTTYPEID1 = 666455232;
	private static final String PRODUCTNAME1 = "Mineral foundation";
	private static final int PRODUCTBARNUM1 = 777777;
	private static final double PRODUCTPRICE1 = 55.0;

	//Data for Product Form 2
	private static final long PRODUCTTYPEID2 = 33645213;
	private static final String PRODUCTNAME2 = "Mineral eyeshadow";
	private static final int PRODUCTBARNUM2 = 52133;
	private static final double PRODUCTPRICE2 = 25.0;
	
	//Data for Product Form 3
	private static final long PRODUCTTYPEID3 = 131345;
	private static final String PRODUCTNAME3 = "Mineral moisturizer";
	private static final int PRODUCTBARNUM3 = 1223;
	private static final double PRODUCTPRICE3 = -15.0;
	
	
	private static final String CHANGEDATA = "changed value";
	private static final String REASON = "wax no longer offered";
		
    private Type type;
	private Service service1, service2, service3;
	private Room room;
	private Employee employee1,employee2,employee3;
	private Client client1,client2;
	private Admin admin1, admin2;
	private Product product1, product2, product3;
	
	
    private TypeForm typeForm;
	private ServiceForm serviceForm1, serviceForm2, serviceForm3;
	private RoomForm roomForm;
	private EmployeeForm employeeForm;
	private HolidayTimeBlockListForm holidayTimeBlockListForm;
	private TimeBlockListForm timeBlockListForm;
	private ClientForm clientForm1, clientForm2;
	private AdminForm adminForm1, adminForm2;
	private ProductForm productForm1, productForm2, productForm3;
	private RemoveServiceForm removeServiceForm;
	private RemoveAdminForm removeAdminForm;
	private RemoveEmployeeForm removeEmployeeForm;
	private RemoveTypeForm removeTypeForm;
	private PageAuthForm pageAuthForm;
	
	private List<Long> serviceIds = new ArrayList<Long>();
	private List<Employee> employeeIDs;
	private List<TimeBlockForm> timeBlockForms;
	private List<DayTimeBlocksForm> timeBlocks;
	private List<Long> clearanceIds = new ArrayList<Long>();
	private List<Long> appointmentIds = new ArrayList<Long>();
	
	private User user;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(0));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	@Before
    public void setUp() throws Exception {
		testHelper.setUp();
        user = new User(EMAIL, "gmail.com", USER_ID);
        schedulerApi = new SchedulerApi();
        
        //Create a form for type & create a new type
        typeForm = new TypeForm(ISSERVICE, TYPE);
        type = new Type(typeForm.getIsService(), typeForm.getTypeName(), NEWTYPEID);
 
        //Create a form for services
        serviceForm1 = new ServiceForm(DEFAULTLENGTH1,NEWNAME1,type.getTypeId(),NEWPRICE1, REQUIRESCLEARANCE1); 
        serviceForm2 = new ServiceForm(DEFAULTLENGTH2,NEWNAME2,type.getTypeId(),NEWPRICE2, REQUIRESCLEARANCE2); 
        serviceForm3 = new ServiceForm(DEFAULTLENGTH3,NEWNAME3,type.getTypeId(),NEWPRICE3, REQUIRESCLEARANCE3); 

        //Create a number of services
        service1 = new Service(serviceForm1.getDefaultLength(), serviceForm1.getClearanceRequired(), NEWPRODUCTID1, serviceForm1.getName(), serviceForm1.getTypeId(), serviceForm1.getPrice());
        service2 = new Service(serviceForm2.getDefaultLength(), serviceForm2.getClearanceRequired(), NEWPRODUCTID2, serviceForm2.getName(), serviceForm2.getTypeId(), serviceForm2.getPrice());
        service3 = new Service(serviceForm3.getDefaultLength(), serviceForm3.getClearanceRequired(), NEWPRODUCTID3, serviceForm3.getName(), serviceForm3.getTypeId(), serviceForm3.getPrice());
        
        //Create a form for room 
        roomForm = new RoomForm(NUMBER,serviceIds);
        
        //Create new room
        room = new Room(roomForm.getNumber(), roomForm.getServiceIds(), CALENDARID, ROOMID);
        
        //Create a form for employee
        EmployeeForm employeeForm = new EmployeeForm(NEWNAME1,serviceIds,FIRSTNAME,LASTNAME,holidayTimeBlockListForm,timeBlockListForm);
        employee1 = new Employee(CALENDARID, employeeForm.getFirstName(), employeeForm.getLastName(),
        		USER_ID, employeeForm.getEmail(),serviceIds,EMPLOYEE_ID, null, null);
        

        //Create a form for clients
        clientForm1 = new ClientForm(FIRSTNAME,LASTNAME,PHONENUM,NEWBIRTHDAY,EMAIL1);
        clientForm2 = new ClientForm(FIRSTNAME2,LASTNAME2,PHONENUM2,NEWBIRTHDAY2,EMAIL2);
        
        client1 = new Client(clientForm1.getFirstName(),clientForm1.getLastName(),clientForm1.getPhoneNumber(),BIRTHDAYID,appointmentIds,
    			clearanceIds,CALENDARID,USER_ID,CLIENT_ID,clientForm1.getEmail());

        client2 = new Client(clientForm2.getFirstName(),clientForm2.getLastName(),clientForm2.getPhoneNumber(),BIRTHDAYID2,appointmentIds,
    			clearanceIds,CALENDARID2,USER_ID2,CLIENT_ID2,clientForm2.getEmail());
        
        //Create a form for Admin
        adminForm1 = new AdminForm(CLEARANCE,ADMINEMAIL1, ADMINFIRSTNAME1, ADMINLASTNAME1);
        adminForm2 = new AdminForm(CLEARANCE,ADMINEMAIL2, ADMINFIRSTNAME2, ADMINLASTNAME2);
        
        //Create new admin
        admin1 = new Admin(adminForm1.getFirstName(),adminForm1.getLastName(), adminForm1.getClearance(),ADMINUSERID1,ADMINID1,adminForm1.getEmail());
        admin2 = new Admin(adminForm2.getFirstName(),adminForm2.getLastName(), adminForm2.getClearance(),ADMINUSERID2,ADMINID2,adminForm2.getEmail());
        
        //Create a form for Product
        productForm1 = new ProductForm(PRODUCTTYPEID1,PRODUCTNAME1,PRODUCTBARNUM1,PRODUCTPRICE1);
        productForm2 = new ProductForm(PRODUCTTYPEID2,PRODUCTNAME2,PRODUCTBARNUM2,PRODUCTPRICE2);
        productForm3 = new ProductForm(PRODUCTTYPEID3,PRODUCTNAME3,PRODUCTBARNUM3,PRODUCTPRICE3);
        
        //Create new products
        product1 = new Product(productForm1.getBarcodeNumber(),PRODUCTTYPEID1,productForm1.getName(),
        		productForm1.getTypeId(),productForm1.getPrice());
        product2 = new Product(productForm2.getBarcodeNumber(),PRODUCTTYPEID2,productForm2.getName(),
        		productForm2.getTypeId(),productForm2.getPrice());
        product3= new Product(productForm3.getBarcodeNumber(),PRODUCTTYPEID3,productForm3.getName(),
        		productForm3.getTypeId(),productForm3.getPrice());
        
        //Create a form for page auth
        //pageAuthForm = new PageAuthForm()
        //AdminClearances newClearance, Set<Integer> newViewOnly, Set<Integer> newViewAndEdit       
        
        
        //Save it on the data store
        ofy().save().entities(room, employee1, type, service1, service2, service3, client1, client2,admin1,admin2,product1,
        		product2,product3).now();
        
	}

	//After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
    	 ofy().clear();
    	testHelper.tearDown();
	}
    
    /*
     * Test: Get All Services
     */
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
    
    /*
     * Test: Get All Client
     */
    @Test
    public void testAllClient() throws Exception{
    	List<Client> clients = schedulerApi.getAllClients(user);
    	assertEquals(2,clients.size());
    	assertTrue("The result should contain client1.", clients.contains(client1));
    	assertTrue("The result should contain client2.", clients.contains(client2));
    	
    	assertEquals(client1, clients.get(0));
        assertEquals(client2, clients.get(1));
    }
    
    /*
     * Test: Get All Rooms
     */
    @Test
    public void testAllRooms() throws Exception{
    	List<Room> rooms = schedulerApi.getAllRooms(user);
    	assertEquals(1,rooms.size());
    	assertTrue("The result should contain room.", rooms.contains(room));
    	
    	assertEquals(room,rooms.get(0));
    }
    
    /*
     * Test: Get All Types
     */
    @Test
    public void testAllTypes() throws Exception{
    	List<Type> types = schedulerApi.getAllTypes(user);
    	assertEquals(1,types.size());
    	assertTrue("The result should contain a type.", types.contains(type));
    	
    	assertEquals(type,types.get(0));
    }
    
    /*
     * Test: Get All Admins
     */
    @Test
    public void testAllAdmins() throws Exception{
    	List<Admin> admins = schedulerApi.getAllAdmins(user);
    	assertEquals(2,admins.size());
    	assertTrue("the result should contain admin 1.", admins.contains(admin1));
    	assertTrue("the result should contain admin 2.", admins.contains(admin2));
    	
    	assertEquals(admin1,admins.get(1));
    	assertEquals(admin2,admins.get(0));
    }
    
    /*
     * Test: Get All Products
     */
    @Test
    public void testAllProducts() throws Exception{
    	List<Product> products = schedulerApi.getAllProducts(user);
    	assertEquals(3,products.size());
    	assertTrue("the result should contain product 1.", products.contains(product1));
    	assertTrue("the result should contain product 2.", products.contains(product2));
    	assertTrue("the result should contain product 3.", products.contains(product3));
    	
    	assertEquals(product2,products.get(0));
    	assertEquals(product1,products.get(1));
    	assertEquals(product3,products.get(2));
    }
    
    /*
     * Test: Add Rooms
     *//*
    @Test
    public void testAddRooms() throws Exception{
    	Room addRoom = schedulerApi.addRoom(user, roomForm);
    	assertEquals(addRoom.getNumber(),addRoom.getNumber());
    }
    
    /*
     * Test: Add Products
     */
    @Test
    public void testAddProducts() throws Exception{
    	Product product1 = schedulerApi.getProduct(user, PRODUCTTYPEID1);
    	assertEquals(PRODUCTTYPEID1,product1.getProductId());
    	assertEquals(PRODUCTNAME1, product1.getName());
    }
    
    /*
     * Test: Add Services
     */
    @Test
    public void testAddServices() throws Exception{
    	Service service1 = schedulerApi.getService(user, NEWPRODUCTID1);
    	assertEquals(NEWPRODUCTID1,service1.getProductId());
    }
    
    /*
     * Test: Add Admin
     */
    @Test
    public void testAddAdmin() throws Exception{
    	Admin admin1 = schedulerApi.getAdmin(user, ADMINID1);
    	assertEquals(ADMINFIRSTNAME1,admin1.getFirstName());
    }
    
    /*
     * Test: Add Type
     */
    @Test
    public void testAddType() throws Exception{
    	Type type1 = schedulerApi.getType(user, NEWTYPEID);
    	assertEquals(NEWTYPEID,type1.getTypeId());
    }
    
    /*
     * Test: Add Changes
     */
    @Test
    public void testAddChange() throws Exception{
    	Changes change1 = schedulerApi.addChange(user, USER_ID,CHANGEDATA);
    	assertEquals(CHANGEDATA,change1.getChange());
    }

    /*
     * Test: Add Employees
     */
    @Test
    public void testAddEmployee() throws Exception{
    	Employee employee1 = schedulerApi.getEmployee(user, EMPLOYEE_ID);
    	assertEquals(EMPLOYEE_ID,employee1.getEmployeeId());
    }
    
    /*
     * Test: Add Service Type
     */
    @Test
    public void testAddServiceType() throws Exception{
    	Service service1 = schedulerApi.getService(user, NEWPRODUCTID1);
    	assertEquals(NEWPRODUCTID1,service1.getProductId());
    	assertEquals(NEWNAME1, service1.getName());
    }
    /*
     * Test: Remove Service
     */
    @Test
    public void testRemoveService() throws Exception{
    	removeServiceForm = new RemoveServiceForm(0, null, 0, 0.0, true, NEWPRODUCTID1);

    	WrappedBoolean service1 = schedulerApi.removeService(user, removeServiceForm);
    	assertTrue(service1.getResult());
    }
    /*
     * Test: Remove Products
     */
    @Test
    public void testRemoveProduct() throws Exception{
    	WrappedBoolean product1 = schedulerApi.removeProduct(user, PRODUCTTYPEID1);
    	assertTrue(product1.getResult());
    }
    /*
     * Test: Remove Admin
     */
    @Test
    public void testRemoveAdmin() throws Exception{
    	removeAdminForm = new RemoveAdminForm(CLEARANCE, ADMINEMAIL1, ADMINID1);
    	WrappedBoolean admin1 = schedulerApi.removeAdmin(user, removeAdminForm);
    	assertTrue(admin1.getResult());
    }
    /*
     * Test: Remove Employee
     *//*
    @Test
    public void testRemoveEmployee() throws Exception{
    	removeEmployeeForm = new RemoveEmployeeForm(null, null, null, EMPLOYEE_ID);
    	WrappedBoolean employee1 = schedulerApi.removeEmployee(user, removeEmployeeForm);
    	assertTrue(employee1.getResult());
    }
    /*
     * Test: Remove Type
     */
    @Test
    public void testRemoveType() throws Exception{
    	removeTypeForm = new RemoveTypeForm(NEWTYPEID, TYPE);
    	WrappedBoolean type1 = schedulerApi.removeType(user, removeTypeForm);
    	assertTruetypen1.getResult());
    }
}
