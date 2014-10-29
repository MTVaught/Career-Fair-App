package com.example.careerfair;

import java.util.ArrayList;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.SharedPreferences;
import android.content.res.Configuration;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v4.app.ActionBarDrawerToggle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.careerfair.*;
import com.example.careerfair.NavigationDrawerFragment.NavigationDrawerCallbacks;

public class CompanyListFragment extends Fragment{
	private static final String DB_NAME = "careerFairDB.db";
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "CompanyList";
	/**
	 * Remember the position of the selected item.
	 */
	private static final String STATE_SELECTED_POSITION = "selected_navigation_drawer_position";


	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static CompanyListFragment newInstance(int sectionNumber) {
		CompanyListFragment fragment = new CompanyListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	private int mCurrentSelectedPosition = 0;
	//private boolean mFromSavedInstanceState;
	//private boolean mUserLearnedDrawer;
	//private SQLiteDatabase database;
	private ListView mCompanyListView;
	private CompanyListCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;
	//private ExternalDbOpenHelper dbOpenHelper;
	//private static ArrayList<Company> companyList;
	private static ArrayList<String> companyNames;
	/*
	 * ArrayList to store the information returned by the database
	 */
	//private ArrayList<String> companies = new ArrayList<String>();
	//Create database if necessary and then open it

	public CompanyListFragment(){

	}


	public static CompanyListFragment newInstance(int sectionNumber, ArrayList<String> companyName, ArrayList<Company> companies) {
		companyNames = companyName;
		//companyList = companies;
		CompanyListFragment fragment = new CompanyListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);


		// Read in the flag indicating whether or not the user has demonstrated
		// awareness of the
		// drawer. See PREF_USER_LEARNED_DRAWER for details.
		//SharedPreferences sp = PreferenceManager
		//		.getDefaultSharedPreferences(getActivity());
		//mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		//			if (savedInstanceState != null) {
		//				mCurrentSelectedPosition = savedInstanceState
		//						.getInt(STATE_SELECTED_POSITION);
		//				mFromSavedInstanceState = true;
		//			}
		//
		//			// Select either the default item (0) or the last selected item.
		//			selectItem(mCurrentSelectedPosition);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {


		// Inflate the layout for this fragment
		this.mCompanyListView =(ListView) inflater.inflate(
				R.layout.company_list, 
				container,
				false);
		this.mCompanyListView
		.setOnItemClickListener(new AdapterView.OnItemClickListener() {
			@Override
			public void onItemClick(AdapterView<?> parent, View view,
					int position, long id) {
				selectItem(position);
			}
		});
		mCompanyListView.setAdapter(new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_list_item_activated_1,
				companyNames));
		mCompanyListView.setItemChecked(mCurrentSelectedPosition, true);
		return mCompanyListView;
	}
	//http://developer.android.com/training/multiscreen/index.html
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}

	private void selectItem(int position) {
		mCurrentSelectedPosition = position;
		if (mCompanyListView != null) {
			mCompanyListView.setItemChecked(position, true);
		}
		//if (mDrawerLayout != null) {
		//	mDrawerLayout.closeDrawer(mFragmentContainerView);
		//}
		if (mCallbacks != null) {
			mCallbacks.onCompanyListItemSelected(position);
		}
	}

	public static interface CompanyListCallbacks {
		/**
		 * Called when an item in the CompanyList drawer is selected.
		 */
		void onCompanyListItemSelected(int position);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		try {
			mCallbacks = (CompanyListCallbacks) activity;
		} catch (ClassCastException e) {
			throw new ClassCastException(
					"Activity must implement CompanyListCallbacks.");
		}

		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));

	}

	@Override
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
		//database = null;
	}

	@Override
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	@Override
	public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
		// If the drawer is open, show the global app actions in the action bar.
		// See also
		// showGlobalContextActionBar, which controls the top-left area of the
		// action bar.
		//if (isDrawerOpen()) {
		//	inflater.inflate(R.menu.global, menu);
		//	showGlobalContextActionBar();
		//}
		//super.onCreateOptionsMenu(menu, inflater);
	}
}

