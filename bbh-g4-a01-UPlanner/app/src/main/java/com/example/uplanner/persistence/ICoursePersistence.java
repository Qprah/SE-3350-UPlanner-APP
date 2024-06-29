package com.example.uplanner.persistence;

import java.util.Set;

/**
 * Defines an Interface for persisting course data.
 */

public interface ICoursePersistence {
    public Set<String> getActiveCourse(String email);
    public void insertCourse();

}
