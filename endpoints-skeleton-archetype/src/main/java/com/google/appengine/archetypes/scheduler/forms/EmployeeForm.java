/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.scheduler.entities.Clearances;
// Start of user code (user defined imports)
import com.google.appengine.archetypes.scheduler.entities.Service;
// End of user code
import com.google.appengine.archetypes.scheduler.list.AdminClearances;

/**
 * Description of EmployeeForm.
 * 
 * @author Lindsey
 */
public class EmployeeForm {
	/**
	 * Description of the property name.
	 */
	private String name;

	/**
	 * Description of the property calendar.
	 */
	private Calendar calendar;

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


	
	public EmployeeForm(){
		
	}
	
	public EmployeeForm(String newName, Calendar newCalendar, List<Long> newServiceIds){
		
		this.calendar = newCalendar;
		this.name = newName;
		this.serviceIds = newServiceIds;
		this.clearance = AdminClearances.employee;
	}
	
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns calendar.
	 * @return calendar 
	 */
	public Calendar getCalendar() {
		return this.calendar;
	}

	/**
	 * Sets a value to attribute calendar. 
	 * @param newCalendar 
	 */
	public void setCalendar(Calendar newCalendar) {
		this.calendar = newCalendar;
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


}
