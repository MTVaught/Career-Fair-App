package com.example.careerfair;



import android.app.Activity;
import android.app.Fragment;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.Toast;



public class MultiPurposeGymFragment extends Fragment 
{
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
		LinearLayout main = (LinearLayout) inflater.inflate( R.layout.fragment_multipurposegym, container, false );
		main.setOrientation( LinearLayout.HORIZONTAL );
		ImageMap imageMap = (ImageMap)  main.findViewById( R.id.map );
		imageMap.setImageResource( R.drawable.multi );
		
		
		//add click handler
		imageMap.addOnImageMapClickedHandler( new ImageMap.OnImageMapClickedHandler()
        {
			@Override
			public void onImageMapClicked(int id, ImageMap imageMap)
			{
				// when the area is tapped, show the name in a 
				// text bubble
				Toast.makeText( imageMap.getContext(), "Booth: " + id , Toast.LENGTH_SHORT ).show();
				
				Log.v("Booth: ", "" + id );
			}

			@Override
			public void onBubbleClicked( int id )
			{
				
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
