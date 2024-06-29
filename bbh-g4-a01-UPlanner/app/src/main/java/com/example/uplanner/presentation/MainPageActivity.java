package com.example.uplanner.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;

import com.example.uplanner.R;
import com.example.uplanner.presentation.ProfessorRatingActivityMain.ProfessorRatingMenuActivity;

public class MainPageActivity extends Activity {

	private Button userProfileButton;
	private Button professorRatingsButton;
	private Button courseRatingsButton;
	private Button tuitionAndFundsButton;
	private Button courseHistoryButton;
	private Button academicSupportButton;
	private Button planMyDegreeButton;
	private Button jobInformationButton;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main_page);

		initializeButtons();
		setListeners();
	}

	private void initializeButtons() {
		userProfileButton = findViewById(R.id.user_profile_button);
		professorRatingsButton = findViewById(R.id.professor_ratings_button);
		courseRatingsButton = findViewById(R.id.course_ratings_button);
		tuitionAndFundsButton = findViewById(R.id.tuition_and_funds_button);
		courseHistoryButton = findViewById(R.id.course_history_button);
		academicSupportButton = findViewById(R.id.academic_support_button);
		planMyDegreeButton = findViewById(R.id.plan_my_degree_button);
		jobInformationButton = findViewById(R.id.job_information_button);
	}

	private void setListeners() {
		setButtonListener(userProfileButton, UserProfileActivity.class);
		setButtonListener(courseHistoryButton, CourseHistoryActivity.class);
		setButtonListener(professorRatingsButton, ProfessorRatingMenuActivity.class);
		setButtonListener(courseRatingsButton, UnderConstructionActivity.class);
		setButtonListener(tuitionAndFundsButton, UnderConstructionActivity.class);
		setButtonListener(academicSupportButton, UnderConstructionActivity.class);
		setButtonListener(planMyDegreeButton, UnderConstructionActivity.class);
		setButtonListener(jobInformationButton, UnderConstructionActivity.class);
	}

	private void setButtonListener(Button button, final Class<? extends Activity> activityClass) {
		button.setOnClickListener(new View.OnClickListener() {
			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), activityClass);
				startActivity(nextScreen);
			}
		});
	}
}
	
	