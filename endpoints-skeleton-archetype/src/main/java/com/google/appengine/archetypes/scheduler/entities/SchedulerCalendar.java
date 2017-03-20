package com.google.appengine.archetypes.scheduler.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;


/**
 * Description of Appointment.
 * 
 * @author Lindsey
 */
@Entity
public class SchedulerCalendar {

	/**
	 * Description of the property eventId.
	 */
	@Id
	private String calendarId;
	

	
	
	public SchedulerCalendar(){
		
	}
	
	public SchedulerCalendar(String newCalendarId){
		
		this.calendarId = newCalendarId;
		
	}

	
	public void setCalendarId(String newCalendarId){
		this.calendarId = newCalendarId;
	}
	
	public String getCalendarId(){
		return this.calendarId;
	}
	
}
