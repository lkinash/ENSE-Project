package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

/*
 * @author Archana
 * Tests for Room
 */
public class RoomTest {
	
	// Data for Employee class fields
	private static final long ROOMID = 11155556;
	private static final int NUMBER = 5;
	private static final String CALENDARID = "123458956";

	private Room room;

	@Test
	public void testGetters() throws Exception {
		assertEquals(ROOMID, room.getRoomId());
		assertEquals(NUMBER, room.getNumber());
		assertEquals(CALENDARID, room.getCalendar());
	}
}
