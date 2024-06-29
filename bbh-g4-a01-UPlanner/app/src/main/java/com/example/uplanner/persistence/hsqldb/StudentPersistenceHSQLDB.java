/**
 * This class, StudentPersistenceHSQLDB, handles the persistence of student data using an HSQLDB database.
 * It implements the IStudentPersistence interface and interacts with the database to perform CRUD operations on student records.
 * The class contains methods to retrieve student information, insert new students, authenticate login credentials, change passwords, and delete student records.
 */

package com.example.uplanner.persistence.hsqldb;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import com.example.uplanner.objects.Course;
import com.example.uplanner.objects.Student;
import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.persistence.stuAndCourseUtility.CourseUtility;

public class StudentPersistenceHSQLDB implements IStudentPersistence {
    // Database file path
    private final String dbPath;
    // Instance of CoursePersistenceHSQLDB for interacting with course data
    public CoursePersistenceHSQLDB coursePersistenceHSQLDB;

    /**
     * Constructor to initialize StudentPersistenceHSQLDB with the provided database path.
     *
     * @param dbPath The path to the HSQLDB database file.
     */
    public StudentPersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
        // Initialize CoursePersistenceHSQLDB instance
        this.coursePersistenceHSQLDB = new CoursePersistenceHSQLDB(dbPath);
    }

    /**
     * Establishes a connection to the HSQLDB database.
     *
     * @return Connection object representing the connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /**
     * Constructs a Student object from the ResultSet obtained from a database query.
     *
     * @param rs ResultSet containing student data.
     * @return Constructed Student object.
     * @throws SQLException If a SQL exception occurs.
     */
    private Student fromResultSet(final ResultSet rs) throws SQLException {
        final String studentEmail = rs.getString("STUDENTEMAIL");
        final String studentPassword = rs.getString("PASSWORD");
        final String fullname = rs.getString("STUDENTNAME");
        final int studentNumber = rs.getInt("STUDENTNUMBER");
        final int year = rs.getInt("YEAR");
        final Set<String> courses = coursePersistenceHSQLDB.getActiveCourse(studentEmail);
        return new Student(fullname, studentEmail, studentPassword, studentNumber, year, courses);
    }

    /**
     * Retrieves a list of all students from the database sequentially.
     *
     * @return List of Student objects representing all students in the database.
     */
    @Override
    public List<Student> getStudentSequential() {
        final List<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final Statement st = c.createStatement();
            final ResultSet rs = st.executeQuery("SELECT * FROM STUDENTS");
            while (rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }
            rs.close();
            st.close();

            return students;
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Retrieves student information based on the provided Student object.
     *
     * @param currentStudent The Student object for which information is to be retrieved.
     * @return String containing the information of the specified student.
     */
    @Override
    public String getStudentInformation(Student currentStudent) {
        final List<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENTEMAIL = ?");
            st.setString(1, currentStudent.getStudentEmail());
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }
            rs.close();
            st.close();

            if (!students.isEmpty()) {
                return students.get(0).toString();
            } else {
                return null;
            }
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Deletes the record of the specified student from the database.
     *
     * @param currentStudent The Student object to be deleted.
     */
    @Override
    public void deleteStudent(Student currentStudent) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("DELETE FROM STUDENTS WHERE STUDENTEMAIL = ?");
            st.setString(1, currentStudent.getStudentEmail());
            st.executeUpdate();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Authenticates login credentials of a student.
     *
     * @param email The email of the student.
     * @param password The password provided for authentication.
     * @return An integer indicating the result of authentication.
     */
    @Override
    public int authenticate(String email, String password) {
        final List<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENTEMAIL = ?");
            st.setString(1, email);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }
            rs.close();
            st.close();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }

        if (students.isEmpty()) {
            return EMAIL_NOT_FOUND;
        } else if (students.get(0).getStudentPassword().equals(password)) {
            return LOGIN_SUCCESS;
        } else {
            return INCORRECT_PASSWORD;
        }
    }

    /**
     * Inserts a new student record into the database.
     *
     * @param fullName The full name of the student.
     * @param email The email of the student.
     * @param password The password of the student.
     * @return An integer indicating the result of insertion.
     */
    @Override
    public int insertStudent(String fullName, String email, String password) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(("INSERT INTO STUDENTS VALUES(?, ?, ?, ?, ?)"));
            st.setString(1, fullName);
            st.setString(2, email);
            st.setString(3, password);
            Student student = new Student(fullName, email, password);
            st.setInt(4, student.getStudentNumber());
            st.setInt(5, student.getYear());
            st.executeUpdate();
            st.close();
            // Insert courses for the student
            coursePersistenceHSQLDB.insertCourse(email, student.getCompletedCourses());
            return SIGNUP_SUCCESS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Checks if a given email already exists in the database.
     *
     * @param email The email to be checked.
     * @return True if the email exists in the database, false otherwise.
     */
    @Override
    public boolean emailExisted(String email) {
        final List<Student> students = new ArrayList<>();
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT * FROM STUDENTS WHERE STUDENTEMAIL = ?");
            st.setString(1, email);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Student student = fromResultSet(rs);
                students.add(student);
            }
            rs.close();
            st.close();
            // If the list is not empty, the email exists in the database
            return !students.isEmpty();
        } catch (final SQLException e) {
            throw new PersistenceException(e);
        }
    }

    /**
     * Retrieves the active student with the given email from the database.
     *
     * @param email The email of the student to be retrieved.
     * @return The active Student object with the specified email.
     */
    @Override
    public Student getActiveStudent(String email) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(("SELECT * FROM STUDENTS WHERE STUDENTEMAIL = ?"));
            st.setString(1, email);
            final ResultSet rs = st.executeQuery();
            while (rs.next()) {
                final Student student = fromResultSet(rs);
                return student;
            }
            rs.close();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return null;
    }

    /**
     * Changes the password of the specified student.
     *
     * @param student The Student object for which the password is to be changed.
     * @param newPassword The new password to be set.
     * @return An integer indicating the result of the password change operation.
     */
    @Override
    public int changePassword(Student student, String newPassword) {
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("UPDATE STUDENTS SET PASSWORD = ? WHERE STUDENTEMAIL = ?");
            st.setString(1, newPassword);
            st.setString(2, student.getStudentEmail());

            st.executeUpdate();
            st.close();
            return SUCCESS;
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

}
