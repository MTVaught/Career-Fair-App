package com.example.careerfair;



import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

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

	private SQLiteDatabase database;
	private ExternalDbOpenHelper dbOpenHelper;
	private ArrayList<Company> companyList;
	private ArrayList<String> companyNames;
	private boolean databaseOpen = false;
	public SharedPreferences sharedPref;
	public SharedPreferences.Editor editor;
	
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
		mCompanyReaderFragment = (CompanyReaderFragment)getFragmentManager()
				.findFragmentById(R.id.company_reader);
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

		inCompanyView = false;

		if (!databaseOpen) {
			databaseOpen();
		}

		switch(position){

		case 0:
			// Before sending the companies to be filled, filter out the incorrect ones
			// companyNames AND companyList need to be of the same length
			// Delete these comments here when implemented
			
			
			ft.replace(R.id.container, CompanyListFragment.newInstance(position, companyNames)).commit();
			break;
		case 1:
			ft.replace(R.id.container, MultiPurposeGymFragment.newInstance(position)).commit();
			break;
		case 2:
			ArrayList<String> MajorAbbrevs = DbAccess.getAllMajorAbbrevs(database);
			ArrayList<String> WorkAuths = DbAccess.getAllWorkAuths(database);
			ArrayList<String> Positions = DbAccess.getAllPositions(database);
			ft.replace(R.id.container, PreferencesViewFragment.newInstance(position, MajorAbbrevs,WorkAuths,Positions)).commit();
			break;
		}
		
	}

	@Override
	public void onCompanyListItemSelected(int position) {
		// TODO Auto-generated method stub
		FragmentManager fragmentManager = super.getFragmentManager();
		FragmentTransaction ft = fragmentManager.beginTransaction();
		Company clickedCompany = companyList.get(position);
		ft.replace(R.id.container, CompanyReaderFragment.newInstance(position,clickedCompany)).commit();
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
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}


	@Override
	public void onBackPressed() {
		Log.d("CDA", "onBackPressed Called");
		if(inCompanyView){
			FragmentManager fragmentManager = getFragmentManager();
			FragmentTransaction ft = fragmentManager.beginTransaction();
			inCompanyView = false;
			ft.replace(R.id.container, CompanyListFragment.newInstance(0))
			.commit();
		} else {
		}
	}

	private void databaseOpen() {

		long start = System.currentTimeMillis( );

		dbOpenHelper = new ExternalDbOpenHelper(this.getApplicationContext(), "careerFairDB.db");
		database = dbOpenHelper.openDataBase();

		//Database is open
		companyNames = new ArrayList<String>();
		DbAccess.fillCompanies(companyNames, database);
		companyList = DbAccess.getAllCompanies(database);

		databaseOpen = true;

		long end2 = System.currentTimeMillis( );

		long diff2 = end2 - start;

	}	
}
