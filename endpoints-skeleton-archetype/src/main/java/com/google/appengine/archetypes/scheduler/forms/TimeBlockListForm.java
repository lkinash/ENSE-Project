package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

import com.google.api.client.util.DateTime;

public class TimeBlockListForm {

	
	public List<TimeBlockForm> timeBlockForms;
	
	
	public TimeBlockListForm(){
		
	}
	
	public TimeBlockListForm(List<TimeBlockForm> newTimeBlockFormList){
		
		this.timeBlockForms = newTimeBlockFormList;
		
	}
	
	public void setTimeBlockList(List<TimeBlockForm> newTimeBlockFormList){

		this.timeBlockForms = newTimeBlockFormList;
	}

	public List<TimeBlockForm> getTimeBlockList(){
		
		return this.timeBlockForms;
	}
	
}
