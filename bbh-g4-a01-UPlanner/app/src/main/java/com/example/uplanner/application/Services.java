package com.example.uplanner.application;

import com.example.uplanner.persistence.IStudentPersistence;
import com.example.uplanner.persistence.hsqldb.StudentPersistenceHSQLDB;
import com.example.uplanner.persistence.stub.StudentPersistenceStub;

public class Services {
    private static IStudentPersistence studentPersistence = null;

    // Default database name
    private static String dbName = "ADB";

    /**
     * Sets the path name for the database.
     * @param name The name of the database to be set.
     */
    public static void setDBPathName(final String name) {
        try {
            // Loads the HSQLDB JDBC driver
            Class.forName("org.hsqldb.jdbcDriver").getDeclaredConstructor().newInstance();
        } catch (Exception e) {
            // Log the exception or handle it as appropriate for your application
            System.out.println(e.getMessage());
        }
        // Sets the database name
        dbName = name;
    }

    /**
     * Gets the current database path name.
     * @return The current database path name.
     */
    public static String getDBPathName() {
        return dbName;
    }

    /**
     * Gets the instance of StudentPersistence.
     * @return An instance of StudentPersistence.
     */
    public static synchronized IStudentPersistence getStudentPersistence() {
        if (studentPersistence == null) {
            // Uncomment one of the following lines to select between Stub database and HSQLDB
            //studentPersistence = new StudentPersistenceStub(); // For using the Stub database
            studentPersistence = new StudentPersistenceHSQLDB(dbName); // For using the HSQLDB
        }
        return studentPersistence;
    }
}
