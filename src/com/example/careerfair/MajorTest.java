package com.example.careerfair;

import java.util.ArrayList;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MajorTest extends TestCase {

	private Major mTest;
	
	/**
	 * Called before any test method
	 */
	protected void setUp() throws Exception 
	{
		
		Major test = new Major("aSampleMajor", "SPMJ");
		mTest = test;
		
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	
	public void testMajor() {
		Major test = new Major("aSampleMajor", "SPMJ");
		Assert.assertNotNull(test);
	}

	public void testGetName() {
		String name = mTest.getName();
		
		Assert.assertNotNull(name);
		Assert.assertTrue(name.equals("aSampleMajor"));
	}

	public void testGetAbbrev() {
		String abbrev = mTest.getAbbrev();
		
		Assert.assertNotNull(abbrev);
		Assert.assertTrue(abbrev.equals("SPMJ"));
	}

}
