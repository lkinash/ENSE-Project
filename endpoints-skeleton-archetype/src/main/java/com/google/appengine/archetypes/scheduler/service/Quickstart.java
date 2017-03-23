package com.google.appengine.archetypes.scheduler.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets.Details;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.calendar.model.Event;
import com.google.appengine.api.users.User;
import com.google.appengine.archetypes.scheduler.Constants;
import com.google.appengine.archetypes.scheduler.ConstantsSecret;
import com.google.appengine.archetypes.scheduler.wrappers.WrappedBoolean;
import com.google.common.collect.Lists;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;

public class Quickstart {

	
    /** Application name. */
    //private static final String APPLICATION_NAME =
      //  "Google Calendar API Java Quickstart";

    /** Directory to store user credentials for this application. */
    //private static final java.io.File DATA_STORE_DIR = new java.io.File(
    	//	"/credentials/calendar-java-quickstart.json");

    /** Global instance of the {@link FileDataStoreFactory}. */

    
    /** Global instance of the JSON factory. */
    //private static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

    /** Global instance of the HTTP transport. */
    //private static HttpTransport HTTP_TRANSPORT;

    //private static final AppEngineDataStoreFactory DATA_STORE_FACTORY = AppEngineDataStoreFactory.getDefaultInstance();
    
    /** Global instance of the scopes required by this quickstart.     */
    //private static final List<String> SCOPES = Lists.newArrayList(Constants.CALENDAR_SCOPE, Constants.CALENDAR_READONLY_SCOPE, Constants.EMAIL_SCOPE);

     /*   
    static {
        try {
            HTTP_TRANSPORT = new UrlFetchTransport();
        } catch (Throwable t) {
            t.printStackTrace();
            System.exit(1);
        }

    }

    /**
     * Creates an authorized Credential object.
     * @return an authorized Credential object.
     * @throws IOException
     *//*
    public static Credential authorize(final User user) throws IOException {
              
    	GoogleAuthorizationCodeFlow flow =  new GoogleAuthorizationCodeFlow.Builder(
    	        HTTP_TRANSPORT, JSON_FACTORY,
    	        ConstantsSecret.client_id, ConstantsSecret.client_secret,
    	        SCOPES).setDataStoreFactory(DATA_STORE_FACTORY).build();
    	       
    	LocalServerReceiver localReceiver = new LocalServerReceiver.Builder().setPort(8080).build();
    	
    	Details details = new Details();
        details.setClientId(ConstantsSecret.client_id);
        details.setClientSecret(ConstantsSecret.client_secret);

        GoogleClientSecrets clientSecrets = new GoogleClientSecrets();
        clientSecrets.setInstalled(details);
        
        // Create the OAuth2 credential.
        GoogleCredential credential = new GoogleCredential.Builder()
            .setTransport(new NetHttpTransport())
            .setJsonFactory(new JacksonFactory())
            .setClientSecrets(clientSecrets)
            .build();
    	
        //Credential credential = new AuthorizationCodeInstalledApp(flow, localReceiver).authorize(ConstantsSecret.client_id);
        
    	
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
    	
    
    
    }

    
    /**
     * Build and return an authorized Calendar client service.
     * @return an authorized Calendar client service
     * @throws IOException
     *//*
    public static com.google.api.services.calendar.Calendar getCalendarService(final User user) throws IOException {
        Credential credential = authorize(user);
        return new com.google.api.services.calendar.Calendar.Builder(
                HTTP_TRANSPORT, JSON_FACTORY, credential)
                .setApplicationName(APPLICATION_NAME)
                .build();
        
    }*/
	
	private static final AppEngineDataStoreFactory DATA_STORE_FACTORY =
		      AppEngineDataStoreFactory.getDefaultInstance();
		  
		  /** Global instance of the HTTP transport. */
		  static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();

		  /** Global instance of the JSON factory. */
		  static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

		  private static GoogleClientSecrets clientSecrets = null;

		  static GoogleClientSecrets getClientCredential() throws IOException {
			  
			  
			  Details details = new Details();
		        details.setClientId(ConstantsSecret.client_id);
		        details.setClientSecret(ConstantsSecret.client_secret);

		        clientSecrets = new GoogleClientSecrets();
		        clientSecrets.setInstalled(details);
			/* 
		    if (clientSecrets == null) {
		      clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
		          new InputStreamReader(Quickstart.class.getResourceAsStream("/client_secret.json")));
		      Preconditions.checkArgument(!clientSecrets.getDetails().getClientId().startsWith("Enter ")
		          && !clientSecrets.getDetails().getClientSecret().startsWith("Enter "),
		          "Download client_secrets.json file from https://code.google.com/apis/console/"
		          + "?api=calendar into calendar-appengine-sample/src/main/resources/client_secret.json");
		    }
		    */
		    return clientSecrets;
		  }

		  static String getRedirectUri(HttpServletRequest req) {
		    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		    url.setRawPath("/oauth2callback");
		    return url.build();
		  }

		  /*
		  static GoogleAuthorizationCodeFlow newFlow() throws IOException {
		    return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
		        getClientCredential(), Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
		        DATA_STORE_FACTORY).setAccessType("offline").build();
		  }
		  */
		  
		  static GoogleAuthorizationCodeFlow newFlow() throws IOException {
			  return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY,
			      getClientCredential(), Collections.singleton(CalendarScopes.CALENDAR)).setDataStoreFactory(
			      DATA_STORE_FACTORY).setAccessType("offline").setApprovalPrompt("force").build();
			}

		  static Calendar loadCalendarClient() throws IOException {
		    String userId = "110528491283071203527";
		    		//UserServiceFactory.getUserService().getCurrentUser().getUserId();
		    Credential credential = newFlow().loadCredential(userId);
		    return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential).build();
		  }

		  /**
		   * Returns an {@link IOException} (but not a subclass) in order to work around restrictive GWT
		   * serialization policy.
		   */
		  static IOException wrappedIOException(IOException e) {
		    if (e.getClass() == IOException.class) {
		      return e;
		    }
		    return new IOException(e.getMessage());
		  }

    public static WrappedBoolean addEvent(final User user, String calendarId, Event event) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
    	
        //System.out.println(user.getEmail() + "   " +  user.getAuthDomain());
        
    	// j6pq7ifpumics69e9948q2bhdc@group.calendar.google.com
    	
    	   java.io.File licenseFile = new java.io.File("39790cb51b361f51cab6940d165c6cda4dc60177-privatekey.p12");

    	   GoogleCredential credential = new GoogleCredential.Builder()

    	  .setTransport(HTTP_TRANSPORT)
    	  .setJsonFactory(JSON_FACTORY)
    	  .setServiceAccountId("master-552@hello-world-147504.iam.gserviceaccount.com")   

    	  .setServiceAccountUser("kinash.lindsey@gmail.com")
    	  //.setServiceAccountScopes(Constants.CALENDAR_SCOPE)
    	  .setServiceAccountPrivateKeyFromP12File(licenseFile)
    	  .build();

    	   com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
    	                        HTTP_TRANSPORT, JSON_FACTORY, credential)
    	                        .setApplicationName( "Google Calendar Sync").build();
    	
        //com.google.api.services.calendar.Calendar service 
        //= loadCalendarClient();
            //getCalendarService(user);

    
        event = service.events().insert(calendarId, event).execute();

    
        return null;
    }
    
}