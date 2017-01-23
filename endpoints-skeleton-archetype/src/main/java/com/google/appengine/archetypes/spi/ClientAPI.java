/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;

import java.util.Date;
import java.util.List;

import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.wrappers.*;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Client;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.EmployeeForm;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Clearances;
import com.google.appengine.archetypes.forms.ClientForm;
import com.google.common.base.Strings;
import com.googlecode.objectify.Key;



/**
 * Description of ClientAPI.
 * 
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
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "createClient", httpMethod = "post")
  	public Client createClient(final User user, ClientForm clientForm) throws UnauthorizedException {

  		if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
		
        final Key<Client> clientKey = factory().allocateId(Client.class);
        final long clientId = clientKey.getId();
       
        List<Long> newAppointmentIds = null;
        List<Long> newClearanceIds = null;
        Calendar newCalendar = new Calendar(null, null, null);
        // TODO 
        // Properly declare variables based on google calendar
        
        String calendarId = "";
        //TODO
        //Get the calendar Id from the calendar
		
        int phoneNumber;
        Date birthday;
        
        if(clientForm.getPhoneNumber() < 1111111)
        	phoneNumber = 1111111;
        else 
        	phoneNumber = clientForm.getPhoneNumber();
        
        if(clientForm.getBirthday() == null)
        	//TODO
        	//Set a defult birthday value here
        	birthday = null;
        else
        	birthday = clientForm.getBirthday();
        
        
        
        // Client must enter first name, last name, email and a password
        
		Client client = new Client(clientForm.getFirstName(), clientForm.getLastName(),
				phoneNumber, birthday, newAppointmentIds, newClearanceIds, calendarId,
				clientForm.getEmail(), clientForm.getPassword(), clientId);
			
  		ofy().save().entities(client, newCalendar).now();
        
		return client;
		 
        // TODO 
        // Set the return value 
		
	}

	/**
	 * Description of the method modifyClient.
	 * @param clientForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "modifyClient", httpMethod = "post")
  	public Client modifyClient(ClientForm clientForm, final User user, @Named("clientId") final long clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

	    Client client = getClient(user, clientId);
	    
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
	    if(!(clientForm.getPassword() == null)){
	    	client.setPassword(clientForm.getPassword());
	    }
	    
  		ofy().save().entities(client).now();
	    
	    // TODO 
	    // Ensure in the form elements that are not set are set to null
		
		return client;
	}



	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "addClientClearance", httpMethod = "post")
  	public WrappedBoolean addClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId);

		clearance.setRenewalDate(date);
		
		client.addClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client, clearance).now();
		
		return null;
	}
	
	/**
	 * Description of the method addClientClearances.
	 * @param clientId 
	 * @param clearance 
	 * @param date 
	 * @throws UnauthorizedException 
	 */
	 
	@ApiMethod(name = "removeClientClearance", httpMethod = "post")
  	public WrappedBoolean removeClientClearances(@Named("clientId") final long clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId);
		
		client.removeClearance(clearance.getClearanceId());
		
  		ofy().save().entities(client).now();
  		
  		Key<Clearances> clearanceKey = Key.create(Clearances.class, clearance.getClearanceId());
  		
  		ofy().delete().key(clearanceKey).now();
		
		return null;
	}
	
	
	@ApiMethod(name = "getClient", httpMethod = "get")
  	public Client getClient(final User user,@Named("clientId") final long clientId) throws UnauthorizedException {
		//pass in a client ID to access a client other than the current user

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
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
  	
  	@ApiMethod(name = "removeClient",  path = "removeClient", httpMethod = "post")
 	public WrappedBoolean removeClient(final User user, @Named("adminId") final long clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
	    Key<Client> key = Key.create(Client.class, clientId);
		
		ofy().delete().key(key).now();
	   
		
		// TODO 
	    // Test and Set Return Value
  		
		return null;
  	}
	
	private static boolean checkClientAuthorizationForPage(final User user){
  		
        // TODO 
        // Get the page ID
  		
		
		// TODO 
        // Get the user clearances
		
		// TODO 
        // Check the user clearances against the page ID
  		
  		return true;
  	}
}
