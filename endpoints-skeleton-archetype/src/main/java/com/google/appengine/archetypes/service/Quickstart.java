package com.google.appengine.archetypes.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.*;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.ConstantsSecret;
import com.google.appengine.archetypes.wrappers.WrappedBoolean;
import com.google.common.collect.Lists;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class Quickstart {

	
    /** Application name. */
    private static final String APPLICATION_NAME =
        "Google Calendar API Java Quickstart";

    /** Directory to store user credentials for this application. */
    private static final java.io.File DATA_STORE_DIR = new java.io.File(
    		"/credentials/calendar-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */

    
    /** Global instance of the JSON factory. */
    private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    private static HttpTransport HTTP_TRANSPORT;

    private static final AppEngineDataStoreFactory DATA_STORE_FACTORY = AppEngineDataStoreFactory.getDefaultInstance();
    
    /** Global instance of the scopes required by this quickstart.     */
    private static final List<String> SCOPES = Lists.newArrayList(Constants.CALENDAR_SCOPE, Constants.CALENDAR_READONLY_SCOPE, Constants.EMAIL_SCOPE);

        
    static {
        try {
            HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     */
    public static Credential authorize(final User user) throws IOException {
              
    	GoogleAuthorizationCodeFlow flow =  new GoogleAuthorizationCodeFlow.Builder(
    	        HTTP_TRANSPORT, JSON_FACTORY,
    	        ConstantsSecret.client_id, ConstantsSecret.client_secret,
    	        SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).build();
    	       
    	LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();
    	
    	
        Credential credential = new AuthorizationCodeInstalledApp(
           flow, localReceiver).authorize(ConstantsSecret.client_id);
        
    	
    	System.out.println(
                "Credentials saved to " + DATA_STORE_DIR.getAbsolutePath());
        return credential;
        /*
		String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
	    Credential credential = null;
		try {
			credential = CalendarUtility.newFlow().loadCredential(userId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar service = new Calendar.Builder(CalendarUtility.HTTP_TRANSPORT, CalendarUtility.JSON_FACTORY, credential)
	    .setApplicationName("applicationName").build();
	// Retrieve the calendar
		Calendar calendar = null;
		
		try {
			calendar = service.calendars().get(calendarId).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(calendar.getSummary());
		
		
    	return calendar;
    	*/
    
    
    }

    
    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     */
    public static com.google.api.services.calendar.Calendar getCalendarService(final User user) throws IOException {
        Credential credential = authorize(user);
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
    }

    public static WrappedBoolean addEvent(String calendarId, final User user, Event event) throws IOException {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
    	
        System.out.println(user.getEmail() + "   " +  user.getAuthDomain());
        
    	
        com.google.api.services.calendar.Calendar service =
            getCalendarService(user);

    
    event = service.events().insert(calendarId, event).execute();
    System.out.printf("Event created: %s\n", event.getHtmlLink());
    
        return null;
    }
    
}