package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.archetypes.entities.User;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.EmployeeForm;

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
	@ApiMethod(name = "addEmployee", httpMethod = "post")
  	public WrappedBoolean addEmployee(User user, EmployeeForm employeeForm) {
  		
		return null;
  	}

  	/**
  	 * Description of the method updateEmployee.
  	 * @param admin 
  	 * @param employeeForm 
  	 */
  
   	@ApiMethod(name = "updateEmployee", httpMethod = "post")
    public WrappedBoolean updateEmployee(User user, EmployeeForm employeeForm) {

   		return null;
  	}

  	/**
  	 * Description of the method removeEmployee.
  	 * @param admin 
  	 * @param employeeId 
  	 */
  	/*
 	@ApiMethod(name = "removeEmployee", httpMethod = "post")
    public WrappedBoolean removeEmployee(User user, long employeeId) {
  		// Start of user code for method removeEmployee
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method addRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 */
      /*
   	  @ApiMethod(name = "addRoom", httpMethod = "post")
  	public WrappedBoolean addRoom(User user, RoomForm roomForm) {
  		// Start of user code for method addRoom
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method updateRoom.
  	 * @param admin 
  	 * @param roomForm 
  	 */
      /*
 	  @ApiMethod(name = "updateRoom", httpMethod = "post")
 	public WrappedBoolean updateRoom(User user, RoomForm roomForm) {
  		// Start of user code for method updateRoom
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method removeRoom.
  	 * @param admin 
  	 * @param roomNumber 
  	 */
  	/*
  	@ApiMethod(name = "removeRoom", httpMethod = "post")
 	public WrappedBoolean removeRoom(User user, int roomNumber) {
  		// Start of user code for method removeRoom
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method addService.
  	 * @param admin 
  	 * @param serviceForm 
  	 */
      /*
  	@ApiMethod(name = "addService", httpMethod = "post")
 	public WrappedBoolean addService(User user, ServiceForm serviceForm) {
  		// Start of user code for method addService
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method updateService.
  	 * @param admin 
  	 * @param serviceForm 
  	 */
      /*
  	@ApiMethod(name = "updateService", httpMethod = "post")
 	public WrappedBoolean updateService(User user, ServiceForm serviceForm) {
  		// Start of user code for method updateService
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method addProduct.
  	 * @param admin 
  	 * @param productForm 
  	 */
  	/*
  	@ApiMethod(name = "addProduct", httpMethod = "post")
 	public WrappedBoolean addProduct(User user, ProductForm productForm) {
  		// Start of user code for method addProduct
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method removeProductService.
  	 * @param admin 
  	 * @param productId 
  	 */
  	/*
  	@ApiMethod(name = "removeProductService", httpMethod = "post")
 	public WrappedBoolean removeProductService(User user, Long productId) {
  		// Start of user code for method removeProductService
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method addAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	/*
  	@ApiMethod(name = "addAdmin", httpMethod = "post")
 	public WrappedBoolean addAdmin(User user, AdminForm adminForm) {
  		// Start of user code for method addAdmin
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method updateAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	/*
  	@ApiMethod(name = "updateAdmin", httpMethod = "post")
 	public WrappedBoolean updateAdmin(User user, AdminForm adminForm) {
  		// Start of user code for method updateAdmin
  		// End of user code
		return null;
  	}

  	/**
  	 * Description of the method removeAdmin.
  	 * @param admin 
  	 * @param adminForm 
  	 */
  	/*
  	@ApiMethod(name = "removeAdmin", httpMethod = "post")
 	public WrappedBoolean removeAdmin(User user, AdminForm adminForm) {
  		// Start of user code for method removeAdmin
  		// End of user code
		return null;
  	}

  	// Start of user code (user defined methods for AdminAPi)

  	// End of user code
  	/**
  	 * Returns rooms.
  	 * @return rooms 
  	 */
  	/*
  	@ApiMethod(name = "getRooms", httpMethod = "get")
 	public Room getRooms() {
  		return null;
  	}

  	/**
  	 * Returns services.
  	 * @return services 
  	 */
  	/*
  	@ApiMethod(name = "getServices", httpMethod = "get")
 	public Service getServices() {
  		return null;
  	}

  	/**
  	 * Returns saleItems.
  	 * @return saleItems 
  	 */
  	/*
  	@ApiMethod(name = "getSaleItems", httpMethod = "get")
 	public SaleItem getSaleItems() {
  		return null;
  	}

  	/**
  	 * Returns products.
  	 * @return products 
  	 */
  	/*
  	@ApiMethod(name = "getProducts", httpMethod = "get")
 	public Product getProducts() {
  		return null;
  	}

  	/**
  	 * Returns admins.
  	 * @return admins 
  	 */
  	/*
  	@ApiMethod(name = "getAdmins", httpMethod = "get")
 	public Admin getAdmins() {
  		return null;
  	}
  */
  

	
}
