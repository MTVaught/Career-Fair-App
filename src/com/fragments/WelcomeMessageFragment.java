package com.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ScrollView;
import android.widget.TextView;

public class WelcomeMessageFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "WelcomeMessage";

	public static WelcomeMessageFragment newInstance(int sectionNumber) {
		WelcomeMessageFragment fragment = new WelcomeMessageFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		ScrollView sv = new ScrollView(getActivity());
		LinearLayout ll = new LinearLayout(getActivity());
		ll.setOrientation(LinearLayout.VERTICAL);
		sv.addView(ll);

		// Welcome Title
		TextView tv = new TextView(getActivity());
		tv.setTextSize(24);
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText("Purpose");
		ll.addView(tv);

		// Content
		tv = new TextView(getActivity());
		tv.setTextSize(17);
		tv.setText("The intent of this android application is to replace the "
				+ "Michigan Technological University Career Fair information "
				+ "booklet.\nThere are three main elements in this "
				+ "application: The company lists, the maps, and the "
				+ "filtering. This page will give a brief overview of how to"
				+ " navigate each one.\n"
				+ "If you ever need to see this page again, it can be found "
				+ "in the left navigation menu under the title \"Welcome\".");
		ll.addView(tv);

		// The Company List Title
		newLine(ll);
		tv = new TextView(getActivity());
		tv.setTextSize(24);
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText("The Company List");
		ll.addView(tv);

		// Content
		tv = new TextView(getActivity());
		tv.setTextSize(17);
		tv.setText("The list of all companies attending the career fair can be"
				+ " navigated to through the left navigation drawer. The list"
				+ " is populated in alphabetical order ignoring \"the\" at"
				+ " the beginning of company names. To quickly navigate to a "
				+ "company, use the quick navigation bar on the right of the "
				+ "screen to select a letter in the alphabet to jump to that "
				+ "position in the list.\n"
				+ "To view more details about any company, simply tap on the "
				+ "name in the list. A detailed view of the company will "
				+ "appear. From this menu you can navigate to the company's "
				+ "booth location. To return to the list, use the back button"
				+ " of your android device.");
		ll.addView(tv);

		// The Map Views Title
		newLine(ll);
		tv = new TextView(getActivity());
		tv.setTextSize(24);
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText("The Map Views");
		ll.addView(tv);

		// Content
		tv = new TextView(getActivity());
		tv.setTextSize(17);
		tv.setText("There are two maps in this application: "
				+ "The Multi-Purpose Room and the Varsity Gym.\n"
				+ "Both are operated the same way. The maps support pinch-to-"
				+ "zoom and panning. To see what company is at a table, tap on"
				+ " the table. A bubble will appear containing the company "
				+ "name and the booth number. Tapping on the bubble will open "
				+ "up a screen with more details about that company.");
		ll.addView(tv);

		// Filter Options Title
		newLine(ll);
		tv = new TextView(getActivity());
		tv.setTextSize(24);
		tv.setTypeface(null, Typeface.BOLD);
		tv.setText("Filter Options");
		ll.addView(tv);

		// Content
		tv = new TextView(getActivity());
		tv.setTextSize(17);
		tv.setText("The filter options are designed so that job offers for "
				+ "specific work authorization, employment terms, and majors "
				+ "can be easily identified and located.\n"
				+ "The recommended use of these filters is to check all that "
				+ "apply. The application will automatically filter from "
				+ "those results. The list of companies will automatically "
				+ "repopulate based on the filter. The maps will display a "
				+ "yellow rectangle around the booths that match the filtering"
				+ ". To quickly remove all filtering, use the reset filters "
				+ "button, located at the top of the filtering menu.");
		ll.addView(tv);
		return sv;
	}

	private void newLine(LinearLayout ll) {
		TextView tv = new TextView(getActivity());
		tv.setTextSize(8);
		tv.setText(" ");
		ll.addView(tv);
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
