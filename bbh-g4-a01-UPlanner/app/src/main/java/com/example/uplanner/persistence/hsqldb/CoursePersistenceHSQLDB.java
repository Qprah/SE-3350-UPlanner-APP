/**
 * This class-> CoursePersistenceHSQLDB- handles the persistence of course data using an HSQLDB database.
 */

package com.example.uplanner.persistence.hsqldb;

import androidx.annotation.NonNull;

import com.example.uplanner.objects.Course;
import com.example.uplanner.persistence.ICoursePersistence;
import com.example.uplanner.persistence.stuAndCourseUtility.CourseUtility;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.Set;

public class CoursePersistenceHSQLDB {
    // Database file path
    private final String dbPath;

    /**
     * Constructor to initialize CoursePersistenceHSQLDB with the provided database path.
     *
     * @param dbPath The path to the HSQLDB database file.
     */
    public CoursePersistenceHSQLDB(final String dbPath) {
        this.dbPath = dbPath;
    }

    /*
     * Establishes a connection to the HSQLDB database.
     *
     * @return Connection object representing the connection to the database.
     * @throws SQLException If a database access error occurs.
     */
    private Connection connection() throws SQLException {
        return DriverManager.getConnection("jdbc:hsqldb:file:" + dbPath + ";shutdown=true", "SA", "");
    }

    /**
     * Inserts courses for a student into the database.
     *
     * @param email The email of the student.
     * @param courses A set of course codes.
     */
    public void insertCourse(String email, Set<String> courses){
        // Initialize a set to store courses along with their grades
        Set<String> coursesWithGrades = getStrings(courses);

        // Insert courses into the database
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement(("INSERT INTO STUDENTCOURSES VALUES(?, ?)"));
            StringBuilder courseList = new StringBuilder();
            for (String course:coursesWithGrades)
                courseList.append(course).append(",");
            st.setString(1, email);
            st.setString(2, courseList.toString());
            st.executeUpdate();
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    @NonNull
    private static Set<String> getStrings(Set<String> courses) {
        Set<String> coursesWithGrades = new HashSet<>();

        // Iterate through the provided course codes
        for(String code: courses){
            // Find the corresponding course object and add it to the set if found
            for (Course course : CourseUtility.getCp().getCourses()) {
                if (course.getCourseId().equals(code)) {
                    coursesWithGrades.add(code + "-" + course.getLetterGrade());
                    break; // Break the loop since the course is found
                }
            }
        }
        return coursesWithGrades;
    }

    /**
     * Retrieves active courses for a student from the database.
     *
     * @param email The email of the student.
     * @return A set of active course codes.
     */
    public Set<String> getActiveCourse(String email) {
        Set<String> courses = new HashSet<>();
        // Retrieve active courses for the student from the database
        try (final Connection c = connection()) {
            final PreparedStatement st = c.prepareStatement("SELECT COURSEID FROM STUDENTCOURSES WHERE STUDENTEMAIL = ?");
            st.setString(1, email);
            final ResultSet rs = st.executeQuery();
            // Convert the result set to a set of course codes
            while (rs.next()) {
                courses = convertStringToSet(rs.getString("COURSEID"));
            }
            st.close();
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
        return courses;
    }

    /**
     * Converts a comma-separated string of course codes into a set of strings.
     *
     * @param input The comma-separated string of course codes.
     * @return A set of course codes.
     */
    private static Set<String> convertStringToSet(String input) {
        Set<String> resultSet = new HashSet<>();
        String[] courses = input.split(",");
        for (String course : courses) {
            String trimmedCourse = course.trim();
            if (!trimmedCourse.isEmpty()) {
                resultSet.add(trimmedCourse);
            }
        }
        return resultSet;
    }
}
