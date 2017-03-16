package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

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
	private List<Long> serviceIds;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        room = new Room(NUMBER,serviceIds,CALENDARID, ROOMID);
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }

	@Test
	public void testGetters() throws Exception {
		assertEquals(ROOMID, room.getRoomId());
		assertEquals(NUMBER, room.getNumber());
		assertEquals(CALENDARID, room.getCalendar());
	}
}
