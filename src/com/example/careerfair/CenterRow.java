package com.example.careerfair;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;

public class CenterRow extends View
{

	
	
	public CenterRow(Context context, ArrayList<Company> aCompanyList, int start, int end ) 
	{
		super(context);
		

		//bottom single booth
		SingleBooth top = new SingleBooth( context, aCompanyList.get( start ) );
		//left side starts with double
		
	}

}
