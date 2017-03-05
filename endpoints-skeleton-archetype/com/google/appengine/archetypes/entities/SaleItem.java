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
	 * Description of the property productId.
	 */
	@Id 
	private long productId;


	/**
	 * Description of the property type.
	 */
	@Index
    private long typeId;
	
	
	public SaleItem(){
		
	}
	

	public SaleItem(long newProductId, String newName, double newPrice, long newTypeId ){
		
		this.productId = newProductId;
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
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public long getTypeId(){
		return this.typeId;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeId(long newTypeId) {
		this.typeId = newTypeId;
	}

}
