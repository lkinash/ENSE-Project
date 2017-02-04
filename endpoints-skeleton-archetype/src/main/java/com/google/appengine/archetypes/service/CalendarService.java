package com.google.appengine.archetypes.service;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

import java.util.List;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
 
import com.google.gdata.util.AuthenticationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
 
import com.google.gdata.client.authn.oauth.OAuthHmacSha1Signer;
import com.google.gdata.client.authn.oauth.OAuthParameters;
//import com.google.gdata.client.calendar.CalendarService;
import com.google.gdata.data.calendar.CalendarEntry;
import com.google.gdata.data.calendar.CalendarFeed;
 

public class CalendarService {

	
	  private Properties credentials;
	  
	    /**
	     * Sets properties that contains the application's consumer key and consumer secret.
	     *
	     * @param credentials consumer key and consumer secret.
	     */
	    public void setCredentials(Properties credentials) {
	        this.credentials = credentials;
	    }
	 
	    /**
	     * Obtains a list of names of a user's public and private calendars from the Google
	     * Calendar API.
	     * 
	     * @param accessToken OAuth access token.
	     * @param accessTokenSecret OAuth access token secret.
	     * @return list of names of a user's public and private calendars.
	     *//*
	    public List<String> getCalendarNames(String accessToken, String accessTokenSecret) throws Exception {
	        CalendarService calendarService = new CalendarService(); 
	        OAuthParameters params = getOAuthParams(accessToken, accessTokenSecret);
	        calendarService.setOAuthCredentials(params, new OAuthHmacSha1Signer());
	        URL feedUrl = new URL("http://www.google.com/calendar/feeds/default/");
	        CalendarFeed resultFeed = calendarService.getFeed(feedUrl, CalendarFeed.class);
	 
	        ArrayList<String> result = new ArrayList<String>();
	        for (int i = 0; i < resultFeed.getEntries().size(); i++) {
	            CalendarEntry entry = resultFeed.getEntries().get(i);
	            result.add(entry.getTitle().getPlainText());
	        }
	        return result;
	    }
	     
	    private OAuthParameters getOAuthParams(String accessToken, String accessTokenSecret) {
	        OAuthParameters params = new OAuthParameters();
	        params.setOAuthConsumerKey(credentials.getProperty("consumer.key"));
	        params.setOAuthConsumerSecret(credentials.getProperty("consumer.secret"));
	        params.setOAuthToken(accessToken);
	        params.setOAuthTokenSecret(accessTokenSecret);
	        return params;
	    }	
 
	    @SuppressWarnings("unchecked")
	    @RequestMapping(method = RequestMethod.GET)
	    public String handleGet(
	            HttpServletRequest request, 
	            HttpServletResponse response, 
	            ModelMap model) throws Exception {
	 
	        List<String> calendarNames = null;
	 
	        // Get OAuth tokens from cookies
	        String accessToken = getAccessToken(request);
	        String accessTokenSecret = getAccessTokenSecret(request);
	         
	        if (accessToken == null) {
	            model.put("message", "No OAuth access token available");
	            return "/WEB-INF/jsp/authorize.jsp";
	        }
	         
	        try {
	            // Get calendar names from Google Calendar API
	            calendarNames = service.getCalendarNames(accessToken, accessTokenSecret);
	        } catch (AuthenticationException e) {
	            model.put("message", "OAuth access token invalid");
	            return "/WEB-INF/jsp/authorize.jsp";
	        }
	         
	        model.put("calendarNames", calendarNames);
	        return "/WEB-INF/jsp/calendar.jsp";        
	    }
	     
	    private static String getAccessToken(HttpServletRequest request) {
	        return getCookieValue(request.getCookies(), "TUTORIAL-ACCESS-TOKEN");
	    }
	     
	    private static String getAccessTokenSecret(HttpServletRequest request) {
	        return getCookieValue(request.getCookies(), "TUTORIAL-ACCESS-TOKEN-SECRET");
	    }
	 
	    */
}
