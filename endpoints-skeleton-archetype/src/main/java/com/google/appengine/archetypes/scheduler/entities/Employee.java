/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;


import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;

// End of user code

/**
 * Description of Employee.
 * 
 * @author Lindsey
 */
	@Entity
	public class Employee {

	/**
	 * Description of the property calendar.
	 */
	@Index
	private String calendarId;

	/**
	 * Description of the property firstName.
	 */
	@Index
	private String name;

	/**
	 * Description of the property firstName.
	 */
	@Index
	private List<Long> serviceIds;

	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String userId;
	
	/**
	 * Description of the property AccountId.
	 */
	@Id
	private long employeeId;
	
	/**
	 * Description of the property adminClearance.
	 */
	private AdminClearances clearance;
	
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private List<Long> timeBlockIds;
	
	
	
	public Employee(){
		
	}
	
	public Employee(String newCalendarId, String newName, String newUserId, List<Long> newServiceIds, long newEmployeeId, List<Long> newTimeBlockIds){
		
		this.userId = newUserId;
		this.calendarId = newCalendarId;
		this.name = newName;
		this.serviceIds = newServiceIds;
		this.employeeId = newEmployeeId;
		this.clearance = AdminClearances.employee;
		this.timeBlockIds = newTimeBlockIds;
	}

	
	/**
	 * Description of the method setServices.
	 * @param serviceIds 
	 */
	public void setServiceIds(List<Long> newServices) {
		this.serviceIds = newServices;
	}

	/**
	 * Description of the method getServices.
	 * @return 
	 */
	public List<Long> getServiceIds() {
		return this.serviceIds;
	}
	
	
	/**
	 * Returns calendarId.
	 * @return calendarId 
	 */
	public String getCalendarId() {
		return this.calendarId;
	}

	/**
	 * Sets a value to attribute calendarId. 
	 * @param newCalendarId 
	 */
	public void setCalendarId(String newCalendarId) {
		this.calendarId = newCalendarId;
	}

	/**
	 * Returns firstName.
	 * @return firstName 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute firstName. 
	 * @param newFirstName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(String newAccountId) {
		this.userId = newAccountId;
	}

	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getEmployeeId() {
		return this.employeeId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setEmployeeId(long newEmployeeId) {
		this.employeeId = newEmployeeId;
	}
	
	/**
	 * Returns adminClearance.
	 * @return adminClearance 
	 */
	public AdminClearances getAdminClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute adminClearance. 
	 * @param newAdminClearance 
	 */
	public void setAdminClearance(AdminClearances newAdminClearance) {
		this.clearance = newAdminClearance;
	}


	public void setTimeBlockIds(List<Long> newTimeBlockId){
		this.timeBlockIds = newTimeBlockId;
	}
	
	public List<Long> getTimeBlockIds(){
		return this.timeBlockIds;
	}
	
	public void addTimeBlock(long newTimeBlockId){
		this.timeBlockIds.add(newTimeBlockId);
	}
	
	public void removeTimeBlock(long newTimeBlockId){
		this.timeBlockIds.remove(newTimeBlockId);
	}
}
