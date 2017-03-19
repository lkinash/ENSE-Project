package com.google.appengine.archetypes.scheduler.forms;

public class TypeForm {

	private String typeName;

    private boolean isService;	


	public TypeForm(){
		
	}
	
	public TypeForm(boolean newIsService, String newType){
	
		this.isService = newIsService;
		this.typeName = newType;
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public String getTypeName(){
		return this.typeName;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeName(String newType){
		this.typeName = newType;
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public boolean getIsService(){
		return this.isService;
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public void setIsService(boolean newIsService){
		this.isService = newIsService;
	}

	
}
