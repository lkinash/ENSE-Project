package com.google.appengine.archetypes.scheduler.forms;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.google.appengine.archetypes.scheduler.list.AdminClearances;
import com.google.appengine.tools.development.testing.LocalDatastoreServiceTestConfig;
import com.google.appengine.tools.development.testing.LocalServiceTestHelper;

public class ProductFormTest {
	
	//Data for the Product Form class fields
	private long TYPEID = 5555663;
	private String NAME = "Concealer";
	private double PRICE = 65.0;
	private int BARCODENUM = 4456;
	
	private ProductForm productForm;
	
	private final LocalServiceTestHelper testHelper =  new LocalServiceTestHelper(new LocalDatastoreServiceTestConfig().setDefaultHighRepJobPolicyUnappliedJobPercentage(100));

    
	 //Before the test is run set up the test data store helper and create a new instance of the SaleItem Object
	 @Before
	 public void setUp() throws Exception {
		 testHelper.setUp();
		 productForm = new ProductForm(TYPEID,NAME,BARCODENUM,PRICE);
	 }

	 //After the test is run, user the helper to remove the data store entities that were involved in the test as they are unneeded 
	 @After
	 public void tearDown() throws Exception {
		 testHelper.tearDown();
	 }

	@Test
	public void testGetters() throws Exception{
		assertEquals(TYPEID,productForm.getTypeId());
		assertEquals(NAME,productForm.getName());
		assertEquals(BARCODENUM,productForm.getBarcodeNumber());
		assertEquals(PRICE,productForm.getPrice(),0.01);
	}
}
