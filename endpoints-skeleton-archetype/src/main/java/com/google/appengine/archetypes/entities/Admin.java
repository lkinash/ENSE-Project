/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import java.util.List;

import com.google.appengine.archetypes.list.AdminClearances;
import com.google.appengine.archetypes.entities.Account;
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
public class Admin extends Account {
	/**
	 * Description of the property adminClearance.
	 */
	private AdminClearances clearance;


	public Admin(){
		
	}
	
	public Admin(AdminClearances adminClearance, String newEmail, String newPassword, long newUserId){
		
		super(newEmail, newPassword, newUserId);
		
		this.clearance = adminClearance;
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
	// Start of user code (user defined methods for Admin)

	// End of user code
	/**
	 * Returns adminClearance.
	 * @return adminClearance 
	 */
	public AdminClearances getAdminClearance() {
		return this.clearance;
	}

	/**
	 * Sets a value to attribute adminClearance. 
	 * @param newAdminClearance 
	 */
	public void setAdminClearance(AdminClearances newAdminClearance) {
		this.clearance = newAdminClearance;
	}

}
