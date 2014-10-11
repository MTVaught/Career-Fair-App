package com.example.careerfair;

import java.util.ArrayList;

import android.app.Activity;
import android.app.Fragment;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

	public class CompanyReaderFragment extends Fragment{
		private static final String DB_NAME = "careerFairDB.db";
		/**
		* The fragment argument representing the section number for this fragment.
		*/
		private static final String ARG_SECTION_NUMBER = "CompanyReader";
		
		/**
		* Returns a new instance of this fragment for the given section number.
		*/
		
		public static CompanyReaderFragment newInstance(int sectionNumber) {
			CompanyReaderFragment fragment = new CompanyReaderFragment();
			Bundle args = new Bundle();
			args.putInt(ARG_SECTION_NUMBER, sectionNumber);
			fragment.setArguments(args);
			return fragment;
		}
			
	       private SQLiteDatabase database;
	       private View mCompanyReaderView;
	       /*
	        * ArrayList to store the information returned by the database
	        */
	        

		    public CompanyReaderFragment(){
		    	
		    }
		    @Override
		    public View onCreateView(LayoutInflater inflater, ViewGroup container,
		                             Bundle savedInstanceState) {
		    	// Inflate the layout for this fragment
		        this.mCompanyReaderView = inflater.inflate(
		        		R.layout.company_reader, 
		        		container,
		        		false);
		    	
		        
		        
		        return mCompanyReaderView;
		    }
		    @Override
		    	public void onAttach(Activity activity) {
		    		super.onAttach(activity);
		    		((MainActivity) activity).onSectionAttached(getArguments().getInt(
		    				ARG_SECTION_NUMBER));
		    	}
		    
		 }	   

