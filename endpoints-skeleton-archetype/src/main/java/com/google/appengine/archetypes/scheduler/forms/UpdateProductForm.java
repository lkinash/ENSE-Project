package com.google.appengine.archetypes.scheduler.forms;

public class UpdateProductForm {


	/**
	 * Description of the property serivces.
	 */
	private long productId;

	/**
	 * Description of the property type.
	 */
	private long typeId;

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


	
	public UpdateProductForm(){
		
	}

	public UpdateProductForm(long newProductId, long newTypeId, String newName, int newBarcodeNumber, double newPrice){
		
		this.barcodeNumber = newBarcodeNumber;
		this.name = newName;
		this.price = newPrice;
		this.typeId = newTypeId;
		this.productId = newProductId;
		
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public long getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeId(long newTypeId) {
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
	
	

	/**
	 * Returns product.
	 * @return product 
	 */
	public long getProductId() {
		return this.productId;
	}

	/**
	 * Sets a value to attribute product. 
	 * @param newproduct 
	 */
	public void setProductId(long newProductId) {
		this.productId = newProductId;
	}

	
}
