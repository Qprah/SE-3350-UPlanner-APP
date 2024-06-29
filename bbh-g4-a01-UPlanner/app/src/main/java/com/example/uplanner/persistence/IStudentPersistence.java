package com.example.uplanner.persistence;

import com.example.uplanner.objects.Student;

import java.util.List;

/**
 * Defines an interface for interacting with student data operations.
 */
public interface IStudentPersistence {

    int LOGIN_SUCCESS = 1;
    int INCORRECT_PASSWORD = 0;
    int EMAIL_NOT_FOUND = -1;
    int SIGNUP_SUCCESS = 1;
    int SUCCESS = 0;
    int ERROR = -1;

    /**
     * Retrieves a list of students sequentially.
     *
     * @return A list of Student objects.
     */
    List<Student> getStudentSequential();

    /**
     * Retrieves information about the specified student.
     *
     * @param currentStudent The student for which to retrieve information.
     * @return Information about the student.
     */
    String getStudentInformation(Student currentStudent);

    /**
     * Deletes the specified student from the data.
     *
     * @param currentStudent The student to delete.
     */
    void deleteStudent(Student currentStudent);

    /**
     * Authenticates a user with the provided username and password.
     *
     * @param username The username (email) of the user.
     * @param password The password of the user.
     * @return An integer representing the authentication result.
     */
    int authenticate(String username, String password);

    /**
     * Inserts a new student into the data.
     *
     * @param fullName The full name of the student.
     * @param email    The email address of the student.
     * @param password The password of the student.
     * @return An integer representing the insertion result.
     */
    int insertStudent(String fullName, String email, String password);

    /**
     * Checks if the given email address already exists in the system.
     *
     * @param email The email address to check.
     * @return True if the email exists, false otherwise.
     */
    boolean emailExisted(String email);

    /**
     * Retrieves the active student with the specified email.
     *
     * @param email The email address of the student to retrieve.
     * @return The active student with the given email.
     */
    Student getActiveStudent(String email);

    /**
     * Changes the password of the specified student.
     *
     * @param student      The student whose password is to be changed.
     * @param newPassword The new password.
     * @return An integer representing the password change result.
     */
    int changePassword(Student student, String newPassword);
}
