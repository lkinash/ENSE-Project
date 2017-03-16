package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

/*
 * @author Archana
 * Tests for Product
 */
public class ProductTest {
	
    private int BARCODENUM = 58;
    private String NAME = "soap";
    private double PRICE = 55.00;
    private long PRODUCTID = 556345;
    private long TYPEID = 22656;
    
    private Product product;
    
    private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

	//Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
    @Before
    public void setUp() throws Exception {
        testHelper.setUp();
        product = new Product(BARCODENUM,PRODUCTID,NAME,TYPEID,PRICE);
    }

    //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
    @After
    public void tearDown() throws Exception {
        testHelper.tearDown();
    }
  
    @Test
    public void testGetters() throws Exception{
    	assertEquals(BARCODENUM,product.getBarcodeNumber());
    	assertEquals(NAME,product.getName());
    	assertEquals(PRICE,product.getPrice(), 0.0001);
    	assertEquals(PRODUCTID,product.getProductId());
    	assertEquals(TYPEID,product.getTypeId());
    	
    }
	
}
