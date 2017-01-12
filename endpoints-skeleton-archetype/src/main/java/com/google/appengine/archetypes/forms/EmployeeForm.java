/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import java.util.List;

import com.google.api.services.calendar.Calendar;
import com.google.appengine.archetypes.entities.Clearances;
// Start of user code (user defined imports)
import com.google.appengine.archetypes.entities.Service;
// End of user code

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
	private List<Service> serivces;

	/**
	 * Description of the property password.
	 */
	private String password;

	/**
	 * Description of the property email.
	 */
	private String email;

	
	public EmployeeForm(){
		
	}
	
	public EmployeeForm(String newName, Calendar newCalendar, List<Service> newServices){
		
		this.calendar = newCalendar;
		this.name = newName;
		this.serivces = newServices;
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
	 * Returns serivces.
	 * @return serivces 
	 */
	public List<Service> getSerivces() {
		return this.serivces;
	}

	/**
	 * Sets a value to attribute serivces. 
	 * @param newSerivces 
	 */
	public void setSerivces(List<Service> newSerivces) {
		this.serivces = newSerivces;
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


}
