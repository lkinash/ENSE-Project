package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;
import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.ConstantsSecret;
import com.google.appengine.archetypes.Defaults;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Changes;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.entities.Product;
import com.google.appengine.archetypes.entities.Room;
import com.google.appengine.archetypes.entities.Service;
import com.google.appengine.archetypes.entities.Type;
import com.google.appengine.archetypes.forms.AdminForm;
import com.google.appengine.archetypes.forms.AppointmentForm;
import com.google.appengine.archetypes.forms.CancelAppointmentForm;
import com.google.appengine.archetypes.forms.ClientForm;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.appengine.archetypes.forms.EventForm;
import com.google.appengine.archetypes.forms.ProductForm;
import com.google.appengine.archetypes.forms.RoomForm;
import com.google.appengine.archetypes.forms.ServiceForm;
import com.google.appengine.archetypes.forms.TypeForm;
import com.google.appengine.archetypes.list.Status;
import com.google.appengine.archetypes.service.EventCreator;
import com.google.appengine.archetypes.service.Quickstart;
import com.google.appengine.archetypes.servlets.Sendgrid;
import com.google.appengine.archetypes.wrappers.WrappedBoolean;
import com.google.appengine.archetypes.wrappers.WrappedId;
import com.google.appengine.archetypes.wrappers.WrapperStatus;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Api(
	    name = "scheduler",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE, Constants.CALENDAR_SCOPE, Constants.CALENDAR_READONLY_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	    description = "Scheduler Application API."

	)
public class SchedulerAPI {
	
	
	/**
  	 * Description of the method addEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 */
	
	@ApiMethod(name = "admin.addEmployee", path = "admin.addEmployee", httpMethod = "post")
  	public Employee addEmployee(final User user, EmployeeForm employeeForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException{
		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        final Key<Employee> employeeKey = factory().allocateId(Employee.class);
        final long employeeId = employeeKey.getId();
   
        
        // TODO 
        // Properly declare variables based on google calendar
        //Get the calendar Id from the calendar
        
        
        String calendarId = "";     
        
        //employee must have a name, email and a password set
        
  		Employee employee  = new Employee(calendarId, employeeForm.getName(), employeeId, employeeForm.getServiceIds());
  			

  		ofy().save().entities(employee).now();
  		
		return employee;
  	}

  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 * @throws IOException 
  	 */
     
   	@ApiMethod(name = "admin.addRoom", path = "admin.addRoom", httpMethod = "post")
  	public Room addRoom(final User user, RoomForm roomForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {
   		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        

        final Key<Room> roomKey = factory().allocateId(Room.class);
        final long roomId = roomKey.getId();
        
        String calendar = createCalendar(user).getId();
        		
        List <Long> service = null;
        
        Room room = new Room(roomForm.getNumber(), service, calendar, roomId);
    		
  		ofy().save().entities(room).now(); 
   		
		return room;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
      
  	@ApiMethod(name = "admin.addService",  path = "admin.addService", httpMethod = "post")
 	public Service addService(final User user, ServiceForm serviceForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        final Key<Service> serviceKey = factory().allocateId(Service.class);
        final long serviceId = serviceKey.getId();
        
        
        boolean requiresClearance = serviceForm.getClearanceRequired();
        
  		Service service  = new Service(serviceForm.getDefaultLength(), requiresClearance , serviceId, serviceForm.getName(), serviceForm.getTypeId(), serviceForm.getPrice());
  		
  	    ofy().save().entities(service).now();
  		
		return service;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addProduct",  path = "admin.addProduct", httpMethod = "post")
 	public Product addProduct(final User user, ProductForm productForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
 		
        final Key<Product> productKey = factory().allocateId(Product.class);
        final long productId = productKey.getId();
        
        int barcode;
        
        if(productForm.getBarcodeNumber() < 1)
        	barcode = 00000;
        else 
        	barcode = productForm.getBarcodeNumber();
        	
  		Product product  = new Product(barcode , productId, productForm.getName(), productForm.getTypeId(), productForm.getPrice());
  		
  	    ofy().save().entities(product).now();
  			
		return product;
  	}

  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addAdmin", path = "admin.addAdmin", httpMethod = "post")
 	public Admin addAdmin(final User user, AdminForm adminForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        final Key<Admin> adminKey = factory().allocateId(Admin.class);
        final long adminId = adminKey.getId();
        
        
  		Admin admin  = new Admin(adminForm.getClearance(),  adminId);
  			
  		ofy().save().entities(admin).now();
  		
		return admin;
		
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addType", path = "admin.addType", httpMethod = "post")
 	public Type addType(final User user, TypeForm typeForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  	

        final Key<Type> typeKey = factory().allocateId(Type.class);
        final long typeId = typeKey.getId();
        
        Type newType = new Type(typeForm.getIsService(), typeForm.getTypeName(), typeId);
        
  		ofy().save().entities(newType).now();
  		
		return newType;
		
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addChange", path = "admin.addChange", httpMethod = "post")
 	public Changes addChange(final User user, @Named("adminId") final long adminId, @Named("change") final String change , @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  	

        final Key<Changes> changeKey = factory().allocateId(Changes.class);
        final long changeId = changeKey.getId();
        
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
        Changes newChange = new Changes(timeStamp, adminId, changeId, change);
        
  		ofy().save().entities(newChange).now();
  		
		return newChange;
		
  	}
  	
  	/**
	 * Description of the method createClient.
	 * @param clientForm 
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "client.addClient", path = "client.addClient", httpMethod = "post")
  	public Client addClient(final User user, ClientForm clientForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
		
        final Key<Client> clientKey = factory().allocateId(Client.class);
        final long clientId = clientKey.getId();
       
        List<Long> newAppointmentIds = null;
        List<Long> newClearanceIds = null;
        
        String calendarId = createCalendar(user).getId();
		
        int phoneNumber;
        Date birthday;
        
        if(clientForm.getPhoneNumber() < 1111111)
        	phoneNumber = 1111111;
        else 
        	phoneNumber = clientForm.getPhoneNumber();
        
        if(clientForm.getBirthday() == null)
        	birthday = Defaults.BIRTHDAY;
        else
        	birthday = clientForm.getBirthday();
        
        
        // Client must enter first name, last name, email and a password
        
		Client client = new Client(clientForm.getFirstName(), clientForm.getLastName(),
				phoneNumber, birthday, newAppointmentIds, newClearanceIds, calendarId, clientId);
			
  		ofy().save().entities(client).now();
        
		return client;

	}

	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "client.addClientClearance", path = "client.addClientClearance", httpMethod = "post")
  	public WrappedBoolean addClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId, pageNumber);

		clearance.setRenewalDate(date);
		
		client.addClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client, clearance).now();
		
		return null;
	}
	
	

	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "appointment.addAppointment", path = "appointment.addAppointment", httpMethod = "post")
  	public Appointment addAppointment(final User user, AppointmentForm appointmentForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  
        
        EventForm eventForm = appointmentForm.getEventForm();
        
        Key<Employee> employeeKey = Key.create(Employee.class, appointmentForm.getEmployeeId());

    	Employee employee = (Employee) ofy().load().key(employeeKey).now();

        final String calendarId = employee.getCalendarId();
        
        
        WrappedId wrappedId = createEvent(user, calendarId, eventForm);
        
        final String eventId = wrappedId.getId();
        
        
        final Key<Appointment> appointmentKey = factory().allocateId(employeeKey, Appointment.class);
        final long appointmentId = appointmentKey.getId();
        
        Appointment appointment = new Appointment(Status.booked, eventId, appointmentId, employeeKey, appointmentForm.getAppointmentType(), appointmentForm.getService());
    		
  		ofy().save().entities(appointment).now();
  		
		return appointment;

	}

	
	
  	/**
	 * Description of the method updateEmployee.
	 * @param admin 
	 * @param employeeForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateEmployee", path = "admin.updateEmployee", httpMethod = "post")
	public Employee updateEmployee(final User user, EmployeeForm employeeForm, @Named("employeeId") final long employeeId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		 
	    Employee employee = getEmployee(user, employeeId, pageNumber);
	    
	    if(!(employeeForm.getName() == null)){
	    	employee.setName(employeeForm.getName());
	    }
	    
  		ofy().save().entities(employee).now();
	    
		return employee;
	}

	/**
	 * Description of the method updateRoom.
	 * @param admin 
	 * @param roomForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateRoom", path = "admin.updateRoom", httpMethod = "post")
	public Room updateRoom(final User user, RoomForm roomForm, @Named("roomId") final int roomId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
	    
	    Room room = getRoom(user, roomId, pageNumber);
	   
	    if(!(roomForm.getNumber() == -1)){
	    	room.setNumber(roomForm.getNumber());
	    }
	    if(!(roomForm.getServiceIds() == null)){
	    	room.setServices(roomForm.getServiceIds());
	    }
	    
  		ofy().save().entities(room).now();
	    
		return room;
	}

	/**
	 * Description of the method updateService.
	 * @param admin 
	 * @param serviceForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateService", path = "admin.updateService", httpMethod = "post")
	public Service updateService(final User user, ServiceForm serviceForm, @Named("serviceId") final long serviceId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		

	    Service service = getService(user, serviceId, pageNumber);
	    
	    if(!(serviceForm.getName() == null)){
	    	service.setName(serviceForm.getName());
	    }
	    if(!(serviceForm.getClearanceRequired() == null)){
	    	service.setRequiresClearance(serviceForm.getClearanceRequired());
	    }
	    if(!(serviceForm.getTypeId() < 1)){
	    	service.setTypeId(serviceForm.getTypeId());
	    }
	    if(!(serviceForm.getDefaultLength() < 1)){
	    	service.setDefaultLength(serviceForm.getDefaultLength());
	    }
	    
	    if(!(serviceForm.getPrice() == -1)){
	    	service.setPrice(serviceForm.getPrice());
	    }
	    
  		ofy().save().entities(service).now();
		
		return service;
	}
	

	/**
	 * Description of the method updateService.
	 * @param admin 
	 * @param serviceForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateProduct", path = "admin.updateProduct", httpMethod = "post")
	public Product updateProduct(final User user, ProductForm productForm, @Named("productId") final long productId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }

	    Product product = getProduct(user, productId, pageNumber);
	    
	    if(!(productForm.getBarcodeNumber() == -1)){
	    	product.setBarcodeNumber(productForm.getBarcodeNumber());
	    }
	    if(!(productForm.getName() == null)){
	    	product.setName(productForm.getName());
	    }

	    if(!(productForm.getPrice() == -1)){
	    	product.setPrice(productForm.getPrice());
	    }
	    if(!(productForm.getTypeId() < 1)){
	    	product.setTypeId(productForm.getTypeId());
	    }
	    
  		ofy().save().entities(product).now();
	    
		return product;
	}

	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.updateAdmin", path = "admin.updateAdmin", httpMethod = "post")
 	public Admin updateAdmin(final User user, AdminForm adminForm, @Named("adminId") final long adminId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
  		Admin admin = getAdmin(user, adminId, pageNumber);
  			    
	    if(!(adminForm.getClearance() == null)){
	    	admin.setAdminClearance(adminForm.getClearance());
	    }

	    
	    // TODO
	    // Create a secure password send method
	    
	    
  		ofy().save().entities(admin).now();
	   
		return admin;
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.updateType", path = "admin.updateType", httpMethod = "post")
 	public Type updateType(final User user, TypeForm typeForm, @Named("typeId") final long typeId  , @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        
  		Type type = getType(user, typeId, pageNumber);
		    
	    if(!(typeForm.getIsService() == type.getIsService())){
	    	type.setIsService(typeForm.getIsService());
	    }
	    if(!(typeForm.getTypeName() == null)){
	    	type.setTypeName(typeForm.getTypeName());
	    }
	 
	    // TODO
	    // Create a secure password send method
	    
  		ofy().save().entities(type).now();
	    
		return type;
		
  	}
  	
  	
  	/**
	 * Description of the method updateClient.
	 * @param clientForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "client.updateClient", path = "client.updateClient", httpMethod = "post")
  	public Client updateClient(ClientForm clientForm, final User user, @Named("clientId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

	    Client client = getClient(user, clientId, pageNumber);
	    
	    if(!(clientForm.getFirstName() == null)){
	    	client.setFirstName(clientForm.getFirstName());
	    }
	    if(!(clientForm.getLastName() == null)){
	    	client.setLastName(clientForm.getLastName());
	    }
	    if(!(clientForm.getPhoneNumber() == -1)){
	    	client.setPhoneNumber(clientForm.getPhoneNumber());
	    }
	    if(!(clientForm.getBirthday() == null)){
	    	client.setBirthday(clientForm.getBirthday());
	    }

	    
  		ofy().save().entities(client).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return client;
	}
	
	
	/**
	 * Description of the method updateAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "appointment.updateAppointment", path = "appointment.updateAppointment",  httpMethod = "post")
  	public Appointment updateAppointment(final User user, @Named("clientId") final long clientId, @Named("appointmentId") final long appointmentId, AppointmentForm appointmentForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
		
        Appointment appointment = getAppointment(user, appointmentId, pageNumber);
        
        //TODO
        // Update the variables of the appointment
        //
        
        updateEvent(user, getClientCalendarId(user, clientId, pageNumber).getId(), appointmentForm.getEventForm());
        
        ofy().save().entities(appointment).now();
        
		return appointment;
	}

	

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.updateAppointmentStatus", path = "appointment.updateAppointmentStatus", httpMethod = "post")
  	public WrapperStatus updateAppointmentStatus(final User user, @Named("appointmentId") final long appointmentId,  @Named("status") final Status status, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        Appointment appointment = getAppointment(user, appointmentId, pageNumber);
        appointment.setStatus(status);
        
  		ofy().save().entities(appointment).now();

		return new WrapperStatus(status);
	}

	

  	/**
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeService", path = "admin.removeService", httpMethod = "post")
	public WrappedBoolean removeService(final User user, @Named("serviceId") final long serviceId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Service> key = Key.create(Service.class, serviceId);
		
	    ofy().delete().key(key).now();
	    
		return new WrappedBoolean(true);
	}

  	/**
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeProduct", path = "admin.removeProduct", httpMethod = "post")
	public WrappedBoolean removeProduct(final User user, @Named("productId") final long productId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Product> key = Key.create(Product.class, productId);
		
		ofy().delete().key(key).now();
	   	    
		return new WrappedBoolean(true);
	}

	/**
	 * Description of the method removeRoom.
	 * @param admin 
	 * @param roomNumber 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeRoom",  path = "admin.removeRoom", httpMethod = "post")
	public WrappedBoolean removeRoom(final User user, @Named("roomId") final int roomId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Room> key = Key.create(Room.class, roomId);
		
		ofy().delete().key(key).now();
	    
		return new WrappedBoolean(true);
	}

	/**
	 * Description of the method removeEmployee.
	 * @param admin 
	 * @param employeeId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeEmployee", path = "admin.removeEmployee", httpMethod = "post")
	public WrappedBoolean removeEmployee(final User user, @Named("employeeId") long employeeId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    
	    Key<Employee> key = Key.create(Employee.class, employeeId);
	
		ofy().delete().key(key).now();
		
		return new WrappedBoolean(true);
	}

	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.removeAdmin",  path = "admin.removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(final User user, @Named("adminId") final long adminId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
	    Key<Admin> key = Key.create(Admin.class, adminId);
		
		ofy().delete().key(key).now();
	   
		return new WrappedBoolean(true);
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.removeType", path = "admin.removeType", httpMethod = "post")
 	public WrappedBoolean removeType(final User user, @Named("typeId") final long typeId , @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		
	    Key<Type> key = Key.create(Type.class, typeId);
		
		ofy().delete().key(key).now();
	   
		return new WrappedBoolean(true);
		
  	}

  	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "appointment.removeAppointment", path = "appointment.removeAppointment", httpMethod = "post")
  	public WrappedBoolean removeAppointment(final User user, @Named("calendarId") final String calendarId, CancelAppointmentForm removeAppointmentForm, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Appointment appointment = getAppointment(user, removeAppointmentForm.getAppointmentId(), pageNumber);
        
        deleteEvent(user, calendarId, appointment.getEventId());
    	
	    Key<Appointment> key = Key.create(Appointment.class, removeAppointmentForm.getAppointmentId());
		
		ofy().delete().key(key).now();
		
		return null;
	}
	
	
	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "client.removeClient", path = "client.removeClient", httpMethod = "post")
 	public WrappedBoolean removeClient(final User user, @Named("adminId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
	    Key<Client> key = Key.create(Client.class, clientId);
		
		ofy().delete().key(key).now();
	   
		
		// TODO 
	    // Test and Set Return Value
  		
		return null;
  	}
  	
  	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "client.removeClientClearance", path = "client.removeClientClearance", httpMethod = "post")
  	public WrappedBoolean removeClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId, pageNumber);
		
		client.removeClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client).now();
  		
  		Key<Clearances> clearanceKey = Key.create(Clearances.class, clearance.getClearanceId());
  		
  		ofy().delete().key(clearanceKey).now();
		
		return null;
	}
	
  	
  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllServices", path = "admin.getAllServices", httpMethod = "get")
 	public List<Service> getAllService(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Query<Service> query =  ofy().load().type(Service.class);
    	query = query.order("name");
    	
        return query.list();
        
  	}

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllEmployee", path = "admin.getAllEmployee", httpMethod = "get")
 	public List<Employee> getAllEmployee(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Query<Employee> query =  ofy().load().type(Employee.class);
    	query = query.order("name");
    	
        return query.list();
  		
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllProducts", path = "admin.getAllProducts", httpMethod = "get")
 	public List<Product> getAllProduct(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Query<Product> query =  ofy().load().type(Product.class);
    	query = query.order("name");
    	
        return query.list();
  		
  	}

  	/**
  	 * Returns admins.
  	 * @return admins 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllAdmin", path = "admin.getAllAdmin", httpMethod = "get")
 	public List<Admin> getAllAdmin(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Query<Admin> query =  ofy().load().type(Admin.class);
    	query = query.order("email");
    	
        return query.list();
        
  	}
  	
 	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllRooms", path = "admin.getAllRooms", httpMethod = "get")
 	public List<Room> getAllRoom(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Query<Room> query =  ofy().load().type(Room.class);
  
        return query.list();
  	}
  	
 	/**
  	 * Returns changess.
  	 * @return changess 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllChanges", path = "admin.getAllChanges", httpMethod = "get")
 	public List<Changes> getAllChanges(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Query<Changes> query =  ofy().load().type(Changes.class);
  
        return query.list();
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getServices", path = "admin.getServices", httpMethod = "get")
 	public Service getService(final User user,  @Named("serviceId") final long serviceId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Key<Service> key = Key.create(Service.class, serviceId);

    	Service service = (Service) ofy().load().key(key).now();
    	return service;
  		
  	}

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getEmployee", path = "admin.getEmployee", httpMethod = "get")
 	public Employee getEmployee(final User user, @Named("employeeId") final long employeeId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Key<Employee> key = Key.create(Employee.class, employeeId);

    	Employee employee = (Employee) ofy().load().key(key).now();
    	return employee;
  		
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getProducts", path = "admin.getProducts", httpMethod = "get")
 	public Product getProduct(final User user, @Named("productId") final long productId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Key<Product> key = Key.create(Product.class, productId);

    	Product product = (Product) ofy().load().key(key).now();
    	return product;
  		
  	}

  	/**
  	 * Returns admins.
  	 * @return admins 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAdmin", path = "admin.getAdmin", httpMethod = "get")
 	public Admin getAdmin(final User user, @Named("adminId") final long adminId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Key<Admin> key = Key.create(Admin.class, adminId);

       	Admin admin = (Admin) ofy().load().key(key).now();
       	return admin;
        
  	}
  	
  	/**
	 * Returns rooms.
	 * @return rooms 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.getRoom", path = "admin.getRoom", httpMethod = "get")
	public Room getRoom(final User user, @Named("roomId") final long roomId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }       
	    if (!checkAuthorizationForPage(user, pageNumber)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }

	    Key<Room> key = Key.create(Room.class, roomId);
	    		
	    Room room = (Room) ofy().load().key(key).now();
		
		return room;
	}

	/**
  	 * Returns types.
  	 * @return types 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getType", path = "admin.getType", httpMethod = "get")
 	public Type getType(final User user, @Named("typeId") final long typeId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Key<Type> key = Key.create(Type.class, typeId);

       	Type type = (Type) ofy().load().key(key).now();
       	return type;
        
  	}
  	
  	
  	@ApiMethod(name = "admin.getIsAType", path = "admin.getIsAType", httpMethod = "get")
 	public WrappedBoolean getIsAType(final User user, Type testType, @Named("typeId") final long typeId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

       if (user == null) {
           throw new UnauthorizedException("Authorization required");
       }
       if (!checkAuthorizationForPage(user, pageNumber)) {
           throw new UnauthorizedException("Authorization level too low.");
       }

       //TODO
       // Test if a type is a type already
       
       //if(){
    	   return new WrappedBoolean(true);
       //}
       //else
    	 //  return new WrappedBoolean(false, "Type is not in the Type List.");
        
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.getClientAppointments", path = "appointment.getClientAppointments", httpMethod = "post")
  	public List<Appointment> getClientAppointments(final User user, @Named("clientId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
    	Query<Appointment> query =  ofy().load().type(Appointment.class);
    	query = query.filter("clientId =", clientId);
    	
        return query.list();
        
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.getAppointment", path = "appointment.getAppointment", httpMethod = "post")
  	public Appointment getAppointment(final User user, @Named("appointmentId") final long appointmentId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Key<Appointment> key = Key.create(Appointment.class, appointmentId);

       	Appointment appointment = (Appointment) ofy().load().key(key).now();
       	return appointment;
        
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.getClientCalendarId", path = "appointment.getClientCalendarId", httpMethod = "post")
  	public WrappedId getClientCalendarId(final User user, @Named("clientId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        Key<Client> key = Key.create(Client.class, clientId);

        Client client = (Client) ofy().load().key(key).now();
       	
        return new WrappedId(client.getCalendarId());
        
	}
  	


	
	



	
	
	
	
	@ApiMethod(name = "client.getClient", path = "client.getClient", httpMethod = "get")
  	public Client getClient(final User user,@Named("clientId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
		//pass in a client ID to access a client other than the current user

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

        Key<Client> key = null;
        
        if(clientId < 1){
        	String userId = user.getUserId();
        	key = Key.create(Client.class, userId);
        }
        else{
        	key = Key.create(Client.class, clientId);
        }
        
    	Client client = (Client) ofy().load().key(key).now();
    	return client;
	}
	
	
  	  	
	
	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "client.getClientProducts",  path = "client.getClientProducts",  httpMethod = "post")
 	public List<Product> getClientProducts(final User user, @Named("adminId") final long clientId, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
  	
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        //TODO
        //Get the products purchased by this user
        
        
        return null;
  	}

	


	


	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.findAvailableAppointmentTimes", path = "appointment.findAvailableAppointmentTimes", httpMethod = "get")
  	public Object findAvailableAppointmentTimes(AppointmentForm appointmentForm, final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user, pageNumber)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // write function
        
		
		return null;
	} 
	
	@ApiMethod(name = "client.sendEmail", path = "client.sendEmail", httpMethod = "post")
  	public WrappedBoolean sendEmail(final User user,@Named("email") final String email, @Named("subject") final String subject, @Named("content") final String content, @Named("pageNumber") final int pageNumber) throws UnauthorizedException {
	
		 try {
             // initialize Sendgrid class
             // please replace "<sendgrid_username>" and "<sendgrid_password>" with your SendGrid credentials
             Sendgrid mail = new Sendgrid(ConstantsSecret.SENDGRID_USERNAME,ConstantsSecret.SENDGRID_PASSWORD);
             // set to address, from address, subject, the html/text content and send the email 
             mail.setTo(email)
                 // update the <from_address> with your email address
                 .setFrom("<Scheduler_App>")
                 .setSubject(subject)
                 .setText(content)
                 .setHtml("")
                 .send();

             // check the response and display proper message
             if (mail.getServerResponse() == "success") {
                 return new WrappedBoolean(true);
             } else {
            	 return new WrappedBoolean(false, "Request failed  - " + mail.getServerResponse());
             }

         } catch (JSONException e) {
             e.printStackTrace();
         }
		
		return null;
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
  	private static WrappedId createCalendar(final User user) throws UnauthorizedException, IOException {

        
        //TODO
        //create a calendar
        
		return null;
  	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
  	private static WrappedId createEvent(final User user, @Named("calendarId") final String calendarId, EventForm eventForm) throws UnauthorizedException, IOException {
        
  		Event event = EventCreator.createEvent(eventForm);

        Quickstart.addEvent(calendarId, user, event);
        
        return null;
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	private static WrappedId deleteEvent(final User user, @Named("calendarId") final String calendarId, @Named("eventId") final String eventId ) throws UnauthorizedException, IOException {

		Calendar service = getCalendarService(user);
        service.events().delete(calendarId, eventId).execute();
        
        return null;
	}
	
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	private static WrappedBoolean updateEvent(final User user, @Named("calendarId") final String calendarId, EventForm eventForm) throws UnauthorizedException, IOException {

		//TODO
        
       // Event event = service.events().get("primary", "eventId").execute();

     // Make a change
   //  event.setSummary("Appointment at Somewhere");

     // Update the event
     //Event updatedEvent = service.events().update("primary", event.getId(), event).execute();

	
        return null;
	}
	
	
	private static Calendar getCalendarService(final User user) throws IOException{
		
		Calendar service = null;

		service = Quickstart.getCalendarService(user);
		
		return service;
		
	}
	
	
	private static boolean checkAuthorizationForPage(final User user, @Named("pageNumber") final int pageNumber){
  		
		
		// TODO 
        // Get the user clearances
		// Check the user clearances against the page ID
  		
	 
        
  		
  		return true;
  	}

	
	
}
