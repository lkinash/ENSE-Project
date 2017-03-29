package com.google.appengine.archetypes.scheduler.forms;

import com.googlecode.objectify.annotation.Index;

public class FindAppointmentForm {


	/**
	 * Description of the property employee.
	 */
	private long employeeId;
	

	/**
	 * Description of the property employee.
	 */
	private String employeeName;
	
	/**
	 * Description of the property type
	 */
	private long typeId;
	
	/**
	 * Description of the property type
	 */
	private String typeName;

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
	 * Description of the property start 
	 */
	private TimeBlockForm startDateRange;
	

	/**
	 * Description of the property end
	 */
	private TimeBlockForm endDateRange;

	
	public FindAppointmentForm(){
		
	}
	
	
	public FindAppointmentForm(	long newEmployeeId, String newEmployeeName, long newTypeId, String newTypeName,
			long newServiceId, String newServiceName, long newClientId, TimeBlockForm newStartDateRange, TimeBlockForm newEndDateRange){
	
		this.employeeId = newEmployeeId;
		this.employeeName = newEmployeeName;
		this.typeId = newTypeId;
		this.typeName = newTypeName;
		this.serviceId = newServiceId;
		this.serviceName = newServiceName;
		this.clientId = newClientId;
		this.startDateRange = newStartDateRange;
		this.endDateRange = newEndDateRange;
		
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
	
	public long getTypeId(){
		return this.typeId;
	}
	
	public void setTypeId(long newId){
		this.typeId = newId;
	}
	
 
	public String getTypeName(){
		return this.typeName;
	}
	
	public void setTypeName(String newName){
		this.typeName = newName;
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
	
	
	public TimeBlockForm getStartDateRange(){
		return this.startDateRange;
	}
	
	public void setStartDateRange(TimeBlockForm newStartDateRange){
		this.startDateRange = newStartDateRange;
	}
	
	
	public TimeBlockForm getEndDateRange(){
		return this.endDateRange;
	}
	
	public void setEndDateRange(TimeBlockForm newEndDateRange){
		this.endDateRange = newEndDateRange;
	}
	
	
}
