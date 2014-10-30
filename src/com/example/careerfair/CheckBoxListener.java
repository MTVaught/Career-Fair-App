/**
 * 
 */
package com.example.careerfair;

import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

/**
 * @author andrewzComp
 *
 */
public class CheckBoxListener implements OnCheckedChangeListener {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String prefKey;
	
	/**
	 * 
	 */
	public CheckBoxListener() {
		// TODO Auto-generated constructor stub
	}
	
	public CheckBoxListener(String prefKey, SharedPreferences sharedPref, Editor editor) {
		// TODO Auto-generated constructor stub
		this.prefKey = prefKey;
		this.sharedPref = sharedPref;
		this.editor = editor;
	}

	/* (non-Javadoc)
	 * @see android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged(android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		//Log.w("myApp", ""+ isChecked);
		
		editor.putBoolean(prefKey, isChecked);
		editor.commit();
		
		Log.w("myApp", "Before sharedPref Print");
		// Temp code to print what is in sharedPreferences in the LogCat window in Eclipse
		Map<String,?> keys = sharedPref.getAll();

		for(Map.Entry<String,?> entry : keys.entrySet()){
		            Log.d("map values",entry.getKey() + ": " + 
		                                   entry.getValue().toString());            
		 }
		
		Log.w("myApp", "After sharedPref Print");
		
	}

}
