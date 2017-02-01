package com.google.appengine.archetypes.service;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.java6.auth.oauth2.AuthorizationCodeInstalledApp;
import com.google.api.client.extensions.jetty.auth.oauth2.LocalServerReceiver;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.store.DataStoreFactory;
import com.google.api.client.util.store.FileDataStoreFactory;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.Collections;


public class CalendarAuth {

	String APPLICATION_NAME = "PlusSample";
	java.io.File DATA_STORE_DIR =
	      new java.io.File(System.getProperty("user.home"), ".store/plus_sample");
	FileDataStoreFactory dataStoreFactory;

	// Set up the HTTP transport and JSON factory
	HttpTransport httpTransport = GoogleNetHttpTransport.newTrustedTransport();
	JsonFactory jsonFactory = JacksonFactory.getDefaultInstance();

	// Load client secrets
	GoogleClientSecrets clientSecrets = GoogleClientSecrets.load(jsonFactory,
	    new InputStreamReader(CalendarAuth.class.getResourceAsStream("/client_secrets.json")));

	// Set up authorization code flow
	//GoogleAuthorizationCodeFlow flow = new GoogleAuthorizationCodeFlow.Builder(
	  //  httpTransport, jsonFactory, clientSecrets,
	  //  Collections.singleton(PlusScopes.PLUS_ME)).setDataStoreFactory(dataStoreFactory)
	   // .build();

	// Authorize
	//Credential credential =
	  //  new AuthorizationCodeInstalledApp(flow, new LocalServerReceiver()).authorize("user");


	
}
