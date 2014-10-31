/** 
 * This class stores all the relevant information about a company
 * 
 * @author Hannah Wilder 
 * @version 1.0
 */
package com.example.careerfair;

import java.util.ArrayList;

public class Company {
	private String name = null;
	private String website = null;
	private String tableNum = null;
	private String room = null;

	private ArrayList<Major> majors = new ArrayList<Major>();
	private ArrayList<String> positions = new ArrayList<String>();
	private ArrayList<String> workAuths = new ArrayList<String>();

	/**
	 * Company constructor
	 * 
	 * @param aName - the name of the company
	 * @param aWebsite - the name of the website
	 * @param aTableNum - the number of the table (in a string)
	 * @param aRoom - the room name (hall/multipurpose/wood)
	 * @param aMajors - an ArrayList of the majors this company is looking for
	 * @param aPositions - an ArrayList of the positions a company is hiring
	 * @param aWorkAuths - an ArrayList of work authorizations a company is looking for
	 */
	public Company(String aName, String aWebsite, String aTableNum, String aRoom, ArrayList<Major> aMajors, ArrayList<String> aPositions, ArrayList<String> aWorkAuths) {
		name = aName;
		website = aWebsite;
		tableNum = aTableNum;
		room = aRoom;
		
		majors = aMajors;
		positions = aPositions;
		workAuths = aWorkAuths; 
	}

	// ACCESS METHODS
	
	/**
	 * getMajors
	 * @return returns a copy the ArrayList of majors
	 */
	public ArrayList<Major> getMajors() {
		return (ArrayList<Major>) majors.clone();
	}

	/**
	 * getPositions
	 * @return returns a copy of the ArrayList of positions
	 */
	public ArrayList<String> getPositions() {
		return (ArrayList<String>) positions.clone();
	}

	/**
	 * getWorkAuth
	 * @return returns a copy of the ArrayList of work authorizations
	 */
	public ArrayList<String> getWorkAuth() {
		return (ArrayList<String>) workAuths.clone();
	}

	/**
	 * getName
	 * @return the name of the company
	 */
	public String getName() {
		return name;
	}

	/**
	 * getWebsite
	 * @return the company's website
	 */
	public String getWebsite() {
		return website;
	}

	/**
	 * getTableNum
	 * @return the company's assigned table number (as a string)
	 */
	public String getTableNum() {
		return tableNum;
	}
	
	/**
	 * getRoom
	 * @return the room assignment (hall/multipurpose/wood)
	 */
	public String getRoom() {
		return room;
	}

}
