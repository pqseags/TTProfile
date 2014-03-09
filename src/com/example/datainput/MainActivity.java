package com.example.datainput;


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
		
		//create spinners
		spinner = (Spinner) findViewById(R.id.monthSpinner);
		ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.months_array, android.R.layout.simple_spinner_item);
		adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner.setAdapter(adapter);
		
		spinner2 = (Spinner) findViewById(R.id.yearSpinner);
		ArrayAdapter<CharSequence> adapter2 = ArrayAdapter.createFromResource(this, R.array.years_array, android.R.layout.simple_spinner_item);
		adapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
		spinner2.setAdapter(adapter2);
		
		//set up skill container
		mContainerView = (ViewGroup) findViewById(R.id.container);
		mContainerView2 = (ViewGroup) findViewById(R.id.container2);
		
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
		
		//move to new activity and display profile info
		Intent intent = new Intent(this, ViewProfileActivity.class);
		startActivity(intent);

	}
	


}
