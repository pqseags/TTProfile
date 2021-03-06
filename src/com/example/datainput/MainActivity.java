package com.example.datainput;


import com.example.datainput.Degree.DegreeLevel;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
//import android.widget.Toast;
//import android.content.Context;
import android.widget.TextView;



public class MainActivity extends Activity {
	private static final String TAG = "UpdateInfoActivity";
	
	Spinner spinner;
	Spinner spinner2;
	
	
	private ViewGroup mContainerView;
	private ViewGroup mContainerView2;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		//-----------------CREATE STRUCTURE
		//create spinners
		spinner = (Spinner) findViewById(R.id.monthSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner2 = (Spinner) findViewById(R.id.yearSpinner);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		
		//set up skill/job containers
		mContainerView = (ViewGroup) findViewById(R.id.container);
		mContainerView2 = (ViewGroup) findViewById(R.id.container2);
		
		
		//++++++++v+++++++++v++++++v++++++++++ADD DATA++++++++++v+++++++++++v+++++++++++++v++++++++
		
		//display name
				
				
				String firstName= Profile.getInstance().getFirstName();
				EditText firstNameField = (EditText) findViewById(R.id.firstNameField);
				firstNameField.setText(firstName);
				
				String lastName = Profile.getInstance().getLastName();
				EditText lastNameField = (EditText) findViewById(R.id.lastNameField);
				lastNameField.setText(lastName);
				
			//display phone number
				String phone = Profile.getInstance().getPhoneNumber();
				EditText phoneField = (EditText)findViewById(R.id.phoneNumberField);
				phoneField.setText(phone);
				
				if(Profile.getInstance().getCurrentDegree()!=null){
//				
//			//display degree
				String schoolName = Profile.getInstance().getCurrentDegree().schoolName;
				EditText schoolNameDisplay =(EditText)findViewById(R.id.schoolField);
				schoolNameDisplay.setText(schoolName);
			
				
				DegreeLevel deg = Profile.getInstance().getCurrentDegree().degreeLevel;
				RadioGroup degreeLevelRadio = (RadioGroup)findViewById(R.id.degreeGroup);
				RadioButton RB;
				if (deg.equals(DegreeLevel.bachelors))
					degreeLevelRadio.check(((RadioButton) degreeLevelRadio.findViewById(R.id.B1)).getId());
				else if (deg.equals(DegreeLevel.masters))
					degreeLevelRadio.check(((RadioButton) degreeLevelRadio.findViewById(R.id.M2)).getId());
				else if (deg.equals(DegreeLevel.phd))
					degreeLevelRadio.check(((RadioButton) degreeLevelRadio.findViewById(R.id.P3)).getId());
				
				
			
				String major = (Profile.getInstance().getCurrentDegree().major);
				EditText majorDisplay =(EditText)findViewById(R.id.majorField);
				majorDisplay.setText(major);
				
			//display graduation date
				spinner.setSelection((Profile.getInstance().getCurrentDegree().graduationDate.month)-1);
				spinner2.setSelection((Profile.getInstance().getCurrentDegree().graduationDate.year)-(Profile.getInstance().getCurrentDegree().SPINNER_OFFPUT));
				
			//display gpa
				String gpa = (Profile.getInstance().getCurrentDegree().GPA);
				EditText gpaDisplay =(EditText)findViewById(R.id.gpaField);
				gpaDisplay.setText(gpa);

			}	
			//display Skills
				//mContainerView = (ViewGroup) findViewById(R.id.container);
				for(Skill s : Profile.getInstance().getSkills()){
					final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.job_list_entry_item, mContainerView, false);
					((EditText) newView.findViewById(R.id.skillName)).setText(s.skillName);
					((EditText) newView.findViewById(R.id.skillRating)).setText(Integer.toString(s.rating));
					mContainerView.addView(newView, 0);
				}
				
			//display jobs
				//mContainerView2=(ViewGroup)findViewById(R.id.jobContainer);
				for(WorkExperience w : Profile.getInstance().getJobs()){
					final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.work_list_entry_item, mContainerView2, false);
					((EditText) newView.findViewById(R.id.companyNameField)).setText(w.company);
					((EditText) newView.findViewById(R.id.positionField)).setText(w.position);
					mContainerView2.addView(newView, 0);
					
				}
		
				
				//++++^+++++++++++++^++++++++++^+++++++ADD DATA++++++++^+++++++++++^+++++++++++++++^++++++++
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public void addSkill(View view){
		Log.d("Adding Skill","Adding Skill");
		
		final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.job_list_entry_item, mContainerView, false);
		
		newView.findViewById(R.id.delete_button).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	//remove view when clicking delete
            	if (mContainerView.getChildCount() > 0)
            		mContainerView.removeView(newView);
            }
																							});
		//add the new view
		mContainerView.addView(newView, 0);
		
	}
	
	public void addWork(View view){
		Log.d("Adding Skill","Adding WORK");
		
		final ViewGroup newView = (ViewGroup) LayoutInflater.from(this).inflate(R.layout.work_list_entry_item, mContainerView2, false);
		
		newView.findViewById(R.id.delete_button2).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            	//remove view when clicking delete
            	if (mContainerView2.getChildCount() > 0)
            		mContainerView2.removeView(newView);
            }
																							});
		//add the new view
		mContainerView2.addView(newView, 0);
		
		//format the spinners
		Spinner startMonthSpinner = (Spinner) newView.findViewById(R.id.startWorkMonthSpinner);
		Spinner startYearSpinner = (Spinner) newView.findViewById(R.id.startWorkYearSpinner);
		Spinner endMonthSpinner = (Spinner) newView.findViewById(R.id.endWorkMonthSpinner);
		Spinner endYearSpinner = (Spinner) newView.findViewById(R.id.endWorkYearSpinner);
		
		ArrayAdapter<CharSequence> monthAdapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
		monthAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		startMonthSpinner.setAdapter(monthAdapter);
		endMonthSpinner.setAdapter(monthAdapter);
		
		ArrayAdapter<CharSequence> yearAdapter = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
		yearAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		startYearSpinner.setAdapter(yearAdapter);
		endYearSpinner.setAdapter(yearAdapter);

	}
	
	public void inputData(View view){
		
		Log.d(TAG, "Pressed Update Info Button");
		
		//store personal info
		EditText editFirstName = (EditText) findViewById(R.id.firstNameField);
		Profile.getInstance().setFirstName(editFirstName.getText().toString());
		
		EditText editLastName = (EditText) findViewById(R.id.lastNameField);
		Profile.getInstance().setLastName( editLastName.getText().toString());
		
		EditText editPhone = (EditText) findViewById(R.id.phoneNumberField);
		Profile.getInstance().setPhoneNumber( editPhone.getText().toString());
		
		//store degree info
		EditText editSchool = (EditText) findViewById(R.id.schoolField);
		EditText editMajor= (EditText) findViewById(R.id.majorField);
		EditText editGPA= (EditText) findViewById(R.id.gpaField);
		
		//parse degree level (lev)
		RadioGroup r = (RadioGroup) findViewById(R.id.degreeGroup);
		int radioButtonID = r.getCheckedRadioButtonId();
		RadioButton radioButton = (RadioButton) r.findViewById(radioButtonID);
		String lev = radioButton.getText().toString();
		
		//parse graduation date
		int gradMonthInt = spinner.getSelectedItemPosition() +1;	
		int gradYearInt = Integer.parseInt((String)(spinner2.getSelectedItem()));
		GeneralDate gradDate = new GeneralDate(gradMonthInt,gradYearInt);
		Degree degree = new Degree(editSchool.getText().toString(), editMajor.getText().toString(), editGPA.getText().toString(), lev, gradDate);
		Profile.getInstance().setCurrentDegree(degree);
		
		//parse skills
		View a;
		ViewGroup b;
		Profile.getInstance().emptySkills();
		for(int i =0; i < mContainerView.getChildCount(); i++){
			a = mContainerView.getChildAt(i);
			if (a instanceof ViewGroup){
				b = (ViewGroup)a;
				String skillName  = ((EditText)b.findViewById(R.id.skillName)).getText().toString();
				int skillRating = Integer.parseInt(((EditText)b.findViewById(R.id.skillRating)).getText().toString());
				Skill newSkill = new Skill(skillName, skillRating);
				Profile.getInstance().addSkill(newSkill);
			}
		}
		
		//parse jobs
		View aa;
		ViewGroup bb;
		Profile.getInstance().emptyJobs();
		for(int i=0; i<mContainerView2.getChildCount();i++){
			aa = mContainerView2.getChildAt(i);
			if(aa instanceof ViewGroup){
				bb=(ViewGroup)aa;
				String companyName  = ((EditText)bb.findViewById(R.id.companyNameField)).getText().toString();
				String position = ((EditText)bb.findViewById(R.id.positionField)).getText().toString();
				WorkExperience newWork = new WorkExperience(companyName, position);
				Profile.getInstance().addJob(newWork);
			}
		}
		
		
		//move to new activity and display profile info
		Intent intent = new Intent(this, ViewProfileActivity.class);
		startActivity(intent);

	}
	


}
