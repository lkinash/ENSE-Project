/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

import com.google.scheduler.entities.SaleItem;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Service.
 * 
 * @author Lindsey
 */
public class Service extends SaleItem {
	/**
	 * Description of the property requiresClearance.
	 */
	private Boolean requiresClearance = Boolean.FALSE;

	// Start of user code (user defined attributes for Service)

	// End of user code

	/**
	 * Description of the method setRequireClearance.
	 * @param requireClearance 
	 */
	public void setRequireClearance(Boolean requireClearance) {
		// Start of user code for method setRequireClearance
		// End of user code
	}

	/**
	 * Description of the method getRequireClearance.
	 * @return 
	 */
	public Boolean getRequireClearance() {
		// Start of user code for method getRequireClearance
		Boolean getRequireClearance = Boolean.FALSE;
		return getRequireClearance;
		// End of user code
	}

	// Start of user code (user defined methods for Service)

	// End of user code
	/**
	 * Returns requiresClearance.
	 * @return requiresClearance 
	 */
	public Boolean getRequiresClearance() {
		return this.requiresClearance;
	}

	/**
	 * Sets a value to attribute requiresClearance. 
	 * @param newRequiresClearance 
	 */
	public void setRequiresClearance(Boolean newRequiresClearance) {
		this.requiresClearance = newRequiresClearance;
	}

}
