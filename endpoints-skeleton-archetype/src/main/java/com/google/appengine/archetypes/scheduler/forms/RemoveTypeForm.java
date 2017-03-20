package com.google.appengine.archetypes.scheduler.forms;

public class RemoveTypeForm {

	private String typeName;

    private long typeId;	


	public RemoveTypeForm(){
		
	}
	
	public RemoveTypeForm(long newTypeId, String newType){
	
		this.typeId = newTypeId;
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
	public long getTypeId(){
		return this.typeId;
	}

	/**
	 * Returns type.
	 * @return type 
	 */
	public void setTypeId(long newTypeId){
		this.typeId = newTypeId;
	}
	
}
