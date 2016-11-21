/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.google.appengine.archetypes.entities.SaleItem;
// Start of user code (user defined imports)

// End of user code

/**
 * Description of Product.
 * 
 * @author Lindsey
 */
public class Product extends SaleItem {
	/**
	 * Description of the property barcodeId.
	 */
	private int barcodeId = 0;

	public Product(){
		
	}

	public Product(int newBarcodeId){
		super();
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
