/*******************************************************************************
 * 2016, All rights reserved.
 *******************************************************************************/
package com.google.appengine.archetypes.spi;

import static com.google.appengine.archetypes.service.OfyDatabaseService.factory;
import static com.google.appengine.archetypes.service.OfyDatabaseService.ofy;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.List;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.extensions.appengine.datastore.AppEngineDataStoreFactory;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.javanet.GoogleNetHttpTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.client.util.DateTime;
import com.google.api.client.util.store.FileDataStoreFactory;
import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.Named;
import com.google.api.server.spi.response.UnauthorizedException;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserServiceFactory;
import com.google.appengine.archetypes.Constants;
import com.google.appengine.archetypes.entities.Admin;
import com.google.appengine.archetypes.entities.Appointment;
import com.google.appengine.archetypes.entities.Employee;
import com.google.appengine.archetypes.forms.AppointmentForm;
import com.google.appengine.archetypes.forms.CancelAppointmentForm;
import com.google.appengine.archetypes.forms.EventForm;
import com.google.appengine.archetypes.list.Status;
import com.google.appengine.archetypes.wrappers.WrappedBoolean;
import com.google.appengine.archetypes.wrappers.WrappedId;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.cmd.Query;
import com.google.appengine.archetypes.service.Quickstart;

/**
 * Description of AppointmentAPI.
 * 
 */

@Api(
	    name = "appointment",
	    version = "v1",
	    scopes = {Constants.EMAIL_SCOPE, Constants.CALENDAR_SCOPE, Constants.CALENDAR_READONLY_SCOPE},
	    clientIds = {Constants.WEB_CLIENT_ID, Constants.ANDROID_CLIENT_ID, Constants.IOS_CLIENT_ID, Constants.API_EXPLORER_CLIENT_ID},
	    description = "An API for making appointments."
	)
public class AppointmentAPI {

	
	/**
	 * Description of the method createAppointment.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "createAppointment", httpMethod = "post")
  	public Appointment createAppointment(final User user, AppointmentForm appointmentForm, EventForm eventForm) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // Create the event
        //
        //
        
        /*
        //Key<Employee> employeeKey = appointmentForm.getEmployeeKey();

    	//Employee employee = (Employee) ofy().load().key(employeeKey).now();

        final String calendarId = "";
        		//employee.getCalendarId();
        
        
        WrappedId wrappedId = createEvent(user, calendarId, eventForm);
        
        final String eventId = wrappedId.getId();
        
        
        //final Key<Appointment> appointmentKey = factory().allocateId(employeeKey, Appointment.class);
        final long appointmentId = 0;
        		//appointmentKey.getId();
        
        //Appointment appointment = new Appointment(Status.booked, eventId, appointmentId, employeeKey, appointmentForm.getappointmentType(), appointmentForm.getService());
    		
  		//ofy().save().entities(appointment).now();
  		
		//return appointment;
	*/
        return null;
	}

	/**
	 * Description of the method modifyAppointment.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "modifyAppointment", httpMethod = "post")
  	public WrappedBoolean modifyAppointment(final User user, AppointmentForm appointmentForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method cancelAppointment.
	 * @param client 
	 * @param appointmentId 
	 * @param removeAppointmentForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "cancelAppointment", httpMethod = "post")
  	public WrappedBoolean cancelAppointment(final User user,@Named("userAppointmentId") final long userAppointmentId, CancelAppointmentForm removeAppointmentForm) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
        
		
		return null;
	}

	/**
	 * Description of the method updateAppointmentStatus.
	 * @param admin 
	 * @param appointmentId 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "updateAppointmentStatus", httpMethod = "post")
  	public WrappedBoolean updateAppointmentStatus(final User user, @Named("appointmentId") final long appointmentId,  @Named("status") final Status status) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        Appointment appointment = getAppointment(user, appointmentId);
        appointment.setStatus(status);
        
  		ofy().save().entities(appointment).now();
        
        // TODO 
        // Return value set
  		
		return null;
	}

	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "getClientAppointments", httpMethod = "post")
  	public List<Appointment> getClientAppointments(final User user, @Named("clientId") final long clientId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
    	Query<Appointment> query =  ofy().load().type(Appointment.class);
    	query = query.filter("clientId =", clientId);
    	
        return query.list();
        
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "getAppointment", httpMethod = "post")
  	public Appointment getAppointment(final User user, @Named("appointmentId") final long appointmentId) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        Key<Appointment> key = Key.create(Appointment.class, appointmentId);

       	Appointment appointment = (Appointment) ofy().load().key(key).now();
       	return appointment;
        
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "createEvent", httpMethod = "post")
  	public WrappedId createEvent(final User user, @Named("calendarId") final String calendarId, EventForm eventForm) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        
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

        
        Quickstart.addEvent(calendarId, user);
        
        return null;
	}
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "deleteEvent", httpMethod = "post")
  	public WrappedId deleteEvent(final User user, @Named("calendarId") final String calendarId, EventForm eventForm) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
	
        return null;
	}
	
	
	/**
	 * Description of the method queryAppointments.
	 * @throws UnauthorizedException 
	 * @throws IOException 
	 */
	
	@ApiMethod(name = "modifyEvent", httpMethod = "post")
  	public WrappedBoolean modifyEvent(final User user, @Named("calendarId") final String calendarId, EventForm eventForm) throws UnauthorizedException, IOException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
	
        return null;
	}

	/**         
	 * Description of the method filterAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "filterAppointments", httpMethod = "post")
  	public WrappedBoolean filterAppointments(final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
		
        
		return null;
	}

	/**
	 * Description of the method findAvailableAppointmentTimes.
	 * @param appointmentForm 
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "findAvailableAppointmentTimes", httpMethod = "get")
  	public Object findAvailableAppointmentTimes(AppointmentForm appointmentForm, final User user) throws UnauthorizedException {

        if (user == null) {
            throw new UnauthorizedException("Authorization required");
        }
        if (!checkAuthorizationForPage(user)) {
            throw new UnauthorizedException("Authorization level too low.");
        }
  		
        
        // TODO 
        // 
        
		
		return null;
	} 
	
	/**
	 * Description of the method filterAppointments.
	 * @throws UnauthorizedException 
	 */
	
	@ApiMethod(name = "getCalendar", httpMethod = "post")
	public Calendar getCalendarService( final User user){
		
		//TODO
		//get calendar based on id passed in
/*
		String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
	    Credential credential = null;
		try {
			credential = CalendarUtility.newFlow().loadCredential(userId);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		Calendar service = new Calendar.Builder(CalendarUtility.HTTP_TRANSPORT, CalendarUtility.JSON_FACTORY, credential)
	    .setApplicationName("applicationName").build();
	// Retrieve the calendar
		Calendar calendar = null;
		
		try {
			calendar = service.calendars().get(calendarId).execute();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		System.out.println(calendar.getSummary());
		
		
    	return calendar;
    	*/
		
		Calendar service = null;
		
		String calendarId = "j6pq7ifpumics69e9948q2bhdc@group.calendar.google.com";
		
	
		try {
			Quickstart.addEvent(calendarId, user);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return service;
		
	}
	
	
	private static boolean checkAuthorizationForPage(final User user){
  		
        // TODO 
        // Get the page ID
  		
		
		// TODO 
        // Get the user clearances
		
		
		// TODO 
        // Check the user clearances against the page ID
  		
  		
  		return true;
  	}

	
}
