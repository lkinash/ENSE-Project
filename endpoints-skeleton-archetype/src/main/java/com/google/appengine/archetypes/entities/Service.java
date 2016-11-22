/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.entities.SaleItem;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Service.
 * 
 * @author Lindsey
 */
@Entity
public class Service extends SaleItem {
	/**
	 * Description of the property requiresClearance.
	 */
	private boolean requiresClearance;

	// Start of user code (user defined attributes for Service)

	// End of user code

	public Service(){
		
	}
	
	public Service(boolean newRequiresClearance, Long newProductId, String newName, String newType, double newPrice){
	
		super(newProductId, newName, newType, newPrice);
		
		this.requiresClearance = newRequiresClearance;
	}
	
	/**
	 * Returns requiresClearance.
	 * @return requiresClearance 
	 */
	public boolean getRequiresClearance() {
		return this.requiresClearance;
	}

	/**
	 * Sets a value to attribute requiresClearance. 
	 * @param newRequiresClearance 
	 */
	public void setRequiresClearance(boolean newRequiresClearance) {
		this.requiresClearance = newRequiresClearance;
	}

}
