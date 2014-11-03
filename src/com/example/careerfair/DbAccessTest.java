package com.example.careerfair;

import java.util.ArrayList;

import junit.framework.Assert;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.test.AndroidTestCase;

public class DbAccessTest extends AndroidTestCase {

	SQLiteDatabase database;

	@Override
	protected void setUp() throws Exception {
		MainActivity app = new MainActivity();
		Context aContext = getContext();
		ExternalDbOpenHelper helper = new ExternalDbOpenHelper(getContext(),
				"careerFairDB.db");
		database = helper.openDataBase();

		super.setUp();
	}

	@Override
	protected void tearDown() throws Exception {
		super.tearDown();
	}

	public void testFillCompanies() {
		ArrayList<Company> companies = new ArrayList<Company>();
		DbAccess.fillCompanies(companies, database);

		Assert.assertNotNull(companies);
	}

	public void testGetFilteredNames() {
		testGetCompaniesWith();
		ArrayList<String> filteredNames = DbAccess.getFilteredNames(database);

		Assert.assertNotNull(filteredNames);

	}

	public void testGetAllCompaniesArrayListOfCompanySQLiteDatabase() {
		ArrayList<Company> companies = new ArrayList<Company>();
		DbAccess.getAllCompanies(companies, database);

		Assert.assertNotNull(companies);
	}

	public void testGetAllCompaniesSQLiteDatabase() {
		ArrayList<Company> companies;
		companies = DbAccess.getAllCompanies(database);

		Assert.assertNotNull(companies);
	}

	public void testGetCompaniesWith() {
		ArrayList<Company> companies = DbAccess.getCompaniesWith("", "",
				new ArrayList<String>(), new ArrayList<String>(),
				new ArrayList<String>(), database);

		Assert.assertNotNull(companies);
	}

	// Depricated (currently unused after performance fixes)
	public void testGetMajorsForCompany() {

	}

	// Depricated (currently unused after performance fixes)
	public void testGetPositionsForCompany() {

	}

	// Depricated (currently unused after performance fixes)
	public void testGetWorkAuthsForCompany() {

	}

	public void testGetAllMajors() {
		ArrayList<Major> majorsByName = DbAccess.getAllMajors(database, true);
		ArrayList<Major> majorsByAbbrev = DbAccess
				.getAllMajors(database, false);

		Assert.assertNotNull(majorsByName);
		Assert.assertNotNull(majorsByAbbrev);

		Assert.assertTrue(majorsByName.size() == majorsByAbbrev.size());
		Assert.assertTrue(majorsByName.size() == 84);
		Assert.assertTrue(majorsByAbbrev.size() == 84);
	}

	public void testGetAllMajorNames() {
		ArrayList<String> majorNames = DbAccess.getAllMajorNames(database);

		Assert.assertNotNull(majorNames);

		Assert.assertTrue(majorNames.size() == 84);
	}

	public void testGetAllMajorAbbrevs() {
		ArrayList<String> majorAbbrevs = DbAccess.getAllMajorAbbrevs(database);

		Assert.assertNotNull(majorAbbrevs);

		Assert.assertTrue(majorAbbrevs.size() == 84);
	}

	public void testGetAllWorkAuths() {
		ArrayList<String> workAuths = DbAccess.getAllWorkAuths(database);

		Assert.assertNotNull(workAuths);

		Assert.assertTrue(workAuths.size() == 6);
	}

	public void testGetAllPositions() {
		ArrayList<String> positions = DbAccess.getAllPositions(database);

		Assert.assertNotNull(positions);

		Assert.assertTrue(positions.size() == 4);
	}

}
