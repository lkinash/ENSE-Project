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
	
	public Type(boolean newIsService, String newType, TypeList list) throws NoSuchFieldException{
	

		this.isService = newIsService;
		
		if(list.getIsAType(newType))
			this.type = newType;
		else
			throw new NoSuchFieldException();
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
	public void setType(String newType, TypeList list) throws NoSuchFieldException {
		
		if(list.getIsAType(newType))
			this.type = newType;
		else
			throw new NoSuchFieldException();
		
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public Boolean getIsService(){
		return this.isService;
	}


	
}
