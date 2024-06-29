


package com.example.uplanner.presentation;

import static com.example.uplanner.R.*;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UnderConstructionActivity extends Activity {


	private Button backSpaceUnderConstruction;
	private Button getNotified;
	private EditText getNotifiedEmail;

	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		setContentView(layout.under_construction);

		backSpaceUnderConstruction = (Button) findViewById(id.back_space_in_under_con);
		getNotified = (Button) findViewById(id.get_notified);
		getNotifiedEmail = (EditText) findViewById(id.input_box_email_uc);

		backSpaceUnderConstruction.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				Intent nextScreen = new Intent(getApplicationContext(), MainPageActivity.class);
				startActivity(nextScreen);
			}
		});

		getNotified.setOnClickListener(new View.OnClickListener() {

			public void onClick(View v) {
				if(getNotifiedEmail.length() == 0) //entered nothing
				{
					Toast.makeText(UnderConstructionActivity.this, "Please enter your email", Toast.LENGTH_SHORT).show();
				}
				else
				{
					Toast.makeText(UnderConstructionActivity.this, "Thank you! You'll be notified soon!", Toast.LENGTH_SHORT).show();
				}
			}
		});

	}
}
	
	