/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import java.util.List;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

// End of user code

/**
 * Description of Admin.
 * 
 * @author Lindsey
 */
	@Entity
	public class Admin{
	/**
	 * Description of the property adminClearance.
	 */
	private AdminClearances clearance;

	/**
	 * Description of the property AccountId.
	 */
	@Id
	private long adminId;

	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String userId;
	
	
	/**
	 * Description of the property AccountId.
	 */
	@Index
	private String email;
	

	/**
	 * Description of the property firstName.
	 */
	@Index
    private String firstName;

	/**
	 * Description of the property lastName.
	 */
	@Index
    private String lastName;
	
	
	public Admin(){
		
	}
	
	public Admin(String newFirstName, String newLastName, AdminClearances adminClearance, String newUserId, long newAdminId, String newEmail){
		
		this.userId = newUserId;
		this.clearance = adminClearance;
		this.adminId = newAdminId;
		this.email = newEmail;
		this.firstName = newFirstName;
		this.lastName = newLastName;
		
	}
	
	
	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(String newAccountId) {
		this.userId = newAccountId;
	}
	
	
	/**
	 * Returns Email.
	 * @return Email 
	 */
	public String getEmail() {
		return this.email;
	}

	/**
	 * Sets a value to attribute Email. 
	 * @param newEmail 
	 */
	public void setEmail(String newEmail) {
		this.email = newEmail;
	}
	
	
	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getAdminId() {
		return this.adminId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setAdminId(long newAdminId) {
		this.adminId = newAdminId;
	}
	
	
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
