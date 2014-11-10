package com.JUnit;

import java.util.ArrayList;

import com.database.Company;
import com.database.Major;

import junit.framework.Assert;
import junit.framework.TestCase;

public class CompanyTest extends TestCase {
	// private SQLiteDatabase db;
	private Company mTest;
	private ArrayList<Major> mMajors;
	private ArrayList<String> mWorkAuths;
	private ArrayList<String> mPositions;

	/**
	 * Called before any test method
	 */
	@Override
	protected void setUp() throws Exception {

		//Initialize some values for testing
		ArrayList<Major> majors = new ArrayList<Major>();
		ArrayList<String> positions = new ArrayList<String>();
		ArrayList<String> workAuths = new ArrayList<String>();

		mMajors = majors;
		mWorkAuths = workAuths;
		mPositions = positions;

		majors.add(new Major("SampleMajor", "SPMJ"));
		positions.add("Intern");
		workAuths.add("US Citizen");

		Company test = new Company("aCompany", "www.aCompanyWebsite.com", "1",
				"Wood Gym", majors, positions, workAuths);
		mTest = test;
		super.setUp();
	}

	//Destroys objects after test
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	//Test constructor
	public void testCompany() {

		Company test = new Company("aCompany", "www.aCompanyWebsite.com", "1",
				"Wood Gym", mMajors, mPositions, mWorkAuths);
		Assert.assertNotNull(test);
		mTest = test;

	}

	//Tests the getMajors method
	public void testGetMajors() {
		ArrayList<Major> majorList = mTest.getMajors();
		
		//Make sure something is returned
		Assert.assertNotNull(majorList);

		// getMajors should return a copy of the ArrayList, not the same
		// arrayList
		Assert.assertTrue(majorList.toString() != mMajors.toString());

		// Check majors returned
		Assert.assertTrue(majorList.size() == mMajors.size());
		Assert.assertTrue(majorList.get(0).getName() == mMajors.get(0)
				.getName());
		Assert.assertTrue(majorList.get(0).getAbbrev() == mMajors.get(0)
				.getAbbrev());

	}

	//Tests the getPositions method
	public void testGetPositions() {
		ArrayList<String> positions = mTest.getPositions();
		
		//Make sure something is returned
		Assert.assertNotNull(positions);

		// getPositions should return a copy of the ArrayList, not the same
		// arrayList
		Assert.assertTrue(positions.toString() != mPositions.toString());

		// Check positions returned
		Assert.assertTrue(positions.size() == mPositions.size());
		Assert.assertTrue(positions.get(0) == mPositions.get(0));
	}

	//Tests the getWorkAuth method
	public void testGetWorkAuth() {

		ArrayList<String> workAuth = mTest.getWorkAuth();
		
		//Make sure something is returned
		Assert.assertNotNull(workAuth);

		// getWorkAuth should return a copy of the ArrayList, not the same
		// arrayList
		Assert.assertTrue(workAuth.toString() != mWorkAuths.toString());

		// Check workAuths
		Assert.assertTrue(workAuth.size() == mWorkAuths.size());
		Assert.assertTrue(workAuth.get(0) == mWorkAuths.get(0));
	}

	//Tests the get name method
	public void testGetName() {
		String name = mTest.getName();
		
		//Make sure something is returned
		Assert.assertNotNull(name);
		
		//Compare with expected value
		Assert.assertEquals(mTest.getName(), "aCompany");
	}

	//Tests the getWebsite method
	public void testGetWebsite() {
		String website = mTest.getWebsite();
		
		//Make sure something is returned
		Assert.assertNotNull(website);
		
		//Compare with expected value
		Assert.assertEquals(mTest.getWebsite(), "www.aCompanyWebsite.com");
	}

	//Tests the getTableNum method
	public void testGetTableNum() {
		String tableNum = mTest.getTableNum();
		
		//Make sure something is returned
		Assert.assertNotNull(tableNum);
		
		//Compare with expected value
		Assert.assertEquals(mTest.getTableNum(), "1");
	}

	//Tests the getTableNum method
	public void testGetRoom() {
		String room = mTest.getRoom();
		
		//Make sure something is returned
		Assert.assertNotNull(room);
		
		//Compare with expected value
		Assert.assertEquals(mTest.getRoom(), "Wood Gym");
	}

}
