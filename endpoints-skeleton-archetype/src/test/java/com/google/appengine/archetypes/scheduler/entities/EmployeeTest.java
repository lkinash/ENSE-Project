package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

/*
 * @author Archana
 * Tests for Employee
 */
public class EmployeeTest {
	
	//Data for Employee class fields
	private static final String CALENDARID = "12345678999";
	private static final String NAME = "Test User";
	private static final String USERID = "123458956";
	private static final String EMPLOYEEID = "6665559";
	
	//private static final AdminClearances clearance
	
	
	private Employee employee;
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(CALENDARID, employee.getCalendarId());
		assertEquals(NAME, employee.getName());
		assertEquals(USERID, employee.getUserId());
		assertEquals(EMPLOYEEID, employee.getEmployeeId());
	}
}
