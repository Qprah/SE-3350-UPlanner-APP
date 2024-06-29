package com.example.uplanner.objects;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import junit.framework.TestCase;

import org.junit.Test;

public class StudentTest extends TestCase {

    @Test
    public void testStudent1() {
        Student student;

        System.out.println("\nStarting testStudent");

        student = new Student("test", "test@myumanitoba.ca", "password");
        assertNotNull(student);
        assertTrue("test".equals(student.getStudentFullName()));
        assertTrue("test@myumanitoba.ca".equals(student.getStudentEmail()));
        assertTrue("password".equals(student.getStudentPassword()));

        System.out.println("Finished testStudent");
    }

}
