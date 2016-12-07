package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiMethod.HttpMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
//import com.google.appengine.archetypes.entities.User;
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
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
	    audiences = {Constants.ANDROID_AUDIENCE}
	)
public class AdminApi {

	
  	/**
  	 * Description of the method addEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 */
	
	@ApiMethod(name = "addEmployee", path = "addEmployee", httpMethod = "post")
  	public WrappedBoolean addEmployee(final User user, EmployeeForm employeeForm) throws UnauthorizedException{
  		
        // TODO 
        // 
		
		return null;
  	}

  	/**
  	 * Description of the method updateEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 */
  
   	@ApiMethod(name = "updateEmployee", path = "updateEmployee", httpMethod = "post")
    public WrappedBoolean updateEmployee(final User user, EmployeeForm employeeForm) {

        // TODO 
        // 
   		
   		return null;
  	}

  	/**
  	 * Description of the method removeEmployee.
  	 * @param admin 
  	 * @param employeeId 
  	 */
  	
 	@ApiMethod(name = "removeEmployee", path = "removeEmployee", httpMethod = "post")
    public WrappedBoolean removeEmployee(final User user, @Named("employeeId") long employeeId) {

        // TODO 
        // 
 		
		return null;
  	}

  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 */
     
   	@ApiMethod(name = "addRoom", path = "addRoom", httpMethod = "post")
  	public WrappedBoolean addRoom(final User user, RoomForm roomForm) {

        // TODO 
        // 
   		
		return null;
  	}

  	/**
  	 * Description of the method updateRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 */
      
 	@ApiMethod(name = "updateRoom", path = "updateRoom", httpMethod = "post")
 	public WrappedBoolean updateRoom(final User user, RoomForm roomForm) {

        // TODO 
        // 
 		
		return null;
  	}

  	/**
  	 * Description of the method removeRoom.
  	 * @param admin 
  	 * @param roomNumber 
  	 */
  	
  	@ApiMethod(name = "removeRoom",  path = "removeRoom", httpMethod = "post")
 	public WrappedBoolean removeRoom(final User user, @Named("roomNumber") final int roomNumber) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 */
      
  	@ApiMethod(name = "addService",  path = "addService", httpMethod = "post")
 	public WrappedBoolean addService(final User user, ServiceForm serviceForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method updateService.
  	 * @param admin 
  	 * @param serviceForm 
  	 */
    
  	@ApiMethod(name = "updateService", path = "updateService", httpMethod = "post")
 	public WrappedBoolean updateService(final User user, ServiceForm serviceForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 */
  	
  	@ApiMethod(name = "addProduct",  path = "addProduct", httpMethod = "post")
 	public WrappedBoolean addProduct(final User user, ProductForm productForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method removeProductService.
  	 * @param admin 
  	 * @param productId 
  	 */
  	
  	@ApiMethod(name = "removeProductService", path = "removeProductService", httpMethod = "post")
 	public WrappedBoolean removeProductService(final User user, @Named("productId") final long productId) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	
  	@ApiMethod(name = "addAdmin", path = "addAdmin", httpMethod = "post")
 	public WrappedBoolean addAdmin(final User user, AdminForm adminForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	
  	@ApiMethod(name = "updateAdmin", path = "updateAdmin", httpMethod = "post")
 	public WrappedBoolean updateAdmin(final User user, AdminForm adminForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	
  	@ApiMethod(name = "removeAdmin",  path = "removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(final User user, AdminForm adminForm) {

        // TODO 
        // 
  		
		return null;
  	}

  	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 */
  	
  	@ApiMethod(name = "getRooms", httpMethod = "get")
 	public Room getRooms(final User user) {
  		
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 */
  	
  	@ApiMethod(name = "getServices", httpMethod = "get")
 	public Service getServices(final User user) {
  		
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 */
  	
  	@ApiMethod(name = "getSaleItems", httpMethod = "get")
 	public SaleItem getSaleItems(final User user) {
  		
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 */
  	
  	@ApiMethod(name = "getProducts", httpMethod = "get")
 	public Product getProducts(final User user) {
  		
        // TODO 
        // 
  		
  		return null;
  	}

  	/**
  	 * Returns admins.
  	 * @return admins 
  	 */
  	
  	@ApiMethod(name = "getAdmins", httpMethod = "get")
 	public Admin getAdmins(final User user) {
  		
        // TODO 
        // 
  		
  		return null;
  	}
  
  

	
}
