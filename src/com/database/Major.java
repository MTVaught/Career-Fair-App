/** 
 * This class stores all the relevant information about a major
 * 
 * @author Hannah Wilder 
 * @version 1.0
 */

package com.database;

public class Major {
	private String name = null;
	private String abbrev = null;

	/**
	 * Major constructor
	 * 
	 * @param aName
	 *            the "long" name of the major
	 * @param aAbbrev
	 *            the abbreviation of the major
	 */
	public Major(String aName, String aAbbrev) {
		name = aName;
		abbrev = aAbbrev;
	}

	/**
	 * getName
	 * 
	 * @return the "long" name of the major
	 */
	public String getName() {
		return name;
	}

	/**
	 * getAbbrev
	 * 
	 * @return returns the abbreviated name of the major
	 */
	public String getAbbrev() {
		return abbrev;
	}
}
