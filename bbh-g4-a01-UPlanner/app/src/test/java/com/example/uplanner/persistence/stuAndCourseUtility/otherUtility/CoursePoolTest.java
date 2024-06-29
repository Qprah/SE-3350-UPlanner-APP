package com.example.uplanner.persistence.stuAndCourseUtility.otherUtility;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

import com.example.uplanner.objects.Course;
import org.junit.Test;
import org.junit.Assert;
import org.junit.Before;

import java.util.List;

public class CoursePoolTest {
    private CoursePool coursePool;

    @Before
    public void setUp() {
        coursePool = new CoursePool();
    }
    @Test
    public void testGetCourses() {
        List<Course> courses = coursePool.getCourses();
        assertNotNull(courses, "Courses list should not be null");
        assertFalse(courses.isEmpty(), "Courses list should not be empty");
        Course course = courses.get(0);
        assertNotNull(course, "Course should not be null");
        Assert.assertEquals("COMP 1010", course.getCourseId());
        Assert.assertEquals("Introductory Computer Science 1", course.getCourseName());
        Assert.assertEquals("(Lab required) An introduction to computer programming using a procedural high level language. May not be held with COMP 1011, COMP 1012, or COMP 1013.", course.getCourseDescription());
    }
}