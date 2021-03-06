/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.scheduler.entities;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.scheduler.entities.SaleItem;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
// Start of user code (user defined imports)
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

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
	@Index
    private int barcodeNumber;
	
	
	public Product(){
		
	}

	public Product(int newBarcodeNumber, long newProductId, String newName, long newTypeId, double newPrice){
		super(newProductId, newName, newPrice, newTypeId);
		this.barcodeNumber = newBarcodeNumber;
	}


	
	/**
	 * Returns barcodeId.
	 * @return barcodeId 
	 */
	public int getBarcodeNumber() {
		return this.barcodeNumber;
	}

	/**
	 * Sets a value to attribute barcodeId. 
	 * @param newBarcodeId 
	 */
	public void setBarcodeNumber(int newBarcodeNumber) {
		this.barcodeNumber = newBarcodeNumber;
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
