package com.example.uplanner.presentation.utility;

import com.example.uplanner.objects.Student;

//this class is used for active session i.e. the student that is logged in at a given point in time
//Follows the singleton pattern
public class ActiveStudentManager {
    private static ActiveStudentManager instance;
    private Student activeStudent;

    private ActiveStudentManager() {
        // Private constructor to prevent instantiation
    }

    //Only initialized once
    public static synchronized ActiveStudentManager getInstance() {
        if (instance == null) {
            instance = new ActiveStudentManager();
        }
        return instance;
    }

    //returns active student
    public Student getActiveStudent() {
        return activeStudent;
    }

    //sets active student
    public void setActiveStudent(Student student) {
        activeStudent = student;
    }
}