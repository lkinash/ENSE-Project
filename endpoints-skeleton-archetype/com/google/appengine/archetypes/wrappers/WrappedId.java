package com.google.appengine.archetypes.wrappers;

public class WrappedId {

	private String id;
	
	public WrappedId(String newId){
		this.id = newId;
	}

	public String getId(){
		return this.id;
	}
	
}
