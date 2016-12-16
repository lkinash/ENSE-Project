/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.entities.SaleItem;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


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

	/**
	 * Description of the property type.
	 */
	@Index
    private Type type;


	public Service(){
		
	}
	
	public Service(boolean newRequiresClearance, long newProductId, String newName, Type newType, double newPrice){
	
		super(newProductId, newName, newPrice);
		
		this.requiresClearance = newRequiresClearance;
		this.type = newType;
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
	
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getName() {
		return super.getName();
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setName(String newName) {
		super.setName(newName);
	}

	/**
	 * Returns price.
	 * @return price 
	 */
	public double getPrice() {
		return super.getPrice();
	}

	/**
	 * Sets a value to attribute price. 
	 * @param newPrice 
	 */
	public void setPrice(double newPrice) {
		super.setPrice(newPrice);
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public Type getType(){
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(Type newType) {
		this.type = newType;
	}

	/**
	 * Returns productId.
	 * @return productId 
	 */
	public long getProductId(){
		return super.getProductId();
	}

	/**
	 * Sets a value to attribute productId. 
	 * @param newProductId 
	 */
	public void setProductId(long newProductId) {
		super.setProductId(newProductId);
	}

}
