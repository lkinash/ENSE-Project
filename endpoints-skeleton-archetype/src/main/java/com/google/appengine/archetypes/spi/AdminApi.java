package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.entities.Product;
import com.google.appengine.archetypes.entities.Room;
import com.google.appengine.archetypes.entities.SaleItem;
import com.google.appengine.archetypes.entities.Service;
import com.google.appengine.archetypes.forms.AdminForm;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.appengine.archetypes.forms.ProductForm;
import com.google.appengine.archetypes.forms.RoomForm;
import com.google.appengine.archetypes.forms.ServiceForm;
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
  	public WrappedBoolean addEmployee(final User user, EmployeeForm employeeForm) throws UnauthorizedException{

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
		
		return null;
  	}

  	/**
  	 * Description of the method updateEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 * @throws UnauthorizedException 
  	 */
  
   	@ApiMethod(name = "updateEmployee", path = "updateEmployee", httpMethod = "post")
    public WrappedBoolean updateEmployee(final User user, EmployeeForm employeeForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
   		
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
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
 		
		return null;
  	}

  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 */
     
   	@ApiMethod(name = "addRoom", path = "addRoom", httpMethod = "post")
  	public WrappedBoolean addRoom(final User user, RoomForm roomForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
   		
		return null;
  	}

  	/**
  	 * Description of the method updateRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 * @throws UnauthorizedException 
  	 */
      
 	@ApiMethod(name = "updateRoom", path = "updateRoom", httpMethod = "post")
 	public WrappedBoolean updateRoom(final User user, RoomForm roomForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user

        // TODO 
        // 
 		
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
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
      
  	@ApiMethod(name = "addService",  path = "addService", httpMethod = "post")
 	public WrappedBoolean addService(final User user, ServiceForm serviceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		

        final Key<Service> serviceKey = factory().allocateId(Service.class);
        final long serviceId = serviceKey.getId();
        
        boolean requiresClearance = true;
        // TODO 
        // Link to get this value from a list
        
  		Service service  = new Service(requiresClearance , serviceId, serviceForm.getName(), serviceForm.getType(), serviceForm.getPrice());
  		
  	    ofy().save().entities(service).now();
  		
		return null;
  	}

  	/**
  	 * Description of the method updateService.
  	 * @param admin 
  	 * @param serviceForm 
  	 * @throws UnauthorizedException 
  	 */
    
  	@ApiMethod(name = "updateService", path = "updateService", httpMethod = "post")
 	public WrappedBoolean updateService(final User user, ServiceForm serviceForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "addProduct",  path = "addProduct", httpMethod = "post")
 	public WrappedBoolean addProduct(final User user, ProductForm productForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
 		
        final Key<Product> productKey = factory().allocateId(Product.class);
        final long productId = productKey.getId();
        
  		Product product  = new Product(productForm.getBarcodeNumber() , productId, productForm.getName(), productForm.getType(), productForm.getPrice());
  		
  	    ofy().save().entities(product).now();
  			
		return null;
  	}

  	/**
  	 * Description of the method removeProductService.
  	 * @param admin 
  	 * @param productId 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "removeProductService", path = "removeProductService", httpMethod = "post")
 	public WrappedBoolean removeProductService(final User user, @Named("productId") final long productId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "addAdmin", path = "addAdmin", httpMethod = "post")
 	public WrappedBoolean addAdmin(final User user, AdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "updateAdmin", path = "updateAdmin", httpMethod = "post")
 	public WrappedBoolean updateAdmin(final User user, AdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "removeAdmin",  path = "removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(final User user, AdminForm adminForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getRooms", httpMethod = "get")
 	public Room getRooms(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getServices", httpMethod = "get")
 	public Service getServices(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getSaleItems", httpMethod = "get")
 	public SaleItem getSaleItems(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getProducts", httpMethod = "get")
 	public Product getProducts(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns admins.
  	 * @return admins 
  	 * @throws UnauthorizedException 
  	 */
  	
  	@ApiMethod(name = "getAdmins", httpMethod = "get")
 	public Admin getAdmins(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user
  		
        
        // TODO 
        // 
  		
  		return null;
  	}
  
  

	
}
