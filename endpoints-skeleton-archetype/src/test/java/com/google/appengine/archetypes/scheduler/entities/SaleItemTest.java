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
 * Tests for SaleItem
 */
public class SaleItemTest {
	
	//Data for SaleItem class fields
	private String NAME = "vivier moisturiser";
	private double PRICE = 65.00;
	private long PRODUCTID = 2256598;
	private long TYPEID = 33269;
	
	private SaleItem saleItem;
	
    private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
    //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        saleItem = new SaleItem(PRODUCTID, NAME, PRICE, TYPEID);
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }
	
    
	@Test
	public void testGetters() throws Exception{
		assertEquals(NAME,saleItem.getName());
		assertEquals(PRICE,saleItem.getPrice(), 0.001);
		assertEquals(PRODUCTID,saleItem.getProductId());
		assertEquals(TYPEID,saleItem.getTypeId());
	}
}
