/** 
 * This class handles setting the filtering options.
 * 
 * @author Andrew Hanson
 * @version 1.0
 */
package com.fragments;

import java.util.ArrayList;

import com.example.careerfair.R;
import com.helpers.CheckBoxListener;
import com.helpers.ResetButtonListener;
import com.helpers.separateListListener;

import android.app.ActionBar;
import android.app.Activity;
import android.app.Fragment;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.Switch;
import android.widget.TextView;
import android.widget.ToggleButton;

public class PreferencesViewFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "PreferencesView";

	static ArrayList<String> MajorAbbrevs;
	static ArrayList<String> WorkAuths;
	static ArrayList<String> Positions;

	/**
	 * PreferencesViewFragment
	 * 
	 * Default constructor
	 */
	public PreferencesViewFragment() {}

	/**
	 * newInstance
	 * 
	 * Returns a new instance of this fragment for the given section number.
	 * 
	 * @param sectionNumber
	 * @param Abbrevs. List of Abbreviations
	 * @param Auths. List of Authorizations
	 * @param Pos. List of of positions
	 * 
	 * @return PreferencesViewFragment, the newely created fragment
	 */
	public static PreferencesViewFragment newInstance(int sectionNumber,
			ArrayList<String> Abbrevs, ArrayList<String> Auths,
			ArrayList<String> Pos) {
		MajorAbbrevs = Abbrevs;
		WorkAuths = Auths;
		Positions = Pos;
		PreferencesViewFragment fragment = new PreferencesViewFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	}

	/**
	 * onCreateView 
	 * 
	 * Create the view displayed for settings the filters. This is done dynamically.
	 * 	this will display the filters for majors, positions, workauthorizations
	 * 
	 * @param inflater. The LayoutInflater object that can be used to inflate any views in the fragment
	 * @param container. If non-null, this is the parent view that the fragment's UI should be attached to. 
	 * 			The fragment should not add the view itself, but this can be used to generate the LayoutParams of the view.
	 * @param savedInstanceState. If non-null, this fragment is being re-constructed from a previous saved state as given here.
	 * 
	 * @return View. Return the View for the fragment's UI, or null
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		// Bring the MainActivity's sharedPreferences into this fragment
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);
		SharedPreferences.Editor editor = sharedPref.edit();
		
		// Store all the checkboxes in an array
		ArrayList<CheckBox> checkBoxArray = new ArrayList<CheckBox>();

		// Create view and layout
		ScrollView sv = new ScrollView(getActivity());
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);

		/**
		TextView tv = new TextView(getActivity());
		tv.setText("Set your preferences below");
		tv.setTextSize(24);
		ll.addView(tv);
		*/		
		
		Switch separateCompanyList = new Switch(getActivity());
		separateCompanyList.setOnCheckedChangeListener(new separateListListener(separateCompanyList, sharedPref, editor, getActivity()));
		separateCompanyList.setText("Move companies missing information for filtered fields to the bottom of the list");
		ll.addView(separateCompanyList);
		
		View ruler = new View(getActivity());
		ruler.setBackgroundColor(0xFF33b5e5);
		ViewGroup.MarginLayoutParams params = new ViewGroup.MarginLayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2);
		ll.addView(ruler, params);
		
		// Add reset button, Listener is added later
		Button resetButton = new Button(getActivity());		 
		resetButton.setText("Reset All Filters");
		ResetButtonListener resetButtonListen = new ResetButtonListener(sharedPref,MajorAbbrevs,WorkAuths,Positions,checkBoxArray);
		resetButton.setOnClickListener(resetButtonListen);
		ll.addView(resetButton);	

		//Add a horizontal rule
		View ruler2 = new View(getActivity());
		ruler2.setBackgroundColor(0xFF33b5e5);
		ll.addView(ruler2, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
		
		TextView WorkAuthText = new TextView(getActivity());
		WorkAuthText.setText("Work Authorizations");
		ll.addView(WorkAuthText);

		for (int i = 0; i < WorkAuths.size(); i++) {

			CheckBox cb = new CheckBox(getActivity());
			checkBoxArray.add(cb);
			
			// If it was previously checked, set it to that state in the
			// SharedPreferences
			Boolean checked = sharedPref.contains(WorkAuths.get(i));
			if (checked) {
				// Get the value
				cb.setChecked(sharedPref.getBoolean(WorkAuths.get(i), false));
			}

			cb.setText(WorkAuths.get(i));
			CheckBoxListener cbListen = new CheckBoxListener("workAuths",
					WorkAuths.get(i), sharedPref, editor, getActivity());
			cb.setOnCheckedChangeListener(cbListen);

			ll.addView(cb);

		}

		//Add a horizontal rule
		View ruler3 = new View(getActivity());
		ruler3.setBackgroundColor(0xFF33b5e5);
		ll.addView(ruler3, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
		
		TextView PositionText = new TextView(getActivity());
		PositionText.setText("Positions");
		ll.addView(PositionText);

		// Populate 
		for (int i = 0; i < Positions.size(); i++) {

			CheckBox cb = new CheckBox(getActivity());
			checkBoxArray.add(cb);

			// If it was previously checked, set it to that state in the
			// SharedPreferences
			Boolean checked = sharedPref.contains(Positions.get(i));
			if (checked) {
				// Get the value
				cb.setChecked(sharedPref.getBoolean(Positions.get(i), false));
			}

			cb.setText(Positions.get(i));
			CheckBoxListener cbListen = new CheckBoxListener("positions",
					Positions.get(i), sharedPref, editor, getActivity());
			cb.setOnCheckedChangeListener(cbListen);

			ll.addView(cb);

		}

		//Add a horizontal rule
		View ruler4 = new View(getActivity());
		ruler4.setBackgroundColor(0xFF33b5e5);
		ll.addView(ruler4, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 2));
				
		TextView majorText = new TextView(getActivity());
		majorText.setText("Majors");
		ll.addView(majorText);

		for (int i = 0; i < MajorAbbrevs.size(); i++) {

			CheckBox cb = new CheckBox(getActivity());
			checkBoxArray.add(cb);

			// If it was previously checked, set it to that state in the
			// SharedPreferences
			Boolean checked = sharedPref.contains(MajorAbbrevs.get(i));
			if (checked) {
				// Get the value
				cb.setChecked(sharedPref.getBoolean(MajorAbbrevs.get(i), false));
			}

			cb.setText(MajorAbbrevs.get(i));
			CheckBoxListener cbListen = new CheckBoxListener("majors",
					MajorAbbrevs.get(i), sharedPref, editor, getActivity());
			cb.setOnCheckedChangeListener(cbListen);

			ll.addView(cb);

		}

		return sv;
	}

	/**
	 * onAttach
	 * 
	 * Called when a fragment is first attached to its activity. onCreate(Bundle) will be called after this.
	 * 
	 * @param activity. The activity this fragment is associated with.
	 * 
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	/**
	 * onDestroyView
	 * 
	 * Called when the view previously created by onCreateView(LayoutInflater, ViewGroup, Bundle) has been detached from the fragment
	 * 
	 */
	@Override
	public void onDestroyView() {
		SharedPreferences sharedPref = getActivity().getPreferences(
				Context.MODE_PRIVATE);

		MainActivity main = (MainActivity) this.getActivity();

		main.filterCompanies();
		super.onDestroyView();

	}
	
	/**onResume
	 * Called when this fragment is visible to user .Right now this method is just used to reset the title of the ActionBar
	 * when user using Back button to get back to a fragment which is previously invisible to user
	 */
	
	@Override
	public void onResume(){
		super.onResume();
		ActionBar ab = getActivity().getActionBar();
		((MainActivity)getActivity()).mTitle = getString(R.string.title_preferencesview);
		ab.setTitle(((MainActivity)getActivity()).mTitle);
	}

}
