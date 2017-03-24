package com.google.appengine.archetypes.scheduler.forms;

import com.google.api.client.util.DateTime;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class TimeBlockForm {


	private DateTime startTime;
	

	private DateTime endTime;
	
	
	
	public TimeBlockForm(){
		
	}
	
	public TimeBlockForm( DateTime newStartTime, DateTime newEndTime){
		
		this.endTime = newEndTime;
		this.startTime = newStartTime;
		
	}
	
	public void setEndTime(DateTime newEndTime){
		this.endTime = newEndTime;
	}
	
	public DateTime getEndTime(){
		return this.endTime;
	}
	
	
	public void setStartTime(DateTime newStartTime){
		this.startTime = newStartTime;
	}
	
	public DateTime getStartTime(){
		return this.startTime;
	}
	
	
	
}
