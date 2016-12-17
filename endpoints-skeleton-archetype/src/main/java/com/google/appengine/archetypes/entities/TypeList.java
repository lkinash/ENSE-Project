/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.entities;

import java.util.List;

import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.SaleItem;
import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;


/**
 * Description of Service.
 * 
 * @author Lindsey
 */
@Entity
public class TypeList {
	
	private static TypeList instance = null;
	
	@Index
	private String typeListID;
	
	/**
	 * Description of the property type.
	 */
	@Index
    private List<Type> types;
	
	private TypeList(){
	
		types = null;
		typeListID = Constants.TYPE_LIST_ID;
	}
		
	public static synchronized TypeList getInstance(){
		
		if(instance == null){
			instance = new TypeList();
		}
	
		return instance;
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public Boolean getIsAType(Type newType){
		
		for (Type temp : types) {
			if(newType.equals(temp)){				//return true if the type is in the list
				return true;
			}
		}

		return false; 

	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public boolean getIsAType(String newType){
		
		for (Type temp : types) {
			if(newType.equals(temp.getTypeName())){				//return true if the type is in the list
				return true;
			}
		}

		return false; 

	}

	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void addType(Type newType) {
		
		if(!getIsAType(newType)){			//avoid duplicates
			types.add(newType);		
		}
	}
	
	public void removeType(Type newType) {
		types.remove(newType);
	}
	
	public List<Type> getList() {
		return types;
	}
	
	public boolean getTypeIsService(String typeName) throws NoSuchFieldException{
		for (Type temp : types) {
			if(typeName.equals(temp.getTypeName())){
				return temp.getIsService();
			}
		}
		throw new NoSuchFieldException();
	}

	
}
