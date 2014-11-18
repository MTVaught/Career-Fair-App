package com.fragments;

import java.util.ArrayList;

import com.database.DbAccess;
import com.example.careerfair.R;
import com.example.careerfair.R.id;
import com.example.careerfair.R.layout;
import com.helpers.SeparatedListAdapter;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
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
	 * 
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
	
	private static ArrayList<String> companyNameTagNoBlank = new ArrayList<String>();
	private static ArrayList<String> companyNameTagBlank = new ArrayList<String>();
	
	
	private static int goToPosition;
	private static int goToOffset;
	private static boolean positionSaved;

	/*
	 * ArrayList to store the information returned by the database
	 */
	// private ArrayList<String> companies = new ArrayList<String>();
	// Create database if necessary and then open it

	public CompanyListFragment() {

	}
	
	/**newInstance
	 * Returns a new instance of this fragment for the given section number.
	 * @para sectionNumber - 
	 * @para companyName - the filtedCompanyNames in the MainActivity 
	 * @return Return a new instance of this fragment
	 */

	public static CompanyListFragment newInstance(int sectionNumber,
			ArrayList<String> companyName) {
		companyNames = companyName;
		CompanyListFragment fragment = new CompanyListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public static CompanyListFragment newInstance(int sectionNumber) {
		CompanyListFragment fragment = new CompanyListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public static CompanyListFragment newInstance(int sectionNumber, int aGoToPosition, int aGoToOffset) {
		CompanyListFragment fragment = new CompanyListFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		goToPosition = aGoToPosition;
		goToOffset = aGoToOffset;
		positionSaved = true;
		return fragment;
	}

	@Override
	/**onCreate 
	 * Called to initialize the fragment
	 * @ savedInstanceState - if fragment is re-created from previous saved state ,this is the state
	 * 
	 */
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
	/**onCreateView
	 * 
	 * Android Auto generated method for the fragment to create the view of the CompanyList.
	 * It will create a basic listview of CompanyList and scrollable alphabetic index buttons 
	 *  
	 * @para inflater - The LayoutInflater object that can be used to inflate any view in this fragment
	 * @para container - if non-null,this is the parent view that the fragment's UI should be attached to.
	 * The fragment should not add view itself ,but this can be used to generate the LayoutParams of the view
	 * @param savedInstanceState - if fragment is re-created from previous saved state ,this is the state
	 * @return mCompanyListView the returned user interface for companyListView   
	 */
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

		// Bring the MainActivity's sharedPreferences into this fragment
		SharedPreferences sharedPref = getActivity().getPreferences(
						Context.MODE_PRIVATE);
			
		if (sharedPref.getBoolean("separateLists", true)) {
			SeparatedListAdapter adapter = new SeparatedListAdapter(this.getActivity());
			adapter.addSection("Matches", new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_activated_1, DbAccess.getFilteredNamesSep(false) ));
			if (DbAccess.getFilteredNamesSep(true).size() > 0) {
				adapter.addSection("Other Possible Matches (Information Missing)", new ArrayAdapter<String>(getActionBar().getThemedContext(), android.R.layout.simple_list_item_activated_1, DbAccess.getFilteredNamesSep(true) ));
			}
			
			lv1.setAdapter(adapter);
			
			lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				/**onItemClick
				 * Called when item in the adapterView is clicked
				 * @param parent - The adapter view where the click happened
				 * @param view - The view within the Adapter View that was clicked
				 * @param position - the position of view in the adapter
				 * @param id The Row id of the item that was clicked
				 */
				public void onItemClick(AdapterView<?> parent, View view,
						int position, long id) {
					SeparatedListAdapter adapter = (SeparatedListAdapter) parent.getAdapter();
					adapter.getItem(position);
					selectItem(position, adapter.getLastSearchedPosition());
				}
			});
			
			LinearLayout ll = (LinearLayout)mCompanyListView.findViewById(id.scroll_alphabet);
			
			for(int i=0;i<companyNameTagNoBlank.size();i++){

				Button btn1 = new Button(getActivity());
				btn1.setText(companyNameTagNoBlank.get(i).toString());
				btn1.setTag(companyNameTagNoBlank.get(i).toString());
				ll.addView(btn1);
				//set up the button listener to listen to clicked index
				btn1.setOnClickListener(new Button.OnClickListener(){ 
					@Override
					public void onClick(View v) {
						String firstLetter = (String) v.getTag();
						int index = 0;
						ArrayList<String> filteredNames = DbAccess.getFilteredNamesSep(false);
						if (filteredNames != null) {
							for (String string : filteredNames) {
								if (string.replace("The ","").trim().startsWith(firstLetter)) {
									index = filteredNames.indexOf(string);
									break;
								}
							}
						}
						ListView lv1 = (ListView) mCompanyListView.findViewById(id.listView1);
						lv1.setSelectionFromTop(index + 1, 0);
					}
				});    
			}
			if (DbAccess.getFilteredNamesSep(true).size() > 0) {
				Button btn2 = new Button(getActivity());
				btn2.setText("-");
				btn2.setOnClickListener(new Button.OnClickListener() {
					@Override
					public void onClick(View v) {
						ListView lv1 = (ListView) mCompanyListView.findViewById(id.listView1);
						lv1.setSelectionFromTop(DbAccess.getFilteredNamesSep(false).size()+1, 0);
					}
				});
				ll.addView(btn2);
				
				for(int i=0;i<companyNameTagBlank.size();i++){

					Button btn1 = new Button(getActivity());
					btn1.setText(companyNameTagBlank.get(i).toString());
					btn1.setTag(companyNameTagBlank.get(i).toString());
					ll.addView(btn1);
			
					//set up the button listener to listen to clicked index
					btn1.setOnClickListener(new Button.OnClickListener(){ 
						@Override
						public void onClick(View v) {
							String firstLetter = (String) v.getTag();
							int index = 0;
							ArrayList<String> filteredNames = DbAccess.getFilteredNamesSep(true);
							int nonBlankSize = DbAccess.getFilteredNamesSep(false).size();
							if (filteredNames != null) {
								for (String string : filteredNames) {
									if (string.replace("The ","").trim().startsWith(firstLetter)) {
										index = filteredNames.indexOf(string);
										break;
									}
								}
							}
							ListView lv1 = (ListView) mCompanyListView.findViewById(id.listView1);
							lv1.setSelectionFromTop(index + nonBlankSize + 2, 0);
						}
					});    
				}
			}
			
		} else {

			

			lv1.setOnItemClickListener(new AdapterView.OnItemClickListener() {
				@Override
				/**onItemClick
				 * Called when item in the adapterView is clicked
				 * @param parent - The adapter view where the click happened
				 * @param view - The view within the Adapter View that was clicked
				 * @param position - the position of view in the adapter
				 * @param id The Row id of the item that was clicked
				 */
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
								if (string.replace("The ","").trim().startsWith(firstLetter)) {
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
		}
		if (positionSaved) {
			lv1.setSelectionFromTop(goToPosition, goToOffset);
			positionSaved = false;
		}
		return mCompanyListView;
	}

	// http://developer.android.com/training/multiscreen/index.html
	@Override
	/**onActivityCreated
	 * Called when fragment's activity has been created and this fragment's view hierarchy instantiated
	 * @para savedInstanceState - if fragment is re-created from previous saved state ,this is the state
	 */
	public void onActivityCreated(Bundle savedInstanceState) {
		super.onActivityCreated(savedInstanceState);
		// Indicate that this fragment would like to influence the set of
		// actions in the action bar.
		setHasOptionsMenu(true);
	}
	/**selectItem
	 * Called in onItemClick(AdapterView<?> parent, View view,int position, long id)
	 * if the mCompanyListView is not null ,Set the item clicked to be checked 
	 * And if mCallbacks is not null,call its method.
	 * @param position - the index of clicked item in the list
	 */

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
	
	/**selectItem
	 * Called in onItemClick(AdapterView<?> parent, View view,int position, long id)
	 * if the mCompanyListView is not null ,Set the item clicked to be checked 
	 * And if mCallbacks is not null,call its method.
	 * @param position - the index of clicked item in the list
	 */

	private void selectItem(int absPosition, int relPosition) {
		mCurrentSelectedPosition = absPosition;
		if (mCompanyListView != null) {
			ListView lv1 = (ListView)mCompanyListView.findViewById(id.listView1);
			lv1.setItemChecked(absPosition, true);
		}
		// if (mDrawerLayout != null) {
		// mDrawerLayout.closeDrawer(mFragmentContainerView);
		// }
		if (mCallbacks != null) {
			mCallbacks.onCompanyListItemSelected(absPosition, relPosition);
		}
	}
	
    /**CompanyListCallbacks
     * The interface which must be implemented by MainActivity to handle the click events
     * @author zichengl
     *
     */
	public static interface CompanyListCallbacks {
		/**onCompanyListItemSelected
		 * Called in selectItem(int position) when an item in the CompanyList drawer is selected.
		 * @para position - the index of clicked item in the list
		 */
		void onCompanyListItemSelected(int position);
		void onCompanyListItemSelected(int absPosition, int relPosition);
	}

	@Override 
	public void onPause() {
		super.onPause();
		ListView lv1 = (ListView)mCompanyListView.findViewById(id.listView1);
		// save index and top position
		int index = lv1.getFirstVisiblePosition();
		View v = lv1.getChildAt(0);
		int top = (v == null) ? 0 : v.getTop();
		
		MainActivity main = (MainActivity)getActivity();
		main.mLastPosition = index;
		main.mLastOffset = top;
	}
	
	@Override
	/**onAttach
	 * Called when this fragment is first attached to its activity
	 * @para activity - the activity it belongs to
	 */
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
	
		if (sharedPref.getBoolean("separateLists", true)) {
			//Setup the alphabet array from filteredCompanyNames
			for(String string: DbAccess.getFilteredNamesSep(false)){
				String tag = string.replace("The ","").trim().substring(0, 1);
					if (!companyNameTagNoBlank.contains(tag))
						companyNameTagNoBlank.add(tag);
						
			}
			
			//Setup the alphabet array from filteredCompanyNames
			for(String string: DbAccess.getFilteredNamesSep(true)){
				String tag = string.replace("The ","").trim().substring(0, 1);
					if (!companyNameTagBlank.contains(tag))
						companyNameTagBlank.add(tag);
						
			}
		} else {
			//Setup the alphabet array from filteredCompanyNames
			for(String string: ((MainActivity)getActivity()).filteredCompanyNames){
				String tag = string.replace("The ","").trim().substring(0, 1);
					if (!companyNameTag.contains(tag))
						companyNameTag.add(tag);
						
			}
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
	/**onDetach
	 * called when this fragment is no longer attached to its activity
	 */
	public void onDetach() {
		super.onDetach();
		mCallbacks = null;
		//clean out the array when detach
		companyNameTag = new ArrayList<String>();
		// database = null;
	}
    
	@Override
	/**onSaveInstanceState
     * Called to ask the fragment to save its current dynamic state.So it can later be reconstructed in a new instance 
     * of its process is restarted .(Refer to official JavaDoc)
     * @para Bundle in which to place your state
     */
	public void onSaveInstanceState(Bundle outState) {
		super.onSaveInstanceState(outState);
		outState.putInt(STATE_SELECTED_POSITION, mCurrentSelectedPosition);
	}

	@Override
	/**onConfigurationChanged
	 * Called by the system when the device configuration changes while your component is running
	 * @para newConfig - The new device configuration
	 */
	public void onConfigurationChanged(Configuration newConfig) {
		super.onConfigurationChanged(newConfig);
		// Forward the new configuration the drawer toggle component.
		mDrawerToggle.onConfigurationChanged(newConfig);
	}
	/**getActionBar
	 * Retrieve a reference to this activity's action bar
	 * @return The activity's action bar ,or null if it does not have one
	 */
	
	private ActionBar getActionBar() {
		return getActivity().getActionBar();
	}

	@Override
	/**onCreateOptionsMenu
	 * Initialize the contents of the Activity's standard options menu.(refer to offical JavaDoc)
	 * @para The options menu where you place your items
	 */
	
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
