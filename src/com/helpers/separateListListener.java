/**
 * This class handles ActionEvents for the switch that controls whether lists are separate or together
 * 
 * @author hewilder, Andrew Hanson (initial code written by Andrew)
 */

package com.helpers;

import android.content.Context;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.widget.CompoundButton;
import android.widget.Switch;
import android.widget.CompoundButton.OnCheckedChangeListener;

public class separateListListener implements OnCheckedChangeListener {

	SharedPreferences sharedPref;
	SharedPreferences.Editor editor;
	Context context;

	/**
	 * CheckBoxListener
	 * 
	 * Default constructor
	 */
	public separateListListener() {}

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
	public separateListListener(Switch optionsSwitch, SharedPreferences sharedPref, Editor editor, Context context) {
		// TODO Auto-generated constructor stub

		this.sharedPref = sharedPref;
		this.editor = editor;
		this.context = context;

		boolean separateLists = sharedPref.getBoolean("separateLists", true);
		optionsSwitch.setChecked(separateLists);

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

		editor.putBoolean("separateLists", isChecked);
		editor.commit();

	}

}


