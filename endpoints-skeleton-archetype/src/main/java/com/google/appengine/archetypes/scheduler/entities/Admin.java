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
	

	public Admin(){
		
	}
	
	public Admin(AdminClearances adminClearance, String newUserId, long newAdminId){
		
		this.userId = newUserId;
		this.clearance = adminClearance;
		this.adminId = newAdminId;
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

}
