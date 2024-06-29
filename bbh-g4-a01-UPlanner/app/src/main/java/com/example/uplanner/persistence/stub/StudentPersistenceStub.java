/**
 * A stub implementation of the IStudentPersistence interface for testing purposes.
 * This class provides methods to interact with student-related data in a stub db environment.
 */
package com.example.uplanner.persistence.stub;

import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.IStudentPersistence;

import java.util.ArrayList;
import java.util.List;

public class StudentPersistenceStub implements IStudentPersistence {

    private static StudentPersistenceStub instance = null;
    private List<Student> students ;

    /**
     * Constructs a new instance of the StudentPersistenceStub class.
     * Initializes the list of students with dummy data.
     */
    public StudentPersistenceStub() {
        if (this.students == null) {
            this.students = new ArrayList<>();
            students.add(new Student("user","user@myumanitoba.ca", "12345678"));
            students.add(new Student("user2", "user2@myumanitoba.ca", "password2"));
        }
    }

    /**
     * Retrieves the singleton instance of the StudentPersistenceStub class.
     *
     * @return The singleton instance of StudentPersistenceStub.
     */
    public static StudentPersistenceStub getInstance() {
        if (instance == null) {
            instance = new StudentPersistenceStub();
        }
        return instance;
    }

    /**
     * Retrieves the information of the specified student.
     *
     * @param Student The student object.
     * @return The information of the student.
     */
    @Override
    public String getStudentInformation(Student Student){
        for (Student s:students){
            if (s.equals(Student))
                return "Display Name: " + s.getStudentFullName()
                        + ", Student number: " + s.getStudentNumber()
                        + ", Year: " + s.getYear();
        }
        return "Account not found!";
    }

    /**
     * Retrieves the active student based on the email.
     *
     * @param email The email of the student.
     * @return The active student object.
     */
    @Override
    public Student getActiveStudent(String email){
        for (Student s:students){
            if (s.getStudentEmail().equals(email))
                return s;
        }
        return null;
    }

    /**
     * Changes the password of the specified student.
     *
     * @param student The student object.
     * @param newPassword The new password.
     * @return The status of the password change operation.
     */
    @Override
    public int changePassword(Student student, String newPassword){
        for (Student s:students){
            if (s.equals(student)) {
                s.updatePassword(newPassword);
                return SUCCESS;
            }
        }
        return ERROR;
    }

    /**
     * Deletes the specified student from the list of students.
     *
     * @param currentStudent The student to be deleted.
     */
    @Override
    public void deleteStudent(Student currentStudent) {
        for (Student s:students){
            if (s.equals(currentStudent))
                students.remove(s);
        }
    }

    /**
     * Authenticates the user based on email and password.
     *
     * @param email The email of the user.
     * @param password The password of the user.
     * @return The authentication status.
     */
    @Override
    public int authenticate(String email, String password) {
        for (Student student : students) {
            if (student.getStudentEmail().equals(email)) {
                if (student.getStudentPassword().equals(password)) {
                    return LOGIN_SUCCESS;
                } else {
                    return INCORRECT_PASSWORD;
                }
            }
        }
        return EMAIL_NOT_FOUND;
    }

    /**
     * Checks if the given email exists in the list of students.
     *
     * @param email The email to be checked.
     * @return True if the email exists, false otherwise.
     */
    @Override
    public boolean emailExisted(String email){
        for (Student s:students){
            if (s.getStudentEmail().equals(email))
                return true;
        }
        return false;
    }

    /**
     * Inserts a new student into the list of students.
     *
     * @param fullName The full name of the student.
     * @param email The email of the student.
     * @param password The password of the student.
     * @return The status of the student insertion operation.
     */
    @Override
    public int insertStudent(String fullName, String email, String password) {
        // Signup successful
        students.add(new Student(fullName, email, password));
        return SIGNUP_SUCCESS;
    }

    /**
     * Retrieves the list of students sequentially.
     *
     * @return The list of students.
     */
    @Override
    public List<Student> getStudentSequential() {
        return students;
    }
}
