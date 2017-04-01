package com.google.appengine.archetypes.scheduler.forms;

public class UpdateTypeForm {

	private String typeName;

    private boolean isService;	

    
	/**
	 * Description of the property serivces.
	 */
	private long typeId;


	public UpdateTypeForm(){
		
	}
	
	public UpdateTypeForm(long newTypeId, boolean newIsService, String newType){
	
		this.isService = newIsService;
		this.typeName = newType;
		this.typeId = newTypeId;
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
	
	

	/**
	 * Returns type.
	 * @return type 
	 */
	public long getTypeId() {
		return this.typeId;
	}

	/**
	 * Sets a value to attribute type. 
	 * @param newtype 
	 */
	public void setTypeId(long newTypeId) {
		this.typeId = newTypeId;
	}
}
