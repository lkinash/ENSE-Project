package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.Test;

import com.googlecode.objectify.annotation.Id;
import com.googlecode.objectify.annotation.Index;

import java.util.List;
import java.util.Date;

/*
 * @author Archana
 * Tests for Clearances
 */
public class ClearancesTest {
	
	//Data for Clearances class fields
	private long CLEARANCEID = 22556 ; 
    private Service SERVICE;
    private Date RENEWALDATE = new Date(1993,6,24);
    
    private Clearances clearance;
    
    @Test
    public void testGetters() throws Exception{
    	assertEquals(CLEARANCEID,clearance.getClearanceId());
    	assertEquals(RENEWALDATE,clearance.getRenewalDate());
    	assertEquals(SERVICE,clearance.getService());
    	assertEquals(RENEWALDATE,clearance.getRenewalDate());
    }

}
