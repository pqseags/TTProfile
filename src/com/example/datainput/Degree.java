package com.example.datainput;

import android.util.Log;


public class Degree {
	public String schoolName;
	enum DegreeLevel {bachelors, masters, phd};
	public DegreeLevel degreeLevel;
	GeneralDate graduationDate;
	String major;
	String GPA;

	
	public final int SPINNER_OFFPUT = 2014;
	
	Degree(String name, String major, String gpa, String level, GeneralDate date){
		Log.d("DegreeFormulation", "Just making it");
		this.schoolName = name;
		this.major = major;
		this.GPA=gpa;
		this.graduationDate = date;
		
		if (level.equals("BA/BS"))
			degreeLevel = DegreeLevel.bachelors;
		else if (level.equals("Masters"))
			degreeLevel=DegreeLevel.masters;
		else if (level.equals("PhD"))
			degreeLevel=DegreeLevel.phd;
		else Log.d("DegreeFormulation", "Invalid Degree Level");
	}
	
	
}
