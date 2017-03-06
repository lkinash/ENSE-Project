package com.google.appengine.archetypes.scheduler.wrappers;

import com.google.appengine.archetypes.scheduler.list.Status;

public class WrapperStatus {

	Status status;
	
	public WrapperStatus(){
		
	}
	
	public WrapperStatus(Status newStatus){
		
		this.status = newStatus;
	}
	
	public Status getStatus(){
		return this.status;
	}
	
	public void setStatus(Status newStatus){
		this.status = newStatus;
	}
	
}
