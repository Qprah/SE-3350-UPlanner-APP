package com.example.uplanner.objects;

import junit.framework.TestCase;

import org.junit.Test;

public class CourseTest extends TestCase
{
    @Test
    public void testCourse1()
    {
        Course course;

        System.out.println("\nStarting testCourse");

        course = new Course("COMP 0000", "Testing Course Name", "Testing Course Description", "");
        assertNotNull(course);
        assertEquals("COMP 0000", course.getCourseId());
        assertEquals("Testing Course Name", course.getCourseName());
        assertEquals("Testing Course Description", course.getCourseDescription());


        System.out.println("Finished testCourse");
    }
}