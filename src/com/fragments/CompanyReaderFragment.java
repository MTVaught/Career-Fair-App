package com.fragments;

import java.util.ArrayList;

import com.database.Company;
import com.example.careerfair.R;
//import com.example.careerfair.R.id;
//import com.example.careerfair.R.layout;







import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

public class CompanyReaderFragment extends Fragment {
	private static final String DB_NAME = "careerFairDB.db";
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "CompanyReader";

	private static Company companyObj;
	//private View mCompanyReaderView;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */

	public static CompanyReaderFragment newInstance(int sectionNumber,
			Company company) {
		companyObj = company;
		CompanyReaderFragment fragment = new CompanyReaderFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public static CompanyReaderFragment newInstance( Company company) {
		companyObj = company;
		CompanyReaderFragment fragment = new CompanyReaderFragment();
		Bundle args = new Bundle();
		fragment.setArguments(args);
		return fragment;
	}

	// Default constructor
	public CompanyReaderFragment() {

	}

	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		
		ScrollView sv = new ScrollView(getActivity());
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);
		
		// Display the Company name
		TextView tv = new TextView(getActivity());
		tv.setText("Company: " + companyObj.getName());
		ll.addView(tv);
		
		// Print a header for the Major table
		tv = new TextView(getActivity());
		tv.setText("\nMajor List Below:");
		ll.addView(tv);
		
		// Create a table for majors
		TableLayout majorTable = new TableLayout(getActivity());
		ArrayList<String> majorNames = companyObj.getMajorNames();
		ArrayList<String> majorAbbrevs = companyObj.getMajorAbbrevs();
		
		if (majorNames.size() != majorAbbrevs.size()) {
			Log.w("myApp", "MajorNames was a different size then MajorAbbrevs in CompanyReaderFragment");
		}
		
		for (int i = 0; i < majorNames.size(); i++) {
			
			// Pull the two String values
			String majorFullStr = majorNames.get(i);
			String majorAbbrevStr = majorAbbrevs.get(i);
			
			// Create a row from template
			TableRow row = (TableRow) inflater.inflate(R.layout.major_row, container, false);
			
			// Set the text for the two template values
			TextView majorAbbrev = (TextView) row.findViewById(R.id.majorAbbrev);
		    majorAbbrev.setText("\t" + majorAbbrevStr);
			
			
			TextView majorFull = (TextView) row.findViewById(R.id.majorFull);
			majorFull.setText("\t" + majorFullStr);
			
			
		    
		    // Add the row to the table
		    majorTable.addView(row);
			
		}
		
		// Add the table to the view
		ll.addView(majorTable);
		
		// Print a header for the position table
		tv = new TextView(getActivity());
		tv.setText("\nPosition list Below:");
		ll.addView(tv);
		
		// Create a table for positions
		TableLayout positionTable = new TableLayout(getActivity());
		ArrayList<String> positionStrings = companyObj.getPositions();
		
		for (int i = 0; i < positionStrings.size(); i++) {
			
			// Pull the two String values
			String positionStr = positionStrings.get(i);
			
			// Create a row from template
			TableRow row = (TableRow) inflater.inflate(R.layout.row_single_entry, container, false);
			
			// Set the text for the two template values
			TextView position = (TextView) row.findViewById(R.id.position);
			position.setText("\t" + positionStr);
			
		    // Add the row to the table
			positionTable.addView(row);
			
		}
		
		// Add the table to the view
		ll.addView(positionTable);
		
		// Print a header for the work authorization table
		tv = new TextView(getActivity());
		tv.setText("\nWork Authorization list below:");
		ll.addView(tv);
		
		// Create a table for work authorizations
		TableLayout workAuthTable = new TableLayout(getActivity());
		ArrayList<String> WorkAuthStrings = companyObj.getWorkAuth();
		
		for (int i = 0; i < WorkAuthStrings.size(); i++) {
			
			// Pull the two String values
			String workAuthStr = WorkAuthStrings.get(i);
			
			// Create a row from template
			TableRow row = (TableRow) inflater.inflate(R.layout.row_single_entry, container, false);
			
			// Set the text for the Work Authorization
			TextView workAuth = (TextView) row.findViewById(R.id.position);
			workAuth.setText("\t" + workAuthStr);
			
		    // Add the row to the table
			workAuthTable.addView(row);
			
		}
		
		// Add the table to the view
		ll.addView(workAuthTable);
		
		// Print a blank line
		tv = new TextView(getActivity());
		tv.setText("");
		ll.addView(tv);
		
		// Display website URL
		tv = new TextView(getActivity());
		tv.setText(Html.fromHtml("Website: <a href='"
				+ companyObj.getWebsite() + "'> " + companyObj.getWebsite()
				+ " </a>"));

		
        tv.setClickable(true);
		tv.setMovementMethod(LinkMovementMethod.getInstance());
		ll.addView(tv);
        
		//button for map view
		Button MapButton = (Button) sv
		.findViewById(R.id.ShowMapButton);
		
		MapButton.setOnClickListener(new View.OnClickListener() {
		   
			public void onClick(View v) {
				FragmentManager fragmentManager = MainActivity.appMainActivity.getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				
				boolean isInMulti = false ;
				if ( companyObj.getRoom().contains("Multi") )
				{
					isInMulti = true;
				}
				if ( isInMulti )
				{
					ft.replace(R.id.container,
							MultiPurposeGymFragment.newInstance( companyObj ))
							.commit();
				}
				else
				{
					ft.replace(R.id.container,
							WoodGymFragment.newInstance( companyObj ))
							.commit();
				}
		    }
		});		
		return sv;

	}
	
//	@Override
//	public View onCreateView(LayoutInflater inflater, ViewGroup container,
//			Bundle savedInstanceState) {
//		// Inflate the layout for this fragment
//		this.mCompanyReaderView = inflater.inflate(R.layout.company_reader,
//				container, false);
//
//		// Display the Company name
//		TextView text = (TextView) mCompanyReaderView
//				.findViewById(R.id.company_name_label);
//		text.setText("Company: " + companyObj.getName());
//
//		// Display Majors
//		text = (TextView) mCompanyReaderView
//				.findViewById(R.id.company_major_label);
//		text.setText("Majors: " + companyObj.getMajorNames().toString());
//
//		// Display Positions
//		text = (TextView) mCompanyReaderView
//				.findViewById(R.id.company_position_label);
//		text.setText("Positions: " + companyObj.getPositions().toString());
//
//		// Display Work Authorizations
//		text = (TextView) mCompanyReaderView
//				.findViewById(R.id.company_workauths_label);
//		text.setText("Work Authorizations: "
//				+ companyObj.getWorkAuth().toString());
//
//		// Display Website URL
//		text = (TextView) mCompanyReaderView
//				.findViewById(R.id.company_website_label);
//		text.setText(Html.fromHtml("Website: <a href='"
//				+ companyObj.getWebsite() + "'> " + companyObj.getWebsite()
//				+ " </a>"));
//		text.setClickable(true);
//		text.setMovementMethod(LinkMovementMethod.getInstance());
//
//		return mCompanyReaderView;
//	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
