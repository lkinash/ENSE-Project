package com.google.appengine.archetypes.scheduler.forms;

import java.util.Arrays;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;

public class EventCreatorForm {

    private String summary;
    private String location;
    private String description;

    private DateTime startDateTime;
    private DateTime endDateTime;
    
	
    public EventCreatorForm(){
    	
    }
    
    public EventCreatorForm(String newSummary,String newLocation, String newDescription, 
    		DateTime newStartDateTime, DateTime newEndDateTime ){
    	
    	
    	this.summary = newSummary; 
    	this.location = newLocation; 
    	this.description = newDescription;

    	this.startDateTime = newStartDateTime;
    	this.endDateTime = newEndDateTime;
    		    
    	
    }
    
    public String getSummary(){
    	return this.summary;
    }
	
    public void setSummary(String newSummary){
    	this.summary = newSummary;
    }
    
    public String getLocation(){
    	return this.location;
    }
	
    public void setLocation(String newLocation){
    	this.location = newLocation;
    }
    
    public String getDescription(){
    	return this.description;
    }
	
    public void setDescription(String newDescription){
    	this.description = newDescription;
    }
    
    
    public DateTime getStartDateTime(){
    	return this.startDateTime;
    }
	
    public void setStartDateTime(DateTime newStartDateTime){
    	this.startDateTime = newStartDateTime;
    }
 
    public DateTime getEndDateTime(){
    	return this.endDateTime;
    }
	
    public void setEndDateTime(DateTime newEndDateTime){
    	this.endDateTime = newEndDateTime;
    }
    

}
