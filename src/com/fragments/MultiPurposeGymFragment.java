package com.fragments;

import java.util.ArrayList;

import com.database.Company;
import com.database.DbAccess;
import com.example.careerfair.R;
import com.example.careerfair.R.drawable;
import com.example.careerfair.R.id;
import com.example.careerfair.R.layout;
import com.fragments.ImageMap.OnImageMapClickedHandler;

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
	private SQLiteDatabase mDatabase;
	private ArrayList<Company> mCompanies;
	private Company mDefaultCompany;

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
	
	public static MultiPurposeGymFragment newInstance( Company company ) {
		
		MultiPurposeGymFragment fragment = new MultiPurposeGymFragment( company );
		Bundle args = new Bundle();
		fragment.setArguments(args);
		
		return fragment;
	}

	public MultiPurposeGymFragment() {
		
		mDatabase = MainActivity.appMainActivity.database;
		mCompanies = DbAccess.getAllCompanies( mDatabase);
	}
	
	public MultiPurposeGymFragment( Company c ) {
		
		mDatabase = MainActivity.appMainActivity.database;
		mCompanies = DbAccess.getAllCompanies( mDatabase);
		mDefaultCompany = c;
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
						FragmentManager fragmentManager = MainActivity.appMainActivity.getFragmentManager();
						FragmentTransaction ft = fragmentManager.beginTransaction();
						String name = mMultiMap.mIdToArea.get( id ).getName();
						//int subI = name.indexOf(",");
						name = name.replaceFirst(",\\s\\d+", "");
						//String bId = mMultiMap.mIdToArea.get( id ).getbId();
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
								CompanyReaderFragment.newInstance( clickedCompany ))
								.commit();
						MainActivity.appMainActivity.inCompanyView = true;
					}
				});
		
		if (mDefaultCompany != null )
		{
			String name = mDefaultCompany.getName() + ", " + mDefaultCompany.getTableNum();
			mMultiMap.showBubble( mMultiMap.mAreaNameToId.get( name ) );
		}

		return main;
	}

	@Override
	public void onAttach(Activity activity) {
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}

}
