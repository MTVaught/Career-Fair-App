/**
 * 
 */
package com.helpers;

import java.util.ArrayList;
import java.util.Map;

import android.content.SharedPreferences;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.CheckBox;

/**
 * @author andrewzComp
 *
 */
public class ResetButtonListener implements OnClickListener {

	/**
	 * 
	 */
	SharedPreferences sharedPref;
	ArrayList<String> MajorAbbrevs;
	ArrayList<String> WorkAuths;
	ArrayList<String> Positions;
	ArrayList<CheckBox> checkBoxArray;
	
	public ResetButtonListener(SharedPreferences sharedPref,ArrayList<String> MajorAbbrevs, ArrayList<String> WorkAuths, ArrayList<String> Positions,ArrayList<CheckBox> checkBoxArray) {
		// TODO Auto-generated constructor stub
		this.sharedPref = sharedPref;
		this.MajorAbbrevs = MajorAbbrevs;
		this.WorkAuths  = MajorAbbrevs;
		this.Positions  = WorkAuths;
		this.checkBoxArray  = checkBoxArray;
	}

	/* (non-Javadoc)
	 * @see android.view.View.OnClickListener#onClick(android.view.View)
	 */
	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub
		SharedPreferences.Editor edit = sharedPref.edit();
		// 1) Go through each value in sharedPreferences and set to false
		
		// 2) Go through each checkbox, set to false
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
		
		Log.w("myApp", "Before sharedPref Print");
		// Temp code to print what is in sharedPreferences in the LogCat window
		// in Eclipse
		Map<String, ?> keys = sharedPref.getAll();

		for (Map.Entry<String, ?> entry : keys.entrySet()) {
			Log.d("map values", entry.getKey() + ": "
					+ entry.getValue().toString());
		}

		Log.w("myApp", "After sharedPref Print");
		

	}

}
