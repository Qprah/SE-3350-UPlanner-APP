/**
 * StudentLogic class handles the business logic related to student operations such as
 * authentication, user registration, password management, and retrieval of student information.
 * It interacts with the persistence layer to perform these operations.
 */

package com.example.uplanner.business;

import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.objects.Student;
import com.example.uplanner.application.Services;

public class StudentLogic {
    private final IStudentPersistence studentPersistence;

    // Constructor with dependency injection
    public StudentLogic(final IStudentPersistence studentPersistence) {
        this.studentPersistence = studentPersistence;
    }

    // Default constructor using the Service locator pattern
    public StudentLogic() {
        this.studentPersistence = Services.getStudentPersistence();
    }

    // Method to authenticate a user login
    public int loginUser(String email, String password) {
        return studentPersistence.authenticate(email, password);
    }

    // Method to retrieve the active student based on email
    public Student getActiveStudent(String email){
        return studentPersistence.getActiveStudent(email);
    }

    // Method to change the password of a student
    public int changePassword(Student s, String newPassword){
        return studentPersistence.changePassword(s,newPassword);
    }

    // Method to sign up a new user
    public int signupUser(String fullName, String email, String password) {
        return studentPersistence.insertStudent(fullName, email, password);
    }

    // Method to check if an email already exists in the system
    public boolean isEmailExists(String email) {
        return studentPersistence.emailExisted(email);
    }

    // Method to retrieve information about a student
    public String getStudentInformation(Student student) {
        return studentPersistence.getStudentInformation(student);
    }

    // Method to delete a student
    public void deleteStudent(Student student) {
        studentPersistence.deleteStudent(student);
    }

    // Method to insert a new student
    public void insertStudent(String fullName, String email, String password) {
        studentPersistence.insertStudent(fullName, email, password);
    }
}
