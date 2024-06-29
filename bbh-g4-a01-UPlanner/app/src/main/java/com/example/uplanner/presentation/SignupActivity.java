

package com.example.uplanner.presentation;

import android.app.Activity;
import android.os.Bundle;


import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.content.Intent;

import com.example.uplanner.R;
import com.example.uplanner.presentation.utility.LoginSignupHandler;

public class SignupActivity extends Activity {
    private LoginSignupHandler logInSignUpHandler;
    private Button newAccountButton;
    private Button backSpaceCreateAnAccount;
    private TextView logInHyperlink;
    private EditText textFullName;
    private EditText textEmail;
    private EditText textPassword;
    private EditText textConfirmPassword;

    public SignupActivity(){};

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_an_account_page);
        logInSignUpHandler  = new LoginSignupHandler(this);
        backSpaceCreateAnAccount = (Button) findViewById(R.id.back_space_create_an_account);
        logInHyperlink = (TextView) findViewById(R.id.log_in_hyperlink_ek2);
        newAccountButton = (Button) findViewById(R.id.new_account_button);

        //variables assignment for the four input fields
        textFullName = findViewById(R.id.input_box_fullname);
        textEmail = findViewById(R.id.input_box_email);
        textPassword = findViewById(R.id.input_box_password);
        textConfirmPassword = findViewById(R.id.input_box_confirm_password);

        //below event listener is for the back button to take you back to the welcome page
        backSpaceCreateAnAccount.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), WelcomePageActivity.class);
                startActivity(nextScreen);
            }
        });

        //below event listener is for the already planning link to take you to the login page
        logInHyperlink.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(nextScreen);
            }
        });

        //below event listener is for the register button
        newAccountButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String fullName = textFullName.getText().toString();
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                String confirmPassword = textConfirmPassword.getText().toString();
                logInSignUpHandler.signupUser(fullName, email,password, confirmPassword,v.getContext());
            }
        });
    }
}

