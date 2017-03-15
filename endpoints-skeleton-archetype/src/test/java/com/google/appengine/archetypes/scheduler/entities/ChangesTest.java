package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

/*
 * @author Archana
 * Tests for Changes
 */
public class ChangesTest {
	
	//Data for Changes class fields
	private String TIMESTAMP = "2005-10-30 T 10:45 UTC";
	private String USERID = "478956opo";
	private long CHANGEID = 111447;
	private String CHANGE = "laser machine type has been changed";
	
	private Changes changes;
	
	public void getGetters() throws Exception{
		assertEquals(TIMESTAMP,changes.getTimeStamp());
		assertEquals(USERID,changes.getUserId());
		assertEquals(CHANGEID,changes.getChangeId());
		assertEquals(CHANGE,changes.getChange());
	}

}
