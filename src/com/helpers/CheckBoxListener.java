/** 
 * This class handles ActionEvents for the checkboxes when setting preference options.
 * 
 * @author Andrew Hanson
 * @version 1.0
 */
package com.helpers;

import java.util.ArrayList;
import java.util.Map;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Handler;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.Toast;

import com.google.gson.Gson;

public class CheckBoxListener implements OnCheckedChangeListener {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String prefKey;
	String category;
	Context context;

	/**
	 * CheckBoxListener
	 * 
	 * Default constructor
	 */
	public CheckBoxListener() {}

	/**
	 * CheckBoxListener
	 * 
	 * Constructor for setting up the checkbox based upon passed-in parameters.
	 * 
	 * @param category.
	 * @param prefKey.
	 * @param sharedPref. The global sharedPreferences object where data is stored
	 * @param editor. The editor for the sharedPreferences.
	 * @param context. The application's context, in this case it is the mainActivity
	 * 
	 */
	public CheckBoxListener(String category, String prefKey,
			SharedPreferences sharedPref, Editor editor, Context context) {
		// TODO Auto-generated constructor stub
		this.category = category;
		this.prefKey = prefKey;
		this.sharedPref = sharedPref;
		this.editor = editor;
		this.context = context;
	}

	/**
	 * onCheckedChanged (non-Javadoc)
	 * 
	 * Called when the checked state of a compound button has changed.
	 * 
	 * @param buttonView. The compound button view whose state has changed.
	 * @param isChecked. The new checked state of buttonView.
	 * 
	 * @see
	 * android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged
	 * (android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {

		// Test code for shared prefs
		ArrayList<String> list;

		Gson gson = new Gson();
		String json = sharedPref.getString(category, "");
		if (json.equals("")) {
			list = new ArrayList<String>();
		} else {
			list = gson.fromJson(json, ArrayList.class);
		}

		if (isChecked) {
			list.add(prefKey);
		} else {
			list.remove(prefKey);
		}

		json = gson.toJson(list);
		editor.putString(category, json);
		editor.commit();
		// End test code

		editor.putBoolean(prefKey, isChecked);
		editor.commit();

		//Display toast message. Toast does not allow for custom durations.
		//This 'hack' was found on stackoverflow, where it is possible to cancel the 
		//toast after a certain duration
		//http://stackoverflow.com/questions/3775074/set-toast-appear-length/9715422#9715422
		int duration = 500;
		
		final Toast toast = Toast.makeText(context, "Saved", Toast.LENGTH_SHORT);
		toast.show();
		
		Handler handler = new Handler();
	        handler.postDelayed(new Runnable() {
	           @Override
	           public void run() {
	               toast.cancel(); 
	           }
	        }, duration);
		
        	// Debug code to print out what is in sharedPreferences to the log window
		Log.w("myApp", "Before sharedPref Print");

		Map<String, ?> keys = sharedPref.getAll();

		for (Map.Entry<String, ?> entry : keys.entrySet()) {
			Log.d("map values", entry.getKey() + ": "
					+ entry.getValue().toString());
		}

		Log.w("myApp", "After sharedPref Print");

	}

}
