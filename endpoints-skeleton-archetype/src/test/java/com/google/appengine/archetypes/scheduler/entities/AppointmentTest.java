package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import com.google.api.server.spi.config.AnnotationBoolean;
import com.google.api.server.spi.config.ApiResourceProperty;
import com.google.appengine.archetypes.scheduler.list.Status;
import com.googlecode.objectify.Key;
import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;
import com.googlecode.objectify.annotation.Parent;

import java.util.List;

/*
 * @author Archana
 * Tests for Appointment
 */
public class AppointmentTest{
	
	//Data for Appointment class fields
    private Status STATUS;
	private String EVENTID = "5584712";
	private long APPOINTMENTID = 44556;
	private Type APPOINTMENTTYPE;
	private Service SERVICE;
	private long CLIENTID = 55522;
	
	private Appointment appointment;

	@Test
	public void testGetters() throws Exception{
		assertEquals(EVENTID,appointment.getEventId());
		assertEquals(APPOINTMENTID,appointment.getAppointmentId());
		assertEquals(CLIENTID,appointment.getclientId());
		
	}
	
}