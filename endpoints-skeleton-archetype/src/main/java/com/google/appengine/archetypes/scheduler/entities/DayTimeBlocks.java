package com.google.appengine.archetypes.scheduler.entities;

import com.google.appengine.archetypes.scheduler.list.WeekDay;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

/**
 * Description of Employee.
 * 
 * @author Lindsey
 */
@Entity
public class DayTimeBlocks {

	/**
	 * Description of the property AccountId.
	 */
	@Id
	private long id;
	
	private WeekDay weekDay;
	
	
	private int startHour;
	
	private int endHour;
	
	private int startMinute;
	
	private int endMinute;
	
	
	public DayTimeBlocks(){
		
	}
	

	public DayTimeBlocks(long newId, WeekDay newWeekDay, 
	int newStartHour, int newStartMinute, int newEndHour, int newEndMinute){
			
		
		this.id = newId;
		this.weekDay = newWeekDay;
	 
		this.startHour = newStartHour;
		this.startMinute = newStartMinute;
		this.endHour = newEndHour;
		this.endMinute = newEndMinute;
	
	}
	

	/**
	 * Returns id.
	 * @return id 
	 */
	public long getId() {
		return this.id;
	}

	/**
	 * Sets a value to attribute id 
	 * @param id 
	 */
	public void setId(long newId) {
		this.id = newId;
	}


	/**
	 * Returns weekDay.
	 * @return weekDay 
	 */
	public WeekDay getWeekDay() {
		return this.weekDay;
	}

	/**
	 * Sets a value to attribute weekDay 
	 * @param weekDay 
	 */
	public void setWeekDay(WeekDay newWeekDay) {
		this.weekDay = newWeekDay;
	}

	
	
	
	
	/**
	 * Returns StartHour.
	 * @return StartHour 
	 */
	public int getStartHour() {
		return this.startHour;
	}

	/**
	 * Sets a value to attribute startHour. 
	 * @param newstartHour 
	 */
	public void setStartHour(int newStartHour) {
		this.startHour = newStartHour;
	}
	
	
	/**
	 * Returns StartMinute.
	 * @return StartMinute 
	 */
	public int getStartMinute() {
		return this.startMinute;
	}

	/**
	 * Sets a value to attribute StartMinute. 
	 * @param newStartMinute 
	 */
	public void setStartMinute(int newStartMinute) {
		this.startMinute = newStartMinute;
	}
	
	/**
	 * Returns EndHour.
	 * @return EndHour 
	 */
	public int getEndHour() {
		return this.endHour;
	}

	/**
	 * Sets a value to attribute EndHour. 
	 * @param newEndHour 
	 */
	public void setEndHour(int newEndHour) {
		this.endHour = newEndHour;
	}
	
	/**
	 * Returns EndMinute.
	 * @return EndMinute 
	 */
	public int getEndMinute() {
		return this.endMinute;
	}

	/**
	 * Sets a value to attribute EndMinute. 
	 * @param newEndMinute 
	 */
	public void setEndMinute(int newEndMinute) {
		this.endMinute = newEndMinute;
	}
	
	
	
}
