/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.entities.SaleItem;
import com.google.appengine.archetypes.nuImage.ProductType;
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
    private int barcodeId;
	
	/**
	 * Description of the property type.
	 */
	@Index
    private ProductType type;

	
	public Product(){
		
	}

	public Product(int newBarcodeId, long newProductId, String newName, ProductType newType, double newPrice){
		super(newProductId, newName, newPrice);
		this.barcodeId = newBarcodeId;
		this.type = newType;
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
	 * Returns type.
	 * @return type 
	 */
	public ProductType getType() {
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(ProductType newType) {
		this.type = newType;
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
