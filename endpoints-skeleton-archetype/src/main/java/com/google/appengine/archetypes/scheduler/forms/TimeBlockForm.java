package com.google.appengine.archetypes.scheduler.forms;

import com.google.api.client.util.DateTime;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

public class TimeBlockForm {


	private int year;
	
	private int month;
	
	private int day;
	
	
	public TimeBlockForm(){
		
	}
	
	public TimeBlockForm(int newYear, int newMonth, int newDay){
		
		this.day = newDay;
		this.month = newMonth;
		this.year = newYear;
		
	}
	
	public void setYear(int newYear){
		this.year = newYear;
	}
	
	public int getYear(){
		return this.year;
	}
	
	
	public void setMonth(int newMonth){
		this.month = newMonth;
	}
	
	public int getMonth(){
		return this.month;
	}
	
	
	public void setDay(int newDay){
		this.day = newDay;
	}
	
	public int getDay(){
		return this.day;
	}
	
	
	
	
}
