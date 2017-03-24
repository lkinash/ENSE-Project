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
public class TimeBlock {

	/**
	 * Description of the property eventId.
	 */
	@Id
	private long timeBlockId;
	
	
	@Index
	private DateTime startTime;
	

	@Index
	private DateTime endTime;
	
	
	
	public TimeBlock(){
		
	}
	
	public TimeBlock(long newTimeBlockId, DateTime newStartTime, DateTime newEndTime){
		
		this.endTime = newEndTime;
		this.startTime = newStartTime;
		this.timeBlockId = newTimeBlockId;
		
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
	
	
	public void setTimeBlockId(long newTimeBlockId){
		this.timeBlockId = newTimeBlockId;
	}
	
	public long getTimeBlockId(){
		return this.timeBlockId;
	}
	
	
}
