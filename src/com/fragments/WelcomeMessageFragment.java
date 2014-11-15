package com.fragments;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
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

		LinearLayout ll = new LinearLayout(getActivity());
		TextView tv = new TextView(getActivity());
		tv.setText("Text");
		ll.addView(tv);
		return ll;

	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
