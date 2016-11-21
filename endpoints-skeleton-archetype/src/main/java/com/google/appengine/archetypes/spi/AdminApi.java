package com.google.appengine.archetypes.spi;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.appengine.archetypes.Constants;


@Api(
	    name = "admin",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID},
	    audiences = {Constants.ANDROID_AUDIENCE}
	)
public class AdminApi {

	  @ApiMethod(name = "greetings.multiply", httpMethod = "post")
	  public Object insertGreeting() {

	    return "Name";
	  }
	  

	  	/**
	  	 * The constructor.
	  	 */
	  	/*
	  	public AdminAPi() {
	  		// Start of user code constructor for AdminAPi)
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method addEmployee.
	  	 * @param admin 
	  	 * @param employeeForm 
	  	 */
		  /*
	      @ApiMethod(name = "addEmployee", path = "addemployee", httpMethod = HttpMethod.POST)
	  	public void addEmployee(User user, EmployeeForm employeeForm) {
	  		// Start of user code for method addEmployee
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method updateEmployee.
	  	 * @param admin 
	  	 * @param employeeForm 
	  	 */
	      /*
	      @ApiMethod(name = "updateEmployee", path = "updateEmployee", httpMethod = HttpMethod.POST)
	  	public void updateEmployee(User user, EmployeeForm employeeForm) {
	  		// Start of user code for method updateEmployee
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method removeEmployee.
	  	 * @param admin 
	  	 * @param employeeId 
	  	 */
	  	/*
	      public void removeEmployee(User user, long employeeId) {
	  		// Start of user code for method removeEmployee
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method addRoom.
	  	 * @param admin 
	  	 * @param roomForm 
	  	 */
	      /*
	      @ApiMethod(name = "addRoom", path = "room", httpMethod = HttpMethod.POST)
	  	public void addRoom(User user, RoomForm roomForm) {
	  		// Start of user code for method addRoom
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method updateRoom.
	  	 * @param admin 
	  	 * @param roomForm 
	  	 */
	      /*
	  	public void updateRoom(User user, RoomForm roomForm) {
	  		// Start of user code for method updateRoom
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method removeRoom.
	  	 * @param admin 
	  	 * @param roomNumber 
	  	 */
	  	/*
	  	public void removeRoom(User user, int roomNumber) {
	  		// Start of user code for method removeRoom
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method addService.
	  	 * @param admin 
	  	 * @param serviceForm 
	  	 */
	      /*
	  	public void addService(User user, ServiceForm serviceForm) {
	  		// Start of user code for method addService
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method updateService.
	  	 * @param admin 
	  	 * @param serviceForm 
	  	 */
	      /*
	  	public void updateService(User user, ServiceForm serviceForm) {
	  		// Start of user code for method updateService
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method addProduct.
	  	 * @param admin 
	  	 * @param productForm 
	  	 */
	  	/*
	  	public void addProduct(User user, ProductForm productForm) {
	  		// Start of user code for method addProduct
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method removeProductService.
	  	 * @param admin 
	  	 * @param productId 
	  	 */
	  	/*
	  	public void removeProductService(User user, Long productId) {
	  		// Start of user code for method removeProductService
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method addAdmin.
	  	 * @param admin 
	  	 * @param adminForm 
	  	 */
	  	/*
	  	public void addAdmin(User user, AdminForm adminForm) {
	  		// Start of user code for method addAdmin
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method updateAdmin.
	  	 * @param admin 
	  	 * @param adminForm 
	  	 */
	  	/*
	  	public void updateAdmin(User user, AdminForm adminForm) {
	  		// Start of user code for method updateAdmin
	  		// End of user code
	  	}

	  	/**
	  	 * Description of the method removeAdmin.
	  	 * @param admin 
	  	 * @param adminForm 
	  	 */
	  	/*
	  	public void removeAdmin(User user, AdminForm adminForm) {
	  		// Start of user code for method removeAdmin
	  		// End of user code
	  	}

	  	// Start of user code (user defined methods for AdminAPi)

	  	// End of user code
	  	/**
	  	 * Returns rooms.
	  	 * @return rooms 
	  	 */
	  	/*
	  	public Room getRooms() {
	  		return null;
	  	}

	  	/**
	  	 * Returns services.
	  	 * @return services 
	  	 */
	  	/*
	  	public Service getServices() {
	  		return null;
	  	}

	  	/**
	  	 * Returns saleItems.
	  	 * @return saleItems 
	  	 */
	  	/*
	  	public SaleItem getSaleItems() {
	  		return null;
	  	}

	  	/**
	  	 * Returns products.
	  	 * @return products 
	  	 */
	  	/*
	  	public Product getProducts() {
	  		return null;
	  	}

	  	/**
	  	 * Returns admins.
	  	 * @return admins 
	  	 */
	  	/*
	  	public Admin getAdmins() {
	  		return null;
	  	}
	  */
	  

	
}
