package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;

import java.util.List;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.entities.Product;
import com.google.appengine.archetypes.entities.Room;
import com.google.appengine.archetypes.entities.SaleItem;
import com.google.appengine.archetypes.entities.Service;
import com.google.appengine.archetypes.entities.Type;
import com.google.appengine.archetypes.entities.TypeList;
import com.google.appengine.archetypes.forms.AdminForm;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.appengine.archetypes.forms.ProductForm;
import com.google.appengine.archetypes.forms.RoomForm;
import com.google.appengine.archetypes.forms.ServiceForm;
import com.google.appengine.archetypes.forms.TypeForm;
import com.google.appengine.archetypes.list.AdminClearances;
import com.google.appengine.api.users.User;
import com.googlecode.objectify.Key;

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
        
  		Employee employee  = new Employee(employeeForm.getCalendar(), employeeForm.getName(), employeeId);

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
        
        
        Calendar calendar = null;
        // TODO 
        // Link to get this value as a calendar object
        
        
        Room room = new Room(roomForm.getRoomNumber(), roomForm.getServices(), calendar, roomId);
    		
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
        
        
  		Service service  = new Service(requiresClearance , serviceId, serviceForm.getName(), serviceForm.getType(), serviceForm.getPrice());
  		
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
        
  		Product product  = new Product(productForm.getBarcodeNumber() , productId, productForm.getName(), productForm.getType(), productForm.getPrice());
  		
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
        
        
        String adminStringId = "";
        // TODO 
        // Fix to set to the admin Id
      
        
  		Admin admin  = new Admin(adminForm.getClearance(), adminForm.getEmail(),adminForm.getPassword(), adminStringId);
  			
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
  		
       
        TypeList list = getTypeList(user);

        Type newType = new Type(typeForm.getIsService(), typeForm.getTypeName());
        
        list.addType(newType);
        
  		ofy().save().entities(list).now();
  		
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
	    
	    if(!(employeeForm.getCalendar() == null)){
	    	employee.setCalendar(employeeForm.getCalendar());
	    }
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
	    if(!(roomForm.getServices() == null)){
	    	room.setServices(roomForm.getServices());
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
	    if(!(serviceForm.getType() == null)){
	    	service.setType(serviceForm.getType());
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
	    if(!(productForm.getType() == null)){
	    	product.setType(productForm.getType());
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
	public WrappedBoolean removeRoom(final User user, @Named("roomNumber") final int roomNumber) throws UnauthorizedException {
	
	    if (user == null) {
	        throw new UnauthorizedException("Authorization required");
	    }
	    if (!checkAdminAuthorizationForPage(user)) {
	        throw new UnauthorizedException("Authorization level too low.");
	    }
		
	    Key<Room> key = Key.create(Room.class, roomNumber);
		
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
 	public WrappedBoolean removeAdmin(final User user, @Named("adminId") final String adminId) throws UnauthorizedException {

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
 	public WrappedBoolean removeType(final User user, TypeForm typeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
       
        TypeList list = getTypeList(user);

        Type newType = new Type(typeForm.getIsService(), typeForm.getTypeName());
        
        list.removeType(newType);
        
  		ofy().save().entities(list).now();
  		
		// TODO 
	    // Test and Set Return Value
  		
		return null;
		
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
  	
  	@ApiMethod(name = "getTypeList", httpMethod = "get")
 	public TypeList getTypeList(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAdminAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
        
        Key<TypeList> key = Key.create(TypeList.class, Constants.TYPE_LIST_ID);

       	TypeList list = (TypeList) ofy().load().key(key).now();
       	return list;
        
  	}
  	
  	@ApiMethod(name = "getIsAType", httpMethod = "get")
 	public WrappedBoolean getIsAType(final User user, Type testType) throws UnauthorizedException {

       if (user == null) {
           throw new UnauthorizedException("Authorization required");
       }
       if (!checkAdminAuthorizationForPage(user)) {
           throw new UnauthorizedException("Authorization level too low.");
       }
        
       TypeList list = getTypeList(user);
       	 
       if(list.getIsAType(testType)){
    	   return new WrappedBoolean(true);
       }
       else
    	   return new WrappedBoolean(false, "Type is not in the Type List.");
        
  	}
  	
  	
  	private static boolean checkAdminAuthorizationForPage(final User user){
  		
        // TODO 
        // Add clearance check 
  		
  		
  		return true;
  	}
  	
	
}
