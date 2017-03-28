package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.client.util.DateTime;
import com.google.appengine.archetypes.scheduler.entities.DayTimeBlocks;

public class TimeBlockListForm {

	
	private List<DayTimeBlocksForm> timeBlocks;
	
	
	public TimeBlockListForm(){
		
	}
	
	public TimeBlockListForm(List<DayTimeBlocksForm> newTimeBlocks){
		
		this.timeBlocks = newTimeBlocks;
		
	}
	
	
	public void setTimeBlocks(List<DayTimeBlocksForm> newTimeBlocks){
		
		this.timeBlocks = newTimeBlocks;
		
	}
	
	public List<DayTimeBlocksForm> getTimeBlocks(){
		return this.timeBlocks;
	}
	
}
