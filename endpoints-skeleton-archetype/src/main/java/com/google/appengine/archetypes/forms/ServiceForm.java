/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

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
	private String name;

	/**
	 * Description of the property clearanceRequired.
	 */
	private boolean clearanceRequired;

	/**
	 * Description of the property price.
	 */
	private double price;

	/**
	 * Description of the property type.
	 */
	private String type;

	// Start of user code (user defined methods for ServiceForm)

	// End of user code
	
	public ServiceForm(){
		
	}

	public ServiceForm(String newName, String newType, double newPrice, boolean newClearanceRequired){
		
		this.clearanceRequired = newClearanceRequired;
		this.name = newName;
		this.price = newPrice;
		this.type = newType;
	}
	
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
