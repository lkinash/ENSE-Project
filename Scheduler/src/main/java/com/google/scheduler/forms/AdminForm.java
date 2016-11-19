/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.forms;

import java.util.List;

import com.google.scheduler.entities.AdminClearances;
import com.google.scheduler.entities.Clearances;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of AdminForm.
 * 
 * @author Lindsey
 */
public class AdminForm {
	/**
	 * Description of the property password.
	 */
	private String password = "";

	/**
	 * Description of the property clearance.
	 */
	private List<AdminClearances> clearance = null;

	/**
	 * Description of the property email.
	 */
	private String email = "";


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
	public List<AdminClearances> getClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute clearance. 
	 * @param newClearance 
	 */
	public void setClearance(List<AdminClearances> newClearance) {
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
