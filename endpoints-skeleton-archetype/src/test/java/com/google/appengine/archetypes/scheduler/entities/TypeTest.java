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
 * Tests for Type
 */
public class TypeTest {
	
	//Data for Type class fields
	private static final String TYPE = "Laser";
	private static final long TYPEID = 100000;
	private static final boolean ISSERVICE = true;
	
	private Type type;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        type = new Type(ISSERVICE,TYPE,TYPEID);
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(TYPE,type.getTypeName());
		assertEquals(TYPEID,type.getTypeId());
		assertEquals(ISSERVICE,type.getIsService());
	}

}
