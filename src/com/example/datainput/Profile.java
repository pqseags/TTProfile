package com.example.datainput;

import java.util.ArrayList;

public class Profile {
	
	//data
	private String firstName;
	private String lastName;
	private String phoneNumber;
	
	private Degree currentDegree;
	private ArrayList<Skill> skills = new ArrayList<Skill>();
	private ArrayList<WorkExperience> jobs = new ArrayList<WorkExperience>();
	
	//singleton
	private static final Profile profile = new Profile();
	public static Profile getInstance(){return profile;}
	
	//getters and setters
	public String getFirstName() {return firstName;}
	public void setFirstName(String name){this.firstName=name;}
	
	public String getLastName() {return lastName;}
	public void setLastName(String name){this.lastName=name;}
	
	public String getPhoneNumber() {return phoneNumber;}
	public void setPhoneNumber(String no){this.phoneNumber=no;}
	
	public Degree getCurrentDegree(){return currentDegree;}
	public void setCurrentDegree(Degree deg){this.currentDegree=deg;}
	
	public ArrayList<Skill> getSkills(){return skills;}
	public void addSkill(Skill s){skills.add(s);}
	
	public ArrayList<WorkExperience> getJobs(){return jobs;}
	public void addJob(WorkExperience j){jobs.add(j);}
	
	

}
