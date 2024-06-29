package com.example.uplanner.objects;

import androidx.annotation.NonNull;

import com.example.uplanner.business.CourseGraph;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CurrentCourseSelector;
import com.example.uplanner.persistence.stub.CoursePersistenceStub;
import com.example.uplanner.persistence.stuAndCourseUtility.StuUtility;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Represents a student in the application.
 */
public class Student implements Serializable {
    private final String studentEmail;
    private String studentPassword;

    /**
     * Display name will be the first part of the student email
     * e.g. student email abc@myumanitoba.ca then display name will be abc
     */
    private final String studentDisplayName;
    private final String studentFullName;
    private final int studentNumber;
    private final int year;
    private double gpa;
    private Set<String> currentCourses;
    private Set<String> completedCourses;
    private Set<String> nextAvailableCourses;

    /**
     * Constructor for creating a new student.
     *
     * @param StudentFullName The full name of the student.
     * @param newEmail        The email address of the student.
     * @param newPassword     The password of the student.
     */
    public Student(String StudentFullName, String newEmail, String newPassword) {
        this.studentEmail = newEmail;
        this.studentPassword = newPassword;
        this.studentFullName = StudentFullName;
        this.studentDisplayName = newEmail.substring(0, newEmail.indexOf('@'));
        this.studentNumber = StuUtility.assignStudentNumber();
        this.year = StuUtility.getRandomYear();

        // Sets the student with courses according to the year they're in
        populateCourses();

        this.gpa = StuUtility.calculateGPA(getCompletedCourses(), true, null);
    }

    /**
     * Constructor for creating a new student with completed courses.
     *
     * @param StudentFullName  The full name of the student.
     * @param newEmail         The email address of the student.
     * @param newPassword      The password of the student.
     * @param StudentNumber    The student number.
     * @param year             The year of the student.
     * @param completedCourses The set of completed courses.
     */
    public Student(String StudentFullName, String newEmail, String newPassword, int StudentNumber, int year, Set<String> completedCourses) {
        this.studentEmail = newEmail;
        this.studentPassword = newPassword;
        this.studentFullName = StudentFullName;
        this.studentDisplayName = newEmail.substring(0, newEmail.indexOf('@'));
        this.studentNumber = StudentNumber;
        this.year = year;

        ArrayList<String> switchedList = new ArrayList<>(completedCourses);
        // Strip completed courses with their grades
        this.completedCourses = splitCoursesWithGrades(switchedList);

        // Sets the student with courses according to the year they're in
        // populateCourses();
        this.gpa = StuUtility.calculateGPA(null, false, switchedList);
    }

    // Helper method to split the courses from their grades and adding to Courses
    private Set<String> splitCoursesWithGrades(ArrayList<String> CoursesWithGrades) {
        Set<String> courses = new HashSet<>();
        for (String c : CoursesWithGrades) {
            String[] coursesSplit = c.split("-");
            if (coursesSplit.length > 1) {
                courses.add(coursesSplit[0]);
            }
        }

        return courses;
    }

    // Populates the courses for the student
    private void populateCourses() {
        CurrentCourseSelector selectCurrent = new CurrentCourseSelector(year);
        currentCourses = new HashSet<>(selectCurrent.getCurrentCourses());

        CoursePersistenceStub courses = new CoursePersistenceStub();
        CourseGraph graph = courses.getGraph();

        completedCourses = new HashSet<>(graph.getAllDoneCourses(currentCourses));
        nextAvailableCourses = new HashSet<>(graph.getNextAvailableCourses(currentCourses));
    }

    // Generates hash code for the student
    public int hashCode() {
        return Objects.hash(studentEmail, studentPassword, studentDisplayName);
    }

    // Compares this student to another object
    public boolean equals(final Student o) {
        return Objects.equals(this.studentEmail, o.studentEmail) &&
                Objects.equals(this.studentPassword, o.studentPassword);
    }

    // Updates the password of the student
    public void updatePassword(String newPassword) {
        this.studentPassword = newPassword;
    }

    // Getters and setters

    public Set<String> getCompletedCourses() {
        return completedCourses;
    }

    public int getStudentNumber() {
        return studentNumber;
    }

    public int getYear() {
        return year;
    }

    public String getStudentEmail() {
        return (studentEmail);
    }

    public String getStudentPassword() {
        return (studentPassword);
    }

    public String getStudentFullName() {
        return (studentFullName);
    }

    public double getGpa() {
        return gpa;
    }

    // Provides a string representation of the student object
    @NonNull
    @Override
    public String toString() {

        return "Student Full Name: " + studentFullName + "\n" +
                "Student Email: " + studentEmail + "\n" +
                "Student Number: " + studentNumber + "\n" +
                "Year: " + year + "\n" +

                // Get completed courses
                "Completed Courses: " + completedCourses + "\n" +

                // Get current courses
                "Current Courses: " + currentCourses + "\n" +

                // Get next available courses
                "Next Available Courses: " + nextAvailableCourses + "\n";
    }
}

