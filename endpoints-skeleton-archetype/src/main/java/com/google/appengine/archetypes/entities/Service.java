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


	public Service(){
		
	}
	
	public Service(boolean newRequiresClearance, long newProductId, String newName, long newTypeId, double newPrice){
	
		super(newProductId, newName, newPrice, newTypeId);
		
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
	public long getTypeId(){
		return super.getTypeId();
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeId(long newTypeId) {
		super.setTypeId(newTypeId);
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
