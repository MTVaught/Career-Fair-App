package com.example.careerfair;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.shapes.RectShape;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup.LayoutParams;
import android.widget.Toast;


public class SingleBooth extends View implements OnClickListener
{
	private ShapeDrawable mBooth;
	private final int bound = 10; //number of pixels
	
	/**
	 * Constructs a single booth
	 * @param context
	 * @param aRotation
	 */
	public SingleBooth(Context context, Company aCompany )
	{
		super(context);
		LayoutParams fillParams = 
				new LayoutParams( LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT );
		super.setLayoutParams( fillParams );
		
		mBooth = new ShapeDrawable( new RectShape() );
		mBooth.getPaint().setColor( Color.BLACK );
		mBooth.setBounds( 0, 10, 10, 0 );
		
	}
	
	public void onDraw( Canvas canvas )
	{
		mBooth.draw( canvas );
	}

	@Override
	public void onClick(View v) 
	{
		Toast.makeText( v.getContext(), "single", 10 );
		
	}

}
