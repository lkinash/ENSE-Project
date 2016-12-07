/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import java.util.Date;

import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.forms.ClientForm;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of ClientAPI.
 * 
 * @author Lindsey
 */
@Api(
	    name = "client",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID },
	    description = "Client side API."
	)
public class ClientAPI {


	/**
	 * Description of the method createClient.
	 * @param clientForm 
	 */
	
	@ApiMethod(name = "createClient", httpMethod = "post")
  	public WrappedBoolean createClient(ClientForm clientForm) {

        // TODO 
        // 
		
		return null;
		 
	}

	/**
	 * Description of the method modifyClient.
	 * @param clientForm 
	 */
	
	@ApiMethod(name = "modifyClient", httpMethod = "post")
  	public WrappedBoolean modifyClient(ClientForm clientForm, final User user) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 */
	
	@ApiMethod(name = "addClientClearance", httpMethod = "post")
  	public WrappedBoolean addClientClearances(final User user, @Named("clientdId") final long clientId, Clearances clearance, @Named("date") final Date date) {

        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 */
	 
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public WrappedBoolean setClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) {

        // TODO 
        // 
		
		return null;
	}


}
