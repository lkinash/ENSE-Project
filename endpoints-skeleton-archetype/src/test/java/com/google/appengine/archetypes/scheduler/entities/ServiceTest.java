package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

/*
 * @author Archana
 * Tests for Service
 */
public class ServiceTest {
	
	//Data for Room class fields
	private static final boolean REQUIRESCLEARANCE = false;
	private static final int DEFAULTLENGTH = 5;
	
	private static final long NEWPRODUCTID = 4478956;
	private static final String NEWNAME = "leg wax";
	private static final long NEWTYPEID = 3325623;
	private static final double NEWPRICE = 33.05;

	//data for superclass
	
	private Service service;
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(REQUIRESCLEARANCE,service.getRequiresClearance());
		assertEquals(DEFAULTLENGTH,service.getDefaultLength());
		assertEquals(NEWPRODUCTID,service.getProductId());
		assertEquals(NEWNAME,service.getName());
		assertEquals(NEWTYPEID,service.getTypeId());
		assertEquals(NEWPRICE,service.getPrice());
		
	}
}
