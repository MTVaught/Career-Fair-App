package com.fragments;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.database.Company;
import com.database.DbAccess;
import com.database.ExternalDbOpenHelper;
import com.example.careerfair.R;
import com.example.careerfair.R.id;
import com.example.careerfair.R.layout;
import com.example.careerfair.R.menu;
import com.example.careerfair.R.string;
import com.google.gson.Gson;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,
		CompanyListFragment.CompanyListCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	private CompanyListFragment mCompanyListFragment;
	private CompanyReaderFragment mCompanyReaderFragment;
	private WoodGymFragment mWoodGymFragment;

	protected SQLiteDatabase database;
	private ExternalDbOpenHelper dbOpenHelper;
	private ArrayList<Company> companyList;
	private ArrayList<String> companyNames;
	protected ArrayList<Company> filteredCompanyList;
	protected ArrayList<String> filteredCompanyNames;
	private boolean databaseOpen = false;
	public SharedPreferences sharedPref;
	public SharedPreferences.Editor editor;

	// Holds reference to MainActivity object being used by the app;
	public static MainActivity appMainActivity;

	/**
	 * Used to store the last screen title. For use in
	 * {@link #restoreActionBar()}.
	 */
	private CharSequence mTitle;
	boolean inCompanyView = false;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		FragmentManager fragmentManager = getFragmentManager();
		// FragmentTransaction ft = fragmentManager.beginTransaction();

		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mCompanyListFragment = (CompanyListFragment) getFragmentManager()
				.findFragmentById(R.id.listView1);
		mCompanyReaderFragment = (CompanyReaderFragment) getFragmentManager()
				.findFragmentById(R.id.company_reader);
		mWoodGymFragment = (WoodGymFragment) getFragmentManager()
				.findFragmentById(R.id.varsity);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		// Set up the company list

		// Setup the shared Preferences
		sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		editor = sharedPref.edit();

	}

	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by adding fragments
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		appMainActivity = this;
		inCompanyView = false;

		if (!databaseOpen) {
			databaseOpen();
		}

		switch (position) {

		case 0:
			// Before sending the companies to be filled, filter out the
			// incorrect ones
			// companyNames AND companyList need to be of the same length
			// Delete these comments here when implemented

			ft.replace(
					R.id.container,
					CompanyListFragment.newInstance(position,
							filteredCompanyNames)).commit();
			break;
		case 1:
			ft.replace(R.id.container,
					MultiPurposeGymFragment.newInstance(position)).commit();
			break;
		case 2:
			ft.replace(R.id.container, WoodGymFragment.newInstance(position))
					.commit();
			break;
		case 3:
			ArrayList<String> MajorAbbrevs = DbAccess
					.getAllMajorAbbrevs(database);
			ArrayList<String> WorkAuths = DbAccess.getAllWorkAuths(database);
			ArrayList<String> Positions = DbAccess.getAllPositions(database);
			ft.replace(
					R.id.container,
					PreferencesViewFragment.newInstance(position, MajorAbbrevs,
							WorkAuths, Positions)).commit();
			break;
		}

	}

	@Override
	public void onCompanyListItemSelected(int position) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = super.getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		Company clickedCompany = filteredCompanyList.get(position);
		ft.replace(R.id.container,
				CompanyReaderFragment.newInstance(position, clickedCompany))
				.commit();
		inCompanyView = true;
	}

	public void onSectionAttached(int number) {
		switch (number) {
		case 0:
			mTitle = getString(R.string.ListView);
			break;
		case 1:
			mTitle = getString(R.string.title_multipurposegym);
			break;
		case 2:
			mTitle = getString(R.string.title_woodgym);
			break;
		case 3:
			mTitle = getString(R.string.PreferencesFragment);
			break;
		}
	}

	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		if (!mNavigationDrawerFragment.isDrawerOpen()) {
			// Only show items in the action bar relevant to this screen
			// if the drawer is not showing. Otherwise, let the drawer
			// decide what to show in the action bar.
			getMenuInflater().inflate(R.menu.main, menu);
			restoreActionBar();
			return true;
		}
		return super.onCreateOptionsMenu(menu);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		int position = item.getOrder();
		if (id == R.id.action_settings) {
			ArrayList<String> MajorAbbrevs = DbAccess
					.getAllMajorAbbrevs(database);
			ArrayList<String> WorkAuths = DbAccess.getAllWorkAuths(database);
			ArrayList<String> Positions = DbAccess.getAllPositions(database);
			FragmentManager fragmentManager = super.getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			ft.replace(
					R.id.container,
					PreferencesViewFragment.newInstance(position, MajorAbbrevs,
							WorkAuths, Positions)).commit();
			// getMenuInflater().inflate(R.menu.setting,(Menu) item);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");
		if (inCompanyView) {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			inCompanyView = false;
			ft.replace(R.id.container, CompanyListFragment.newInstance(0))
					.commit();
		} else {
		}
	}

	private void databaseOpen() {

		long start = System.currentTimeMillis();

		dbOpenHelper = new ExternalDbOpenHelper(this.getApplicationContext(),
				"careerFairDB.db");
		database = dbOpenHelper.openDataBase();

		// Database is open
		companyNames = new ArrayList<String>();
		DbAccess.fillCompanies(companyNames, database);
		companyList = DbAccess.getAllCompanies(database);

		filterCompanies();

		databaseOpen = true;

		long end2 = System.currentTimeMillis();

		long diff2 = end2 - start;

	}

	protected void filterCompanies() {
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

		ArrayList<String> majors;
		ArrayList<String> workAuth;
		ArrayList<String> position;

		Gson gson = new Gson();
		String jsonMajor = sharedPref.getString("majors", "");
		String jsonWorkAuth = sharedPref.getString("workAuths", "");
		String jsonPosition = sharedPref.getString("positions", "");
		if (jsonMajor.equals("")) {
			majors = new ArrayList<String>();
		} else {
			majors = gson.fromJson(jsonMajor, ArrayList.class);
		}

		if (jsonWorkAuth.equals("")) {
			workAuth = new ArrayList<String>();
		} else {
			workAuth = gson.fromJson(jsonWorkAuth, ArrayList.class);
		}

		if (jsonPosition.equals("")) {
			position = new ArrayList<String>();
		} else {
			position = gson.fromJson(jsonPosition, ArrayList.class);
		}

		filteredCompanyList = DbAccess.getCompaniesWith("", "", majors,
				workAuth, position, database);
		filteredCompanyNames = DbAccess.getFilteredNames(database);
	}
}
