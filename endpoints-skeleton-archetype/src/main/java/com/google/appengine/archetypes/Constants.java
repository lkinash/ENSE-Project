package com.google.appengine.archetypes;

import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming the helloworld API.
 */
public class Constants {

  public static final String WEB_CLIENT_ID = "573107621545-si8jv3f90624dd6haotsuffu805hbcm8.apps.googleusercontent.com";

  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
	
  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  public static final String CALENDAR_SCOPE = "https://www.googleapis.com/auth/calendar";
  public static final String CALENDAR_READONLY_SCOPE = "https://www.googleapis.com/auth/calendar.readonly";
  
<<<<<<< HEAD
=======
  public static final String SENDGRID_USERNAME = "ENSE-Project";
  public static final String SENDGRID_PASSWORD = "";
  
  public static final String TYPE_LIST_ID = "01type12list23ID34";
>>>>>>> origin/EmailAPI
}
