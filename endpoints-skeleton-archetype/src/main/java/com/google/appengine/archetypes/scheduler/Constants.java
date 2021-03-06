package com.google.appengine.archetypes.scheduler;


import com.google.api.server.spi.Constant;

/**
 * Contains the client IDs and scopes for allowed clients consuming the conference API.
 */
public class Constants {
    public static final String WEB_CLIENT_ID = "1032207459940-kl5tt995gsvp5j15ec0stmoastj68ame.apps.googleusercontent.com";
    
    //public static final String ANDROID_CLIENT_ID = "replace this with your Android client ID";
    //public static final String IOS_CLIENT_ID = "replace this with your iOS client ID";
    public static final String ANDROID_AUDIENCE = WEB_CLIENT_ID;
    public static final String EMAIL_SCOPE = Constant.API_EMAIL_SCOPE;
    public static final String API_EXPLORER_CLIENT_ID = Constant.API_EXPLORER_CLIENT_ID;
    public static final String USER_INFO_SCOPE = "https://www.googleapis.com/auth/userinfo.profile";

    public static final String CALENDAR_SCOPE = "https://www.googleapis.com/auth/calendar";
    public static final String CALENDAR_READONLY_SCOPE = "https://www.googleapis.com/auth/calendar.readonly";
    
    public static final String MEMCACHE_ANNOUNCEMENTS_KEY = "RECENT_ANNOUNCEMENTS";
}