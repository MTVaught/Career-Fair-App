package com.fragments;

import java.util.ArrayList;

import com.example.careerfair.R;
import com.example.careerfair.R.id;
import com.example.careerfair.R.layout;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.res.Configuration;
import android.os.Bundle;
import android.support.v4.app.ActionBarDrawerToggle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.Button;
import android.widget.ScrollView;
	/**
	* @authour zichengl
	*/
public class CompanyListFragment extends Fragment {
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
	// private boolean mFromSavedInstanceState;
	// private boolean mUserLearnedDrawer;
	// private SQLiteDatabase database;
	private View mCompanyListView;
	private CompanyListCallbacks mCallbacks;
	private ActionBarDrawerToggle mDrawerToggle;
	// private ExternalDbOpenHelper dbOpenHelper;
	// private static ArrayList<Company> companyList;
	private static ArrayList<String> companyNames;
	private static ArrayList<String> companyNameTag = new ArrayList<String>();

	/*
	 * ArrayList to store the information returned by the database
	 */
	// private ArrayList<String> companies = new ArrayList<String>();
	// Create database if necessary and then open it

	public CompanyListFragment() {

	}

	public static CompanyListFragment newInstance(int sectionNumber,
			ArrayList<String> companyName) {
		companyNames = companyName;
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
		// SharedPreferences sp = PreferenceManager
		// .getDefaultSharedPreferences(getActivity());
		// mUserLearnedDrawer = sp.getBoolean(PREF_USER_LEARNED_DRAWER, false);

		// if (savedInstanceState != null) {
		// mCurrentSelectedPosition = savedInstanceState
		// .getInt(STATE_SELECTED_POSITION);
		// mFromSavedInstanceState = true;
		// }
		//
		// // Select either the default item (0) or the last selected item.
		// selectItem(mCurrentSelectedPosition);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Inflate the layout for this fragment
		/* non-javadoc
		 * changed the xml file .The basic company_list.xml layout is <RelativeLayout> now
		 * and the ListView is one its subView
		 */
		this.mCompanyListView =  inflater.inflate(
		R.layout.company_list, container, false);
		
		/*non-javadoc
		 * To get the ListView of the layout
		 */
		ListView lv1 = (ListView) mCompanyListView.findViewById(id.listView1);
		
		
		lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
					@Override
					public void onItemClick(AdapterView<?> parent, View view,
							int position, long id) {
						selectItem(position);
					}
				});
		lv1.setAdapter(new ArrayAdapter<String>(getActionBar()
				.getThemedContext(),
				android.R.layout.simple_list_item_activated_1,
				((MainActivity)getActivity()).filteredCompanyNames));                                                                                                                                                                                                                                                                                                                                                                                                                                                         
		lv1.setItemChecked(mCurrentSelectedPosition, true);
		
		
		
		/*
		 * non-java-doc
		 * Setup the alphabet array from filteredCompanyNames
		 */

		
		for(int i=0;i<companyNameTag.size();i++){
			
			LinearLayout ll = (LinearLayout)mCompanyListView.findViewById(id.scroll_alphabet);
			
			Button btn1 = new Button(getActivity());
			btn1.setWidth(32);
			btn1.setHeight(9);
			btn1.setText(companyNameTag.get(i).toString());
			btn1.setTag(companyNameTag.get(i).toString());
			ll.addView(btn1);
			//set up the button listener to listen to clicked index
			btn1.setOnClickListener(new Button.OnClickListener(){ 
				@Override
				public void onClick(View v) {
					String firstLetter = (String) v.getTag();
					int index = 0;
					if (((MainActivity)getActivity()).filteredCompanyNames != null) {
						for (String string : ((MainActivity)getActivity()).filteredCompanyNames) {
							if (string.startsWith(firstLetter)) {
								index = ((MainActivity)getActivity()).filteredCompanyNames.indexOf(string);
								break;
							}
						}
					}
					ListView lv1 = (ListView) mCompanyListView.findViewById(id.listView1);
					lv1.setSelectionFromTop(index, 0);
				}
				});    
			}
		return mCompanyListView;
	}

	// http://developer.android.com/training/multiscreen/index.html
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
			ListView lv1 = (ListView)mCompanyListView.findViewById(id.listView1);
			lv1.setItemChecked(position, true);
		}
		// if (mDrawerLayout != null) {
		// mDrawerLayout.closeDrawer(mFragmentContainerView);
		// }
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
		/*
		 * non-java-doc
		 * Setup the alphabet array from filteredCompanyNames
		 */
		for(String string: ((MainActivity)getActivity()).filteredCompanyNames){
			String tag = string.substring(0, 1);
				if (!companyNameTag.contains(tag))
					companyNameTag.add(tag);
					
		}
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
		//clean out the array when detach
		companyNameTag = new ArrayList<String>();
		// database = null;
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
		// if (isDrawerOpen()) {
		// inflater.inflate(R.menu.global, menu);
		// showGlobalContextActionBar();
		// }
		// super.onCreateOptionsMenu(menu, inflater);
	}
}
