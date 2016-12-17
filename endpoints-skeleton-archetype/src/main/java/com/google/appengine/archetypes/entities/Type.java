/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Index;


/**
 * Description of Service.
 * 
 * @author Lindsey
 */
@Entity
public class Type {

	/**
	 * Description of the property type.
	 */
	@Index
    private String type;

	/**
	 * Description of the property type.
	 */
	@Index
    private boolean isService;

	

	public Type(){
		
	}
	
	public Type(boolean newIsService, String newType){
	
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
	public void setType(String newType) {
		
		this.type = newType;
		
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public boolean getIsService(){
		return this.isService;
	}


	
}
