package com.example.careerfair;

import java.util.ArrayList;
import java.util.HashMap;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Paint.Style;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

/**
 *
 * @author daniel
 *
 */
public class BoothAdapter extends BaseAdapter 
{
	
    private Context mContext;
    private Company mCompany;

    //key == position number, value == booth number
    private HashMap<Integer,Integer> mBackRow;
    
    public BoothAdapter(Context c, Company company ) 
    {
        mContext = c;
        mCompany = company;
        mBackRow = new HashMap<Integer,Integer>();
        int start = 65;
        int end = 36;
        int position = 0;
        for ( int i = start; i > end; i-- )
        {
        	mBackRow.put( position, R.drawable.singlebooth );
        	position++;
        }
    }

    public int getCount() 
    {
        return mBackRow.size();
    }

    public Object getItem(int position) 
    {
        return null;
    }

    public long getItemId( int position)  
    {
        return 0;
    }

    /**
     * Creates an image view using the standard box layouts. This view needs to be modified after created with company info.
     * @param int position
     * 		0 - single booth
     * 		1 - double booth
     * @return a view containing an image of the booth
     */
    public View getView( int position, View convertView, ViewGroup parent) 
    {
        ImageView imageView;
        if (convertView == null) 
        {  
        	// if it's not recycled, initialize some attributes
            imageView = new ImageView( mContext );
            
        } 
        else 
        {
            imageView = (ImageView) convertView;
        }
        
        imageView.setImageResource( mBackRow.get( position ) );
        return imageView;
    }

}