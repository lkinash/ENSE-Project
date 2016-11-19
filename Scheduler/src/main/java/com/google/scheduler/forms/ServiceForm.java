/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.forms;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of ServiceForm.
 * 
 * @author Lindsey
 */
public class ServiceForm {
	/**
	 * Description of the property name.
	 */
	private String name = "";

	/**
	 * Description of the property clearanceRequired.
	 */
	private Boolean clearanceRequired = Boolean.FALSE;

	/**
	 * Description of the property price.
	 */
	private double price = 0;

	/**
	 * Description of the property type.
	 */
	private String type = "";

	// Start of user code (user defined methods for ServiceForm)

	// End of user code
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getName() {
		return this.name;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setName(String newName) {
		this.name = newName;
	}

	/**
	 * Returns clearanceRequired.
	 * @return clearanceRequired 
	 */
	public Boolean getClearanceRequired() {
		return this.clearanceRequired;
	}

	/**
	 * Sets a value to attribute clearanceRequired. 
	 * @param newClearanceRequired 
	 */
	public void setClearanceRequired(Boolean newClearanceRequired) {
		this.clearanceRequired = newClearanceRequired;
	}

	/**
	 * Returns price.
	 * @return price 
	 */
	public double getPrice() {
		return this.price;
	}

	/**
	 * Sets a value to attribute price. 
	 * @param newPrice 
	 */
	public void setPrice(double newPrice) {
		this.price = newPrice;
	}


	/**
	 * Returns type.
	 * @return type 
	 */
	public String getType() {
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(String newType) {
		this.type = newType;
	}

}
