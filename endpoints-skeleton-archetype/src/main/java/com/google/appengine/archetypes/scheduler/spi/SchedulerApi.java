package com.google.appengine.archetypes.scheduler.spi;

import static com.google.appengine.archetypes.scheduler.service.OfyService.factory;
import static com.google.appengine.archetypes.scheduler.service.OfyService.ofy;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.joda.time.DateTime;
import org.json.JSONException;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.Event;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.Constants;
import com.google.appengine.archetypes.scheduler.ConstantsSecret;
import com.google.appengine.archetypes.scheduler.Defaults;
import com.google.appengine.archetypes.scheduler.entities.Admin;
import com.google.appengine.archetypes.scheduler.entities.Appointment;
import com.google.appengine.archetypes.scheduler.entities.Changes;
import com.google.appengine.archetypes.scheduler.entities.Clearances;
import com.google.appengine.archetypes.scheduler.entities.Client;
import com.google.appengine.archetypes.scheduler.entities.DayTimeBlocks;
import com.google.appengine.archetypes.scheduler.entities.Employee;
import com.google.appengine.archetypes.scheduler.entities.PageAuth;
import com.google.appengine.archetypes.scheduler.entities.Product;
import com.google.appengine.archetypes.scheduler.entities.Room;
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.TimeBlock;
import com.google.appengine.archetypes.scheduler.entities.Type;
import com.google.appengine.archetypes.scheduler.entities.TypeWithService;
import com.google.appengine.archetypes.scheduler.forms.AdminForm;
import com.google.appengine.archetypes.scheduler.forms.AppointmentForm;
import com.google.appengine.archetypes.scheduler.forms.CancelAppointmentForm;
import com.google.appengine.archetypes.scheduler.forms.ClientForm;
import com.google.appengine.archetypes.scheduler.forms.DayTimeBlocksForm;
import com.google.appengine.archetypes.scheduler.forms.EmployeeForm;
import com.google.appengine.archetypes.scheduler.forms.EventCreatorForm;
import com.google.appengine.archetypes.scheduler.forms.EventForm;
import com.google.appengine.archetypes.scheduler.forms.FindAppointmentForm;
import com.google.appengine.archetypes.scheduler.forms.HolidayTimeBlockListForm;
import com.google.appengine.archetypes.scheduler.forms.PageAuthForm;
import com.google.appengine.archetypes.scheduler.forms.ProductForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveAdminForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveClientForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveEmployeeForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveRoomForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveServiceForm;
import com.google.appengine.archetypes.scheduler.forms.RemoveTypeForm;
import com.google.appengine.archetypes.scheduler.forms.RoomForm;
import com.google.appengine.archetypes.scheduler.forms.ServiceForm;
import com.google.appengine.archetypes.scheduler.forms.ServiceTypeForm;
import com.google.appengine.archetypes.scheduler.forms.TimeBlockForm;
import com.google.appengine.archetypes.scheduler.forms.TimeBlockListForm;
import com.google.appengine.archetypes.scheduler.forms.TypeForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateAdminForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateAppointmentForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateClientForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateEmployeeForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateEventForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateProductForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateRoomForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateServiceForm;
import com.google.appengine.archetypes.scheduler.forms.UpdateTypeForm;
import com.google.appengine.archetypes.scheduler.forms.UserEmailForm;
import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.archetypes.scheduler.list.Status;
import com.google.appengine.archetypes.scheduler.list.WeekDay;
import com.google.appengine.archetypes.scheduler.service.DateTimeConverter;
import com.google.appengine.archetypes.scheduler.service.EventCreator;
import com.google.appengine.archetypes.scheduler.service.Quickstart;
import com.google.appengine.archetypes.scheduler.servlets.Sendgrid;
import com.google.appengine.archetypes.scheduler.wrappers.WrappedBoolean;
import com.google.appengine.archetypes.scheduler.wrappers.WrappedLongId;
import com.google.appengine.archetypes.scheduler.wrappers.WrappedStringId;
import com.google.appengine.archetypes.scheduler.wrappers.WrapperStatus;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.google.api.services.calendar.model.AclRule.Scope;
import com.google.api.services.calendar.model.Settings;
import com.google.api.services.calendar.model.AclRule;

/**
 * Defines conference APIs.
 */
@Api(	name = "scheduler", 
		version = "v1", 
		scopes = { Constants.EMAIL_SCOPE, Constants.CALENDAR_SCOPE}, 
		clientIds = { Constants.WEB_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID }, 
		description = "API ")

public class SchedulerApi {

	/**
  	 * Description of the method addEmployee.
  	 * @param admin 
  	 * @param employeeForm 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
  	 */
	
	@ApiMethod(name = "admin.addEmployee", path = "admin.addEmployee", httpMethod = "post")
  	public Employee addEmployee(final User user, EmployeeForm employeeForm) throws UnauthorizedException, IOException, GeneralSecurityException{
		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		String userId = null;
		
        
        final Key<Employee> employeeKey = factory().allocateId(Employee.class);
        final long employeeId = employeeKey.getId();
   
	
        List<Long> timeHolidayBlockLong = new ArrayList<Long>();
        		
        
        timeHolidayBlockLong = addHolidayTimeBlocks(user, employeeForm.getHolidayTimeBlockListForm());
        
        
        List<Long> timeBlockLong = new ArrayList<Long>();
        
        timeBlockLong = addTimeBlocks(user, employeeForm.getTimeBlockListForm());
        
        
        String calendarId = createCalendar(user, employeeForm.getFirstName()).getId();     
        
        //employee must have a name, email and a password set
        
  		Employee employee  = new Employee(calendarId, employeeForm.getFirstName(), employeeForm.getLastName(), userId, employeeForm.getEmail(),  
  				employeeForm.getServiceIds(), employeeId, timeHolidayBlockLong, timeBlockLong);
  			

  		ofy().save().entities(employee).now();
  		
  		
  		String change = "Add Employee. Employee Id: " + employeeId;
  		addChange(user, user.getUserId(), change);
  		
  		
		return employee;
  	}

	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 * @throws IOException 
  	 */
     
   	@ApiMethod(name = "admin.addHolidayTimeBlocks", path = "admin.addHolidayTimeBlocks", httpMethod = "post")
  	public List<Long> addHolidayTimeBlocks(final User user, HolidayTimeBlockListForm holidayTimeBlockListForm) throws UnauthorizedException, IOException {
   		
   		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        List<Long> list = new ArrayList<Long>();
        
        
        List<TimeBlockForm> timeBlockForms = new ArrayList<TimeBlockForm>();
        		
        if(holidayTimeBlockListForm != null){
        	
        	timeBlockForms.addAll(holidayTimeBlockListForm.getTimeBlockList()); 
        
        	for(TimeBlockForm tempForm: timeBlockForms){

        		final Key<TimeBlock> timeBlockKey = factory().allocateId(TimeBlock.class);
        		final long Id = timeBlockKey.getId();
        	
        		list.add(Id);
        
        		TimeBlock timeBlock = new TimeBlock(Id, tempForm.getYear(), tempForm.getMonth(), tempForm.getDay());
        
        		ofy().save().entities(timeBlock).now(); 
   		
        	}
		
        	return list;
        }
        else 
        	return null;
        
  	}
   	
   	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 * @throws IOException 
  	 */
     
   	@ApiMethod(name = "admin.addTimeBlocks", path = "admin.addTimeBlocks", httpMethod = "post")
  	public List<Long> addTimeBlocks(final User user, TimeBlockListForm timeBlockListForm) throws UnauthorizedException, IOException {
   		
   		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        List<Long> list = new ArrayList<Long>();
        
        
        List<DayTimeBlocksForm> timeBlock = new ArrayList<DayTimeBlocksForm>();
        		
        if(timeBlockListForm != null){
 
        	timeBlock = timeBlockListForm.getTimeBlocks(); 
        
        	for(DayTimeBlocksForm tempBlock: timeBlock){
        		
        		final Key<DayTimeBlocks> timeBlockKey = factory().allocateId(DayTimeBlocks.class);
        		final long Id = timeBlockKey.getId();
        
        		list.add(Id);
        	
        		DayTimeBlocks dayTimeBlock = new DayTimeBlocks(Id, tempBlock.getWeekDay(), tempBlock.getStartHour(), tempBlock.getStartMinute(), tempBlock.getEndHour(), tempBlock.getEndMinute());
        			
        		
        		ofy().save().entities(dayTimeBlock).now(); 
   		
        	}
		
        	return list;
        }
        
        else 
        	return null;
  	}
	
	
	
  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 * @throws IOException 
  	 * @throws GeneralSecurityException 
  	 */
     
   	@ApiMethod(name = "admin.addRoom", path = "admin.addRoom", httpMethod = "post")
  	public Room addRoom(final User user, RoomForm roomForm) throws UnauthorizedException, IOException, GeneralSecurityException {
   		
   		
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        

        final Key<Room> roomKey = factory().allocateId(Room.class);
        final long roomId = roomKey.getId();
        
        String calendar = createRoomCalendar(user, roomForm.getNumber()).getId();
        		       
        Room room = new Room(roomForm.getNumber(), roomForm.getServiceIds(), calendar, roomId);
    		
  		ofy().save().entities(room).now(); 
   		
  		String change = "Add Room. Room Id: " + roomId;
  		addChange(user, user.getUserId(), change);
  		
		return room;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
      
  	@ApiMethod(name = "admin.addService",  path = "admin.addService", httpMethod = "post")
 	public Service addService(final User user, ServiceForm serviceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        final Key<Service> serviceKey = factory().allocateId(Service.class);
        final long serviceId = serviceKey.getId();
        
        
        boolean requiresClearance = serviceForm.getClearanceRequired();
        
  		Service service  = new Service(serviceForm.getDefaultLength(), requiresClearance , serviceId, serviceForm.getName(), serviceForm.getTypeId(), serviceForm.getPrice());
  		
  	    ofy().save().entities(service).now();
  		
  		String change = "Add Service. Service Id: " + serviceId;
  		addChange(user, user.getUserId(), change);
  	    
		return service;
  	}
  	
	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
      
  	@ApiMethod(name = "admin.addServiceType",  path = "admin.addServiceType", httpMethod = "post")
 	public Service addServiceType(final User user, ServiceTypeForm serviceTypeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        Type type;
        
  		if(serviceTypeForm.getTypeId() == 0){
  			
  			TypeForm typeForm = new TypeForm(serviceTypeForm.getIsService(), serviceTypeForm.getTypeName());
  			type = addType(user, typeForm);
  			
  		}
  		else{
  			type = getType(user, serviceTypeForm.getTypeId());
  		}
        
        
        final Key<Service> serviceKey = factory().allocateId(Service.class);
        final long serviceId = serviceKey.getId();
        
        
        boolean requiresClearance = serviceTypeForm.getClearanceRequired();
        
  		Service service  = new Service(serviceTypeForm.getDefaultLength(), requiresClearance , serviceId, serviceTypeForm.getName(), type.getTypeId(), serviceTypeForm.getPrice());
  		
  	    ofy().save().entities(service).now();
  		
  	    
  		String change = "Add Service. Service Id: " + serviceId;
  		addChange(user, user.getUserId(), change);
  	    
		return service;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addProduct",  path = "admin.addProduct", httpMethod = "post")
 	public Product addProduct(final User user, ProductForm productForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
  			
  		String change = "Add Product. Product Id: " + productId;
  		addChange(user, user.getUserId(), change);
  	    
		return product;
  	}

  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addAdmin", path = "admin.addAdmin", httpMethod = "post")
 	public Admin addAdmin(final User user, AdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }

  		
  		String userId = null;
		
  		
        final Key<Admin> adminKey = factory().allocateId(Admin.class);
        final long adminId = adminKey.getId();
        
        
  		Admin admin  = new Admin(adminForm.getFirstName(), adminForm.getLastName(), adminForm.getClearance(), userId, adminId, adminForm.getEmail());
  			
  		ofy().save().entities(admin).now();
  		
  		String change = "Add Admin. Admin Id: " + adminId;
  		addChange(user, user.getUserId(), change);
  		
  		
		return admin;
		
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addType", path = "admin.addType", httpMethod = "post")
 	public Type addType(final User user, TypeForm typeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  	

        final Key<Type> typeKey = factory().allocateId(Type.class);
        final long typeId = typeKey.getId();
        
        Type newType = new Type(typeForm.getIsService(), typeForm.getTypeName(), typeId);
        
  		ofy().save().entities(newType).now();
  		
  		String change = "Add Type. Type Id: " + typeId;
  		addChange(user, user.getUserId(), change);
  		
  		
		return newType;
		
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.addChange", path = "admin.addChange", httpMethod = "post")
 	public Changes addChange(final User user, @Named("userId") final String userId, @Named("change") final String change ) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  	

        final Key<Changes> changeKey = factory().allocateId(Changes.class);
        final long changeId = changeKey.getId();
        
        String timeStamp = new SimpleDateFormat("yyyy.MM.dd.HH.mm.ss").format(new Date());
        
        Changes newChange = new Changes(timeStamp, userId, changeId, change, user.getEmail());
        
  		ofy().save().entities(newChange).now();
  		
		return newChange;
		
  	}
  	
  	/**
	 * Description of the method createClient.
	 * @param clientForm 
	 * @throws UnauthorizedException 
	 * @throws IOException 
  	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "client.addClient", path = "client.addClient", httpMethod = "post")
  	public Client addClient(final User user, ClientForm clientForm) throws UnauthorizedException, IOException, GeneralSecurityException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
  		String userId = null;
		
        final Key<Client> clientKey = factory().allocateId(Client.class);
        final long clientId = clientKey.getId();
       
        List<Long> newAppointmentIds = null;
        List<Long> newClearanceIds = null;
        
        String calendarId = createCalendar(user, clientForm.getEmail()).getId();
		
        int phoneNumber;
        
        if(clientForm.getPhoneNumber() < 1111111)
        	phoneNumber = 1111111;
        else 
        	phoneNumber = clientForm.getPhoneNumber();
        
        TimeBlockForm tempBlock = clientForm.getBirthday();
        
        
     	final Key<TimeBlock> timeBlockKey = factory().allocateId(TimeBlock.class);
    	final long Id = timeBlockKey.getId();

    	TimeBlock timeBlock = new TimeBlock(Id, tempBlock.getYear(), tempBlock.getMonth(), tempBlock.getDay());
    			
    	ofy().save().entities(timeBlock).now(); 
		
        
        // Client must enter first name, last name, email and a password
        
		Client client = new Client(clientForm.getFirstName(), clientForm.getLastName(),
				phoneNumber, timeBlock.getTimeBlockId(), newAppointmentIds, newClearanceIds, calendarId, userId, clientId, clientForm.getEmail());
			
  		ofy().save().entities(client).now();
        
  		String change = "Add Client. Client Id: " + clientId;
  		addChange(user, user.getUserId(), change);
  		
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
  	public Clearances addClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        

		Client client = getClient(user, clientId);

		clearance.setRenewalDate(date);
		
		client.addClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client, clearance).now();
		
  		String change = "Add Clearances. Clearance Id: " + clearance.getClearanceId();
  		addChange(user, user.getUserId(), change);
  		
		return clearance;
	}
	
	
	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	@ApiMethod(name = "admin.addClearances", path = "admin.addClearances", httpMethod = "post")
  	public WrappedBoolean addClearances(final User user) throws UnauthorizedException, IOException {
	
	       if (user == null) {
	            throw new UnauthorizedException("Authorization required");
	        }		
		
	        Query<PageAuth> query =  ofy().load().type(PageAuth.class);
	    	List<PageAuth> list = query.list();
	        
	        if(list.isEmpty()){
	        	
	        	for (AdminClearances temp : AdminClearances.values()) {
	        		addPageAuth(user, temp);
	        	}
	        	
	        	return new WrappedBoolean(false, "Created");
	        }
	        else 
	        	return new WrappedBoolean(true);
	       
	}
	
	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	 
	@ApiMethod(name = "admin.addClearancesValue", path = "admin.addClearancesValue", httpMethod = "post")
  	public PageAuth addClearancesValues(final User user, PageAuthForm pageAuthForm) throws UnauthorizedException, IOException {
	
	       if (user == null) {
	            throw new UnauthorizedException("Authorization required");
	        }		
			        

	       Query<PageAuth> query =  ofy().load().type(PageAuth.class);
	    	query = query.order("clearance");
	     	List<PageAuth> pageAuthList = query.list();

	     	for(PageAuth pageList: pageAuthList){
	     		
	     		if(pageList.getClearance().equals(pageAuthForm.getClearance())){
	     			
	     			pageList.setView(pageAuthForm.getView());
		      		pageList.setViewAndEdit(pageAuthForm.getViewAndEdit());
		      		
		      		ofy().save().entities(pageList).now();
		      		
	     			return pageList;
	     		}
	     	}
	     	
	   
	      	return null;
	       
	}

	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "appointment.addAppointment", path = "appointment.addAppointment", httpMethod = "post")
  	public Appointment addAppointment(final User user, AppointmentForm appointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  

        Key<Employee> employeeKey = Key.create(Employee.class, appointmentForm.getEmployeeId());

    	Employee employee = (Employee) ofy().load().key(employeeKey).now();

        final String calendarId = employee.getCalendarId();
        
        
        String serviceName = getService(user, appointmentForm.getServiceId()).getName();
        
        serviceName += " Appointment";
        
        int length = appointmentForm.getLength();
        
        int endHour = appointmentForm.getHour();		
        int endMinute = appointmentForm.getMinute();
        
        if((endMinute + length) >= 60){
        	endHour += (length/60);
        	endMinute += (length%60);
        }
        else{
        	endMinute += length;
        }
        
        EventForm eventForm = new EventForm(serviceName, Defaults.LOCATION, serviceName, 
        		appointmentForm.getDate().getYear(), appointmentForm.getDate().getMonth(), appointmentForm.getDate().getDay(), 
        		appointmentForm.getHour(), appointmentForm.getMinute(), appointmentForm.getDate().getYear(), 
        		appointmentForm.getDate().getMonth(), appointmentForm.getDate().getDay(), endHour, endMinute
        		);
       
       	
        
        Event event = createEvent(user, eventForm, calendarId);

        final String eventId = event.getId();
        
        
        final Key<Appointment> appointmentKey = factory().allocateId(employeeKey, Appointment.class);
        final long appointmentId = appointmentKey.getId();
        
        Appointment appointment = new Appointment(Status.booked, eventId, appointmentId, employeeKey, appointmentForm.getTypeId(), appointmentForm.getServiceId(), appointmentForm.getClientId(), appointmentForm.getRoomId());
    		
  		ofy().save().entities(appointment).now();
  		
  		
  		String change = "Add Appointment. Appointment Id: " + appointmentId;
  		addChange(user, user.getUserId(), change);
  		
		return appointment;

	}


	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "appointment.getAppointmentOptions", path = "appointment.getAppointmentOptions", httpMethod = "post")
  	public List<AppointmentForm> getAppointmentOptions(final User user, FindAppointmentForm findAppointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {
	

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        List<AppointmentForm> list = new ArrayList<AppointmentForm>();
        
        List<Room> rooms = getAllRoomsService(user, findAppointmentForm.getServiceId());
        List<Employee> employees;
        List<DayTimeBlocks> dayTimeBlocks;
        List<DayTimeBlocks> weekDayTimeBlocks;
        List<TimeBlock> holidayTimeBlocks;
        Employee employee;
        DateTime startTime;
        List<DateTime> dateList;
        int length = getService(user, findAppointmentForm.getServiceId()).getDefaultLength();
        List<DateTime> startTimes;
        long roomId = 0;
        
    
		DateTime startDate = new DateTime((findAppointmentForm.getStartDateRange().getYear()), findAppointmentForm.getStartDateRange().getMonth(), findAppointmentForm.getStartDateRange().getDay(), 0, 0);
  		DateTime endDate = new DateTime(findAppointmentForm.getEndDateRange().getYear(), findAppointmentForm.getEndDateRange().getMonth(), findAppointmentForm.getEndDateRange().getDay(), 0, 0);
  		
       
  		dateList = getDatesInRange(startDate, endDate);
  		
  	
        if(findAppointmentForm.getEmployeeId() == 0){
        	
        	return getAppointmentOptionsAnyEmployee(user, findAppointmentForm);
        	
        }
        else{
        	
        	
        	employee = getEmployee(user, findAppointmentForm.getEmployeeId());
        	dayTimeBlocks = getEmployeeTimeBlocks(user, findAppointmentForm);
        	holidayTimeBlocks = getEmployeeHolidaysInRange(user, findAppointmentForm);
        	
        	
        	for(DateTime currentDate: dateList){
        	
        		weekDayTimeBlocks = getDayTimeBlocksForWeekDay(currentDate.getDayOfWeek(), dayTimeBlocks);
 
        		for(DayTimeBlocks thisDayTimeBlock: weekDayTimeBlocks){
				
				//TODO
				//Fix to test more than one in a block
				
        			startTimes = getStartTimes(thisDayTimeBlock, length);
        		
        			for(DateTime startTimeDate: startTimes){
        			
        				if(!((testCalendarBusy(user, ConstantsSecret.masterCalendarId, length, currentDate.getYear(), currentDate.getMonthOfYear(), 
        							currentDate.getDayOfMonth(), startTimeDate.getHourOfDay(), startTimeDate.getMinuteOfHour())).getResult())){
					
        					list.add(new AppointmentForm(employee.getEmployeeId(), employee.getFirstName(), findAppointmentForm.getTypeId(), 
        							findAppointmentForm.getTypeName(), findAppointmentForm.getServiceId(), findAppointmentForm.getServiceName(), 
        							findAppointmentForm.getClientId(), roomId, startTimeDate.getHourOfDay(), startTimeDate.getMinuteOfHour(), 
        							(new TimeBlockForm(currentDate.getYear(), currentDate.getMonthOfYear(), currentDate.getDayOfMonth())), length));	
					
        					
        			
        				}
        			
        			}	
        		
	
				} 
	
        	}
        	
        }
        	
        
     
        return list;
	}
	
	

	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "appointment.getAppointmentOptionsAnyEmployee", path = "appointment.getAppointmentOptionsAnyEmployee", httpMethod = "post")
  	public List<AppointmentForm> getAppointmentOptionsAnyEmployee(final User user, FindAppointmentForm findAppointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {
	
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
		
		List<AppointmentForm> list = new ArrayList<AppointmentForm>();
        
        List<Room> rooms = getAllRoomsService(user, findAppointmentForm.getServiceId());
        List<Employee> employees;
        List<DayTimeBlocks> dayTimeBlocks;
        List<DayTimeBlocks> weekDayTimeBlocks;
        List<TimeBlock> holidayTimeBlocks;
        Employee employee;
        DateTime startTime;
        int length = getService(user, findAppointmentForm.getServiceId()).getDefaultLength();
        
    	@SuppressWarnings("deprecation")
		Date startDate = new Date(findAppointmentForm.getStartDateRange().getYear(), findAppointmentForm.getStartDateRange().getMonth(), findAppointmentForm.getStartDateRange().getDay());
  		@SuppressWarnings("deprecation")
		Date endDate = new Date(findAppointmentForm.getEndDateRange().getYear(), findAppointmentForm.getEndDateRange().getMonth(), findAppointmentForm.getEndDateRange().getDay());
  		
  		Date currentDate = startDate;
       
  		String calendarId;
  		
        if(findAppointmentForm.getEmployeeId() == 0){
        	
        	employees = getAllEmployeesService(user, findAppointmentForm.getServiceId());
        
        	
        	
        }
        
        return list;
	
	}
	
	
 	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private List<TimeBlock> getEmployeeHolidaysInRange(final User user,  FindAppointmentForm findAppointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {
  	
  		List<TimeBlock> holidays = new ArrayList<TimeBlock>();
  		Employee employee = getEmployee(user, findAppointmentForm.getEmployeeId());
  		
  		List<Long> ids = employee.getHolidayTimeBlockIds();
  		 
  		for(Long temp: ids){
  			
  			Key<TimeBlock> key = Key.create(TimeBlock.class, temp);
	    		
  			TimeBlock block = (TimeBlock) ofy().load().key(key).now();
		
  			if((inRange(findAppointmentForm.getStartDateRange(), findAppointmentForm.getEndDateRange(), block)).getResult()){
  				holidays.add(block);
  			}
  		}
  		  		
  		
  		return holidays;
  	}
	

  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private List<DayTimeBlocks> getEmployeeTimeBlocks(final User user,  FindAppointmentForm findAppointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {
  	
  		Employee employee = getEmployee(user, findAppointmentForm.getEmployeeId());
  		List<DayTimeBlocks> timeBlocks = new ArrayList<DayTimeBlocks>();
  		List<Long> ids = employee.getWeekdayTimeBlockIds();
 
  		for(Long temp: ids){
  			
  			Key<DayTimeBlocks> key = Key.create(DayTimeBlocks.class, temp);
	    		
  			DayTimeBlocks block = (DayTimeBlocks) ofy().load().key(key).now();
		
			timeBlocks.add(block);
  		}
  		
  		return timeBlocks;
  	}
  	
  	
 
  	  	
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static WrappedBoolean inRange(TimeBlockForm start, TimeBlockForm end, TimeBlock block) throws UnauthorizedException, IOException, GeneralSecurityException {
  	
		DateTime startDate = new DateTime(start.getYear(), start.getMonth(), start.getDay(), 0, 0);
  	
		DateTime endDate = new DateTime(end.getYear(), end.getMonth(), end.getDay(), 0, 0);
  		
		DateTime blockDate = new DateTime(block.getYear(), block.getMonth(), block.getDay(), 0, 0);
  		
  		return new WrappedBoolean(!(blockDate.isBefore(startDate) || blockDate.isAfter(endDate)));
  		
  	}
  	
  	
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static List<DayTimeBlocks> getDayTimeBlocksForWeekDay(@Named("weekDay") final int weekDay, List<DayTimeBlocks> timeBlocks)  {
  	
  		
  		WeekDay day;
  		
  		if(weekDay == 0){
  			day = WeekDay.SUNDAY;
  		}
  		else if(weekDay == 1){
  			day = WeekDay.MONDAY;
  		}
  		else if(weekDay == 2){
  			day = WeekDay.TUESDAY;
  		}
  		else if(weekDay == 3){
  			day = WeekDay.WEDNESDAY;
  		}
  		else if(weekDay == 4){
  			day = WeekDay.THURSDAY;
  		}
  		else if(weekDay == 5){
  			day = WeekDay.FRIDAY;
  		}
  		else{
  			day = WeekDay.SATURDAY;
  		}
  		
  		System.out.println(weekDay);
  		
  		List<DayTimeBlocks> list = new ArrayList<DayTimeBlocks>();
  		
  		for(DayTimeBlocks temp: timeBlocks){
  			
  			System.out.println(temp.getStartHour() + ":" + temp.getStartMinute());
  			
  			if(temp.getWeekDay().equals(day)){
  				list.add(temp);
  			}
  			
  		}
  		
  		
  		return list;
  	}
  	
  	
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static WrappedBoolean dateInHolidayBlock(DateTime currentDate, List<TimeBlock> holidayTimeBlocks){
  		
  		DateTime testDate;
  		
  		for(TimeBlock tempBlock: holidayTimeBlocks){
  			
  			testDate = new DateTime(tempBlock.getYear(), tempBlock.getMonth(), tempBlock.getDay(), 0, 0);
  			
  			if(testDate.equals(currentDate))
  				return new WrappedBoolean(true);
  		}
  		
  		return new WrappedBoolean(false);
  		
  	}
  	
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static WrappedBoolean testCalendarBusy(final User user, @Named("calendarId") final String calendarId, @Named("length") final int length,
  			@Named("year") final int year, @Named("month") final int month, @Named("day") final int day,
  			@Named("hour") final int hour, @Named("minute") final int minute) throws UnauthorizedException, IOException, GeneralSecurityException{
  		
  		
  		//TODO
  		
  		//Write Method
  		
  		//com.google.api.services.calendar.model.Calendar calendar = getCalendar(user, null);
  		
  		
  		
  		return new WrappedBoolean(false);
  	}
  
  
  		
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static List<DateTime> getDatesInRange(@Named("startDate") final DateTime startDate, @Named("endDate") final DateTime endDate){
  	
  		DateTime currentDate = startDate;
  		List<DateTime> dates = new ArrayList<DateTime>();
  		
  		while(!(currentDate.equals(endDate))){
  			
  			dates.add(currentDate);
  			
  			currentDate = currentDate.plusDays(1);

  		}
  		
  		return dates;
  		
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 * 
	 * 
	 */
	
  	private static List<DateTime> getStartTimes(DayTimeBlocks thisDayTimeBlock, @Named("length") final int length){
  		
  		List<DateTime> list = new ArrayList<DateTime>();
  		DateTime startTime = new DateTime(1, 1, 1, thisDayTimeBlock.getStartHour(), thisDayTimeBlock.getStartMinute());
  		DateTime endTime = new DateTime(1, 1, 1, thisDayTimeBlock.getEndHour(), thisDayTimeBlock.getEndMinute());
  		DateTime currentTime = startTime.plusMinutes(length);
  		
  		while(true){
  			
  			if(currentTime.isAfter(endTime)){
  				break;
  			}

  			list.add(currentTime);
  			currentTime = currentTime.plusMinutes(length);
  			
  		}
  		
  		
  		return list;
  		
  	}
  	
  	
  	
  	/**
	 * Description of the method updateEmployee.
	 * @param admin 
	 * @param employeeForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateEmployeeAddAllServicesOfAType", path = "admin.updateEmployeeAddAllServicesOfAType", httpMethod = "post")
	public Employee updateEmployeeAddAllServicesOfAType(final User user, UpdateEmployeeForm updateEmployeeForm) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

		 
	    Employee employee = getEmployee(user, updateEmployeeForm.getEmployeeId());
	    List<Service> services = new ArrayList<Service>();
	    List<Long> serviceIds = employee.getServiceIds();
	    List<Long> types = updateEmployeeForm.gettypeIds();
	    
	    for(Long tempType: types){
	    
	    	services = getServicesOfType(user, tempType);
	    	
	    	for(Service tempService: services){
	    		
	    		if(!serviceIds.contains(tempService.getProductId()))
	    			serviceIds.add(tempService.getProductId());
	    	}
	    }
	    
	    employee.setServiceIds(serviceIds);
	    
  		ofy().save().entities(employee).now();
	    
  		
  		String change = "Update Employee. Employee Id: " + updateEmployeeForm.getEmployeeId();
  		addChange(user, user.getUserId(), change);
  		
  		
		return employee;
	}
  	
  	/**
	 * Description of the method updateEmployee.
	 * @param admin 
	 * @param employeeForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateEmployee", path = "admin.updateEmployee", httpMethod = "post")
	public Employee updateEmployee(final User user, UpdateEmployeeForm employeeForm) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

		 
	    Employee employee = getEmployee(user, employeeForm.getEmployeeId());
	    
	    if(!(employeeForm.getFirstName() == null)){
	    	employee.setFirstName(employeeForm.getFirstName());
	    }

	    if(!(employeeForm.getLastName() == null)){
	    	employee.setLastName(employeeForm.getLastName());
	    }
	    
  		ofy().save().entities(employee).now();
	    
  		
  		String change = "Update Employee. Employee Id: " + employeeForm.getEmployeeId();
  		addChange(user, user.getUserId(), change);
  		
  		
		return employee;
	}


	
	/**
	 * Description of the method updateRoom.
	 * @param admin 
	 * @param roomForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateRoom", path = "admin.updateRoom", httpMethod = "post")
	public Room updateRoom(final User user, UpdateRoomForm roomForm) throws UnauthorizedException {
	
		
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }		

		
	    Room room = getRoom(user, roomForm.getRoomId());
	   
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
	public Service updateService(final User user, UpdateServiceForm serviceForm) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }


	    Service service = getService(user, serviceForm.getServiceId());
	    
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
		
  		String change = "Update Service. Service Id: " + serviceForm.getServiceId();
  		addChange(user, user.getUserId(), change);
  	    
  		
		return service;
	}
	

	/**
	 * Description of the method updateService.
	 * @param admin 
	 * @param serviceForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.updateProduct", path = "admin.updateProduct", httpMethod = "post")
	public Product updateProduct(final User user, UpdateProductForm productForm) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

	    Product product = getProduct(user, productForm.getProductId());
	    
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
	    
  		String change = "Update Product. Product Id: " + productForm.getProductId();
  		addChange(user, user.getUserId(), change);
  	    
  		
		return product;
	}

	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.updateAdmin", path = "admin.updateAdmin", httpMethod = "post")
 	public Admin updateAdmin(final User user, UpdateAdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
  		Admin admin = getAdmin(user, adminForm.getAdminId());
  			    
	    if(!(adminForm.getClearance() == null)){
	    	admin.setAdminClearance(adminForm.getClearance());
	    }
	    
  		ofy().save().entities(admin).now();
	   
  		String change = "Update Admin. Admin Id: " + adminForm.getAdminId();
  		addChange(user, user.getUserId(), change);
  		
  		
		return admin;
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.updateType", path = "admin.updateType", httpMethod = "post")
 	public Type updateType(final User user, UpdateTypeForm typeForm ) throws UnauthorizedException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        
  		Type type = getType(user, typeForm.getTypeId());
		    
	    if(!(typeForm.getIsService() == type.getIsService())){
	    	type.setIsService(typeForm.getIsService());
	    }
	    if(!(typeForm.getTypeName() == null)){
	    	type.setTypeName(typeForm.getTypeName());
	    }
	 
  		ofy().save().entities(type).now();
	    
  		String change = "Update Type. Type Id: " + typeForm.getTypeId();
  		addChange(user, user.getUserId(), change);
  		
  		
		return type;
		
  	}
  	
  	
  	/**
	 * Description of the method updateClient.
	 * @param clientForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "client.updateClient", path = "client.updateClient", httpMethod = "post")
  	public Client updateClient(final User user, UpdateClientForm clientForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        

	    Client client = getClient(user, clientForm.getClientId());
	    
	    if(!(clientForm.getFirstName() == null)){
	    	client.setFirstName(clientForm.getFirstName());
	    }
	    if(!(clientForm.getLastName() == null)){
	    	client.setLastName(clientForm.getLastName());
	    }
	    if(!(clientForm.getPhoneNumber() == -1)){
	    	client.setPhoneNumber(clientForm.getPhoneNumber());
	    }


	    
  		ofy().save().entities(client).now();
	    
  		String change = "Update Client. Client Id: " + clientForm.getClientId();
  		addChange(user, user.getUserId(), change);
  		
		return client;
	}
	
	
	/**
	 * Description of the method updateAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "appointment.updateAppointment", path = "appointment.updateAppointment",  httpMethod = "post")
  	public Appointment updateAppointment(final User user, UpdateAppointmentForm appointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
		
        String serviceName = getService(user, appointmentForm.getServiceId()).getName();
        
        serviceName += " Appointment";
        
        int length = appointmentForm.getLength();
        
        int endHour = appointmentForm.getHour();		
        int endMinute = appointmentForm.getMinute();
        
        if((endMinute + length) >= 60){
        	endHour += (length/60);
        	endMinute += (length%60);
        }
        else{
        	endMinute += length;
        }
        
        UpdateEventForm eventForm = new UpdateEventForm(appointmentForm.getEventId(), serviceName, Defaults.LOCATION, serviceName, 
        		appointmentForm.getDate().getYear(), appointmentForm.getDate().getMonth(), appointmentForm.getDate().getDay(), 
        		appointmentForm.getHour(), appointmentForm.getMinute(), appointmentForm.getDate().getYear(), 
        		appointmentForm.getDate().getMonth(), appointmentForm.getDate().getDay(), endHour, endMinute
        		);
        
        updateEvent(user, appointmentForm.getEventId(), eventForm);
        
        Key<Employee> employeeKey = Key.create(Employee.class, appointmentForm.getEmployeeId());
        
        Appointment appointment = new Appointment(Status.booked, appointmentForm.getEventId(), appointmentForm.getAppointmentId(), employeeKey, appointmentForm.getTypeId(), appointmentForm.getServiceId(), appointmentForm.getClientId(), appointmentForm.getRoomId());
        
        ofy().save().entities(appointment).now();
        
  		String change = "Update Appointment. Appointment Id: " + appointmentForm.getAppointmentId();
  		addChange(user, user.getUserId(), change);
  		
        
		return appointment;
	}

	

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.updateAppointmentStatus", path = "appointment.updateAppointmentStatus", httpMethod = "post")
  	public WrapperStatus updateAppointmentStatus(final User user, @Named("appointmentId") final long appointmentId,  @Named("status") final Status status) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        
        Appointment appointment = getAppointment(user, appointmentId);
        appointment.setStatus(status);
        
  		ofy().save().entities(appointment).now();

  		String change = "Update Appointment Status. Appointment Id: " + appointmentId;
  		addChange(user, user.getUserId(), change);
  		
  		
		return new WrapperStatus(status);
	}

	

  	/**
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeService", path = "admin.removeService", httpMethod = "post")
	public WrappedBoolean removeService(final User user, RemoveServiceForm removeServiceForm) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

	    
	    long serviceId = removeServiceForm.getServiceId();
	    
	    Key<Service> key = Key.create(Service.class, serviceId);
		
	    ofy().delete().key(key).now();
	    
  		String change = "Remove Service. Service Id: " + serviceId;
  		addChange(user, user.getUserId(), change);
  	    
	    
		return new WrappedBoolean(true);
	}

  	/**
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "admin.removeProduct", path = "admin.removeProduct", httpMethod = "post")
	public WrappedBoolean removeProduct(final User user, @Named("productId") final long productId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

		
	    Key<Product> key = Key.create(Product.class, productId);
		
		ofy().delete().key(key).now();
	   	    
  		String change = "Remove Product. Product Id: " + productId;
  		addChange(user, user.getUserId(), change);
  	    
		
		return new WrappedBoolean(true);
	}

	/**
	 * Description of the method removeRoom.
	 * @param admin 
	 * @param roomNumber 
	 * @throws UnauthorizedException 
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "admin.removeRoom",  path = "admin.removeRoom", httpMethod = "post")
	public WrappedBoolean removeRoom(final User user, RemoveRoomForm removeRoomForm) throws UnauthorizedException, IOException, GeneralSecurityException {
	

	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
		
	    removeRoomCalendar(user, removeRoomForm.getRoomId());
	    
	    Key<Room> key = Key.create(Room.class, removeRoomForm.getRoomId());
		
		ofy().delete().key(key).now();
	    
		return new WrappedBoolean(true);
	}

	/**
	 * Description of the method removeEmployee.
	 * @param admin 
	 * @param employeeId 
	 * @throws UnauthorizedException 
	 * @throws GeneralSecurityException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "admin.removeEmployee", path = "admin.removeEmployee", httpMethod = "post")
	public WrappedBoolean removeEmployee(final User user, RemoveEmployeeForm removeEmployeeForm) throws UnauthorizedException, IOException, GeneralSecurityException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }

		long employeeId = removeEmployeeForm.getEmployeeId();
	    
		removeEmployeeCalendar(user, employeeId);
		
	    Key<Employee> key = Key.create(Employee.class, employeeId);
	
		ofy().delete().key(key).now();
		
  		
  		String change = "Remove Employee. Employee Id: " + employeeId;
  		addChange(user, user.getUserId(), change);
  		
		
		return new WrappedBoolean(true);
	}

	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.removeAdmin",  path = "admin.removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(final User user, RemoveAdminForm removeAdminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        long adminId = removeAdminForm.getAdminId();
  		
	    Key<Admin> key = Key.create(Admin.class, adminId);
		
		ofy().delete().key(key).now();
	   
  		String change = "Remove Admin. Admin Id: " + adminId;
  		addChange(user, user.getUserId(), change);
  		
		
		return new WrappedBoolean(true);
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.removeType", path = "admin.removeType", httpMethod = "post")
 	public WrappedBoolean removeType(final User user, RemoveTypeForm removeTypeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        long typeId = removeTypeForm.getTypeId();
		
	    Key<Type> key = Key.create(Type.class, typeId);
		
		ofy().delete().key(key).now();
	   
  		String change = "Remove Type. Type Id: " + typeId;
  		addChange(user, user.getUserId(), change);
  		
		
		return new WrappedBoolean(true);
		
  	}

  	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 * @throws UnauthorizedException 
	 * @throws IOException 
  	 * @throws GeneralSecurityException 
	 */
	
	@ApiMethod(name = "appointment.removeAppointment", path = "appointment.removeAppointment", httpMethod = "post")
  	public WrappedBoolean removeAppointment(final User user, @Named("calendarId") final String calendarId, CancelAppointmentForm removeAppointmentForm) throws UnauthorizedException, IOException, GeneralSecurityException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Appointment appointment = getAppointment(user, removeAppointmentForm.getAppointmentId());
        
        deleteEvent(user, calendarId, appointment.getEventId());
    	
	    Key<Appointment> key = Key.create(Appointment.class, removeAppointmentForm.getAppointmentId());
		
  		String change = "Remove Appointment. Appointment Id: " + key.getId();
  		addChange(user, user.getUserId(), change);
	    
		ofy().delete().key(key).now();
		
		return null;
	}
	
	
	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
	 * @throws GeneralSecurityException 
	 * @throws IOException 
  	 */
  	
  	@ApiMethod(name = "client.removeClient", path = "client.removeClient", httpMethod = "post")
 	public WrappedBoolean removeClient(final User user, RemoveClientForm removeClientForm) throws UnauthorizedException, IOException, GeneralSecurityException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        long clientId = removeClientForm.getClientId();
  		
        removeClientCalendar(user, clientId);
        
	    Key<Client> key = Key.create(Client.class, clientId);
		
	    
	    
		ofy().delete().key(key).now();
	   
		String change = "Remove Client. Client Id: " + clientId;
  		addChange(user, user.getUserId(), change);
		
		return new WrappedBoolean(true);
  	}
  	
  	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "client.removeClientClearance", path = "client.removeClientClearance", httpMethod = "post")
  	public WrappedBoolean removeClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        

		Client client = getClient(user, clientId);
		
		client.removeClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client).now();
  		
  		Key<Clearances> clearanceKey = Key.create(Clearances.class, clearance.getClearanceId());
  		
  		ofy().delete().key(clearanceKey).now();
		
  		String change = "Remove Client Clearance. Client Id: " + clientId;
  		addChange(user, user.getUserId(), change);
  		
		return null;
	}
	
  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllPageAuth", path = "admin.getAllPageAuth", httpMethod = "get")
 	public List<PageAuth> getAllPageAuth(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        Query<PageAuth> query =  ofy().load().type(PageAuth.class);
    	
        return query.list();
        
  	}

	
  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllServices", path = "admin.getAllServices", httpMethod = "get")
 	public List<Service> getAllServices(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
  	
  	@ApiMethod(name = "admin.getAllEmployees", path = "admin.getAllEmployees", httpMethod = "get")
 	public List<Employee> getAllEmployees(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
            
        Query<Employee> query =  ofy().load().type(Employee.class);
    	query = query.order("firstName");
    	
        return query.list();
  		
  	}

  	

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllEmployeesWithTimeBlocks", path = "admin.getAllEmployeesWithTimeBlocks", httpMethod = "get")
 	public List<Employee> getAllEmployeesWithTimeBlocks(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        Query<Employee> query =  ofy().load().type(Employee.class);
    	query = query.order("firstName");
       
    	List<Employee> employeeList = query.list();
        
    	List<TimeBlock> holidayList;
        List<DayTimeBlocks> dayTimeList;
        
        List<Long> holidayIdList;
        List<Long> dayTimeIdList;
        
        Key<TimeBlock> holidayKey;
        TimeBlock holidayBlock;
        
        Key<DayTimeBlocks> dayTimeKey;
        DayTimeBlocks dayTimeBlock;
        
        
        for(Employee tempEmployee: employeeList){
        	
        	holidayList = new ArrayList<TimeBlock>();
        	holidayIdList = tempEmployee.getHolidayTimeBlockIds();
        	
        	if(holidayIdList != null){
        		for(Long tempLong: holidayIdList){
        			holidayKey = Key.create(TimeBlock.class, tempLong);
        			holidayBlock = (TimeBlock) ofy().load().key(holidayKey).now();
        			holidayList.add(holidayBlock);
        		}
        	
        		tempEmployee.setTimeBlocks(holidayList);
        	}
        	
        	
        	dayTimeList = new ArrayList<DayTimeBlocks>();
        	dayTimeIdList = tempEmployee.getWeekdayTimeBlockIds();
        	
        	if(dayTimeIdList != null){
        		for(Long tempLong: dayTimeIdList){
        			dayTimeKey = Key.create(DayTimeBlocks.class, tempLong);
        			dayTimeBlock = (DayTimeBlocks) ofy().load().key(dayTimeKey).now();
        			dayTimeList.add(dayTimeBlock);
        		}
        	
        		tempEmployee.setDayTimeBlocks(dayTimeList);
        	}
        	
        }
        
        return employeeList;
        
  		
  	}
  	
  	
  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllEmployeesWithTimeBlocksServices", path = "admin.getAllEmployeesWithTimeBlocksServices", httpMethod = "get")
 	public List<Employee> getAllEmployeesWithTimeBlocksServices(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        Query<Employee> query =  ofy().load().type(Employee.class);
    	query = query.order("firstName");
       
    	List<Employee> employeeList = query.list();
        
    	List<TimeBlock> holidayList;
        List<DayTimeBlocks> dayTimeList;
        List<Service> serviceList;
        
        List<Long> holidayIdList;
        List<Long> dayTimeIdList;
        List<Long> serviceIdList;
        
        Key<TimeBlock> holidayKey;
        TimeBlock holidayBlock;
        
        Key<DayTimeBlocks> dayTimeKey;
        DayTimeBlocks dayTimeBlock;
        
        Key<Service> serviceKey;
        Service service;
        
        
        for(Employee tempEmployee: employeeList){
        	
        	holidayList = new ArrayList<TimeBlock>();
        	holidayIdList = tempEmployee.getHolidayTimeBlockIds();
        	
        	if(holidayIdList != null){
        		for(Long tempLong: holidayIdList){
        			holidayKey = Key.create(TimeBlock.class, tempLong);
        			holidayBlock = (TimeBlock) ofy().load().key(holidayKey).now();
        			holidayList.add(holidayBlock);
        		}
        	
        		tempEmployee.setTimeBlocks(holidayList);
        	}
        	
        	
        	dayTimeList = new ArrayList<DayTimeBlocks>();
        	dayTimeIdList = tempEmployee.getWeekdayTimeBlockIds();
        	
        	if(dayTimeIdList != null){
        		for(Long tempLong: dayTimeIdList){
        			dayTimeKey = Key.create(DayTimeBlocks.class, tempLong);
        			dayTimeBlock = (DayTimeBlocks) ofy().load().key(dayTimeKey).now();
        			dayTimeList.add(dayTimeBlock);
        		}
        	
        		tempEmployee.setDayTimeBlocks(dayTimeList);
        	}
        	
        	serviceList = new ArrayList<Service>();
        	serviceIdList = tempEmployee.getServiceIds();
        	
        	if(serviceIdList != null){
        		for(Long tempLong: serviceIdList){
        			serviceKey = Key.create(Service.class, tempLong);
        			service = (Service) ofy().load().key(serviceKey).now();
        			serviceList.add(service);
        		}
        	
        		tempEmployee.setServiceList(serviceList);
        	}
        	
        }
        
        return employeeList;
        
  		
  	}
  	
  	
  	
  	
  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllEmployeesService", path = "admin.getAllEmployeesService", httpMethod = "get")
 	public List<Employee> getAllEmployeesService(final User user, @Named("serviceId") final long serviceId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        List<Employee> employees = getAllEmployees(user);
    	List<Employee> list = new ArrayList<Employee>();
    	List<Long> services;
   
    	
    	for(Employee temp: employees){
    		services = temp.getServiceIds();
    	
    		if(services.contains(serviceId)){
    			list.add(temp);
    		}
    	}
        
        
        return list;
  		
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllProducts", path = "admin.getAllProducts", httpMethod = "get")
 	public List<Product> getAllProducts(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
  	
  	@ApiMethod(name = "admin.getAllAdmins", path = "admin.getAllAdmins", httpMethod = "get")
 	public List<Admin> getAllAdmins(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Query<Admin> query =  ofy().load().type(Admin.class);
    	query = query.order("email");
    	
        return query.list();
        
  	}
  	
  	/**
  	 * Returns clients.
  	 * @return clients 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllClients", path = "admin.getAllClients", httpMethod = "get")
 	public List<Client> getAllClients(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Query<Client> query =  ofy().load().type(Client.class);
    	query = query.order("lastName");
    	
        return query.list();
        
  	}
  	
 	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllRooms", path = "admin.getAllRooms", httpMethod = "get")
 	public List<Room> getAllRooms(final User user) throws UnauthorizedException {

  		
  		 if (user == null) {
             throw new UnauthorizedException("Authorization required");
         }       
  
         
         Query<Room> query =  ofy().load().type(Room.class);
         query = query.order("number");
         return query.list();

  	}
  	
  	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllRoomsWithServiceNames", path = "admin.getAllRoomsWithServiceNames", httpMethod = "get")
 	public List<Room> getAllRoomsWithServiceNames(final User user) throws UnauthorizedException {

  		
  		 if (user == null) {
             throw new UnauthorizedException("Authorization required");
         }       
  
         
         Query<Room> query =  ofy().load().type(Room.class);
         List<Room> roomList = query.list();
         List<String> nameList;
         List<Long> idList;
         Key<Service> key;
         Service service;
         
         for(Room tempRoom: roomList){
         	
         	nameList = new ArrayList<String>();
         	idList = tempRoom.getServices();
         	
         	if(idList != null){
         		for(Long tempLong: idList){
         			key = Key.create(Service.class, tempLong);
         			service = (Service) ofy().load().key(key).now();
         			nameList.add(service.getName());
         		}
         	
         		tempRoom.setServiceNames(nameList);
         	}
         	
         }
         
         return roomList;
        

  	}
  	
  	
  	/**
  	 * Returns types.
  	 * @return types 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllTypes", path = "admin.getAllTypes", httpMethod = "get")
 	public List<Type> getAllTypes(final User user ) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        
  		
        Query<Type> query =  ofy().load().type(Type.class);
  
        return query.list();
  	}
  	
  	/**
  	 * Returns types.
  	 * @return types 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllTypesWithService", path = "admin.getAllTypesWithService", httpMethod = "get")
 	public List<TypeWithService> getAllTypesWithService(final User user ) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        
  		List<TypeWithService> list = new ArrayList<TypeWithService>();
        
  		Query<Type> query =  ofy().load().type(Type.class);
  
  		List<Type> typeList = query.list();
  		
  		List<Service> serviceList;
  		
  		for(Type temp: typeList){
  			
  			serviceList = getServicesOfType(user, temp.getTypeId());
  			
  			list.add(new TypeWithService(temp.getIsService(), temp.getTypeName(), temp.getTypeId(), serviceList));
  			
  		}
  		
  		return list;
  		
  	}
  	


  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getServiceEmployees", path = "admin.getServiceEmployees", httpMethod = "post")
 	public List<Employee> getServiceEmployees(final User user, RemoveServiceForm removeServiceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        List<Employee> employees = getAllEmployees(user);
    	List<Employee> list = new ArrayList<Employee>();
    	List<Long> services = new ArrayList<Long>();
   
    	
    	long Id = removeServiceForm.getServiceId();

    	for(Employee temp: employees){
    		
    		services = temp.getServiceIds();

    		if(services != null){
    			for(Long serviceTemp: services){
    		
    				if(serviceTemp.equals(Id)){
    					list.add(temp);
    				}
    			}
    		}
    	}
        
        
        return list;
  		
  	
  	}

  	
  	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getServiceRooms", path = "admin.getServiceRooms", httpMethod = "post")
 	public List<Room> getServiceRooms(final User user, RemoveServiceForm removeServiceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        List<Room> Rooms = ofy().load().type(Room.class).list();
    	List<Room> list = new ArrayList<Room>();
    	List<Long> services = new ArrayList<Long>();
   
    	
    	long Id = removeServiceForm.getServiceId();

    	for(Room temp: Rooms){
    		
    		services = temp.getServices();

    		if(services != null){
    			for(Long serviceTemp: services){
    		
    				if(serviceTemp.equals(Id)){
    					list.add(temp);
    				}
    			}
    		}
    	}
        
        
        return list;
  		
  	
  	}
  	
  	
  	
  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllRoomsService", path = "admin.getAllRoomsService", httpMethod = "get")
 	public List<Room> getAllRoomsService(final User user, @Named("serviceId") final long serviceId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }

        
        List<Room> Rooms = getAllRooms(user);
    	List<Room> list = new ArrayList<Room>();
    	List<Long> services;
    	
    	
    	for(Room temp: Rooms){
    		services = temp.getServices();

    		if(services.contains(serviceId)){
    			list.add(temp);
    		}
    	}
        
        
        return list;
  		
  	}
  	
  	/**
  	 * Returns changess.
  	 * @return changess 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getAllChanges", path = "admin.getAllChanges", httpMethod = "get")
 	public List<Changes> getAllChanges(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        
  		
        Query<Changes> query =  ofy().load().type(Changes.class);
        query = query.order("-timeStamp");
        
        return query.list();
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
 /* 	
  	@ApiMethod(name = "admin.getPageAuth", path = "admin.getPageAuth", httpMethod = "get")
<<<<<<< HEAD
 	public List<PageAuth> getPageAuth(final User user, @Named("clearence") AdminClearances clearance) throws UnauthorizedException {
=======
 	public PageAuth getPageAuth(final User user, @Named("pageAuthId") final long pageAuthId) throws UnauthorizedException {
>>>>>>> origin/master

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
  		
        Key<PageAuth> key = Key.create(PageAuth.class, pageAuthId);

    	PageAuth pageAuth = (PageAuth) ofy().load().key(key).now();
    	return pageAuth;
        
  	}
  	*/
  	
  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getServices", path = "admin.getServices", httpMethod = "get")
 	public Service getService(final User user,  @Named("serviceId") final long serviceId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
 	public Employee getEmployee(final User user, @Named("employeeId") final long employeeId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
 	public Product getProduct(final User user, @Named("productId") final long productId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
 	public Admin getAdmin(final User user, @Named("adminId") final long adminId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
	public Room getRoom(final User user, @Named("roomId") final long roomId) throws UnauthorizedException {

		
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
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
 	public Type getType(final User user, @Named("typeId") final long typeId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Key<Type> key = Key.create(Type.class, typeId);

       	Type type = (Type) ofy().load().key(key).now();
       	return type;
        
  	}
  	
  	/**
  	 * Returns pageAuths.
  	 * @return pageAuths 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getPageAuth", path = "admin.getPageAuth", httpMethod = "get")
 	public PageAuth getPageAuth(final User user, @Named("pageAuthId") final long pageAuthId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Key<PageAuth> key = Key.create(PageAuth.class, pageAuthId);

       	PageAuth pageAuth = (PageAuth) ofy().load().key(key).now();
       
       	return pageAuth;
        
  	}
  	
  	
  	/**
  	 * Returns pageAuths.
  	 * @return pageAuths 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getPageAuthByName", path = "admin.getPageAuthByName", httpMethod = "post")
 	public PageAuth getPageAuthByName(final User user, PageAuthForm pageAuthForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        Query<PageAuth> query =  ofy().load().type(PageAuth.class);
    	query = query.order("clearance");
     	List<PageAuth> pageAuthList = query.list();

     	for(PageAuth pageList: pageAuthList){
     		
     		if(pageList.getClearance().equals(pageAuthForm.getClearance()))
     			return pageList;
     		
     	}
     	
        return null;
        
  	}
  	
  	
  	@ApiMethod(name = "admin.getIsAType", path = "admin.getIsAType", httpMethod = "get")
 	public WrappedBoolean getIsAType(final User user, Type testType) throws UnauthorizedException {

       if (user == null) {
           throw new UnauthorizedException("Authorization required");
       }


       List<Type> types = getAllTypes(user);
       
       String typeName = testType.getTypeName();
       
       for (Type temp : types) {
    	   if(temp.getTypeName().equals(typeName)){
    		   return new WrappedBoolean(true);
    	   }
       }
       
       return new WrappedBoolean(false, "Type is not in the Type List.");
           
  	}
  	


	/**
  	 * Returns types.
  	 * @return types 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "admin.getServiceOfType", path = "admin.getServiceOfType", httpMethod = "get")

 	public List<Service> getServicesOfType(final User user,	@Named("typeId") final long typeId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        Query<Service> query =  ofy().load().type(Service.class);
    	query = query.order("name");

     	query = query.filter("typeId =", typeId);
	
        return query.list();
        
  	}
  	
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "appointment.getClientAppointments", path = "appointment.getClientAppointments", httpMethod = "post")
  	public List<Appointment> getClientAppointments(final User user, @Named("clientId") final long clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
  	public Appointment getAppointment(final User user, @Named("appointmentId") final long appointmentId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
  	public WrappedStringId getClientCalendarId(final User user, @Named("clientId") final long clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }

        Key<Client> key = Key.create(Client.class, clientId);

        Client client = (Client) ofy().load().key(key).now();
       	
        return new WrappedStringId(client.getCalendarId());
        
	}
  	


	
	@ApiMethod(name = "client.getClient", path = "client.getClient", httpMethod = "get")
  	public Client getClient(final User user,@Named("clientId") final long clientId) throws UnauthorizedException {
		//pass in a client ID to access a client other than the current user

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
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
 	public List<Product> getClientProducts(final User user, @Named("clientId") final long clientId) throws UnauthorizedException {
  	
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }

        
        Query<Product> query =  ofy().load().type(Product.class);
    	query = query.order("name");
    	query = query.filter("clientId =", clientId);
    	
        return query.list();

  	}

	

	@ApiMethod(name = "client.sendEmail", path = "client.sendEmail", httpMethod = "post")
  	public WrappedBoolean sendEmail(final User user,@Named("email") final String email, @Named("subject") final String subject, @Named("content") final String content) throws UnauthorizedException {
	
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
	
	@ApiMethod(name = "getIsAuthorizedView", path = "getIsAuthorizedView", httpMethod = "get")
  	public WrappedBoolean getIsAuthorizedView(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {
	
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        AdminClearances clearance = getAdminClearnces(user);
	
  		
        Query<PageAuth> query =  ofy().load().type(PageAuth.class);
    	query = query.filter("clearance =", clearance);
    	
        List<PageAuth> pageAuth = query.list();
	    
        
        if(pageAuth.get(0).canView(pageNumber)){
        	return new WrappedBoolean(true);
        }
        else
        	return new WrappedBoolean(false, "");
	}
	
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "getIsAuthorizedViewAndEdit", path = "getIsAuthorizedViewAndEdit", httpMethod = "get")
  	public WrappedBoolean getIsAuthorizedViewAndEdit(final User user, @Named("pageNumber") final int pageNumber) throws UnauthorizedException, IOException {
	
        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        
        
        AdminClearances clearance = getAdminClearnces(user);
	
  		
        Query<PageAuth> query =  ofy().load().type(PageAuth.class);
    	query = query.filter("clearance =", clearance);
    	
        List<PageAuth> pageAuth = query.list();
	    
        
        if(pageAuth.get(0).canViewAndEdit(pageNumber)){
        	return new WrappedBoolean(true);
        }
        else
        	return new WrappedBoolean(false, "");
	}
	

	
	
	
	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */

	@ApiMethod(name = "loginGetUserId", path = "loginGetUserId", httpMethod = "post")
  	public WrappedStringId loginGetUserId(final User user, UserEmailForm userEmailForm) throws IOException, UnauthorizedException, GeneralSecurityException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
	

        Query<Client> clientQuery =  ofy().load().type(Client.class);
    	clientQuery = clientQuery.filter("email =", userEmailForm.getUserEmail());
    	
        List<Client> client = clientQuery.list();
	
        if(!client.isEmpty()){
        	
        	Client clientEntity = client.get(0);
        	
        	if(clientEntity.getUserId() == null){      
        		
        		clientEntity.setUserId(user.getUserId());
        		
        		ofy().save().entities(clientEntity).now();
        		
        		return new WrappedStringId(user.getUserId());
        		
        	}
        }
        
        
        
        Query<Employee> employeeQuery =  ofy().load().type(Employee.class);
    	employeeQuery = employeeQuery.filter("email =", userEmailForm.getUserEmail());
    	
        List<Employee> employee = employeeQuery.list();
	
        if(!employee.isEmpty()){
        	
        	Employee employeeEntity = employee.get(0);
        	
        	if(employeeEntity.getUserId() == null){      
        		
        		employeeEntity.setUserId(user.getUserId());
        		
        		ofy().save().entities(employeeEntity).now();
        		
        		return new WrappedStringId(user.getUserId());
        		
        	}
        }
        
        Query<Admin> adminQuery =  ofy().load().type(Admin.class);
    	adminQuery = adminQuery.filter("email =", userEmailForm.getUserEmail());
    	
        List<Admin> admin = adminQuery.list();
	
        if(!admin.isEmpty()){
        	
        	Admin adminEntity = admin.get(0);
        	
        	if(adminEntity.getUserId() == null){      
        		
        		adminEntity.setUserId(user.getUserId());
        		
        		ofy().save().entities(adminEntity).now();
        		
        		return new WrappedStringId(user.getUserId());
        		
        	}
        }
        
        return null;
	}
	
	
	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */

	@ApiMethod(name = "appointment.test", path = "appointment.test", httpMethod = "post")
  	public Settings test(final User user) throws IOException, UnauthorizedException, GeneralSecurityException {

      //  if (user == null) {
        //    throw new UnauthorizedException("Authorization required");
       // }
        
		
		//return Quickstart.addEvent(user, ConstantsSecret.calendarId, EventCreator.createEvent());
		
		Calendar service = Quickstart.getService(user);
		
		Settings settings = service.settings().list().execute();

		//osoqisel4rd08hkiihi1d080cg@group.calendar.google.com
		
		return settings;

	}

	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
  	private static AdminClearances getAdminClearnces(final User user) throws UnauthorizedException, IOException {
  	
  		
        Query<Client> clientQuery =  ofy().load().type(Client.class);
    	clientQuery = clientQuery.filter("userId =", user.getUserId());
    	
        List<Client> client = clientQuery.list();
	
        if(!client.isEmpty()){
        	return client.get(0).getAdminClearance();
        }
  		
        
        
        
        Query<Employee> employeeQuery =  ofy().load().type(Employee.class);
    	employeeQuery = employeeQuery.filter("userId =", user.getUserId());
    	
        List<Employee> employee = employeeQuery.list();
	
        if(!employee.isEmpty()){
        	return employee.get(0).getAdminClearance();
        }
        
        
        
        
        Query<Admin> adminQuery =  ofy().load().type(Admin.class);
    	adminQuery = adminQuery.filter("userId =", user.getUserId());
    	
        List<Admin> admin = adminQuery.list();
	
        if(!admin.isEmpty()){
        	return admin.get(0).getAdminClearance();
        }
        
  		return null;
  		
  	}
	

	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
  	private static WrappedLongId addPageAuth(final User user, AdminClearances clearance) throws UnauthorizedException, IOException {


        final Key<PageAuth> key = factory().allocateId(PageAuth.class);
        final long id = key.getId();
        

        PageAuth pageAuth = new PageAuth(id, clearance);
    		
  		ofy().save().entities(pageAuth).now(); 

		return new WrappedLongId(id);
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId createCalendar(final User user, @Named("calendarName") final String calendarName) throws UnauthorizedException, IOException, GeneralSecurityException {

		Calendar service = Quickstart.getService(user);
				   
		com.google.api.services.calendar.model.Calendar calendar = new com.google.api.services.calendar.model.Calendar();
		calendar.setSummary(calendarName);
		calendar.setTimeZone("America/Regina");

		com.google.api.services.calendar.model.Calendar createdCalendar = service.calendars().insert(calendar).execute();

		AclRule rule = new AclRule();
		Scope scope = new Scope();
		scope.setType("default");
		rule.setScope(scope).setRole("reader");

		service.acl().insert(createdCalendar.getId(), rule).execute();
		
		return new WrappedStringId(createdCalendar.getId());
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId createRoomCalendar(final User user, @Named("number") final int number) throws UnauthorizedException, IOException, GeneralSecurityException {

		return (createCalendar(user, Integer.toString(number)));
  	}

	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId removeEmployeeCalendar(final User user,  @Named("employeeId") final long employeeId) throws UnauthorizedException, IOException, GeneralSecurityException {


    	Key<Employee> key = Key.create(Employee.class, employeeId);
    	Employee employee = (Employee) ofy().load().key(key).now();
  		
		return removeCalendar(user, employee.getCalendarId());
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId removeRoomCalendar(final User user,  @Named("roomId") final long roomId) throws UnauthorizedException, IOException, GeneralSecurityException {

    	Key<Room> key = Key.create(Room.class, roomId);
    	Room room = (Room) ofy().load().key(key).now();
  		
		return removeCalendar(user, room.getCalendar());
  	}
  	
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId removeClientCalendar(final User user,  @Named("clientId") final long clientId) throws UnauthorizedException, IOException, GeneralSecurityException {

    	Key<Client> key = Key.create(Client.class, clientId);
    	Client client = (Client) ofy().load().key(key).now();
  		
		return removeCalendar(user, client.getCalendarId());
  	}
  	
  	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static com.google.api.services.calendar.model.Calendar getCalendar(final User user,  @Named("calendarId") final String calendarId) throws UnauthorizedException, IOException, GeneralSecurityException {

		Calendar service = Quickstart.getService(user);
		
  		return service.calendars().get(calendarId).execute();
       
  	}
  	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
  	private static WrappedStringId removeCalendar(final User user,  @Named("roomCalendarId") final String roomCalendarId) throws UnauthorizedException, IOException, GeneralSecurityException {

		Calendar service = Quickstart.getService(user);
		
  		service.calendars().delete(roomCalendarId).execute();
        
		return new WrappedStringId(roomCalendarId);
  	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
  	private static Event createEvent(final User user, EventForm eventForm, @Named("calendarId") final String calendarId) throws UnauthorizedException, IOException, GeneralSecurityException {
        
		Calendar service = Quickstart.getService(user);
		
		EventCreatorForm eventCreatorForm = new EventCreatorForm(eventForm.getSummary(), eventForm.getLocation(), eventForm.getDescription(), 
				DateTimeConverter.convertStartDate(eventForm), DateTimeConverter.convertEndDate(eventForm) );
	    	
  		Event event = EventCreator.createEvent(eventCreatorForm);

        event = service.events().insert(calendarId, event).execute();

        event = service.events().insert(ConstantsSecret.masterCalendarId, event).execute();
		
		return event;
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	private static WrappedStringId deleteEvent(final User user, @Named("calendarId") final String calendarId, @Named("eventId") final String eventId ) throws UnauthorizedException, IOException, GeneralSecurityException {

		Calendar service = Quickstart.getService(user);
		
		service.events().delete(calendarId, eventId).execute();
		
        return new WrappedStringId(eventId);
	}
	
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 * @throws GeneralSecurityException 
	 */
	
	private static Event updateEvent(final User user, @Named("calendarId") final String calendarId, UpdateEventForm eventForm) throws UnauthorizedException, IOException, GeneralSecurityException {

        
		Calendar service = Quickstart.getService(user);
		
        
		Event event = service.events().get(calendarId, eventForm.getEventId()).execute();

		
        EventCreatorForm eventCreatorForm = new EventCreatorForm(eventForm.getSummary(), eventForm.getLocation(), eventForm.getDescription(), 
				DateTimeConverter.convertUpdateStartDate(eventForm), DateTimeConverter.convertUpdateEndDate(eventForm) );
        
        event = EventCreator.createEvent(eventCreatorForm);

        
        Event updatedEvent = service.events().update(calendarId, eventForm.getEventId(), event).execute();

	
        return updatedEvent;
	}
	
     
}
