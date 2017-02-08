package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;
import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.entities.Product;
import com.google.appengine.archetypes.entities.Room;
import com.google.appengine.archetypes.entities.Service;
import com.google.appengine.archetypes.entities.Type;
import com.google.appengine.archetypes.forms.AdminForm;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.appengine.archetypes.forms.ProductForm;
import com.google.appengine.archetypes.forms.RoomForm;
import com.google.appengine.archetypes.forms.ServiceForm;
import com.google.appengine.archetypes.forms.TypeForm;
import com.google.appengine.archetypes.wrappers.WrappedBoolean;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;

@Api(
	    name = "admin",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	    description = "Admin side API."
	)
public class AdminApi {

	
  	/**
  	 * Description of the method addEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 */
	
	@ApiMethod(name = "addEmployee", path = "addEmployee", httpMethod = "post")
  	public Employee addEmployee(final User user, EmployeeForm employeeForm) throws UnauthorizedException{

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        final Key<Employee> employeeKey = factory().allocateId(Employee.class);
        final long employeeId = employeeKey.getId();
   
        // TODO 
        // Properly declare variables based on google calendar
        
        String calendarId = "";
        //TODO
        //Get the calendar Id from the calendar
		
        
        
        //employee must have a name, email and a password set
        
  		Employee employee  = new Employee(calendarId, employeeForm.getName(), employeeForm.getEmail(), employeeForm.getPassword(), employeeId, employeeForm.getServiceIds());
  			

  		ofy().save().entities(employee).now();
  		
		return employee;
  	}

  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 */
     
   	@ApiMethod(name = "addRoom", path = "addRoom", httpMethod = "post")
  	public Room addRoom(final User user, RoomForm roomForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
  		
        final Key<Room> roomKey = factory().allocateId(Room.class);
        final long roomId = roomKey.getId();
        
        Calendar calendar = new Calendar(null, null, null);			
        List <Long> service;
        
        if(roomForm.getServiceIds() != null)
        	service = roomForm.getServiceIds();
        else
        	service = null;
        
        //TODO
        //create a new list of services
        
        Room room = new Room(roomForm.getRoomNumber(), roomForm.getServiceIds(), calendar, roomId);
    		
  		ofy().save().entities(room).now(); 
   		
		return room;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
      
  	@ApiMethod(name = "addService",  path = "addService", httpMethod = "post")
 	public Service addService(final User user, ServiceForm serviceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        final Key<Service> serviceKey = factory().allocateId(Service.class);
        final long serviceId = serviceKey.getId();
        
        
        boolean requiresClearance = true;
        // TODO 
        // Link to get this value from a list
        
        
  		Service service  = new Service(requiresClearance , serviceId, serviceForm.getName(), serviceForm.getTypeId(), serviceForm.getPrice());
  		
  	    ofy().save().entities(service).now();
  		
		return service;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "addProduct",  path = "addProduct", httpMethod = "post")
 	public Product addProduct(final User user, ProductForm productForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "addAdmin", path = "addAdmin", httpMethod = "post")
 	public Admin addAdmin(final User user, AdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        final Key<Admin> adminKey = factory().allocateId(Admin.class);
        final long adminId = adminKey.getId();
        
        
  		Admin admin  = new Admin(adminForm.getClearance(), adminForm.getEmail(),adminForm.getPassword(), adminId);
  			
  		ofy().save().entities(admin).now();
  		
		return admin;
		
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "addType", path = "addType", httpMethod = "post")
 	public Type addType(final User user, TypeForm typeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  	

        final Key<Type> typeKey = factory().allocateId(Type.class);
        final long typeId = typeKey.getId();
        
        Type newType = new Type(typeForm.getIsService(), typeForm.getTypeName(), typeId);
        
  		ofy().save().entities(newType).now();
  		
		return newType;
		
  	}

  	/**
	 * Description of the method updateEmployee.
	 * @param admin 
	 * @param employeeForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateEmployee", path = "updateEmployee", httpMethod = "post")
	public Employee updateEmployee(final User user, EmployeeForm employeeForm, @Named("employeeId") final long employeeId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		 
	    Employee employee = getEmployee(user, employeeId);
	    
	    //if(!(employeeForm.getCalendar() == null)){
	    	//employee.setCalendarId(employeeForm.getCalendar());
	    //}
	   
	    //TODO
	    //Fix this
	    
	    if(!(employeeForm.getName() == null)){
	    	employee.setName(employeeForm.getName());
	    }
	    
  		ofy().save().entities(employee).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return employee;
	}

	/**
	 * Description of the method updateRoom.
	 * @param admin 
	 * @param roomForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateRoom", path = "updateRoom", httpMethod = "post")
	public Room updateRoom(final User user, RoomForm roomForm, @Named("roomId") final int roomId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
	    
	    Room room = getRoom(user, roomId);
	    
	    // TODO
	    // Check what the default value will be 
	    if(!(roomForm.getRoomNumber() == -1)){
	    	room.setNumber(roomForm.getRoomNumber());
	    }
	    if(!(roomForm.getServiceIds() == null)){
	    	room.setServices(roomForm.getServiceIds());
	    }
	    
  		ofy().save().entities(room).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return room;
	}

	/**
	 * Description of the method updateService.
	 * @param admin 
	 * @param serviceForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateService", path = "updateService", httpMethod = "post")
	public Service updateService(final User user, ServiceForm serviceForm, @Named("serviceId") final long serviceId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		

	    Service service = getService(user, serviceId);
	    
	    if(!(serviceForm.getName() == null)){
	    	service.setName(serviceForm.getName());
	    }
	    if(!(serviceForm.getClearanceRequired() == null)){
	    	service.setRequiresClearance(serviceForm.getClearanceRequired());
	    }
	    if(!(serviceForm.getTypeId() < 1)){
	    	service.setTypeId(serviceForm.getTypeId());
	    }
	    // TODO
	    // Check what our default will be for numbers 
	    if(!(serviceForm.getPrice() == -1)){
	    	service.setPrice(serviceForm.getPrice());
	    }
	    
  		ofy().save().entities(service).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return service;
	}
	

	/**
	 * Description of the method updateService.
	 * @param admin 
	 * @param serviceForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateProduct", path = "updateProduct", httpMethod = "post")
	public Product updateProduct(final User user, ProductForm productForm, @Named("productId") final long productId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
  		//new Product(productForm.getBarcodeNumber() , productId, productForm.getName(), productForm.getType(), productForm.getPrice());
  		
	    Product product = getProduct(user, productId);
	    
	    if(!(productForm.getBarcodeNumber() == -1)){
	    	product.setBarcodeNumber(productForm.getBarcodeNumber());
	    }
	    if(!(productForm.getName() == null)){
	    	product.setName(productForm.getName());
	    }
	    // TODO
	    // Check the default values for this 
	    if(!(productForm.getPrice() == -1)){
	    	product.setPrice(productForm.getPrice());
	    }
	    if(!(productForm.getTypeId() < 1)){
	    	product.setTypeId(productForm.getTypeId());
	    }
	    
  		ofy().save().entities(product).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return product;
	}

	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "updateAdmin", path = "updateAdmin", httpMethod = "post")
 	public Admin updateAdmin(final User user, AdminForm adminForm, @Named("adminId") final long adminId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
  		Admin admin = getAdmin(user, adminId);
  			    
	    if(!(adminForm.getClearance() == null)){
	    	admin.setAdminClearance(adminForm.getClearance());
	    }
	    if(!(adminForm.getEmail() == null)){
	    	admin.setEmail(adminForm.getEmail());
	    }
	    if(!(adminForm.getPassword() == null)){
	    	admin.setPassword(adminForm.getPassword());
	    }
	    // TODO
	    // Create a secure password send method
	    
  		ofy().save().entities(admin).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return admin;
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "updateType", path = "updateType", httpMethod = "post")
 	public Type updateType(final User user, TypeForm typeForm, @Named("typeId") final long typeId  ) throws UnauthorizedException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        
  		Type type = getType(user, typeId);
		    
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
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "removeService", path = "removeService", httpMethod = "post")
	public WrappedBoolean removeService(final User user, @Named("serviceId") final long serviceId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Service> key = Key.create(Service.class, serviceId);
		
		ofy().delete().key(key).now();
	   
		
		// TODO 
	    // Test and Set Return Value
	    
		return null;
	}

  	/**
	 * Description of the method removeProductService.
	 * @param admin 
	 * @param productId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "removeProduct", path = "removeProduct", httpMethod = "post")
	public WrappedBoolean removeProduct(final User user, @Named("productId") final long productId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Product> key = Key.create(Product.class, productId);
		
		ofy().delete().key(key).now();
	   
		
		// TODO 
	    // Test and Set Return Value
		
	    
		return null;
	}

	/**
	 * Description of the method removeRoom.
	 * @param admin 
	 * @param roomNumber 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "removeRoom",  path = "removeRoom", httpMethod = "post")
	public WrappedBoolean removeRoom(final User user, @Named("roomId") final int roomId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Room> key = Key.create(Room.class, roomId);
		
		ofy().delete().key(key).now();
	    
		// TODO 
	    // Test and Set Return Value
		
		
		return null;
	}

	/**
	 * Description of the method removeEmployee.
	 * @param admin 
	 * @param employeeId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "removeEmployee", path = "removeEmployee", httpMethod = "post")
	public WrappedBoolean removeEmployee(final User user, @Named("employeeId") long employeeId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    
	    Key<Employee> key = Key.create(Employee.class, employeeId);
	
		ofy().delete().key(key).now();
		
	    
	    // TODO 
	    // return value setting
		
		return null;
	}

	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "removeAdmin",  path = "removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(final User user, @Named("adminId") final long adminId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
	    Key<Admin> key = Key.create(Admin.class, adminId);
		
		ofy().delete().key(key).now();
	   
		
		// TODO 
	    // Test and Set Return Value
  		
		return null;
  	}
  	
  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "removeType", path = "removeType", httpMethod = "post")
 	public WrappedBoolean removeType(final User user, @Named("typeId") final long typeId ) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		
	    Key<Type> key = Key.create(Type.class, typeId);
		
		ofy().delete().key(key).now();
	   
		// TODO 
	    // Test and Set Return Value
  		
		return null;
		
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getAllServices", httpMethod = "get")
 	public List<Service> getAllService(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getAllEmployee", httpMethod = "get")
 	public List<Employee> getAllEmployee(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getAllProducts", httpMethod = "get")
 	public List<Product> getAllProduct(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getAllAdmin", httpMethod = "get")
 	public List<Admin> getAllAdmin(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getAllRooms", httpMethod = "get")
 	public List<Room> getAllRoom(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }       
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        Query<Room> query =  ofy().load().type(Room.class);
    	query = query.order("name");
    	
        return query.list();
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getServices", httpMethod = "get")
 	public Service getService(final User user,  @Named("serviceId") final long serviceId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getEmployee", httpMethod = "get")
 	public Employee getEmployee(final User user, @Named("employeeId") final long employeeId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getProducts", httpMethod = "get")
 	public Product getProduct(final User user, @Named("productId") final long productId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getAdmin", httpMethod = "get")
 	public Admin getAdmin(final User user, @Named("adminId") final long adminId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
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
	
	@ApiMethod(name = "getRooms", httpMethod = "get")
	public Room getRoom(final User user, @Named("roomId") final long roomId) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }       
	    if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "getType", httpMethod = "get")
 	public Type getType(final User user, @Named("typeId") final long typeId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Key<Type> key = Key.create(Type.class, typeId);

       	Type type = (Type) ofy().load().key(key).now();
       	return type;
        
  	}
  	
  	
  	@ApiMethod(name = "getIsAType", httpMethod = "get")
 	public WrappedBoolean getIsAType(final User user, Type testType, @Named("typeId") final long typeId) throws UnauthorizedException {

       if (user == null) {
           throw new UnauthorizedException("Authorization required");
       }
       if (!checkAdminAuthorizationForPage(user)) {
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
  	
  	
  	private static boolean checkAdminAuthorizationForPage(final User user){
  		
        // TODO 
        // Get the page ID
  		
		
		// TODO 
        // Get the user clearances
		
  		
		// TODO 
        // Check the user clearances against the page ID
  		
  		
  		return true;
  	}
  	
	
}
