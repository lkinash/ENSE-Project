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
	 * Description of the property email.
	 */
	private String email;
	
	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;
	
	
	private HolidayTimeBlockListForm holidayTimeBlockListForm;
	
	
	private TimeBlockListForm timeBlockListForm;
	
	
	public EmployeeForm(){
		
	}
	
	public EmployeeForm(String newName, List<Long> newServiceIds, String newFirstName, String newLastName,
			HolidayTimeBlockListForm newHolidayTimeBlockListForm, TimeBlockListForm newTimeBlockListForm ){
		
		this.firstName = newFirstName;
		this.lastName = newLastName;
		this.serviceIds = newServiceIds;
		this.clearance = AdminClearances.employee;
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
