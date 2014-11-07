/**
 * 
 */
package com.fragments;

import com.example.careerfair.R;
import com.example.careerfair.R.drawable;
import com.example.careerfair.R.id;
import com.example.careerfair.R.layout;
import com.fragments.ImageMap.OnImageMapClickedHandler;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

/**
 * @author Matthew Vaught
 *
 */
public class WoodGymFragment extends Fragment {

	private static final String ARG_SECTION_NUMBER = "WoodGym";
	private ImageMap mWoodMap;

	public static WoodGymFragment newInstance(int sectionNumber) {
		WoodGymFragment fragment = new WoodGymFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public WoodGymFragment() {

	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		LinearLayout main = (LinearLayout) inflater.inflate(
				R.layout.fragment_woodgym, container, false);
		main.setOrientation(LinearLayout.HORIZONTAL);
		mWoodMap = (ImageMap) main.findViewById(R.id.varsity);
		mWoodMap.setImageResource(R.drawable.varsity);

		// add click handler
		mWoodMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {

			@Override
			public void onImageMapClicked(int id, ImageMap imageMap) {
				// when the area is tapped, show the name in a text bubble
				mWoodMap.showBubble(id);

				Log.v("Booth: ", "" + id);
			}

			@Override
			public void onBubbleClicked(int id) {
				Log.v("Booth: ", "" + id);

			}
		});
		return main;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
