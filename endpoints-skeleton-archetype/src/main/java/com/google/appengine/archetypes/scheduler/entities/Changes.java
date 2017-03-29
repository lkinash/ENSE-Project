package com.google.appengine.archetypes.scheduler.entities;

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
	 * Description of the property userId.
	 */
	@Index
	private String userId;

	private String adminName;
	
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
	
	public Changes(String newTimeStamp, String newUserId, long newChangeId, String newChange, String newAdminName){
	
		this.userId = newUserId;
		this.change = newChange;
		this.changeId = newChangeId;
		this.timeStamp = newTimeStamp;
		this.adminName = newAdminName;
	
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
	 * Returns userId.
	 * @return userId 
	 */
	public String getUserId() {
		return this.userId;
	}

	/**
	 * Sets a value to attribute userId. 
	 * @param newUserId 
	 */
	public void setUserId(String newUserId) {
		this.userId = newUserId;
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


	/**
	 * Returns change.
	 * @return change 
	 */
	public String getAdminName() {
		return this.adminName;
	}

	/**
	 * Sets a value to attribute change. 
	 * @param newChange 
	 */
	public void setAdminName(String newAdminName) {
		this.adminName = newAdminName;
	}
	
}
