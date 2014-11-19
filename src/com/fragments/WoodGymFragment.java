package com.fragments;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import com.database.Company;
import com.database.DbAccess;
import com.example.careerfair.R;

/**
 * @author Matthew Vaught
 *
 */
public class WoodGymFragment extends Fragment {

	// The identifier for the fragment
	private static final String ARG_SECTION_NUMBER = "WoodGym";
	// Object for storing the click area data
	private ImageMap mWoodMap;
	private SQLiteDatabase mDatabase;
	private ArrayList<Company> mCompanies;
	private Company mDefaultCompany;

	/**
	 * Creates a new instance of the fragment
	 * 
	 * @param sectionNumber
	 *            the identifier for the fragment
	 * @return the fragment generated
	 */
	public static WoodGymFragment newInstance(int sectionNumber) {
		WoodGymFragment fragment = new WoodGymFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}
	
	public static WoodGymFragment newInstance( Company company ) {
		
		WoodGymFragment fragment = new WoodGymFragment( company );
		Bundle args = new Bundle();
		fragment.setArguments(args);
		
		return fragment;
	}
	
	public WoodGymFragment() {
		
		mDatabase = MainActivity.appMainActivity.database;
		mCompanies = DbAccess.getAllCompanies( mDatabase);
	}

	public WoodGymFragment( Company c ) {
		
		mDatabase = MainActivity.appMainActivity.database;
		mCompanies = DbAccess.getAllCompanies( mDatabase);
		mDefaultCompany = c;
	}
	
	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onCreateView(android.view.LayoutInflater,
	 * android.view.ViewGroup, android.os.Bundle)
	 */
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// Define the layout for the fragment
		LinearLayout main = (LinearLayout) inflater.inflate(
				R.layout.fragment_woodgym, container, false);
		// define the orientation
		main.setOrientation(LinearLayout.HORIZONTAL);
		// set the ImageMap view to the global variable
		mWoodMap = (ImageMap) main.findViewById(R.id.varsity);
		// pass the image for the map to the ImageMap
		mWoodMap.setImageResource(R.drawable.varsity);

		// add click handler
		mWoodMap.addOnImageMapClickedHandler(new ImageMap.OnImageMapClickedHandler() {

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.fragments.ImageMap.OnImageMapClickedHandler#onImageMapClicked
			 * (int, com.fragments.ImageMap)
			 */
			@Override
			public void onImageMapClicked(int id, ImageMap imageMap) {
				// when the area is tapped, show the name in a text bubble
				mWoodMap.showBubble(id);

				Log.v("Booth: ", "" + id);
			}

			/*
			 * (non-Javadoc)
			 * 
			 * @see
			 * com.fragments.ImageMap.OnImageMapClickedHandler#onBubbleClicked
			 * (int)
			 */
			@Override
			public void onBubbleClicked(int id) {
				Log.v("Booth: ", "" + id);
				FragmentManager fragmentManager = MainActivity.appMainActivity.getFragmentManager();
				FragmentTransaction ft = fragmentManager.beginTransaction();
				String name = mWoodMap.mIdToArea.get( id ).getName();
				int subI = name.indexOf(",");
				name = name.substring(0, subI);
				String bId = mWoodMap.mIdToArea.get( id ).getbId();
				//replace with hashmap
				Company clickedCompany = null;
				for ( Company c : mCompanies )
				{
					if ( c.getName().equals(name)) 
					{
						clickedCompany = c;
						break;
					}
				}
				MainActivity.appMainActivity.setTitle( clickedCompany.getName() );
				ft.replace(R.id.container,
						CompanyReaderFragment.newInstance( clickedCompany ));
				ft.addToBackStack(null);
				ft.commit();
				MainActivity.appMainActivity.inCompanyView = true;

			}
		});
		
		if (mDefaultCompany != null )
		{
			String name = mDefaultCompany.getName() + ", " + mDefaultCompany.getTableNum();
			int id = mWoodMap.mAreaNameToId.get( name ); 
			mWoodMap.showBubble( id );
		}
		
		return main;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see android.app.Fragment#onAttach(android.app.Activity)
	 */
	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
}
