package com.JUnit;

import com.database.Major;

import junit.framework.Assert;
import junit.framework.TestCase;

public class MajorTest extends TestCase {

	private Major mTest;

	/**
	 * Called before any test method
	 */
	@Override
	protected void setUp() throws Exception {

		//Initialize some values for testing
		Major test = new Major("aSampleMajor", "SPMJ");
		mTest = test;

		super.setUp();
	}

	//Destroy objects after test
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//Tests constructor
	public void testMajor() {
		Major test = new Major("aSampleMajor", "SPMJ");
		
		//Make sure constructor returns an object
		Assert.assertNotNull(test);
	}

	//Tests getName method
	public void testGetName() {
		String name = mTest.getName();

		//Make sure something is returned
		Assert.assertNotNull(name);
		
		//Compare with the expected value
		Assert.assertTrue(name.equals("aSampleMajor"));
	}

	public void testGetAbbrev() {
		String abbrev = mTest.getAbbrev();

		//Make sure something is returned
		Assert.assertNotNull(abbrev);
		
		//Compare with the expected value
		Assert.assertTrue(abbrev.equals("SPMJ"));
	}

}
