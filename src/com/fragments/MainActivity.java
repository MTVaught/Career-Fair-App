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
import com.google.gson.Gson;

public class MainActivity extends Activity implements
		NavigationDrawerFragment.NavigationDrawerCallbacks,
		CompanyListFragment.CompanyListCallbacks {

	/**
	 * Fragment managing the behaviors, interactions and presentation of the
	 * navigation drawer.
	 */
	private NavigationDrawerFragment mNavigationDrawerFragment;
	//private CompanyListFragment mCompanyListFragment;
	//private CompanyReaderFragment mCompanyReaderFragment;
	//private WoodGymFragment mWoodGymFragment;

	protected SQLiteDatabase database;
	private ExternalDbOpenHelper dbOpenHelper;
	private ArrayList<String> companyNames;
	protected ArrayList<Company> filteredCompanyList;
	protected ArrayList<String> filteredCompanyNames;
	public int mLastPosition = -1;
	public int mLastOffset = 0;
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

	/**
	 * onCreate
	 * Called when the activity is starting. This is where most initialization should go (also refer to official Javadocs)
	 * @param savedInstanceState -if the activity is being re-initialized after previously being shut down then this Bundle contains the data it most recently supplied in onSaveInstanceState(Bundle). Note: Otherwise it is null.
	 */
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		mNavigationDrawerFragment = (NavigationDrawerFragment) getFragmentManager()
				.findFragmentById(R.id.navigation_drawer);
		mTitle = getTitle();

		// Set up the drawer.
		mNavigationDrawerFragment.setUp(R.id.navigation_drawer,
				(DrawerLayout) findViewById(R.id.drawer_layout));
		// Set up the company list

		// Setup the shared Preferences
		sharedPref = this.getPreferences(Context.MODE_PRIVATE);
		editor = sharedPref.edit();

	}

	/**
	 * onNavigationDrawerItemSelected
	 * Changes the displayed fragment based on what the user selected in the Navigation Drawer
	 * @param position - a number corresponding to the order in the navigation drawer 
	 */
	@Override
	public void onNavigationDrawerItemSelected(int position) {
		// update the main content by adding fragments
		FragmentManager fragmentManager = getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		appMainActivity = this;
		inCompanyView = false;

		//Check if the database is open, if not, call the method to open the database
		if (!databaseOpen) {
			databaseOpen();
		}

		switch (position) {
		case 0:
			ft.replace(R.id.container,
					WelcomeMessageFragment.newInstance(position)).commit();
			break;
		case 1:
ft.replace(
					R.id.container,
					CompanyListFragment.newInstance(position,
							filteredCompanyNames)).commit();
			break;

		case 2:
			ft.replace(R.id.container,
					MultiPurposeGymFragment.newInstance(position)).commit();
			break;
		case 3:
			ft.replace(R.id.container, WoodGymFragment.newInstance(position))
					.commit();
			break;
		case 4:
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

	/**
	 * onCompanyListItemSelected
	 * Switches to the "detailed"/CompanyReaderView
	 * @param position - the position of the company that was clicked in the ArrayList of companies
	 */
	@Override
	public void onCompanyListItemSelected(int position) {

		FragmentManager fragmentManager = super.getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		Company clickedCompany = filteredCompanyList.get(position);
		mTitle = clickedCompany.getName();
		ft.replace(R.id.container,
				CompanyReaderFragment.newInstance(position, clickedCompany))
				.commit();
		inCompanyView = true;
		//mLastPosition = position;
	}
	
	/**
	 * onCompanyListItemSelected
	 * Switches to the "detailed"/CompanyReaderView
	 * @param position - the position of the company that was clicked in the ArrayList of companies
	 */
	@Override
	public void onCompanyListItemSelected(int absPosition, int relPosition) {

		FragmentManager fragmentManager = super.getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		Company clickedCompany;
		if (absPosition - 1 > relPosition) {
			clickedCompany = DbAccess.getFilteredSep(true).get(relPosition);
		} else {
			clickedCompany = DbAccess.getFilteredSep(false).get(relPosition);
		}
		mTitle = clickedCompany.getName();
		ft.replace(R.id.container,
				CompanyReaderFragment.newInstance(absPosition, clickedCompany))
				.commit();
		inCompanyView = true;
		//mLastPosition = absPosition;
	}

	/**
	 * onSectionAttached
	 * Changes the title on the top bar based on the passed number
	 * @param number - a number corresponding to the order in the navigation drawer
	 */
	public void onSectionAttached(int number) {
		// Changes the title displayed on the top bar based upon the passed number.
		// Corresponds to the order in the navigation drawer.
		switch (number) {

		case 0:
			mTitle = getString(R.string.title_welcomemessage);
			break;
		case 1:
			mTitle = getString(R.string.title_companylist);
			break;
		case 2:
			mTitle = getString(R.string.title_multipurposegym);
			break;
		case 3:
			mTitle = getString(R.string.title_woodgym);
			break;
		case 4:

			mTitle = getString(R.string.title_preferencesview);
			break;

		}
	}

	/**
	 * restoreActionBar (Generated)
	 * Called when exiting navigation bar to restore the action bar
	 */
	public void restoreActionBar() {
		ActionBar actionBar = getActionBar();
		actionBar.setNavigationMode(ActionBar.NAVIGATION_MODE_STANDARD);
		actionBar.setDisplayShowTitleEnabled(true);
		actionBar.setTitle(mTitle);
	}

	/**
	 * onCreateOptionsMenu
	 * Initialize the contents of the Activity's standard options menu. You should place your menu items in to menu. This is only called once, the first time the options menu is displayed. 
	 * To update the menu every time it is displayed, see onPrepareOptionsMenu(Menu).
	 * @param menu - The options menu in which you place your items.
	 * @return boolean - You must return true for the menu to be displayed; if you return false it will not be shown.
	 */
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
  //deleted override onBackPressed() here 
	
	/**onOptionsItemSelected 
	 * Three Dot Drop-down Menu
	 * @param item - the selected item of options menu
	 * @return true - if the item is selected.
	 */
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

	/**
	 * onBackPressed
	 * Controls the actions taken when the back arrow is pressed while in the android application.
	 * By default, no action is taken unless the user is in the Detailed Company View, then they
	 * are taken to the List of Companies.
	 */
	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");
		if (inCompanyView) {
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			inCompanyView = false;
			if (mLastPosition != -1) {
				ft.replace(R.id.container, CompanyListFragment.newInstance(0, mLastPosition, mLastOffset))
				.commit();
			} else {
				ft.replace(R.id.container, CompanyListFragment.newInstance(0))
				.commit();
			}
			
		} else {
		}
	}

	/**
	 * databaseOpen
	 * Opens database and fills initial company lists (with all of the companies) and filters based on shared preferences
	 */
	private void databaseOpen() {

		long start = System.currentTimeMillis();

		dbOpenHelper = new ExternalDbOpenHelper(this.getApplicationContext(),
				"careerFairDB.db");
		database = dbOpenHelper.openDataBase();

		// Database is open
		companyNames = new ArrayList<String>();
		DbAccess.fillCompanies(companyNames, database);
		DbAccess.getAllCompanies(database);

		//Calls the filterCompanies method to filter based on shared preferences
		filterCompanies();
        
		databaseOpen = true;

		long end2 = System.currentTimeMillis();

		long diff2 = end2 - start;

	}

	/**
	 * filterCompanies
	 * Pulls information from shared preferences and updates filtered company lists
	 */
	protected void filterCompanies() {
		SharedPreferences sharedPref = getPreferences(Context.MODE_PRIVATE);

		ArrayList<String> majors;
		ArrayList<String> workAuth;
		ArrayList<String> position;

		//Get preferences from SharedPreferences and "de-serialize" them
		Gson gson = new Gson();
		String jsonMajor = sharedPref.getString("majors", "");
		String jsonWorkAuth = sharedPref.getString("workAuths", "");
		String jsonPosition = sharedPref.getString("positions", "");
		
		//Check if there was any information stored in shared preferences
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

		//Get the new filteredCompanyList based on the shared preferences 
		filteredCompanyList = DbAccess.getCompaniesWith("", "", majors,
				workAuth, position, database);
		filteredCompanyNames = DbAccess.getFilteredNames(database);
	}
}
