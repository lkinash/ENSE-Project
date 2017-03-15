package com.google.appengine.archetypes.scheduler.entities;

import static org.junit.Assert.*;
import org.junit.Test;

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
	
	@Test
	public void testGetters() throws Exception{
		assertEquals(NAME,saleItem.getName());
		assertEquals(PRICE,saleItem.getPrice());
		assertEquals(PRODUCTID,saleItem.getProductId());
		assertEquals(TYPEID,saleItem.getTypeId());
	}
}
