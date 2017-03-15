package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

/*
 * @author Archana
 * Tests for Type
 */
public class TypeTest {
	
	//Data for Type class fields
	private static final String TYPE = "Laser";
	private static final long TYPEID = 100000;
	private static final boolean ISSERVICE = true;
	
	private Type type;
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(TYPE,type.getTypeName());
		assertEquals(TYPEID,type.getTypeId());
		assertEquals(ISSERVICE,type.getIsService());
	}

}
