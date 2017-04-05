package com.google.appengine.archetypes.scheduler.wrappers;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

public class WrappedClearanceWithId {


	private long id;
	
	private AdminClearances clearance;

	public WrappedClearanceWithId(){
		
	}
	
	public WrappedClearanceWithId(long newId, AdminClearances newAdmin){
		
		this.id = newId;
		this.clearance = newAdmin;
		
	}

	public long getId(){
		return this.id;
	}
	
	public void setId(long newId){
		this.id = newId;
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
