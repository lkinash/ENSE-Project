/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.scheduler.entities;

// Start of user code (user defined imports)

// End of user code

/**
 * Description of SaleItem.
 * 
 * @author Lindsey
 */
public class SaleItem {
	/**
	 * Description of the property name.
	 */
	private String name = "";

	/**
	 * Description of the property price.
	 */
	private double price = 0;

	/**
	 * Description of the property type.
	 */
	private String type = "";

	/**
	 * Description of the property productId.
	 */
	private Long productId = null;


	// Start of user code (user defined methods for SaleItem)

	// End of user code
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
	public Long getProductId() {
		return this.productId;
	}

	/**
	 * Sets a value to attribute productId. 
	 * @param newProductId 
	 */
	public void setProductId(Long newProductId) {
		this.productId = newProductId;
	}

}
