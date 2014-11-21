package com.JUnit;

import java.util.ArrayList;

import com.database.Company;
import com.database.DbAccess;
import com.database.ExternalDbOpenHelper;
import com.database.Major;
import com.fragments.MainActivity;

import junit.framework.Assert;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

/**
 * DbAccessTest
 * Contains a set of JUnit test cases to test the DbAccess class
 * @author hewilder
 */
public class DbAccessTest extends AndroidTestCase {

	SQLiteDatabase database;

	/**
	 * setUp
	 * Sets up necessary resources for test
	 */
	@Override
	protected void setUp() throws Exception {
		
		//Initialize values for testing
		MainActivity app = new MainActivity();
		Context aContext = getContext();
		ExternalDbOpenHelper helper = new ExternalDbOpenHelper(getContext(),
				"careerFairDB.db");
		database = helper.openDataBase();

		super.setUp();
	}

	/**
	 * tearDown
	 * Destroy objects when test is finished
	 */
	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	/**
	 * testFillCompanies
	 * Tests fillCompanies method
	 */
	public void testFillCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		DbAccess.fillCompanies(companies, database);

		//Make sure something was returned
		Assert.assertNotNull(companies);
	}

	/**
	 * getFilteredNames
	 * Tests getFilteredNames method
	 */
	public void testGetFilteredNames() {
		testGetCompaniesWith();
		ArrayList<String> filteredNames = DbAccess.getFilteredNames(database);

		//Make sure something was returned
		Assert.assertNotNull(filteredNames);

	}

	/**
	 * testGetAllCompaniesArrayListOfCompanySQLiteDatabase
	 * Tests getAllCompanies (returns ArrayList)
	 */
	public void testGetAllCompaniesArrayListOfCompanySQLiteDatabase() {
		ArrayList<Company> companies = new ArrayList<Company>();
		DbAccess.getAllCompanies(companies, database);

		//Make sure something was returned
		Assert.assertNotNull(companies);
	}

	/**
	 * testGetAllCompaniesSQLiteDatabase
	 * Tests getAllCompanies (void return)
	 */
	public void testGetAllCompaniesSQLiteDatabase() {
		ArrayList<Company> companies;
		companies = DbAccess.getAllCompanies(database);

		//Make sure something was returned
		Assert.assertNotNull(companies);
	}

	/**
	 * testGetCompaniesWith
	 * Tests getCompaniesWith
	 */
	public void testGetCompaniesWith() {
		ArrayList<Company> companies = DbAccess.getCompaniesWith("",
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), database);

		//Make sure something was returned
		Assert.assertNotNull(companies);
	}

	/**
	 * testGetMajorsForCompany
	 * Deprecated (currently unused after performance fixes)
	 */
	public void testGetMajorsForCompany() {

	}

	/**
	 * testGetPositionsForCompany
	 * Deprecated (currently unused after performance fixes)
	 */
	public void testGetPositionsForCompany() {

	}

	/**
	 * testGetWorkAuthsForCompany
	 * Deprecated (currently unused after performance fixes)
	 */
	public void testGetWorkAuthsForCompany() {

	}

	/**
	 * testGetAllMajors
	 * Tests getAllMajors
	 */
	public void testGetAllMajors() {
		ArrayList<Major> majorsByName = DbAccess.getAllMajors(database, true);
		ArrayList<Major> majorsByAbbrev = DbAccess
				.getAllMajors(database, false);

		//Make sure something was returned
		Assert.assertNotNull(majorsByName);
		Assert.assertNotNull(majorsByAbbrev);

		//Compare with the expected sizes
		Assert.assertTrue(majorsByName.size() == majorsByAbbrev.size());
		Assert.assertTrue(majorsByName.size() == 84);
		Assert.assertTrue(majorsByAbbrev.size() == 84);
	}

	/**
	 * testGetAllMajorNames
	 * Tests getAllMajorNames
	 */
	public void testGetAllMajorNames() {
		ArrayList<String> majorNames = DbAccess.getAllMajorNames(database);

		//Make sure something was returned
		Assert.assertNotNull(majorNames);

		//Compare with the expected size
		Assert.assertTrue(majorNames.size() == 84);
	}

	/**
	 * testGetAllMajorAbbrevs
	 * Tests getAllMajorAbbrevs method
	 */
	public void testGetAllMajorAbbrevs() {
		ArrayList<String> majorAbbrevs = DbAccess.getAllMajorAbbrevs(database);

		//Make sure something was returned
		Assert.assertNotNull(majorAbbrevs);

		//Compare with the expected size
		Assert.assertTrue(majorAbbrevs.size() == 84);
	}

	/**
	 * testGetAllWorkAuths
	 * Tests getAllWorkAuths method
	 */
	public void testGetAllWorkAuths() {
		ArrayList<String> workAuths = DbAccess.getAllWorkAuths(database);

		//Make sure something was returned
		Assert.assertNotNull(workAuths);

		//Compare with the expected size
		Assert.assertTrue(workAuths.size() == 6);
	}

	/**
	 * testGetAllPositions
	 * Tests getAllPositions method
	 */
	public void testGetAllPositions() {
		ArrayList<String> positions = DbAccess.getAllPositions(database);

		//Make sure something was returned
		Assert.assertNotNull(positions);

		//Compare with the expected size
		Assert.assertTrue(positions.size() == 4);
	}

}
