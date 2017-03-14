package com.google.appengine.archetypes.scheduler.wrappers;

public class WrappedStringId {

	private String id;
	
	public WrappedStringId(String newId){
		this.id = newId;
	}

	public String getId(){
		return this.id;
	}
	
	
}
