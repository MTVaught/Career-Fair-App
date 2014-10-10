package com.example.careerfair;

import java.util.ArrayList;

public class Company {
	private String name = null;
	private String website = null;
	private String tableNum = null;
	private String room = null;

	private ArrayList<String> majors = new ArrayList<String>();
	private ArrayList<String> positions = new ArrayList<String>();
	private ArrayList<String> workAuths = new ArrayList<String>();

	// CONSTRUCTORS
	public Company(String aName, String aWebsite, String aTableNum, String aRoom, ArrayList<String> aMajors, ArrayList<String> aPositions, ArrayList<String> aWorkAuths) {
		name = aName;
		website = aWebsite;
		tableNum = aTableNum;
		room = aRoom;
		
		majors = aMajors;
		positions = aPositions;
		workAuths = aWorkAuths; 
	}

	// ACCESS METHODS
	public ArrayList<String> getMajors() {
		return (ArrayList<String>) majors.clone();
	}

	public ArrayList<String> getPositions() {
		return (ArrayList<String>) positions.clone();
	}

	public ArrayList<String> getWorkAuth() {
		return (ArrayList<String>) workAuths.clone();
	}

	public String getName() {
		return name;
	}

	public String getWebsite() {
		return website;
	}

	public String getTableNum() {
		return tableNum;
	}
	
	public String getRoom() {
		return room;
	}

	// DO NOT USE, FOR DATA PROCESSING ONLY -- TO BE REMOVED LATER
	public ArrayList<String> getMajorsList() {
		return majors;
	}

	public ArrayList<String> getPositionsList() {
		return positions;
	}

	public ArrayList<String> getWorkAuthList() {
		return workAuths;
	}
}
