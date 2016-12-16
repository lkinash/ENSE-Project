/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.forms;

import com.google.appengine.archetypes.entities.Type;



/**
 * Description of ProductForm.
 * 
 * @author Lindsey
 */
public class ProductForm {
	/**
	 * Description of the property type.
	 */
	private Type type;

	/**
	 * Description of the property name.
	 */
	private String name;

	/**
	 * Description of the property price.
	 */
	private double price;

	/**
	 * Description of the property barcodeNumber.
	 */
	private int barcodeNumber;


	// Start of user code (user defined methods for ProductForm)

	// End of user code
	
	public ProductForm(){
		
	}

	public ProductForm(Type newType, String newName, int newBarcodeNumber, double newPrice){
		
		this.barcodeNumber = newBarcodeNumber;
		this.name = newName;
		this.price = newPrice;
		this.type = newType;
		
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public Type getType() {
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
	 * Returns barcodeNumber.
	 * @return barcodeNumber 
	 */
	public int getBarcodeNumber() {
		return this.barcodeNumber;
	}

	/**
	 * Sets a value to attribute barcodeNumber. 
	 * @param newBarcodeNumber 
	 */
	public void setBarcodeNumber(int newBarcodeNumber) {
		this.barcodeNumber = newBarcodeNumber;
	}

}
