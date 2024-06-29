
package com.example.uplanner.presentation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.uplanner.R;
import com.example.uplanner.presentation.utility.LoginSignupHandler;

public class LoginActivity extends Activity {
    private EditText textEmail;
    private EditText textPassword;
    private LoginSignupHandler logInSignUpHandler;

    public LoginActivity(){};

    @Override
    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.log_in_page);

        logInSignUpHandler  = new LoginSignupHandler(this);

        Button backSpaceLogIn = (Button) findViewById(R.id.back_space_in_log_in);
        Button logInButton = (Button) findViewById(R.id.log_in_button_ek4);


        //variables assignment for the two input fields
        textEmail = findViewById(R.id.input_box_email);
        textPassword = findViewById(R.id.input_box_password);

        backSpaceLogIn.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), WelcomePageActivity.class);
                startActivity(nextScreen);
            }
        });

        //below event listener is for the login button
        logInButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String email = textEmail.getText().toString();
                String password = textPassword.getText().toString();
                logInSignUpHandler.loginUser(email, password,v.getContext());
            }
        });
    }
}