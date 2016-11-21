/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import java.util.Date;

import com.google.appengine.archetypes.entities.Service;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Clearances.
 * 
 * @author Lindsey
 */
public class Clearances {
	/**
	 * Description of the property service.
	 */
	private Service service = null;

	/**
	 * Description of the property renewalDate.
	 */
	private Date renewalDate = null;

	
	public Clearances(){
		
	}
	
	public Clearances(Service service, Date renewalDate){
		this.service = service;
		this.renewalDate = renewalDate;
	}
	
	// Start of user code (user defined methods for Clearances)

	// End of user code
	/**
	 * Returns service.
	 * @return service 
	 */
	public Service getService() {
		return this.service;
	}

	/**
	 * Sets a value to attribute service. 
	 * @param newService 
	 */
	public void setService(Service newService) {
		this.service = newService;
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
