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
	private List<Long> holidayTimeBlockIds;
	
	/**
	 * Description of the property AccountId.
	 */
	private List<TimeBlock> holidayTimeBlocks;
	
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String email;
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private List<Long> weekdayTimeBlockIds;
	
	/**
	 * Description of the property AccountId.
	 */
	private List<DayTimeBlocks> weekdayTimeBlocks;
	
	
	/**
	 * Description of the property firstName.
	 */
	@Index
    private String firstName;

	/**
	 * Description of the property lastName.
	 */
	@Index
    private String lastName;

	
	
	public Employee(){
		
	}
	
	public Employee(String newCalendarId, String newFirstName, String newLastName, String newUserId,  String newEmail,
			List<Long> newServiceIds, long newEmployeeId, List<Long> newHolidayTimeBlockIds,  List<Long> newWeekdayTimeBlockIds){
		
		this.userId = newUserId;
		this.calendarId = newCalendarId;
		this.serviceIds = newServiceIds;
		this.employeeId = newEmployeeId;
		this.clearance = AdminClearances.employee;
		this.holidayTimeBlockIds = newHolidayTimeBlockIds;
		this.email = newEmail;
		this.weekdayTimeBlockIds = newWeekdayTimeBlockIds;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.weekdayTimeBlocks = null;
		this.holidayTimeBlocks = null;
		
	}

	
	/**
	 * Returns Email.
	 * @return Email 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute Email. 
	 * @param newEmail 
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
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
	public String getFirstName() {
		return this.firstName;
	}

	/**
	 * Sets a value to attribute firstName. 
	 * @param newFirstName 
	 */
	public void setFirstName(String newFirstName) {
		this.firstName = newFirstName;
	}

	/**
	 * Returns lastName.
	 * @return lastName 
	 */
	public String getLastName() {
		return this.lastName;
	}

	/**
	 * Sets a value to attribute lastName. 
	 * @param newLastName 
	 */
	public void setLastName(String newLastName) {
		this.lastName = newLastName;
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


	public void setHolidayTimeBlockIds(List<Long> newTimeBlockId){
		this.holidayTimeBlockIds = newTimeBlockId;
	}
	
	public List<Long> getHolidayTimeBlockIds(){
		return this.holidayTimeBlockIds;
	}
	
	public void addTimeBlock(long newTimeBlockId){
		this.holidayTimeBlockIds.add(newTimeBlockId);
	}
	
	public void removeTimeBlock(long newTimeBlockId){
		this.holidayTimeBlockIds.remove(newTimeBlockId);
	}
	
	
	public void setWeekdayTimeBlockIds(List<Long> newTimeBlockId){
		this.weekdayTimeBlockIds = newTimeBlockId;
	}
	
	public List<Long> getWeekdayTimeBlockIds(){
		return this.weekdayTimeBlockIds;
	}
	
	
	/**
	 * Description of the method setdayTimeBlocks.
	 * @param dayTimeBlockIds 
	 */
	public void setDayTimeBlocks(List<DayTimeBlocks> newDayTimeBlocks) {
		this.weekdayTimeBlocks = newDayTimeBlocks;
	}

	/**
	 * Description of the method getdayTimeBlocks.
	 * @return 
	 */
	public List<DayTimeBlocks> getDayTimeBlocks() {
		return this.weekdayTimeBlocks;
	}


	/**
	 * Description of the method setTimeBlocks.
	 * @param TimeBlocks 
	 */
	public void setTimeBlocks(List<TimeBlock> newTimeBlocks) {
		this.holidayTimeBlocks = newTimeBlocks;
	}

	/**
	 * Description of the method getdayTimeBlocks.
	 * @return 
	 */
	public List<TimeBlock> getTimeBlocks() {
		return this.holidayTimeBlocks;
	}

	
}
