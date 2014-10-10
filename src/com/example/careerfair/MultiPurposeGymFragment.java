package com.example.careerfair;

import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.Toast;


public class MultiPurposeGymFragment extends Fragment {
	/**
	 * The fragment argument representing the section number for this fragment.
	 */
	private static final String ARG_SECTION_NUMBER = "MultiPurpose";

	/**
	 * Returns a new instance of this fragment for the given section number.
	 */
	public static MultiPurposeGymFragment newInstance(int sectionNumber)
	{
		
		MultiPurposeGymFragment fragment = new MultiPurposeGymFragment();
		Bundle args = new Bundle();
		args.putInt(ARG_SECTION_NUMBER, sectionNumber);
		fragment.setArguments(args);
		return fragment;
	}

	public MultiPurposeGymFragment() 
	{
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) 
	{
		FrameLayout main = (FrameLayout) inflater.inflate( R.layout.fragment_multipurposegym, container, false );
		GridView gridview = (GridView)  main.findViewById( R.id.gridview );
		gridview.setNumColumns( 30 );
		gridview.setAdapter( new BoothAdapter( getActivity(), new Company() ) );
	    gridview.setOnItemClickListener(new OnItemClickListener() 
	    {
	        public void onItemClick(AdapterView<?> parent, View v, int position, long id) {
	            Log.v( "Position: ", position + "" );
	        }
	    });
	    return main;
	}

	@Override
	public void onAttach(Activity activity) 
	{
		super.onAttach(activity);
		((MainActivity) activity).onSectionAttached(getArguments().getInt(
				ARG_SECTION_NUMBER));
	}
	
}
