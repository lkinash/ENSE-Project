package com.google.appengine.archetypes.scheduler.entities;

import java.util.List;

import com.googlecode.objectify.annotation.Entity;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

/**
 * Description of Service.
 * 
 * @author Lindsey
 */
@Entity
public class TypeWithService {

	/**
	 * Description of the property type.
	 */
	@Index
    private String type;
	
	@Id
	private long typeId;

	/**
	 * Description of the property type.
	 */
	@Index
    private boolean isService;

	
	private List<Service> services;

	
	public TypeWithService(){
		
	}
	
	public TypeWithService(boolean newIsService, String newType, long newTypeId, List<Service> newServices){
	
		this.isService = newIsService;
		this.type = newType;
		this.typeId = newTypeId;
		this.services = newServices;

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
	public void setTypeName(String newType) {
		
		this.type = newType;
		
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
	public boolean getIsService(){
		return this.isService;
	}
	
	/**
	 * Sets a value to attribute type. 
	 * @param newType 
	 */
	public void setTypeId(long newTypeId) {
		
		this.typeId = newTypeId;
		
	}
	
	/**
	 * Returns type.
	 * @return type 
	 */
	public long getTypeId(){
		return this.typeId;
	}
	
	
	public void setServices(List<Service> newServices){
		this.services = newServices;
	}
	
	public List<Service> getService(){
		return this.services;
	}
	
	public void addService(Service newService){
		this.services.add(newService);
	}
	
	public void removeService(Service newService){
		this.services.remove(newService);
	}

}
