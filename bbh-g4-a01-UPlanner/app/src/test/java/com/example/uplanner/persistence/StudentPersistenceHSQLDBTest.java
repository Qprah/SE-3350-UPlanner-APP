package com.example.uplanner.persistence;


import static com.example.uplanner.persistence.IStudentPersistence.LOGIN_SUCCESS;

import com.example.uplanner.application.Services;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.IStudentPersistence;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

import java.io.File;
import java.io.IOException;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;


import com.example.uplanner.persistence.hsqldb.StudentPersistenceHSQLDB;
import com.example.uplanner.utils.TestUtils;

import static org.junit.Assert.*;

public class StudentPersistenceHSQLDBTest {
    private StudentPersistenceHSQLDB studentPersistence;

    private File tempDB;

    @Before
    public void setUp() throws IOException {
        this.tempDB= TestUtils.copyDB();
        this.studentPersistence = new StudentPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
    }

//    @Test
//    public void testGetStudentSequential() {
//        List<Student> students = studentPersistence.getStudentSequential();
//        Student student = null;
//        if (!students.isEmpty()) {
//            student = students.get(0); // get the first student
//        }
//        assertNotNull("first sequential student should not be null", student);
//        assertTrue("Gary Chalmers".equals(student.getStudentFullName()));
//    }

    @Test
    public void testDeleteStudent() {
        Student student = new Student("Test Student", "test@myumanitoba.ca", "password");
        studentPersistence.insertStudent("Test Student", "test@myumanitoba.ca", "password");
        studentPersistence.deleteStudent(student);
        String studentInfo = studentPersistence.getStudentInformation(student);

        assertNull("Deleted student should not be found", studentInfo);
    }

    @Test
    public void testAuthenticate() {
        int result = studentPersistence.authenticate("gary@myumanitoba.ca", "password");
        assertEquals(LOGIN_SUCCESS, result);
    }

    @Test
    public void testInsertStudent() {
        String fullname = "Test Student";
        String email = "test@myumanitoba.ca";
        String password = "password";
        Student student = new Student(fullname, email, password);

        int result = studentPersistence.insertStudent(fullname, email, password);

        assertEquals(IStudentPersistence.SIGNUP_SUCCESS, result);
        String studentInfo = studentPersistence.getStudentInformation(student);
        assertNotNull(studentInfo);
        assertTrue(studentInfo.contains(fullname));
        assertTrue(studentInfo.contains(email));
    }

    @Test
    public void testEmailExisted() {
        String email = "test@myumanitoba.ca";
        Student student = new Student("Test Student", email, "password");
        studentPersistence.insertStudent(student.getStudentFullName(), student.getStudentEmail(), student.getStudentPassword());
        boolean result = studentPersistence.emailExisted(email);
        assertTrue(result);
    }

    @After
    public void tearDown() {
        if (tempDB != null && tempDB.exists()) {
            tempDB.delete();
        }
    }
}
