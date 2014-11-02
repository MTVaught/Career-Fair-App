package com.example.careerfair;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CompanyTest extends TestCase 
{
	//private SQLiteDatabase db;
	private Company mTest;
	private ArrayList<Major> mMajors;
	private ArrayList<String> mWorkAuths;
	private ArrayList<String> mPositions;

	/**
	 * Called before any test method
	 */
	protected void setUp() throws Exception 
	{
		//MainActivity app = new MainActivity();
		//ExternalDbOpenHelper helper = new ExternalDbOpenHelper( app.getBaseContext(), "careerFairDB.db" );
		//db = helper.openDataBase();
		
		ArrayList<Major> majors = new ArrayList<Major>();
		ArrayList<String> positions = new ArrayList<String>();
		ArrayList<String> workAuths = new ArrayList<String>();
		
		mMajors = majors;
		mWorkAuths = workAuths;
		mPositions = positions;
		
		majors.add(new Major("SampleMajor", "SPMJ"));
		positions.add("Intern");
		workAuths.add("US Citizen");
		
		Company test = new Company("aCompany","www.aCompanyWebsite.com","1","Wood Gym" ,
				majors, positions, workAuths );
		mTest= test;
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	public void testCompany() {
		
		
		Company test = new Company("aCompany","www.aCompanyWebsite.com","1","Wood Gym" ,
				mMajors, mPositions, mWorkAuths );
		Assert.assertNotNull( test );
		mTest = test;
	
	}

	public void testGetMajors() 
	{
		ArrayList<Major> majorList = mTest.getMajors();
		Assert.assertNotNull( majorList );
		
		//getMajors should return a copy of the ArrayList, not the same arrayList
		Assert.assertTrue(majorList.toString() != mMajors.toString());
		
		//Check majors
		Assert.assertTrue(majorList.size() == mMajors.size());
		Assert.assertTrue(majorList.get(0).getName() == mMajors.get(0).getName());
		Assert.assertTrue(majorList.get(0).getAbbrev() == mMajors.get(0).getAbbrev());
		
	}

	public void testGetPositions() 
	{
		ArrayList<String> positions = mTest.getPositions();
		Assert.assertNotNull( positions );
		
		//getPositions should return a copy of the ArrayList, not the same arrayList
		Assert.assertTrue(positions.toString() != mPositions.toString());
		
		//Check positions
		Assert.assertTrue(positions.size() == mPositions.size());
		Assert.assertTrue(positions.get(0) == mPositions.get(0));
	}

	public void testGetWorkAuth() 
	{

		ArrayList<String> workAuth = mTest.getWorkAuth();
		Assert.assertNotNull( workAuth );
		
		//getWorkAuth should return a copy of the ArrayList, not the same arrayList
		Assert.assertTrue(workAuth.toString() != mWorkAuths.toString());
		
		//Check workAuths
		Assert.assertTrue(workAuth.size() == mWorkAuths.size());
		Assert.assertTrue(workAuth.get(0) == mWorkAuths.get(0));
	}

	public void testGetName() 
	{
		String name = mTest.getName();	
		Assert.assertNotNull( name );
		Assert.assertEquals(mTest.getName(), "aCompany");
	}

	public void testGetWebsite() 
	{
		String website = mTest.getWebsite();
		Assert.assertNotNull( website );
		Assert.assertEquals(mTest.getWebsite(), "www.aCompanyWebsite.com");
	}

	public void testGetTableNum() 
	{
		String tableNum = mTest.getTableNum();
		Assert.assertNotNull( tableNum );
		Assert.assertEquals(mTest.getTableNum(), "1");
	}

	public void testGetRoom() 
	{
		String room = mTest.getRoom();	
		Assert.assertNotNull( room );
		Assert.assertEquals(mTest.getRoom(), "Wood Gym");
	}

}
