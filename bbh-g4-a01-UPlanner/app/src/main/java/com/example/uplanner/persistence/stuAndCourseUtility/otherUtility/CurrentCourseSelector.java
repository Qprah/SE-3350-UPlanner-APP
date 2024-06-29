/**
 * This utility class, CurrentCourseSelector, assists in selecting current courses for students based on their academic year.
 * It provides functionality to assign a set of current courses for a given year of study.
 */

package com.example.uplanner.persistence.stuAndCourseUtility.otherUtility;

import java.util.Arrays;
import java.util.HashSet;
import java.util.Random;
import java.util.Set;

public class CurrentCourseSelector {
    //current course selector

    private final Set<String> currentCourses;

    /**
     * Constructor: Initializes the CurrentCourseSelector object and assigns current courses for the specified academic year.
     * Parameters:
     *   - year: The academic year for which to select current courses.
     */
    public CurrentCourseSelector(int year){
        currentCourses = assignCurrentCourses(year);
    }

    /**
     * Assigns a set of current courses for the specified academic year.
     * Parameters:
     *   - year: The academic year for which to select current courses.
     * Returns:
     *   A set of current courses for the specified academic year.
     */
    private Set<String> assignCurrentCourses(int year){
        String[][] tuples = yearCourseSelector(year);

        Random rand = new Random();

        // Get a random index to select a tuple
        int randomIndex = rand.nextInt(tuples.length);

        // Return the Set of courses
        return new HashSet<>(Arrays.asList(tuples[randomIndex]));
    }

    /**
     * Selects a set of course combinations based on the specified academic year.
     * Parameters:
     *   - year: The academic year for which to select course combinations.
     * Returns:
     *   A 2D array representing course combinations for the specified academic year.
     */
    private String[][] yearCourseSelector(int year) {
        String[][][] yearTuples = {
                {   //year 1
                        {"COMP 1010"},{"COMP 1020"},{"COMP 1012"},
                        {"COMP 1010","COMP 1500"},{"COMP 1012, COMP 1600"}
                },
                {   // Year 2
                        {"COMP 2150", "COMP 2080", "COMP 2280"},
                        {"COMP 2140", "COMP 2130", "COMP 2160"},
                        {"COMP 2140", "COMP 2080"},
                        {"COMP 2160"}
                },
                {   // Year 3-4
                        {"COMP 3020", "COMP 3380", "COMP 3040"},
                        {"COMP 3020", "COMP 3170", "COMP 3430"},
                        {"COMP 3020", "COMP 3380"}
                },
                {   // Year 4 - with some 3rd year
                        {"COMP 4350", "COMP 3170", "COMP 3430"},
                        {"COMP 4350", "COMP 3170", "COMP 3430", "COMP 3040"}
                }
        };

        // Adjusting year to array index
        int yearIndex = year - 1;

        // Returning the tuple for the specified year
        return yearTuples[yearIndex];
    }

    /**
     * Retrieves the set of current courses.
     * Returns:
     *   The set of current courses.
     */
    public Set<String> getCurrentCourses() {
        return currentCourses;
    }
}
