package com.google.appengine.archetypes.scheduler.service;

import java.util.Arrays;

import com.google.api.services.calendar.model.*;
import com.google.appengine.archetypes.scheduler.forms.EventForm;
import com.google.api.client.util.DateTime;

public class EventCreator {

	public static Event createEvent(EventForm eventForm){

 //Calendar calendar = getCalendar(calendarId);
        
        // Initialize Calendar service with valid OAuth credentials
        
        //GoogleCredential credentials = new GoogleCredential.Builder().setTransport(GoogleNetHttpTransport.newTrustedTransport())
        	//	  .setJsonFactory(new GsonFactory())
        		//  .setServiceAccountId("<service account email address>@developer.gserviceaccount.com")
        	//	  .setServiceAccountScopes(Arrays.asList("https://www.googleapis.com/auth/calendar.readonly"))
        		//  .setServiceAccountPrivateKeyFromP12File(new File("<private key for service account in P12 format>-privatekey.p12"))
        	//	.build();
        //Calendar client = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), new GsonFactory(), credentials).build();
        		
        		
        
        //Calendar service = new Calendar.Builder(GoogleNetHttpTransport.newTrustedTransport(), Constants.JSON_FACTORY, authorize()).setApplicationName("applicationName").build();
        
        //Calendar service = CalendarUtility.loadCalendarClient(user.getUserId());
        
        // Retrieve the calendar
        //Calendar calendar =  service.calendars().get(calendarId).execute();
        
        /*
        Event event = new Event()
        .setSummary(eventForm.getSummary())
        .setLocation(eventForm.getLocation())
        .setDescription(eventForm.getDescription());

        DateTime startDateTime = eventForm.getStartDateTime();
        EventDateTime start = new EventDateTime()
        	.setDateTime(startDateTime)
        	.setTimeZone("America/Los_Angeles");
        event.setStart(start);

        DateTime endDateTime = eventForm.getEndDateTime();
        EventDateTime end = new EventDateTime()
        	.setDateTime(endDateTime)
        	.setTimeZone("America/Los_Angeles");
        event.setEnd(end);

        String[] recurrence = eventForm.getRecurrence();
        event.setRecurrence(Arrays.asList(recurrence));

        EventAttendee[] attendees = eventForm.getAttendees();
        event.setAttendees(Arrays.asList(attendees));

        EventReminder[] reminderOverrides = eventForm.getReminderOverrides();
   
        Event.Reminders reminders = new Event.Reminders()
        	.setUseDefault(false)
        	.setOverrides(Arrays.asList(reminderOverrides));
        event.setReminders(reminders);

        //event = calendar.events().insert(calendarId, event).execute();

        // TODO 
        // 
        Event event = new Event()
        .setSummary("Google I/O 2015")
        .setLocation("800 Howard St., San Francisco, CA 94103")
        .setDescription("A chance to hear more about Google's developer products.");

    DateTime startDateTime = new DateTime("2017-05-28T09:00:00-07:00");
    EventDateTime start = new EventDateTime()
        .setDateTime(startDateTime)
        .setTimeZone("America/Los_Angeles");
    event.setStart(start);

    DateTime endDateTime = new DateTime("2017-05-28T17:00:00-07:00");
    EventDateTime end = new EventDateTime()
        .setDateTime(endDateTime)
        .setTimeZone("America/Los_Angeles");
    event.setEnd(end);

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
*/
   // event = service.events().insert(calendarId, event).execute();
    //System.out.printf("Event created: %s\n", event.getHtmlLink());
        
		//return event;
		
		return null;
	}
	
	
	public static Event createEvent(){
		
		  Event event = new Event()
	        .setSummary("Google I/O 2015")
	        .setLocation("800 Howard St., San Francisco, CA 94103")
	        .setDescription("A chance to hear more about Google's developer products.");

	    DateTime startDateTime = new DateTime("2017-05-28T09:00:00-07:00");
	    EventDateTime start = new EventDateTime()
	        .setDateTime(startDateTime)
	        .setTimeZone("America/Los_Angeles");
	    event.setStart(start);

	    DateTime endDateTime = new DateTime("2017-05-28T17:00:00-07:00");
	    EventDateTime end = new EventDateTime()
	        .setDateTime(endDateTime)
	        .setTimeZone("America/Los_Angeles");
	    event.setEnd(end);

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

}
