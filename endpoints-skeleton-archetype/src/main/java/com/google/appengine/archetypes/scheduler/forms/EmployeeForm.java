/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.scheduler.entities.Clearances;
// Start of user code (user defined imports)
import com.google.appengine.archetypes.scheduler.entities.Service;
import com.google.appengine.archetypes.scheduler.entities.TimeBlock;
// End of user code
import com.google.appengine.archetypes.scheduler.list.AdminClearances;

/**
 * Description of EmployeeForm.
 * 
 * @author Lindsey
 */
public class EmployeeForm {

	/**
	 * Description of the property firstName.
	 */
	private String firstName;
	
	/**
	 * Description of the property lastName.
	 */
	private String lastName;


	/**
	 * Description of the property serivces.
	 */
	private List<Long> serviceIds;

	/**
	 * Description of the property password.
	 */
	private String password;

	/**
	 * Description of the property email.
	 */
	private String email;
	
	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;


	private List<Long> timeBlockIds;
	
	
	private HolidayTimeBlockListForm holidayTimeBlockListForm;
	
	
	private TimeBlockListForm timeBlockListForm;
	
	
	public EmployeeForm(){
		
	}
	
	public EmployeeForm(String newName, List<Long> newServiceIds, 
			List<Long> newTimeBlockIds, String newFirstName, String newLastName,
			HolidayTimeBlockListForm newHolidayTimeBlockListForm, TimeBlockListForm newTimeBlockListForm ){
		
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.serviceIds = newServiceIds;
		this.clearance = AdminClearances.employee;
		this.timeBlockIds = newTimeBlockIds;
		this.holidayTimeBlockListForm = newHolidayTimeBlockListForm;
		this.timeBlockListForm = newTimeBlockListForm;
	}



	/**
	 * Returns services.
	 * @return services 
	 */
	public List<Long> getServiceIds() {
		return serviceIds;
	}

	/**
	 * Sets a value to attribute services. 
	 * @param newServices 
	 */
	public void setServiceIds(List<Long> newServiceIds) {
		this.serviceIds = newServiceIds;
	}
	
	/**
	 * Returns email.
	 * @return email 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute email. 
	 * @param newEmail 
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	/**
	 * Returns password.
	 * @return password 
	 */
	public String getPassword() {
		return this.password;
	}

	/**
	 * Sets a value to attribute password. 
	 * @param newPassword 
	 */
	public void setPassword(String newPassword) {
		this.password = newPassword;
	}

	
	/**
	 * Returns clearance.
	 * @return clearance 
	 */
	public AdminClearances getClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute clearance. 
	 * @param newClearance 
	 */
	public void setClearance(AdminClearances newClearance) {
		this.clearance = newClearance;
	}

	public void setTimeBlockIds(List<Long> newTimeBlockId){
		this.timeBlockIds = newTimeBlockId;
	}
	
	public List<Long> getTimeBlockIds(){
		return this.timeBlockIds;
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
	 * Returns HolidayTimeBlockListForm.
	 * @return HolidayTimeBlockListForm 
	 */
	public HolidayTimeBlockListForm getHolidayTimeBlockListForm() {
		return this.holidayTimeBlockListForm;
	}

	/**
	 * Sets a value to attribute HolidayTimeBlockListForm. 
	 * @param newHolidayTimeBlockListForm 
	 */
	public void setHolidayTimeBlockListForm(HolidayTimeBlockListForm newHolidayTimeBlockListForm) {
		this.holidayTimeBlockListForm = newHolidayTimeBlockListForm;
	}


	/**
	 * Returns TimeBlockListForm.
	 * @return TimeBlockListForm 
	 */
	public TimeBlockListForm getTimeBlockListForm() {
		return this.timeBlockListForm;
	}

	/**
	 * Sets a value to attribute TimeBlockListForm. 
	 * @param newTimeBlockListForm 
	 */
	public void setTimeBlockListForm(TimeBlockListForm newTimeBlockListForm) {
		this.timeBlockListForm = newTimeBlockListForm;
	}

	

	
}
