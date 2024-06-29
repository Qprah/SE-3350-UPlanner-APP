package com.example.uplanner.persistence.stuAndCourseUtility;

import org.junit.Test;
import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.List;

public class CourseUtilityTest {

    @Test
    public void testGenerateRandomLetterGrade() {
        List<String> validGrades = Arrays.asList("A+", "A", "B+", "B", "C+", "C");

        for (int i = 0; i < 1000; i++) {
            String grade = CourseUtility.generateRandomLetterGrade();
            assertTrue("Generated grade should be in the valid grades list", validGrades.contains(grade));
        }
    }
}