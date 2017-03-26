package com.google.appengine.archetypes.scheduler.service;

import java.io.IOException;
import java.security.GeneralSecurityException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

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
import com.google.api.server.spi.Constant;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
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
import com.google.api.client.util.DateTime;
import com.google.api.client.util.Preconditions;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;

import javax.servlet.http.HttpServletRequest;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.model.CalendarListEntry;

public class Quickstart {

	
	private static final AppEngineDataStoreFactory DATA_STORE_FACTORY =
		      AppEngineDataStoreFactory.getDefaultInstance();
		  
	  /** Global instance of the HTTP transport. */
	  private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();

	  /** Global instance of the JSON factory. */
	  private static final JsonFactory JSON_FACTORY = new JacksonFactory();

	
		  private static GoogleClientSecrets clientSecrets = null;

		  static GoogleClientSecrets getClientCredential() throws IOException {
			  
			  
			  Details details = new Details();
		        details.setClientId(ConstantsSecret.client_id);
		        details.setClientSecret(ConstantsSecret.client_secret);

		        clientSecrets = new GoogleClientSecrets();
		        clientSecrets.setInstalled(details);

		    return clientSecrets;
		  }

		  static String getRedirectUri(HttpServletRequest req) {
		    GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		    url.setRawPath("/oauth2callback");
		    return url.build();
		  }

		  
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

    public static Calendar getService(final User user) throws IOException, GeneralSecurityException {
        // Build a new authorized API client service.
        // Note: Do not confuse this class with the
        //   com.google.api.services.calendar.model.Calendar class.
    	
    
    	// j6pq7ifpumics69e9948q2bhdc@group.calendar.google.com
    	
    	   java.io.File licenseFile = new java.io.File("WEB-INF/Hello World-788a6517b932.p12");

    	    Set<String> calendarScope = new HashSet<String>();
        	calendarScope.add("https://www.googleapis.com/auth/calendar");
        	calendarScope.add("https://www.googleapis.com/auth/calendar.readonly");
        	calendarScope.add(Constant.API_EMAIL_SCOPE);
        
    	   
    	   GoogleCredential credential = new GoogleCredential.Builder()

    	  .setTransport(HTTP_TRANSPORT)
    	  .setJsonFactory(JSON_FACTORY)
    	  .setServiceAccountId("master-552@hello-world-147504.iam.gserviceaccount.com")   

    	  .setServiceAccountScopes(calendarScope)
    	  .setServiceAccountPrivateKeyFromP12File(licenseFile)
    	  .build();

    	   com.google.api.services.calendar.Calendar service = new com.google.api.services.calendar.Calendar.Builder(
    	                        HTTP_TRANSPORT, JSON_FACTORY, credential)
    	                        .setApplicationName( "Google Calendar Sync").build();
    	
 
	   
	    return service;
	    
    }
    
}