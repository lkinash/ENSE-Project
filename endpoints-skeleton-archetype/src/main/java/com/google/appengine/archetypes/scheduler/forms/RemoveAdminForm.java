package com.google.appengine.archetypes.scheduler.forms;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

public class RemoveAdminForm {


	/**
	 * Description of the property clearance.
	 */
	private AdminClearances clearance;

	/**
	 * Description of the property email.
	 */
	private String email;

	private long adminId;

	
	public RemoveAdminForm(){
		
	}
	
	public RemoveAdminForm(AdminClearances newClearances, String newEmail, long newAdminId){
	
		this.email = newEmail;
		this.clearance = newClearances;
		this.adminId = newAdminId;
		
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
	 * Returns adminId.
	 * @return adminId 
	 */
	public long getAdminId() {
		return this.adminId;
	}

	/**
	 * Sets a value to attribute adminId. 
	 * @param newadminId 
	 */
	public void setAdminId(long newAdminId) {
		this.adminId = newAdminId;
	}

	
}
