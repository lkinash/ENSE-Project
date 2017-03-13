package com.google.appengine.archetypes.scheduler.wrappers;

public class WrappedBoolean {

	private boolean result;
	private String reason;
	
	public WrappedBoolean(boolean newResult){
		this.reason = "";
		this.result = newResult;
	}
	
	public WrappedBoolean(boolean newResult, String newReason){
		this.reason = newReason;
		this.result = newResult;
	}
	
	public boolean getResult(){
		return this.result;
	}

	public String getReason(){
		return this.reason;
	}
	
}
