package com.google.appengine.archetypes.scheduler.forms;

public class GeneralForm {

	/**
	 * Description of the property name.
	 */
	private String stringValue;

	/**
	 * Description of the property price.
	 */
	private int intValue;

	/**
	 * Description of the property type.
	 */
	private long longValue;

	
	public GeneralForm(){
		
	}
	
	public GeneralForm(int newIntValue, String newStringValue, long newLongValue){
		
		this.intValue = newIntValue;
		this.stringValue = newStringValue;
		this.longValue = newLongValue;
	}
	
	/**
	 * Returns name.
	 * @return name 
	 */
	public String getStringValue() {
		return this.stringValue;
	}

	/**
	 * Sets a value to attribute name. 
	 * @param newName 
	 */
	public void setStringValue(String newName) {
		this.stringValue = newName;
	}

	

	/**
	 * Returns type.
	 * @return type 
	 */
	public long getLongValue() {
		return this.longValue;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setLongValue(long newLongValue) {
		this.longValue = newLongValue;
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public int getIntValue(){
		return this.intValue;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setIntValue(int newIntValue) {
		this.intValue = newIntValue;
	}
	
}
