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
		tv.setText("Pellentesque habitant morbi tristique senectus et netus et"
				+ " malesuada fames ac turpis egestas. Vestibulum tortor quam,"
				+ " feugiat vitae, ultricies eget, tempor sit amet, ante. Done"
				+ "c eu libero sit amet quam egestas semper. Aenean ultricies "
				+ "mi vitae est. Mauris placerat eleifend leo. Quisque sit ame"
				+ "t est et sapien ullamcorper pharetra. Vestibulum erat wisi,"
				+ " condimentum sed, commodo vitae, ornare sit amet, wisi. Aen"
				+ "ean fermentum, elit eget tincidunt condimentum, eros ipsum "
				+ "rutrum orci, sagittis tempus lacus enim ac dui. Donec non e"
				+ "nim in turpis pulvinar facilisis. Ut felis. Praesent dapibu"
				+ "s, neque id cursus faucibus, tortor neque egestas augue, eu"
				+ " vulputate magna eros eu erat. Aliquam erat volutpat. Nam d"
				+ "ui mi, tincidunt quis, accumsan porttitor, facilisis luctus"
				+ ", metus");
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
		tv.setText("Pellentesque habitant morbi tristique senectus et netus et"
				+ " malesuada fames ac turpis egestas. Vestibulum tortor quam,"
				+ " feugiat vitae, ultricies eget, tempor sit amet, ante. Done"
				+ "c eu libero sit amet quam egestas semper. Aenean ultricies "
				+ "mi vitae est. Mauris placerat eleifend leo. Quisque sit ame"
				+ "t est et sapien ullamcorper pharetra. Vestibulum erat wisi,"
				+ " condimentum sed, commodo vitae, ornare sit amet, wisi. Aen"
				+ "ean fermentum, elit eget tincidunt condimentum, eros ipsum "
				+ "rutrum orci, sagittis tempus lacus enim ac dui. Donec non e"
				+ "nim in turpis pulvinar facilisis. Ut felis. Praesent dapibu"
				+ "s, neque id cursus faucibus, tortor neque egestas augue, eu"
				+ " vulputate magna eros eu erat. Aliquam erat volutpat. Nam d"
				+ "ui mi, tincidunt quis, accumsan porttitor, facilisis luctus"
				+ ", metus");
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
		tv.setText("Pellentesque habitant morbi tristique senectus et netus et"
				+ " malesuada fames ac turpis egestas. Vestibulum tortor quam,"
				+ " feugiat vitae, ultricies eget, tempor sit amet, ante. Done"
				+ "c eu libero sit amet quam egestas semper. Aenean ultricies "
				+ "mi vitae est. Mauris placerat eleifend leo. Quisque sit ame"
				+ "t est et sapien ullamcorper pharetra. Vestibulum erat wisi,"
				+ " condimentum sed, commodo vitae, ornare sit amet, wisi. Aen"
				+ "ean fermentum, elit eget tincidunt condimentum, eros ipsum "
				+ "rutrum orci, sagittis tempus lacus enim ac dui. Donec non e"
				+ "nim in turpis pulvinar facilisis. Ut felis. Praesent dapibu"
				+ "s, neque id cursus faucibus, tortor neque egestas augue, eu"
				+ " vulputate magna eros eu erat. Aliquam erat volutpat. Nam d"
				+ "ui mi, tincidunt quis, accumsan porttitor, facilisis luctus"
				+ ", metus");
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
