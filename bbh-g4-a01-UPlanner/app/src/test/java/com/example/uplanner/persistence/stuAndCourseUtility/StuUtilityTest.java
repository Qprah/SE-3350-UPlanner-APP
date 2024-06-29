package com.example.uplanner.persistence.stuAndCourseUtility;

import org.junit.Test;
import static org.junit.Assert.*;

public class StuUtilityTest {

    @Test
    public void testAssignStudentNumber() {
        int initialNumber = StuUtility.assignStudentNumber();
        int nextNumber = StuUtility.assignStudentNumber();
        assertEquals("Student numbers should increment by 1", initialNumber + 1, nextNumber);
    }

    @Test
    public void testGetRandomYear() {
        for (int i = 0; i < 1000; i++) {
            int year = StuUtility.getRandomYear();
            assertTrue("Year should be between 1 and 4", year >= 1 && year <= 4);
        }
    }

    @Test
    public void CalculateGPA(){

    }
}