package com.example.careerfair;

import android.graphics.Color;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnTouchListener;

public class BoothListener implements OnTouchListener
{
	private Company mCompany;
	
	public BoothListener( Company aCompany )
	{
		mCompany = aCompany;
	}

	@Override
	public boolean onTouch(View v, MotionEvent event) 
	{
		v.setBackgroundColor( Color.BLACK );
		Log.v( "Booth: ", mCompany.getBooth() );
		Log.v( "Touched at: ", event.getX() + ", " + event.getY() );
		
		return true;
	}

}
