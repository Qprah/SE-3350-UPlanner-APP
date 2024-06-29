/**
 * This utility class provides methods for handling student-related functionalities,
 * such as assigning unique student numbers, generating random years, and calculating GPAs.
 */

package com.example.uplanner.persistence.stuAndCourseUtility;

import com.example.uplanner.objects.Course;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePool;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Set;

public class StuUtility {
    private static int start = 7148382;

    private static CoursePool cp = null;


    /**
     * Assigns a unique student number.
     *
     * @return A unique student number.
     */
    public static int assignStudentNumber() {
        return start++;
    }

    /**
     * Generates a random year between 1 and 4.
     *
     * @return A random year between 1 and 4.
     */
    public static int getRandomYear() {
        Random rand = new Random();
        int maxYear = 4;
        return rand.nextInt(maxYear) + 1;
    }

    /**
     * Calculates the GPA based on the completed courses.
     *
     * @param completedCourses The set of completed course codes.
     * @param isLeftParameter  Indicates whether the left or right parameter is passed.
     * @param dbCourseSearch   List of courses from the database.
     * @return The calculated GPA.
     */
    public static double calculateGPA(Set<String> completedCourses, boolean isLeftParameter, ArrayList<String> dbCourseSearch){

        List<Course> foundCourses = new ArrayList<>(); //a new list to store found courses
        cp = new CoursePool();

        double total = 0;
        int coursesSize;

        List<Course> allCourses = cp.getCourses();
        if(isLeftParameter){
            for(String code: completedCourses){

                for (Course course : allCourses) {

                    if (course.getCourseId().equals(code)) {
                        // Add the course object to the list if its name matches
                        foundCourses.add(course);
                        break; // Break the loop since the course is found
                    }
                }
            }

            for(Course c : foundCourses){
                total+= c.getGpa();
            }

            coursesSize = foundCourses.size();
        } else {
            //from the hsqldb the courses are in form of COMP1010-B
            for(String code: dbCourseSearch){

                String[] coursesSplit = code.split("-");

                for (Course course : allCourses) {
                    if (course.getCourseId().equals(coursesSplit[0])) {
                        // Add the course object to the list if its name matches
                        course.setLetterGrade(coursesSplit[1]);
                        total += course.getGpa();
                        break; // Break the loop since the course is found
                    }
                }
            }

            coursesSize = dbCourseSearch.size();
        }

        return total/coursesSize;
    }

    /**
     * Retrieves the CoursePool instance.
     *
     * @return The CoursePool instance.
     */
    public static CoursePool getCp() {
        return cp;
    }
}
