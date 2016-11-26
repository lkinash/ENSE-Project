/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of SaleItem.
 * 
 * @author Lindsey
 */
@Entity
public class SaleItem {
	/**
	 * Description of the property name.
	 */
	@Index
    private String name;

	/**
	 * Description of the property price.
	 */
	@Index
    private double price;

	/**
	 * Description of the property type.
	 */
	@Index
    private String type;

	/**
	 * Description of the property productId.
	 */
	@Id 
	private long productId;


	// Start of user code (user defined methods for SaleItem)

	// End of user code
	
	public SaleItem(){
		
	}
	

	public SaleItem(long newProductId, String newName, String newType, double newPrice){
		
		this.productId = newProductId;
		this.name = newName;
		this.type = newType;
		this.price = newPrice;
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

	/**
	 * Returns productId.
	 * @return productId 
	 */
	public long getProductId() {
		return this.productId;
	}

	/**
	 * Sets a value to attribute productId. 
	 * @param newProductId 
	 */
	public void setProductId(long newProductId) {
		this.productId = newProductId;
	}

}
