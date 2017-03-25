package com.google.appengine.archetypes.scheduler.service;

import com.google.api.client.util.DateTime;
import com.google.appengine.archetypes.scheduler.forms.EventForm;

public class DateTimeConverter {

	private static String timeZone = "06:00";  	/// must be in format "00:00"
	
	/**
	 * 
	 * @param year
	 * @param month
	 * @param day
	 * @param hour			
	 * @param minute
	 * @return
	 */
	
	public static DateTime convertStartDate(EventForm eventForm){
		return convert(eventForm.getStartYear(), eventForm.getStartMonth(), eventForm.getStartDay(), eventForm.getStartHour(), eventForm.getStartMinute());
	}
	
	public static DateTime convertEndDate(EventForm eventForm){
		return convert(eventForm.getEndYear(), eventForm.getEndMonth(), eventForm.getEndDay(), eventForm.getEndHour(), eventForm.getEndMinute());
	}
	
	public static DateTime convert(int year, int month, int day, int hour, int minute){
		
		return convert(year, month, day, hour, minute, 0);
	}
	
	public static DateTime convert(int year, int month, int day, int hour, int minute, int second){
		
		//TODO
		// Add converter to date time object
		// 2015-05-28T09:00:00-07:00
		
		String date = year + "-"; 
		
		if(month < 10)
			date += "0" + month + "-" + day + "T";
		else
			date += month + "-" + day + "T";
		
		
		if(hour < 10)
			date += "0" + hour + ":";
		else
			date += hour + ":";
		
		
		if(minute < 10)
			date += "0" + minute + ":";
		else
			date += minute + ":";
		
		
		if(second < 10)
			date += "0" + second + "-";
		else
			date += second + "-";
		
		
		if(timeZone == null)
			date += "07:00";
		else
			date+= timeZone;
		
		
		return new DateTime(date);
	}
	
}
