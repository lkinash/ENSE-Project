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
	private int year;
	
	@Index
	private int month;
	
	@Index
	private int day;
	
	
	
	public TimeBlock(){
		
	}
	
	public TimeBlock(long newTimeBlockId, int newYear, int newMonth, int newDay){
		
		this.day = newDay;
		this.month = newMonth;
		this.year = newYear;
		this.timeBlockId = newTimeBlockId;
		
	}
	
	public void setYear(int newYear){
		this.year = newYear;
	}
	
	public int getYear(){
		return this.year;
	}
	
	
	public void setMonth(int newMonth){
		this.month = newMonth;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	
	public void setDay(int newDay){
		this.day = newDay;
	}
	
	public int getDay(){
		return this.day;
	}
	

	
	public void setTimeBlockId(long newTimeBlockId){
		this.timeBlockId = newTimeBlockId;
	}
	
	public long getTimeBlockId(){
		return this.timeBlockId;
	}
	
	
}
