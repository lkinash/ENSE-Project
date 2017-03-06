/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.archetypes.scheduler.entities.Clearances;

/**
 * Description of AdminForm.
 * 
 * @author Lindsey
 */
public class AdminForm {
	/**
	 * Description of the property password.
	 */
	private String password;

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
	
	public AdminForm(String newPassword, AdminClearances newClearances, String newEmail){
		
		this.password = newPassword;
		this.email = newEmail;
		this.clearance = newClearances;
	}

	// Start of user code (user defined methods for AdminForm)

	// End of user code
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


}
