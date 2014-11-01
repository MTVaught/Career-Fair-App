package com.example.careerfair;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

public class CompanyReaderFragment extends Fragment {
	private static final String DB_NAME = "careerFairDB.db";
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "CompanyReader";

	private static Company companyObj;

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

	private View mCompanyReaderView;

	/*
	 * ArrayList to store the information returned by the database
	 */

	public CompanyReaderFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Inflate the layout for this fragment
		this.mCompanyReaderView = inflater.inflate(R.layout.company_reader,
				container, false);
		
		// Display the Company name
		TextView text = (TextView) mCompanyReaderView
				.findViewById(R.id.company_name_label);
		text.setText("Company: " + companyObj.getName());

		// Display Majors
		text = (TextView) mCompanyReaderView
				.findViewById(R.id.company_major_label);
		text.setText("Majors: " + companyObj.getMajorNames().toString());
		
		// Display Positions
		text = (TextView) mCompanyReaderView
				.findViewById(R.id.company_position_label);
		text.setText("Positions: " + companyObj.getPositions().toString());
		
		// Display Work Authorizations
		text = (TextView) mCompanyReaderView
				.findViewById(R.id.company_workauths_label);
		text.setText("Work Authorizations: " + companyObj.getWorkAuth().toString());
		
		// Display Website URL
		text = (TextView) mCompanyReaderView
				.findViewById(R.id.company_website_label);
		text.setText(Html.fromHtml("Website: <a href='" + companyObj.getWebsite() + "'> " + companyObj.getWebsite()+ " </a>"));
		text.setClickable(true);
		text.setMovementMethod(LinkMovementMethod.getInstance());
	
		
		
		
		return mCompanyReaderView;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

	
}
