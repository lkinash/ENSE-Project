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
    
  //public static final String EMAIL_SCOPE = Constant.API_EMAIL_SCOPE;
  public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
	
  public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
  public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
  public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;

  public static final String EMAIL_SCOPE = "https://www.googleapis.com/auth/userinfo.email";
  
  public static final String SENDGRID_USERNAME = "ENSE-Project";
  public static final String SENDGRID_PASSWORD = "";
  

  /** Directory to store user credentials for this application. */
  public static final java.io.File DATA_STORE_DIR = new java.io.File(System.getProperty("user.home"), ".credentials/calendar-java-quickstart");
  
  /** Global instance of the {@link FileDataStoreFactory}. */
  public static FileDataStoreFactory DATA_STORE_FACTORY;

  /** Global instance of the JSON factory. */
  public static final JsonFactory JSON_FACTORY = JacksonFactory.getDefaultInstance();

  /** Global instance of the HTTP transport. */
  public static HttpTransport HTTP_TRANSPORT;
  
  static {
      try {
          HTTP_TRANSPORT = GoogleNetHttpTransport.newTrustedTransport();
          DATA_STORE_FACTORY = new FileDataStoreFactory(DATA_STORE_DIR);
      } catch (Throwable t) {
          t.printStackTrace();
          System.exit(1);
      }
  }
  
}
