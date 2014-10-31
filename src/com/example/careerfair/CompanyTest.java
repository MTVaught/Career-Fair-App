package com.example.careerfair;

import java.util.ArrayList;

import android.database.sqlite.SQLiteDatabase;
import junit.framework.Assert;
import junit.framework.TestCase;

public class CompanyTest extends TestCase 
{
	//private SQLiteDatabase db;
	private Company mTest;

	/**
	 * Called before any test method
	 */
	protected void setUp() throws Exception 
	{
		//MainActivity app = new MainActivity();
		//ExternalDbOpenHelper helper = new ExternalDbOpenHelper( app.getBaseContext(), "careerFairDB.db" );
		//db = helper.openDataBase();
		mTest = new Company("a","b","c","d" ,
				new ArrayList<Major>(), new ArrayList<String>(), new ArrayList<String>() );
		super.setUp();
	}

	protected void tearDown() throws Exception 
	{
		super.tearDown();
	}

	public void testCompany() {
		Company test = new Company("a","b","c","d" ,
				new ArrayList<Major>(), new ArrayList<String>(), new ArrayList<String>() );
		Assert.assertNotNull( test );
	}

	public void testGetMajors() 
	{
		ArrayList<Major> majorList = mTest.getMajors();
		Assert.assertNotNull( majorList );
	}

	public void testGetPositions() 
	{
		ArrayList<String> positions = mTest.getPositions();
		Assert.assertNotNull( positions );
	}

	public void testGetWorkAuth() 
	{

		ArrayList<String> workAuth = mTest.getWorkAuth();
		Assert.assertNotNull( workAuth );
	}

	public void testGetName() 
	{
		String name = mTest.getName();	
		Assert.assertNotNull( name );
	}

	public void testGetWebsite() 
	{
		String website = mTest.getWebsite();
		Assert.assertNotNull( website );
	}

	public void testGetTableNum() 
	{
		String tableNum = mTest.getTableNum();
		Assert.assertNotNull( tableNum );
	}

	public void testGetRoom() 
	{
		String room = mTest.getRoom();	
		Assert.assertNotNull( room );
	}

}
