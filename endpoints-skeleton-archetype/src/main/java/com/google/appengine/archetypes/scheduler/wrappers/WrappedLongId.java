package com.google.appengine.archetypes.scheduler.wrappers;

public class WrappedLongId {

	private long id;

	public WrappedLongId(){
		
	}
	
	public WrappedLongId(long newId){
		this.id = newId;
	}

	public long getId(){
		return this.id;
	}
	
	public void setId(long newId){
		this.id = newId;
	}
	
	
}
