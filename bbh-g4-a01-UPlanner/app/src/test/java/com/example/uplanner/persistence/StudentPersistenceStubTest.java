package com.example.uplanner.persistence;

import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.stub.StudentPersistenceStub;

import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class StudentPersistenceStubTest {
    private StudentPersistenceStub studentPersistenceStub;

    @Before
    public void setUp() {
        studentPersistenceStub = StudentPersistenceStub.getInstance();
    }

    @Test
    public void testGetStudentInformation() {
        Student student = new Student("user","user@myumanitoba.ca", "12345678");
        String info = studentPersistenceStub.getStudentInformation(student);
        assertNotNull("Student info should not be null", info);
    }

    @Test
    public void testGetActiveStudent() {
        Student student = studentPersistenceStub.getActiveStudent("user@myumanitoba.ca");
        assertNotNull("Active student should not be null", student);
    }

    @Test
    public void testChangePassword() {
        Student student = new Student("user","user@myumanitoba.ca", "12345678");
        int result = studentPersistenceStub.changePassword(student, "newPassword");
        assertEquals("Password change should be successful", StudentPersistenceStub.SUCCESS, result);

    }

    @Test
    public void testAuthenticate() {
        int result = studentPersistenceStub.authenticate("user@myumanitoba.ca", "12345678");
        assertEquals("Authentication should be successful", StudentPersistenceStub.LOGIN_SUCCESS, result);
    }

    @Test
    public void testEmailExisted() {
        boolean result = studentPersistenceStub.emailExisted("user@myumanitoba.ca");
        assertTrue("Email should exist", result);
    }

    @Test
    public void testInsertStudent() {
        int result = studentPersistenceStub.insertStudent("newUser", "newUser@myumanitoba.ca", "newPassword");
        assertEquals("Insertion should be successful", StudentPersistenceStub.SIGNUP_SUCCESS, result);
    }

    @Test
    public void testGetStudentSequential() {
        List<Student> students = studentPersistenceStub.getStudentSequential();
        assertNotNull("Students list should not be null", students);
        assertFalse("Students list should not be empty", students.isEmpty());
    }
}