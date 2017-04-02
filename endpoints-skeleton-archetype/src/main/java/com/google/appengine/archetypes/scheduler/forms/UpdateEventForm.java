package com.google.appengine.archetypes.scheduler.forms;

public class UpdateEventForm {

	/**
	 * Description of the property appointmentType.
	 */
	private String eventId;

	private String summary;
    private String location;
    private String description;
    
	private int startYear;
	private int startMonth;
	private int startDay;
	private int startHour;
	private int startMinute;
	
	private int endYear;
	private int endMonth;
	private int endDay;
	private int endHour;
	private int endMinute;
		
   
    public UpdateEventForm(){
    	
    }
    
    public UpdateEventForm(String newEventId, String newSummary,String newLocation, String newDescription, 
    		 int newStartYear, int newStartMonth, int newStartDay, int newStartHour, int newStartMinute,
    		 int newEndYear, int newEndMonth, int newEndDay, int newEndHour, int newEndMinute ){
    	
    	
    	this.summary = newSummary; 
    	this.location = newLocation; 
    	this.description = newDescription;
    	
    	this.startYear = newStartYear;
    	this.startMonth = newStartMonth;
    	this.startDay = newStartDay;
    	this.startHour = newStartHour;
    	this.startMinute = newStartMinute;
    	
    	this.endYear = newEndYear;
    	this.endMonth = newEndMonth;
    	this.endDay = newEndDay;
    	this.endHour = newEndHour;
    	this.endMinute = newEndMinute;
    	
    	this.eventId = newEventId;
    	
    	
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
        
   
	/**
	 * Returns startYear.
	 * @return startYear 
	 */
	public int getStartYear() {
		return this.startYear;
	}

	/**
	 * Sets a value to attribute startYear. 
	 * @param newstartYear 
	 */
	public void setStartYear(int newStartYear) {
		this.startYear = newStartYear;
	}
	
    	
	/**
	 * Returns startMonth.
	 * @return startMonth 
	 */
	public int getStartMonth() {
		return this.startMonth;
	}

	/**
	 * Sets a value to attribute startMonth. 
	 * @param newstartMonth 
	 */
	public void setStartMonth(int newStartMonth) {
		this.startMonth = newStartMonth;
	}
	
    
	/**
	 * Returns startDay.
	 * @return startDay 
	 */
	public int getStartDay() {
		return this.startDay;
	}

	/**
	 * Sets a value to attribute startDay. 
	 * @param newstartDay 
	 */
	public void setStartDay(int newStartDay) {
		this.startDay = newStartDay;
	}
	
    
	/**
	 * Returns startHour.
	 * @return startHour 
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
	 * Returns startMinute.
	 * @return startMinute 
	 */
	public int getStartMinute() {
		return this.startMinute;
	}

	/**
	 * Sets a value to attribute startMinute. 
	 * @param newstartMinute 
	 */
	public void setStartMinute(int newStartMinute) {
		this.startMinute = newStartMinute;
	}
	
    
	/**
	 * Returns EndYear.
	 * @return EndYear 
	 */
	public int getEndYear() {
		return this.endYear;
	}

	/**
	 * Sets a value to attribute EndYear. 
	 * @param newEndYear 
	 */
	public void setEndYear(int newEndYear) {
		this.endYear = newEndYear;
	}
	
    	
	/**
	 * Returns EndMonth.
	 * @return EndMonth 
	 */
	public int getEndMonth() {
		return this.endMonth;
	}

	/**
	 * Sets a value to attribute EndMonth. 
	 * @param newEndMonth 
	 */
	public void setEndMonth(int newEndMonth) {
		this.endMonth = newEndMonth;
	}
	
    
	/**
	 * Returns EndDay.
	 * @return EndDay 
	 */
	public int getEndDay() {
		return this.endDay;
	}

	/**
	 * Sets a value to attribute EndDay. 
	 * @param newEndDay 
	 */
	public void setEndDay(int newEndDay) {
		this.endDay = newEndDay;
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
	
    
	
	public String getEventId(){
		return this.eventId;
	}
	
	public void setEventId(String newEventId){
		this.eventId = newEventId;
	}
	
}
