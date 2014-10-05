/** 
 * This class handles all database access needs with methods that query the database and return the results in a structed format
 * (ex. ArrayList)
 * 
 * @author Hannah Wilder (with code borrowed from http://blog.softeq.com/2012/12/using-pre-populated-sqlite-database-in.html)
 * @version 1.0
 */

package com.example.careerfair;

import java.util.ArrayList;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ListView;

public class DbAccess {

	private static final String DB_NAME = "careerFairDB.db";

	//A good practice is to define database field names as constants
	private static final String COMPANY_TABLE_NAME = "company";
	private static final String COMPANY_ID = "_id";
	private static final String COMPANY_NAME = "name";


	/**
	 * Queries the database to obtain a list of company names and fill an array list with them
	 * 
	 * @param companies - an empty array list to fill with company names
	 * @param database - the database to query (obtain database with ExternalDbOpenHelper instance)
	 */
	public static void fillCompanies(ArrayList companies, SQLiteDatabase database) {
		//companies = new ArrayList<String>();
		Cursor companiesCursor = database.query(COMPANY_TABLE_NAME, new String[] {COMPANY_ID,
				COMPANY_NAME}, null, null, null, null, COMPANY_NAME);
		companiesCursor.moveToFirst();
		if(!companiesCursor.isAfterLast()) {
			do {
				String name = companiesCursor.getString(1);
				companies.add(name);
			} while (companiesCursor.moveToNext());
		}
		companiesCursor.close();
	}
}	


