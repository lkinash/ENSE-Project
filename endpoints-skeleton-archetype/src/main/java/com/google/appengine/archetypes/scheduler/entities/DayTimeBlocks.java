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
	
	private int morningStartHour;
	
	private int morningEndHour;
	
	private int morningStartMinute;
	
	private int morningEndMinute;
	
	
	private int afternoonStartHour;
	
	private int afternoonEndHour;
	
	private int afternoonStartMinute;
	
	private int afternoonEndMinute;
	
	
	public DayTimeBlocks(){
		
	}
	

	public DayTimeBlocks(long newId, WeekDay newWeekDay, 
			int newMorningStartHour, int newMorningStartMinute, int newMorningEndHour, int newMorningEndMinute,
			int newAfternoonStartHour, int newAfternoonStartMinute, int newAfternoonEndHour, int newAfternoonEndMinute){
			
		
		this.id = newId;
		this.weekDay = newWeekDay;
	 
		this.morningStartHour = newMorningStartHour;
		this.morningStartMinute = newMorningStartMinute;
		this.morningEndHour = newMorningEndHour;
		this.morningEndMinute = newMorningEndMinute;
	
		this.afternoonStartHour = newAfternoonStartHour;
		this.afternoonStartMinute = newAfternoonStartMinute;
		this.afternoonEndHour = newAfternoonEndHour;
		this.afternoonEndMinute = newAfternoonEndMinute;
	
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
	 * Returns morningStartHour.
	 * @return morningStartHour 
	 */
	public int getMorningStartHour() {
		return this.morningStartHour;
	}

	/**
	 * Sets a value to attribute morningStartHour. 
	 * @param newMorningStartHour 
	 */
	public void setMorningStartHour(int newMorningStartHour) {
		this.morningStartHour = newMorningStartHour;
	}
	
	
	/**
	 * Returns morningStartMinute.
	 * @return morningStartMinute 
	 */
	public int getMorningStartMinute() {
		return this.morningStartMinute;
	}

	/**
	 * Sets a value to attribute morningStartMinute. 
	 * @param newMorningStartMinute 
	 */
	public void setMorningStartMinute(int newMorningStartMinute) {
		this.morningStartMinute = newMorningStartMinute;
	}
	
	/**
	 * Returns morningEndHour.
	 * @return morningEndHour 
	 */
	public int getMorningEndHour() {
		return this.morningEndHour;
	}

	/**
	 * Sets a value to attribute morningEndHour. 
	 * @param newMorningEndHour 
	 */
	public void setMorningEndHour(int newMorningEndHour) {
		this.morningEndHour = newMorningEndHour;
	}
	
	/**
	 * Returns morningEndMinute.
	 * @return morningEndMinute 
	 */
	public int getMorningEndMinute() {
		return this.morningEndMinute;
	}

	/**
	 * Sets a value to attribute morningEndMinute. 
	 * @param newMorningEndMinute 
	 */
	public void setMorningEndMinute(int newMorningEndMinute) {
		this.morningEndMinute = newMorningEndMinute;
	}
	
	
	
	
	/**
	 * Returns afternoonStartHour.
	 * @return afternoonStartHour 
	 */
	public int getAfternoonStartHour() {
		return this.afternoonStartHour;
	}

	/**
	 * Sets a value to attribute afternoonStartHour. 
	 * @param newAfternoonStartHour 
	 */
	public void setAfternoonStartHour(int newAfternoonStartHour) {
		this.afternoonStartHour = newAfternoonStartHour;
	}
	
	
	/**
	 * Returns afternoonStartMinute.
	 * @return afternoonStartMinute 
	 */
	public int getAfternoonStartMinute() {
		return this.afternoonStartMinute;
	}

	/**
	 * Sets a value to attribute afternoonStartMinute. 
	 * @param newAfternoonStartMinute 
	 */
	public void setAfternoonStartMinute(int newAfternoonStartMinute) {
		this.afternoonStartMinute = newAfternoonStartMinute;
	}
	
	/**
	 * Returns afternoonEndHour.
	 * @return afternoonEndHour 
	 */
	public int getAfternoonEndHour() {
		return this.afternoonEndHour;
	}

	/**
	 * Sets a value to attribute afternoonEndHour. 
	 * @param newAfternoonEndHour 
	 */
	public void setAfternoonEndHour(int newAfternoonEndHour) {
		this.afternoonEndHour = newAfternoonEndHour;
	}
	
	/**
	 * Returns afternoonEndMinute.
	 * @return afternoonEndMinute 
	 */
	public int getAfternoonEndMinute() {
		return this.afternoonEndMinute;
	}

	/**
	 * Sets a value to attribute afternoonEndMinute. 
	 * @param newAfternoonEndMinute 
	 */
	public void setAfternoonEndMinute(int newAfternoonEndMinute) {
		this.afternoonEndMinute = newAfternoonEndMinute;
	}
	
	
	
}
