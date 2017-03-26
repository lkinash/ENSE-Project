package com.google.appengine.archetypes.scheduler.forms;

import java.util.List;

public class HolidayTimeBlockListForm {

public List<TimeBlockForm> timeBlockForms;
	
	
	public HolidayTimeBlockListForm(){
		
	}
	
	public HolidayTimeBlockListForm(List<TimeBlockForm> newTimeBlockFormList){
		
		this.timeBlockForms = newTimeBlockFormList;
		
	}
	
	public void setTimeBlockList(List<TimeBlockForm> newTimeBlockFormList){

		this.timeBlockForms = newTimeBlockFormList;
	}

	public List<TimeBlockForm> getTimeBlockList(){
		
		return this.timeBlockForms;
	}
	
}
