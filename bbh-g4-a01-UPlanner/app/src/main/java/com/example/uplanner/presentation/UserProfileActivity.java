package com.example.uplanner.presentation;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ProgressBar;
import android.util.Log;

import com.example.uplanner.R;
import com.example.uplanner.business.StudentLogic;
import com.example.uplanner.objects.Course;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePool;
import com.example.uplanner.presentation.utility.ActiveStudentManager;
import com.example.uplanner.presentation.utility.LoginSignupHandler;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class UserProfileActivity extends Activity {
    private Button back_space_user_profile;
    private Button signOutButton;
    private EditText textNewPassword;
    private Button newPasswordButton;
    private StudentLogic studentLogic;
    private LoginSignupHandler logInSignUpHandler;
    private ProgressBar progressBar;

    private Student student = ActiveStudentManager.getInstance().getActiveStudent();

    public UserProfileActivity(Context context) {
        this.studentLogic = new StudentLogic();
    }
    public UserProfileActivity(){}

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.user_profile_page);

        logInSignUpHandler  = new LoginSignupHandler(this);

        //back space button
        back_space_user_profile = (Button) findViewById(R.id.back_space_user_profile);
        signOutButton = (Button) findViewById(R.id.sign_out_button);

        //change password
        textNewPassword = findViewById(R.id.change_password_input);
        newPasswordButton = findViewById(R.id.change_password_button);

        //gpa performance
        progressBar = findViewById(R.id.progress_bar);

        //display dynamic information
        setDisplay();


        newPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                String newPassword = textNewPassword.getText().toString();
                Log.d("password updated:"+ newPassword,"password updated:"+ newPassword);

                Student activeStudent=ActiveStudentManager.getInstance().getActiveStudent();
                logInSignUpHandler.changePassword(activeStudent,newPassword,v.getContext());

                Intent nextScreen = new Intent(getApplicationContext(), MainPageActivity.class);
                startActivity(nextScreen);
            }
        });

        back_space_user_profile.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                Intent nextScreen = new Intent(getApplicationContext(), MainPageActivity.class);
                nextScreen.putExtra("loggedInStudent", student);
                startActivity(nextScreen);
            }
        });

        signOutButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                ActiveStudentManager.getInstance().setActiveStudent(null);  // Clearing activeStudent on logout

                Intent nextScreen = new Intent(getApplicationContext(), LoginActivity.class);
                startActivity(nextScreen);
            }
        });
    }

    //display all dynamic information in the user profile page
     private void setDisplay() {
        //student name display
        final TextView nameTextView = (TextView) findViewById(R.id.student_name_answer);
        nameTextView.setText(student.getStudentFullName());

        //student number display
        final TextView studentNumTextView = (TextView) findViewById(R.id.student_num_answer);
        studentNumTextView.setText(""+student.getStudentNumber());

        //year display
        final TextView yearTextView = (TextView) findViewById(R.id.year_answer);
        yearTextView.setText(""+student.getYear());


        //calculations
         int total = 60;
         int creditHour = 3;
         int validCreditHours = student.getCompletedCourses().size()*creditHour;
         int remainingCreditHours = total - validCreditHours;


         //valid credit hours display
         final TextView validCreditTextView = (TextView) findViewById(R.id.valid_credit_hours_answer);
         validCreditTextView.setText(String.valueOf(validCreditHours)); // Convert int to string

         //valid remaining hours display
         final TextView remainingTextView = (TextView) findViewById(R.id.remaining_answer);
         remainingTextView.setText(String.valueOf(remainingCreditHours)); // Convert int to string

        //gpa display
         double gpa = student.getGpa();
         final TextView gpaTextView = (TextView) findViewById(R.id.gpa_answer);
         gpaTextView.setText(String.format(Locale.US, "%.2f", gpa)); // Format double as string
         int percentage = (int) ((gpa/4.5)*100);
         Log.d(""+percentage, "printing percentage ");
         progressBar.setProgress(percentage);

    }
}
