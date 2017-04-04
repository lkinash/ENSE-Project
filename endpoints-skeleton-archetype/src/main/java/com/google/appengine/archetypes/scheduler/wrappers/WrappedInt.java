package com.google.appengine.archetypes.scheduler.wrappers;

public class WrappedInt {

	private int id;

	public WrappedInt(){
		
	}
	
	public WrappedInt(int newId){
		this.id = newId;
	}

	public int getId(){
		return this.id;
	}
	
	public void setId(int newId){
		this.id = newId;
	}
	
	
}
