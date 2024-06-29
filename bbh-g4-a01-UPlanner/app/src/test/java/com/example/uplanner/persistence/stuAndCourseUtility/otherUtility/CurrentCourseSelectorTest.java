package com.example.uplanner.persistence.stuAndCourseUtility.otherUtility;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Set;

public class CurrentCourseSelectorTest {
    private CurrentCourseSelector currentCourseSelector;

    @Before
    public void setUp() {
        //generate a random year from 1 to 4
        int randomYear = (int) (Math.random() * 4) + 1;
        //create a new CurrentCourseSelector object
        currentCourseSelector = new CurrentCourseSelector(randomYear);
    }

    @Test
    public void testGetCurrentCourses() {
        Set<String> currentCourses = currentCourseSelector.getCurrentCourses();
        assertNotNull("Current courses should not be null", currentCourses);
        assertFalse("Current courses should not be empty", currentCourses.isEmpty());
        //check if the current courses are valid
        for (String course : currentCourses) {
            assertTrue("Invalid course: " + course, course.matches("COMP \\d{4}"));
        }
    }
}