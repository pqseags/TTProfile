package com.example.datainput;

import com.example.datainput.Degree.DegreeLevel;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class ViewProfileActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_view_profile);
		
	//display name
		StringBuilder sb= new StringBuilder(Profile.getInstance().getFirstName());
		sb.append(" ");
		String name = sb.append(Profile.getInstance().getLastName()).toString();
		TextView nameDisplay = (TextView) findViewById(R.id.nameDisplay);
		nameDisplay.setText(name);
		
	//display phone number
		String phone = Profile.getInstance().getPhoneNumber();
		TextView phoneDisplay = (TextView)findViewById(R.id.phoneNumberField);
		phoneDisplay.setText(phone);
		
	//display degree
		StringBuilder sb2 = new StringBuilder(Profile.getInstance().getCurrentDegree().schoolName);
		sb2.append(": ");
		String level;
		DegreeLevel deg = Profile.getInstance().getCurrentDegree().degreeLevel;
		if (deg.equals(DegreeLevel.bachelors))
			level = " Bachelor's in ";
		else if (deg.equals(DegreeLevel.masters))
			level=" Master's in ";
		else if (deg.equals(DegreeLevel.phd))
			level=" PhD in ";
		else level = " Degree in ";
		sb2.append(level);
		sb2.append(Profile.getInstance().getCurrentDegree().major);
		TextView degreeDisplay = (TextView)findViewById(R.id.degreeField);
		degreeDisplay.setText(sb2.toString());
		
	//display graduation date
		StringBuilder sb3 = new StringBuilder("Expect. Graduation: ");
		switch(Profile.getInstance().getCurrentDegree().graduationDate.month){
		case 1: sb3.append("Jan. "); break;
		case 2: sb3.append("Feb. "); break;
		case 3: sb3.append("Mar. "); break;
		case 4: sb3.append("April "); break;
		case 5: sb3.append("May "); break;
		case 6: sb3.append("June "); break;
		case 7: sb3.append("July "); break;
		case 8: sb3.append("Aug. "); break;
		case 9: sb3.append("Sept. "); break;
		case 10: sb3.append("Oct. "); break;
		case 11: sb3.append("Nov. "); break;
		case 12: sb3.append("Dec. "); break;
		default: sb3.append("Month? ");
		}
		sb3.append(Integer.toString(Profile.getInstance().getCurrentDegree().graduationDate.year));
		TextView graduationDisplay = (TextView)findViewById(R.id.graduationField);
		graduationDisplay.setText(sb3.toString());
		
	//display gpa
		StringBuilder sb4 = new StringBuilder("GPA: ");
		TextView gpaDisplay = (TextView)findViewById(R.id.gpaField);
		sb4.append(Profile.getInstance().getCurrentDegree().GPA);
		gpaDisplay.setText(sb4.toString());

		
		
	}
	
	public void goToEditInfo(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}
	
	public void sendProfileForMatching(View view){
		
		//send the profile to the web to match with company's filters
		
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.view_profile, menu);
//		return true;
//	}
	
	

}
