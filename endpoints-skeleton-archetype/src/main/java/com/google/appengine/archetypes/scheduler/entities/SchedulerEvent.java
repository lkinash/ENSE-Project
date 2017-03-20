package com.google.appengine.archetypes.scheduler.entities;

import com.google.api.client.util.DateTime;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Description of Appointment.
 * 
 * @author Lindsey
 */
@Entity
public class SchedulerEvent {

	/**
	 * Description of the property eventId.
	 */
	@Id
	private String eventId;
	
	
	@Index
	private DateTime startTime;
	

	@Index
	private DateTime endTime;
	
	
	
	public SchedulerEvent(){
		
	}
	
	public SchedulerEvent(String newEventId, DateTime newStartTime, DateTime newEndTime){
		
		this.endTime = newEndTime;
		this.startTime = newStartTime;
		this.eventId = newEventId;
		
	}
	
	public void setEndTime(DateTime newEndTime){
		this.endTime = newEndTime;
	}
	
	public DateTime getEndTime(){
		return this.endTime;
	}
	
	
	public void setStartTime(DateTime newStartTime){
		this.startTime = newStartTime;
	}
	
	public DateTime getStartTime(){
		return this.startTime;
	}
	
	
	
	public void setEventId(String newEventId){
		this.eventId = newEventId;
	}
	
	public String getEventId(){
		return this.eventId;
	}
	
}
