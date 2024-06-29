package com.example.uplanner.presentation.utility;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.widget.Toast;

import com.example.uplanner.business.StudentLogic;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.presentation.MainPageActivity;
import com.example.uplanner.presentation.SignupActivity;
import com.example.uplanner.presentation.utility.ActiveStudentManager;

public class LoginSignupHandler{
    private StudentLogic studentLogic;

    public LoginSignupHandler(Context context) {
        this.studentLogic = new StudentLogic();
    }

    public void loginUser(String email, String password, Context context) {
        int loginResult = studentLogic.loginUser(email, password);
        switch (loginResult) {
            case IStudentPersistence.LOGIN_SUCCESS:

                Student loggedInStudent = studentLogic.getActiveStudent(email); // Getting the logged-in student object
                ActiveStudentManager.getInstance().setActiveStudent(loggedInStudent); // Setting activeStudent to the logged-in student

                Toast.makeText(context, "Login successful!", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(context, MainPageActivity.class);
                intent.putExtra("loggedInStudent", loggedInStudent);
                context.startActivity(intent);
                break;
            case IStudentPersistence.INCORRECT_PASSWORD:
                Toast.makeText(context, "Incorrect password. Please try again.", Toast.LENGTH_SHORT).show();
                break;
            case IStudentPersistence.EMAIL_NOT_FOUND:
                Toast.makeText(context, "Email not found. Please sign up.", Toast.LENGTH_SHORT).show();
                Intent intent2 = new Intent(context, SignupActivity.class);
                context.startActivity(intent2);
                break;
        }
    }

    public void signupUser(String fullname, String email, String password, String confirmPassword, Context context) {
        // Check if all fields are filled
        if (fullname.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
            Toast.makeText(context, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if email already exists
        boolean isEmailExists = studentLogic.isEmailExists(email);
        if (isEmailExists) {
            Toast.makeText(context, "Email already exists. Please use another email.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check password length
        if (password.length() < 8) {
            Toast.makeText(context, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            Toast.makeText(context, "Passwords do not match.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Signup successful
        studentLogic.insertStudent(fullname, email, password);

        Student loggedInStudent = studentLogic.getActiveStudent(email); // Getting the logged-in student object
        ActiveStudentManager.getInstance().setActiveStudent(loggedInStudent); // Setting activeStudent to the logged-in student

        Toast.makeText(context, "Signup successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainPageActivity.class);
        context.startActivity(intent);
    }

    public void changePassword(Student s , String newPassword, Context context) {
        // Check if all fields are filled
        if (newPassword.isEmpty()) {
            Toast.makeText(context, "Please fill in the fields.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check password length
        if (newPassword.length() < 8) {
            Toast.makeText(context, "Password must be at least 8 characters long.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Check if passwords match
        if (s.getStudentPassword().equals(newPassword)) {
            Toast.makeText(context, "New Password cannot be the same as old password.", Toast.LENGTH_SHORT).show();
            return;
        }

        // password change successful
        studentLogic.changePassword(s, newPassword);

        //need to also update the active student object in ActiveStudentManager
        Student loggedInStudent = studentLogic.getActiveStudent(s.getStudentEmail()); // Getting the logged-in student object
        ActiveStudentManager.getInstance().setActiveStudent(loggedInStudent); // Setting activeStudent to the logged-in student

        Toast.makeText(context, "Password change successful!", Toast.LENGTH_SHORT).show();
        Intent intent = new Intent(context, MainPageActivity.class);
        intent.putExtra("loggedInStudent", loggedInStudent);
        context.startActivity(intent);
    }

}
