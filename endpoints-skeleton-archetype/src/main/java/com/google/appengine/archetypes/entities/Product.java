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
 * Description of Product.
 * 
 * @author Lindsey
 */
@Entity
public class Product extends SaleItem {
	/**
	 * Description of the property barcodeId.
	 */
	private int barcodeId;

	public Product(){
		
	}

	public Product(int newBarcodeId, long newProductId, String newName, String newType, double newPrice){
		super(newProductId, newName, newType, newPrice);
		this.barcodeId = newBarcodeId;
	}

	// Start of user code (user defined methods for Product)

	// End of user code
	/**
	 * Returns barcodeId.
	 * @return barcodeId 
	 */
	public int getBarcodeId() {
		return this.barcodeId;
	}

	/**
	 * Sets a value to attribute barcodeId. 
	 * @param newBarcodeId 
	 */
	public void setBarcodeId(int newBarcodeId) {
		this.barcodeId = newBarcodeId;
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
	public String getType(){
		return super.getType();
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(String newType) {
		super.setType(newType);
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
