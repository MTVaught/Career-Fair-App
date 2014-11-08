/**
 * 
 */
package com.helpers;

import java.util.ArrayList;
import java.util.Map;

import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;

import com.google.gson.Gson;

/**
 * @author andrewzComp
 *
 */
public class CheckBoxListener implements OnCheckedChangeListener {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	String prefKey;
	String category;

	/**
	 * 
	 */
	public CheckBoxListener() {
		// TODO Auto-generated constructor stub
	}

	public CheckBoxListener(String category, String prefKey,
			SharedPreferences sharedPref, Editor editor) {
		// TODO Auto-generated constructor stub
		this.category = category;
		this.prefKey = prefKey;
		this.sharedPref = sharedPref;
		this.editor = editor;
	}

	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * android.widget.CompoundButton.OnCheckedChangeListener#onCheckedChanged
	 * (android.widget.CompoundButton, boolean)
	 */
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		// TODO Auto-generated method stub
		// Log.w("myApp", ""+ isChecked);

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
