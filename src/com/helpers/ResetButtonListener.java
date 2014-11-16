/** 
 * This class handles the ActionEvent when the reset filters button is presssed.
 * 
 * @author Andrew Hanson
 * @version 1.0
 */
package com.helpers;

import java.util.ArrayList;
import java.util.Map;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

public class ResetButtonListener implements OnClickListener {

	SharedPreferences sharedPref;
	ArrayList<String> MajorAbbrevs;
	ArrayList<String> WorkAuths;
	ArrayList<String> Positions;
	ArrayList<CheckBox> checkBoxArray;
	
	/**
	 * ResetButtonListener
	 * 
	 * Constructor for setting up the reset button listener
	 * 
	 * @param sharedPref. The global sharedPreferences object where data is stored
	 * @param MajorAbbrevs. List of all the major abbreviations
	 * @param WorkAuths. List of all the work authorizations.
	 * @param Positions. List of all the positions
	 * @param checkBoxArray. An arraylist of all the checkboxes used on the preferences page.
	 * 
	 */
	public ResetButtonListener(SharedPreferences sharedPref,ArrayList<String> MajorAbbrevs, ArrayList<String> WorkAuths, ArrayList<String> Positions,ArrayList<CheckBox> checkBoxArray) {
		this.sharedPref = sharedPref;
		this.MajorAbbrevs = MajorAbbrevs;
		this.WorkAuths  = MajorAbbrevs;
		this.Positions  = WorkAuths;
		this.checkBoxArray  = checkBoxArray;
	}

	/** 
	 * onClick
	 * 
	 * Called when a view has been clicked.
	 * 
	 * @param v. The view that was clicked.
	 * 
	 * (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		SharedPreferences.Editor edit = sharedPref.edit();

		// Go through each checkbox and set it to false.
		// Also if that checkbox's data exists in sharedPreferences,
		//	remove it there as well
		for (CheckBox cb : checkBoxArray) {
			if (cb.isChecked()) {
				cb.setChecked(false);
			}
			
			String text = cb.getText().toString();
			if (sharedPref.contains(text)) {
				edit.remove(text).commit();
				//edit.putBoolean(text, false);
			}
			
			
		}

		// Debug code to monitor what is present in the sharedPreferences		
		Log.w("myApp", "Before sharedPref Print");

		Map<String, ?> keys = sharedPref.getAll();

		for (Map.Entry<String, ?> entry : keys.entrySet()) {
			Log.d("map values", entry.getKey() + ": "
					+ entry.getValue().toString());
		}

		Log.w("myApp", "After sharedPref Print");
		

	}

}
