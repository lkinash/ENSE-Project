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

	public Product(int newBarcodeId, Long newProductId, String newName, String newType, double newPrice){
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

}
