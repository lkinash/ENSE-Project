package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;

import java.util.List;

/*
 * @author Archana
 * Tests for Admin
 */
public class AdminTest {
	
	//Data for Admin class fields
	//private static final AdminClearances CLEARANCE = Admin;
	private static final long ADMINID = 5599666;
	private static final String USERID = "sdds659565";
	
	private Admin admin;
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(ADMINID,admin.getAdminId());
		assertEquals(USERID,admin.getUserId());
		//To do: assert admin clearance
	}
}
