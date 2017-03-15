package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

import java.util.List;

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
    
    @Test
    public void testGetters() throws Exception{
    	assertEquals(BARCODENUM,product.getBarcodeNumber());
    	assertEquals(NAME,product.getName());
    	assertEquals(PRICE,product.getPrice());
    	assertEquals(PRODUCTID,product.getProductId());
    	assertEquals(TYPEID,product.getTypeId());
    	
    }
	
}
