/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import java.util.List;

import com.google.appengine.archetypes.list.AdminClearances;
import com.google.appengine.archetypes.entities.User;
// Start of user code (user defined imports)

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

// End of user code

/**
 * Description of Admin.
 * 
 * @author Lindsey
 */
@Entity
public class Admin extends User {
	/**
	 * Description of the property adminClearance.
	 */
	private List<AdminClearances> adminClearance;

	// Start of user code (user defined attributes for Admin)

	// End of user code

	public Admin(){
		
	}
	
	public Admin(List<AdminClearances> adminClearance, String newEmail, String newPassword, String newUserId){
		
		super(newEmail, newPassword, newUserId);
		
		this.adminClearance = adminClearance;
	}
	
	/**
	 * Description of the method getPassword.
	 * @return 
	 */
	public String getPassword() {
		// Start of user code for method getPassword
		String getPassword = "";
		return getPassword;
		// End of user code
	}

	/**
	 * Description of the method setPassword.
	 * @param password 
	 */
	public void setPassword(String password) {
		// Start of user code for method setPassword
		// End of user code
	}


	/**
	 * Description of the method getUserId.
	 * @return 
	 */
	public String getUserId() {
		// Start of user code for method getUserId
		String getUserId = null;
		return getUserId;
		// End of user code
	}

	/**
	 * Description of the method getEmail.
	 * @return 
	 */
	public String getEmail() {
		// Start of user code for method getEmail
		String getEmail = "";
		return getEmail;
		// End of user code
	}

	/**
	 * Description of the method setEmail.
	 * @param email 
	 */
	public void setEmail(String email) {
		// Start of user code for method setEmail
		// End of user code
	}

	/**
	 * Description of the method setUserId.
	 * @param adminId 
	 */
	public void setUserId(Long adminId) {
		// Start of user code for method setUserId
		// End of user code
	}

	// Start of user code (user defined methods for Admin)

	// End of user code
	/**
	 * Returns adminClearance.
	 * @return adminClearance 
	 */
	public AdminClearances getAdminClearance() {
		return null;
	}

	/**
	 * Sets a value to attribute adminClearance. 
	 * @param newAdminClearance 
	 */
	public void setAdminClearance(List<AdminClearances> newAdminClearance) {
		this.adminClearance = newAdminClearance;
	}

}
