/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import java.util.List;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;

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
	private long userId;


	public Admin(){
		
	}
	
	public Admin(AdminClearances adminClearance, long newUserId){
		
		this.userId = newUserId;
		this.clearance = adminClearance;
	}
	
	
	/**
	 * Returns AccountId.
	 * @return AccountId 
	 */
	public long getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute AccountId. 
	 * @param newAccountId 
	 */
	public void setUserId(long newAccountId) {
		this.userId = newAccountId;
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

}
