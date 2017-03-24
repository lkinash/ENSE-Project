package com.google.appengine.archetypes.scheduler.service;

import java.util.Arrays;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventAttendee;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.api.services.calendar.model.EventReminder;
import com.google.appengine.archetypes.scheduler.forms.EventCreatorForm;
import com.google.appengine.archetypes.scheduler.forms.EventForm;

public class EventCreator {

	public static Event createEvent(EventCreatorForm eventCreatorForm){
        
		
        Event event = new Event()
        .setSummary(eventCreatorForm.getSummary())
        .setLocation(eventCreatorForm.getLocation())
        .setDescription(eventCreatorForm.getDescription());

        DateTime startDateTime = eventCreatorForm.getStartDateTime();
        EventDateTime start = new EventDateTime()
        	.setDateTime(startDateTime)
        	.setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = eventCreatorForm.getEndDateTime();
        EventDateTime end = new EventDateTime()
        	.setDateTime(endDateTime)
        	.setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = eventCreatorForm.getRecurrence();
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = eventCreatorForm.getAttendees();
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = eventCreatorForm.getReminderOverrides();
   
        Event.Reminders reminders = new Event.Reminders()
        	.setUseDefault(false)
        	.setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);
        
		return event;
		
	}
	
	/*
	public static Event createEvent(){
		
		  Event event = new Event()
	        .setSummary("Event TEstser")
	        .setLocation("800 Howard St., San Francisco, CA 94103")
	        .setDescription("A chance to hear more about Google's developer products.");

	    DateTime startDateTime = new DateTime("2017-04-28T09:00:00-06:00");
	    EventDateTime start = new EventDateTime()
	        .setDateTime(startDateTime)
	        .setTimeZone("America/Los_Angeles");
	    event.setStart(start);

	    DateTime endDateTime = new DateTime("2017-04-28T17:00:00-06:00");
	    EventDateTime end = new EventDateTime()
	        .setDateTime(endDateTime)
	        .setTimeZone("America/Los_Angeles");
	    event.setEnd(end);

	    /*
	    String[] recurrence = new String[] {"RRULE:FREQ=DAILY;COUNT=2"};
	    event.setRecurrence(Arrays.asList(recurrence));

	    EventAttendee[] attendees = new EventAttendee[] {
	        new EventAttendee().setEmail("lpage@example.com"),
	        new EventAttendee().setEmail("sbrin@example.com"),
	    };
	    event.setAttendees(Arrays.asList(attendees));

	    EventReminder[] reminderOverrides = new EventReminder[] {
	        new EventReminder().setMethod("email").setMinutes(24 * 60),
	        new EventReminder().setMethod("popup").setMinutes(10),
	    };
	    Event.Reminders reminders = new Event.Reminders()
	        .setUseDefault(false)
	        .setOverrides(Arrays.asList(reminderOverrides));
	    event.setReminders(reminders);
	    
	    
	    return event;
		
	}
*/
}
