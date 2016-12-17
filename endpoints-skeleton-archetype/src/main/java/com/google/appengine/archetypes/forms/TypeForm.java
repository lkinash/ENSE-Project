package com.google.appengine.archetypes.forms;

import com.google.appengine.archetypes.entities.TypeList;
import com.googlecode.objectify.annotation.Index;

public class TypeForm {

	private String type;

    private boolean isService;	

	public TypeForm(){
		
	}
	
	public TypeForm(boolean newIsService, String newType){
	
		this.isService = newIsService;
		this.type = newType;
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public String getTypeName(){
		return this.type;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setType(String newType){
		this.type = newType;
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
