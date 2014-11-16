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
 * MultiPurposeGymFragment
 * 
 * Handles the setup and execution of the Multi-Purpose Room map.
 * 
 * @author byrnedj
 * @author MTVaught
 * 
 */
public class MultiPurposeGymFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "MultiPurpose";
	private ImageMap mMultiMap;

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MultiPurposeGymFragment newInstance(int sectionNumber) {

		MultiPurposeGymFragment fragment = new MultiPurposeGymFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);

		fragment.setArguments(args);
		// if (mMultiMap != null )
		// {
		// fragment.getView().invalidate();
		// }
		return fragment;
	}

	public MultiPurposeGymFragment() {
	}

	/**
	 * @author byrnedj
	 * @author MTVaught
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {

		LinearLayout main = (LinearLayout) inflater.inflate(
				R.layout.fragment_multipurposegym, container, false);
		main.setOrientation(LinearLayout.HORIZONTAL);
		mMultiMap = (ImageMap) main.findViewById(R.id.map);
		//Points the image displayed to the .png of the MultiPurpose map
		mMultiMap.setImageResource(R.drawable.multi);

		// add click handler
		mMultiMap
				.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {
					@Override
					public void onImageMapClicked(int id, ImageMap imageMap) {
						// when the area is tapped, show the name in a
						// text bubble

						mMultiMap.showBubble(id);

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
