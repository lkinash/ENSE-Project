package com.google.appengine.archetypes.scheduler.wrappers;

import com.google.appengine.archetypes.scheduler.forms.TimeBlockForm;

public class WrappedAppointmentOption {

	/**
	 * Description of the property employee.
	 */
	private long employeeId;
	

	/**
	 * Description of the property employee.
	 */
	private String employeeName;
	
	/**
	 * Description of the property serviceId.
	 */
	private long serviceId;
	
	
	/**
	 * Description of the property serviceId.
	 */
	private String serviceName;

	/**
	 * Description of the property client
	 */
	private long clientId;
	
	/**
	 * Description of the property serviceId.
	 */
	private int length;
	
	/**
	 * Description of the property end
	 */
	private TimeBlockForm date;
	
	/**
	 * Description of the property end
	 */
	private int hour;
	
	/**
	 * Description of the property end
	 */
	private int minute;
	
	
	public WrappedAppointmentOption(){
		
	}
	
	public WrappedAppointmentOption(long newEmployeeId, String newEmployeeName,TimeBlockForm newDate, int newLength,
			long newServiceId, String newServiceName, long newClientId, int newHour, int newMinute){
		
		this.clientId = newClientId;
		this.employeeId = newEmployeeId;
		this.employeeName = newEmployeeName;
		this.date = newDate;
		this.hour = newHour;
		this.minute = newMinute;
		this.length = newLength;
		this.serviceId = newServiceId;
		this.serviceName = newServiceName;

	}

	
	public long getEmployeeId(){
		return this.employeeId;
	}
	
	public void setEmployeeId(long newId){
		this.employeeId = newId;
	}
	
	public String getEmployeeName(){
		return this.employeeName;
	}
	
	public void setEmployeeName(String newName){
		this.employeeName = newName;
	}
	
	public long getServiceId(){
		return this.serviceId;
	}
	
	public void setServiceId(long newId){
		this.serviceId = newId;
	}
	
	
	public String getServiceName(){
		return this.serviceName;
	}
	
	public void setServiceName(String newName){
		this.serviceName = newName;
	}
	
	
	public long getClientId(){
		return this.clientId;
	}
	
	public void setClientId(long newId){
		this.clientId = newId;
	}
	
	
	public int getHour(){
		return this.hour;
	}
	
	public void setHour(int newHour){
		this.hour = newHour;
	}
	
	
	public int getMinute(){
		return this.minute;
	}
	
	public void setMinute(int newMinute){
		this.minute = newMinute;
	}
	
	public int getLength(){
		return this.length;
	}
	
	public void setLength(int newLength){
		this.length = newLength;
	}
	
	
	
	public TimeBlockForm getDate(){
		return this.date;
	}
	
	public void setDate(TimeBlockForm newDate){
		this.date = newDate;
	}
	
}
