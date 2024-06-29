/**
 * Utility class for managing course-related functionalities such as generating random letter grades and identifying GPAs.
 */

package com.example.uplanner.persistence.stuAndCourseUtility;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePool;
import java.util.Random;

public class CourseUtility {
    // CoursePool instance for accessing course data, current courses info will be updated to instance of coursePool.
    private static final CoursePool cp = new CoursePool();

    /**
     * Generates a random letter grade.
     *
     * @return A random letter grade.
     */
    public static String generateRandomLetterGrade() {
        Random rand = new Random();
        double gradeSelector = rand.nextDouble(); // Select a random number between 0 and 1

        if (gradeSelector < 0.1) { // 10%
            return "A+";
        } else if (gradeSelector < 0.4) { // 30%
            return rand.nextBoolean() ? "A" : "B+";
        } else if (gradeSelector < 0.8) { // 40%
            return "B";
        } else { // 20% chance for C+ or C
            return rand.nextBoolean() ? "C+" : "C";
        }
    }

    /**
     * Identifies the GPA based on the given letter grade.
     *
     * @param letterGrade The letter grade.
     * @return The corresponding GPA.
     */
    public static double identifyGpa(String letterGrade){
        switch (letterGrade) {
            case "A+":
                return 4.5;
            case "A":
                return 4.0;
            case "B+":
                return 3.5;
            case "B":
                return 3.0;
            case "C+":
                return 2.5;
            case "C":
                return 2.0;
            default:
                return 0.0;
        }
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