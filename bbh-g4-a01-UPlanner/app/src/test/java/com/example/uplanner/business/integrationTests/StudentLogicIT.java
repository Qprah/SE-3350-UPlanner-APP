package com.example.uplanner.business.integrationTests;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertNull;
import static org.junit.Assert.assertTrue;

import com.example.uplanner.business.StudentLogic;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.persistence.hsqldb.StudentPersistenceHSQLDB;
import com.example.uplanner.utils.TestUtils;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;

public class StudentLogicIT {
    private StudentLogic studentLogic;
    private IStudentPersistence studentPersistence;
    private File tempDB;

    @Before
    public void setUp() throws IOException {
        // Copying the database file for testing purposes
        tempDB = TestUtils.copyDB();
        // Initializing the HSQLDB persistence with the temporary database
        studentPersistence = new StudentPersistenceHSQLDB(tempDB.getAbsolutePath().replace(".script", ""));
        // Creating an instance of StudentLogic with HSQLDB persistence
        studentLogic = new StudentLogic(studentPersistence);
    }

    @After
    public void tearDown() {
        // Cleaning up resources after each test
        studentLogic = null;
        studentPersistence = null;
        if (tempDB != null && tempDB.exists()) {
            tempDB.delete();
        }
    }

    @Test
    public void testLoginUser() {
        // Assuming the database has a student with the given email and password
        int result = studentLogic.loginUser("user@myumanitoba.ca", "12345678");
        // Asserting the login result
        assertEquals(IStudentPersistence.LOGIN_SUCCESS, result);
    }

    @Test
    public void testSignupUser() {
        // Assuming the signup is successful
        int result = studentLogic.signupUser("Rob Guderian", "robg@myumanitoba.ca", "password");
        // Asserting the signup result
        assertEquals(IStudentPersistence.SIGNUP_SUCCESS, result);
    }

    @Test
    public void testGetActiveStudent() {

        //now testing the getActiveStudent method
        Student testStudent = studentLogic.getActiveStudent("user@myumanitoba.ca");
        assertNotNull(testStudent);
        assertEquals(testStudent.getStudentEmail(),"user@myumanitoba.ca" );
    }

    @Test
    public void testChangePassword() {

        // Assuming the signup is successful
        int signUpresult = studentLogic.signupUser("Rob Guderian", "robg@myumanitoba.ca", "password");
        // Asserting the signup result
        assertEquals(IStudentPersistence.SIGNUP_SUCCESS, signUpresult);
        Student testStudent = studentLogic.getActiveStudent("robg@myumanitoba.ca");
        // test change password method now
        int result = studentLogic.changePassword(testStudent, "12345678");
        // Asserting the signup result
        assertEquals(IStudentPersistence.SUCCESS, result);
    }

    @Test
    public void testIsEmailExists() {

        // Assuming the signup is successful
        int signUpresult = studentLogic.signupUser("Rob Guderian", "robg@myumanitoba.ca", "password");
        // Asserting the signup result
        assertEquals(IStudentPersistence.SIGNUP_SUCCESS, signUpresult);
        Student testStudent = studentLogic.getActiveStudent("robg@myumanitoba.ca");
        // test change password method now
        Boolean result = studentLogic.isEmailExists("robg@myumanitoba.ca");
        // Asserting the signup result
        assertTrue(result);
    }

    @Test
    public void testInsertStudent() {

        String fullname = "Rob Guderian";
        String email = "robg@myumanitoba.ca";
        String password = "password";

        // Inserting the new student using StudentLogic
        studentLogic.insertStudent(fullname, email, password);

        // Retrieving the inserted student from the database
        Student insertedStudent = studentPersistence.getActiveStudent(email);

        assertNotNull(insertedStudent);
        assertEquals(fullname, insertedStudent.getStudentFullName());
        assertEquals(email, insertedStudent.getStudentEmail());
        assertEquals(password, insertedStudent.getStudentPassword());

    }

    @Test
    public void testDeleteStudent() {

        String fullname = "Rob Guderian";
        String email = "robg@myumanitoba.ca";
        String password = "password";

        // Inserting the new student using StudentLogic
        studentLogic.insertStudent(fullname, email, password);

        // Retrieving the inserted student from the database
        Student insertedStudent = studentPersistence.getActiveStudent(email);
        assertNotNull(insertedStudent);
        assertEquals(fullname, insertedStudent.getStudentFullName());
        assertEquals(email, insertedStudent.getStudentEmail());
        assertEquals(password, insertedStudent.getStudentPassword());
        studentLogic.deleteStudent(insertedStudent);

        Student deletedStudent = studentLogic.getActiveStudent(email);
        //verifying if the student is deleted or not
        assertNull(deletedStudent);

    }

    @Test
    public void testGetStudentInformation() {

        String fullname = "Rob Guderian";
        String email = "robg@myumanitoba.ca";
        String password = "password";

        Student testStudent = new Student(fullname,email, password);
        // Inserting the new student using StudentLogic
        studentLogic.insertStudent(fullname, email, password);

        // Retrieving student information using StudentLogic
        String studentInfo = studentLogic.getStudentInformation(testStudent);
        assertNotNull(studentInfo);
    }


}
