package com.example.uplanner.objects;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

import com.example.uplanner.persistence.stuAndCourseUtility.CourseUtility;

/**
 * Represents a course in the application.
 */
public class Course {
    private final String name;
    private String letterGrade;
    private double gpa;
    private final int courseLevel;
    private final String courseId;
    private final String description;
    private final List<String> prerequisites;
    private boolean expanded;

    /**
     * Constructor for creating a new course.
     * @param code The course code.
     * @param name The name of the course.
     * @param description The description of the course.
     * @param prerequisites A comma-separated string of prerequisite course codes.
     */
    public Course(String code, String name, String description, String prerequisites) {
        this.courseId = code;
        this.name = name;

        this.letterGrade = CourseUtility.generateRandomLetterGrade();
        this.gpa = CourseUtility.identifyGpa(letterGrade);

        this.courseLevel = Integer.parseInt(String.valueOf(code.split(" ")[1].charAt(0))); //gets the first character of code after "COMP"
        this.description = description;

        //add prerequisites
        this.prerequisites = new ArrayList<>();
        setPrerequisites(prerequisites);

        //for course history display
        this.expanded = false;
    }

    /**
     * Helper method to set prerequisites from a comma-separated string.
     * @param prerequisites A comma-separated string of prerequisite course codes.
     */
    private void setPrerequisites(String prerequisites) {
        String[] parts = prerequisites.split(",");
        for (String part : parts) {
            String course = part.trim();
            if (!course.isEmpty()) {
                this.prerequisites.add(course);
            }
        }
    }

    /**
     * Retrieves the list of prerequisites for the course.
     * @return The list of prerequisites.
     */
    public List<String> getPrerequisites() {
        return prerequisites;
    }

    /**
     * Retrieves the letter grade received for the course.
     * @return The letter grade.
     */
    public String getLetterGrade() {
        return letterGrade;
    }

    /**
     * Retrieves the course ID.
     * @return The course ID.
     */
    public String getCourseId() {
        return courseId;
    }

    /**
     * Retrieves the description of the course.
     * @return The course description.
     */
    public String getCourseDescription() {
        return description;
    }

    /**
     * Retrieves the name of the course.
     * @return The course name.
     */
    public String getCourseName() {
        return name;
    }

    /**
     * Retrieves the GPA received for the course.
     * @return The GPA.
     */
    public double getGpa(){
        return gpa;
    }

    /**
     * Sets the GPA received for the course.
     * @param gpa The GPA to be set.
     */
    public void setGpa(double gpa){
        this.gpa = gpa;
    }

    /**
     * Sets the letter grade received for the course.
     * @param letterGrade The letter grade to be set.
     */
    public void setLetterGrade(String letterGrade) {
        this.letterGrade = letterGrade;
        this.gpa = CourseUtility.identifyGpa(letterGrade);
    }

    /**
     * Checks if the course is expanded (for course history display).
     * @return True if the course is expanded, false otherwise.
     */
    public boolean isExpanded() {
        return expanded;
    }

    /**
     * Sets whether the course is expanded (for course history display).
     * @param expanded True to expand the course, false otherwise.
     */
    public void setExpanded(boolean expanded) {
        this.expanded = expanded;
    }

    /**
     * Provides a string representation of the course object.
     * @return A string representing the course object.
     */
    @NonNull
    @Override
    public String toString() {
        return "Course{" +
                "code='" + courseId + '\'' +
                ", name='" + name + '\'' +
                ", student=" + " " +
                ", letterGrade='" + letterGrade + '\'' +
                ", courseLevel=" + courseLevel +
                ", description='" + description + '\'' +
                '}';
    }
}
