/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import java.util.Date;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.google.appengine.archetypes.scheduler.entities.Service;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Clearances.
 * 
 * @author Lindsey
 */
@Entity
public class Clearances {
	
	@Id 
	private long clearanceId; 
	/**
	 * Description of the property service.
	 */
	@Index
    private long serviceId;

	/**
	 * Description of the property renewalDate.
	 */
	@Index
    private Date renewalDate ;

	
	public Clearances(){
		
	}
	
	public Clearances(long newService, Date renewalDate, long newClearanceId){
		this.serviceId = newService;
		this.renewalDate = renewalDate;
		this.clearanceId = newClearanceId;
	}
	
	// Start of user code (user defined methods for Clearances)

	// End of user code
	/**
	 * Returns service.
	 * @return service 
	 */
	public long getClearanceId() {
		return this.clearanceId;
	}

	/**
	 * Sets a value to attribute service. 
	 * @param newService 
	 */
	public void setClearanceId(long newClearanceId) {
		this.clearanceId = newClearanceId;
	}
	/**
	 * Returns service.
	 * @return service 
	 */
	public long getService() {
		return this.serviceId;
	}

	/**
	 * Sets a value to attribute service. 
	 * @param newService 
	 */
	public void setService(long newService) {
		this.serviceId = newService;
	}

	/**
	 * Returns renewalDate.
	 * @return renewalDate 
	 */
	public Date getRenewalDate() {
		return this.renewalDate;
	}

	/**
	 * Sets a value to attribute renewalDate. 
	 * @param newRenewalDate 
	 */
	public void setRenewalDate(Date newRenewalDate) {
		this.renewalDate = newRenewalDate;
	}

}
