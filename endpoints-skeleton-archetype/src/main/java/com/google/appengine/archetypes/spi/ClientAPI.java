/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;
import java.util.Date;

import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.forms.ClientForm;
import com.googlecode.objectify.Key;

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
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "modifyClient", httpMethod = "post")
  	public WrappedBoolean modifyClient(ClientForm clientForm, final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for client user
  		
        
        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "addClientClearance", httpMethod = "post")
  	public WrappedBoolean addClientClearances(final User user, @Named("clientdId") final long clientId, Clearances clearance, @Named("date") final Date date) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for client user
  		
       
        // TODO 
        // 
		
		return null;
	}

	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public WrappedBoolean setClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user

		
        // TODO 
        // 
		
		return null;
	}
	
	
	@ApiMethod(name = "getClient", httpMethod = "get")
  	public Client getClient(final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        // TODO 
        // Add clearance check for admin user


        String userId = user.getUserId();
        Key<Client> key = Key.create(Client.class, userId);

    	Client client = (Client) ofy().load().key(key).now();
    	return client;
	}
}
