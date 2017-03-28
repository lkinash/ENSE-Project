package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.appengine.archetypes.scheduler.entities.DayTimeBlocks;

public class TimeBlockListForm {

	
	private List<DayTimeBlocks> timeBlocks;
	
	
	public TimeBlockListForm(){
		
	}
	
	public TimeBlockListForm(List<DayTimeBlocks> newTimeBlocks){
		
		this.timeBlocks = newTimeBlocks;
		
	}
	
	
	public void setTimeBlocks(List<DayTimeBlocks> newTimeBlocks){
		
		this.timeBlocks = newTimeBlocks;
		
	}
	
	public List<DayTimeBlocks> getTimeBlocks(){
		return this.timeBlocks;
	}
	
}
