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

	//data for super class
	
	private Service service;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        service = new Service(DEFAULTLENGTH,REQUIRESCLEARANCE,NEWPRODUCTID,NEWNAME,NEWTYPEID,NEWPRICE); //{
        	//super(NEWPRODUCTID, NEWNAME, NEWPRICE,NEWTYPEID);
        //}
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(REQUIRESCLEARANCE,service.getRequiresClearance());
		assertEquals(DEFAULTLENGTH,service.getDefaultLength());
		assertEquals(NEWPRODUCTID,service.getProductId());
		assertEquals(NEWNAME,service.getName());
		assertEquals(NEWTYPEID,service.getTypeId());
		assertEquals(NEWPRICE,service.getPrice(),0.001);
		
	}
}
