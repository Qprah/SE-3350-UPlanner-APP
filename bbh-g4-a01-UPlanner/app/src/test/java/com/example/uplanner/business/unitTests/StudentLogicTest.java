package com.example.uplanner.business.unitTests;

import com.example.uplanner.business.StudentLogic;
import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.objects.Student;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

public class StudentLogicTest {
    private StudentLogic studentLogic;
    private IStudentPersistence studentPersistence;

    @Before
    public void setup() {
        studentPersistence = mock(IStudentPersistence.class);
        studentLogic = new StudentLogic(studentPersistence);
    }

    @Test
    public void testLoginUser() {
        String email = "test@example.com";
        String password = "password";
        when(studentPersistence.authenticate(email, password)).thenReturn(1);
        int result = studentLogic.loginUser(email, password);
        assertEquals(1, result);
    }

    @Test
    public void testSignupUser() {
        String fullname = "Test User";
        String email = "test@example.com";
        String password = "password";
        when(studentPersistence.insertStudent(fullname, email, password)).thenReturn(1);
        int result = studentLogic.signupUser(fullname, email, password);
        assertEquals(1, result);
    }

    @Test
    public void testGetStudentInformation() {
        Student student = new Student("Test User", "test@example.com", "password");
        when(studentPersistence.getStudentInformation(student)).thenReturn("Test User");
        String result = studentLogic.getStudentInformation(student);
        assertEquals("Test User", result);
    }

    @Test
    public void testGetActiveStudent() {
        String email = "test@example.com";
        Student activeStudent = new Student("Test User", email, "password");
        when(studentPersistence.getActiveStudent(email)).thenReturn(activeStudent);
        Student result = studentLogic.getActiveStudent(email);
        assertEquals(activeStudent, result);
    }

    @Test
    public void testChangePassword() {
        Student student = new Student("Test User", "test@example.com", "password");
        String newPassword = "newPassword";
        when(studentPersistence.changePassword(student, newPassword)).thenReturn(1);
        int result = studentLogic.changePassword(student, newPassword);
        assertEquals(1, result);
    }

    @Test
    public void testIsEmailExistsWhenEmailExists() {
        String email = "test@example.com";
        when(studentPersistence.emailExisted(email)).thenReturn(true);
        assertTrue(studentLogic.isEmailExists(email));
    }

    @Test
    public void testIsEmailExistsWhenEmailDoesNotExist() {
        String email = "nonexistent@example.com";
        when(studentPersistence.emailExisted(email)).thenReturn(false);
        assertFalse(studentLogic.isEmailExists(email));
    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("Test User", "test@example.com", "password");
        // Since deleteStudent method does not return any value, so just verifying that it was called
        studentLogic.deleteStudent(student);
        verify(studentPersistence).deleteStudent(student);
    }

    @Test
    public void testInsertStudent() {
        String fullname = "Test User";
        String email = "test@example.com";
        String password = "password";
        // Since insertStudent method does not return any value, so just verifying just verify that it was called
        studentLogic.insertStudent(fullname, email, password);
        verify(studentPersistence).insertStudent(fullname, email, password);
    }

}