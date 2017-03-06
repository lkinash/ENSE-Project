/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.forms;

import com.google.appengine.archetypes.scheduler.entities.Type;


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
	private long typeId;

	private int defaultLength;
	
	public ServiceForm(){
		
	}

	public ServiceForm(int newDefaultLength, String newName, long newTypeId, double newPrice, boolean newClearanceRequired){
		
		this.clearanceRequired = newClearanceRequired;
		this.name = newName;
		this.price = newPrice;
		this.typeId = newTypeId;
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
	public long getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeId(long newTypeId) {
		this.typeId = newTypeId;
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public int getDefaultLength(){
		return defaultLength;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setDefaultLength(int newDefaultLength) {
		this.defaultLength = newDefaultLength;
	}
	
	
}
