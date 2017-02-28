package com.google.appengine.archetypes.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Description of Account.
 * 
 * @author Lindsey
 */
@Entity
public class Changes {

	/**
	 * Description of the property timeStamp.
	 */
	@Index
	private String timeStamp;
	
	/**
	 * Description of the property adminId.
	 */
	@Index
	private long adminId;

	/**
	 * Description of the property changeId.
	 */
	@Id
	private long changeId;
	
	/**
	 * Description of the property change.
	 */
	@Index
	private String change;
	
	public Changes(){
		
	}
	
	public Changes(String newTimeStamp, long newAdminId, long newChangeId, String newChange){
	
		this.adminId = newAdminId;
		this.change = newChange;
		this.changeId = newChangeId;
		this.timeStamp = newTimeStamp;
	
	}
	
	
	
	/**
	 * Returns timeStamp.
	 * @return timeStamp 
	 */
	public String getTimeStamp() {
		return this.timeStamp;
	}

	/**
	 * Sets a value to attribute timeStamp. 
	 * @param newTimeStamp 
	 */
	public void setTimeStamp(String newTimeStamp) {
		this.timeStamp = newTimeStamp;
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
	 * @param newAdminId 
	 */
	public void setAdminId(long newAdminId) {
		this.adminId = newAdminId;
	}
	
	/**
	 * Returns changeId.
	 * @return changeId 
	 */
	public long getChangeId() {
		return this.changeId;
	}

	/**
	 * Sets a value to attribute changeId. 
	 * @param newChangeId 
	 */
	public void setChangeId(long newChangeId) {
		this.changeId = newChangeId;
	}
	
	/**
	 * Returns change.
	 * @return change 
	 */
	public String getChange() {
		return this.change;
	}

	/**
	 * Sets a value to attribute change. 
	 * @param newChange 
	 */
	public void setChange(String newChange) {
		this.change = newChange;
	}
	
}
