/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;
import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;

import java.io.IOException;
import java.util.Date;
import java.util.List;

import org.json.JSONException;

import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.servlets.Sendgrid;
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
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.servlets.Sendgrid;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.*;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import org.json.JSONException;
import org.json.JSONObject;


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
	 */
	
	@ApiMethod(name = "createClient", httpMethod = "post")
  	public Client createClient(ClientForm clientForm) {

        final Key<Client> clientKey = factory().allocateId(Client.class);
        final long clientId = clientKey.getId();
       
        String clientStringId = "";
        // TODO 
        // Link client Id to the user profile for logging in.
        
        
        List<Appointment> newAppointments = null;
        List<Clearances> newClearances = null;
        Calendar newCalendar = null;
        // TODO 
        // Properly declare variables based on google calendar
		
        
		Client client = new Client(clientForm.getFirstName(), clientForm.getLastName(),
				clientForm.getPhoneNumber(), clientForm.getBirthday(), newAppointments,
				newClearances, newCalendar, 
				clientForm.getEmail(), clientForm.getPassword(), clientStringId);
			
  		ofy().save().entities(client).now();
        
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
  	public Client modifyClient(ClientForm clientForm, final User user, @Named("clientId") final String clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
    	//public Client(String newFirstName, String newLastName, int newPhoneNumber, Date newBirthday, List<Appointment> newAppointments, List<Clearances> newClearances, Calendar newCalendar, String newEmail, String newPassword, String newUserId  ){
    		
        
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
  	public WrappedBoolean addClientClearances(@Named("clientId") final String clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId);

		clearance.setRenewalDate(date);
		
		client.addClearance(clearance);
		
  		ofy().save().entities(client).now();
		
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
  	public WrappedBoolean removeClientClearances(@Named("clientId") final String clientId, Clearances clearance, @Named("date") final Date date, final User user) throws UnauthorizedException {


        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

		Client client = getClient(user, clientId);
		
		client.removeClearance(clearance);
		
  		ofy().save().entities(client).now();
		
		return null;
	}
	
	
	@ApiMethod(name = "getClient", httpMethod = "get")
  	public Client getClient(final User user,@Named("clientId") final String clientId) throws UnauthorizedException {
		//pass in a client ID to access a client other than the current user

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkClientAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }

        Key<Client> key = null;
        
        if(Strings.isNullOrEmpty(clientId)){
        	String userId = user.getUserId();
        	key = Key.create(Client.class, userId);
        }
        else{
        	key = Key.create(Client.class, clientId);
        }
        
    	Client client = (Client) ofy().load().key(key).now();
    	return client;
	}
	
	@ApiMethod(name = "sendEmail", httpMethod = "post")
  	public WrappedBoolean sendEmail(final User user,@Named("email") final String email, @Named("subject") final String subject, @Named("content") final String content) throws UnauthorizedException {
	
		 try {
             // initialize Sendgrid class
             // please replace "<sendgrid_username>" and "<sendgrid_password>" with your SendGrid credentials
             Sendgrid mail = new Sendgrid(Constants.SENDGRID_USERNAME,Constants.SENDGRID_PASSWORD);
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
	
	private static boolean checkClientAuthorizationForPage(final User user){
  		
        // TODO 
        // Add clearance check 
  		
  		
  		return true;
  	}
}
