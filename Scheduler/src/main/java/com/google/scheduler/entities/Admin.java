/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

import com.google.scheduler.entities.AdminClearances;
import com.google.scheduler.entities.User;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Admin.
 * 
 * @author Lindsey
 */
public class Admin extends User {
	/**
	 * Description of the property adminClearance.
	 */
	private AdminClearances adminClearance = null;

	// Start of user code (user defined attributes for Admin)

	// End of user code

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
	 * Description of the method getClearance.
	 * @return 
	 */
	public AdminClearances getClearance() {
		// Start of user code for method getClearance
		AdminClearances getClearance = null;
		return getClearance;
		// End of user code
	}

	/**
	 * Description of the method setClearance.
	 * @param admincClearance 
	 */
	public void setClearance(AdminClearances admincClearance) {
		// Start of user code for method setClearance
		// End of user code
	}

	/**
	 * Description of the method getUserId.
	 * @return 
	 */
	public Long getUserId() {
		// Start of user code for method getUserId
		Long getUserId = null;
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
		return this.adminClearance;
	}

	/**
	 * Sets a value to attribute adminClearance. 
	 * @param newAdminClearance 
	 */
	public void setAdminClearance(AdminClearances newAdminClearance) {
		this.adminClearance = newAdminClearance;
	}

}
