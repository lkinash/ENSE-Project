/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;


import com.google.api.services.calendar.Calendar;
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
public class Employee extends Account {
	/**
	 * Description of the property calendar.
	 */
	@Index
	private Calendar calendar;

	/**
	 * Description of the property firstName.
	 */
	@Index
	private String name;

	
	public Employee(){
		
	}
	
	public Employee(Calendar newCalendar, String newName, String newEmail, String newPassword, long newUserId){
		
		super(newEmail, newPassword, newUserId);
		
		this.calendar = newCalendar;
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
	 * Description of the method getUserId.
	 * @return 
	 */
	public long getUserId() {
		return super.getUserId();
	}
	
	public void setUserId(long newUserId) {
		super.setUserId(newUserId);
	}

	/**
	 * Description of the method getEmail.
	 * @return 
	 */
	public String getEmail() {
		return super.getEmail();
	}

	/**
	 * Description of the method setEmail.
	 * @param email 
	 */
	public void setEmail(String newEmail) {
		super.setEmail(newEmail);
	}


	/**
	 * Description of the method getPassword.
	 * @return 
	 */
	public String getPassword() {
		return super.getPassword();
	}

	/**
	 * Description of the method setPassword.
	 * @param password 
	 */
	public void setPassword(String newPassword) {
		super.setPassword(newPassword);
	}



}
