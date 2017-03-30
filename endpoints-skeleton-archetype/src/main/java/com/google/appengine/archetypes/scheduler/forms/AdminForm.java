/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

/**
 * Description of AdminForm.
 * 
 * @author Lindsey
 */
public class AdminForm {
	
	/**
	 * Description of the property firstName.
	 */
	private String firstName;
	
	/**
	 * Description of the property lastName.
	 */
	private String lastName;


	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;

	/**
	 * Description of the property email.
	 */
	private String email;

	
	public AdminForm(){
		
	}
	
	public AdminForm( AdminClearances newClearances, String newEmail, String newFirstName, String newLastName){
		
		this.email = newEmail;
		this.clearance = newClearances;
		
		this.firstName = newFirstName;
		this.lastName = newLastName;
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

}
