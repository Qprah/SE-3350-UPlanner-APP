/**
 * A stub implementation of the ICoursePersistence interface for testing purposes.
 * This class provides methods to interact with course-related data in a stub db environment.
 */

package com.example.uplanner.persistence.stub;

import com.example.uplanner.business.CourseGraph;
import com.example.uplanner.objects.Course;
import com.example.uplanner.persistence.ICoursePersistence;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePool;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

public class CoursePersistenceStub implements ICoursePersistence, Serializable {

    private static CoursePersistenceStub instance = null;
    private static List<Course> courses = null;
    private static CourseGraph graph = null;

    /**
     * Constructs a new instance of the CoursePersistenceStub class.
     * Initializes the list of courses and constructs the course graph.
     */
    public CoursePersistenceStub() {
        if (courses == null) {
            courses = new ArrayList<>();
            CoursePool extractCourses = new CoursePool();
            courses = extractCourses.getCourses();
            constructGraph();
        }
    }

    /**
     * Retrieves the singleton instance of the CoursePersistenceStub class.
     *
     * Only used when In services class the stub is uncommented and hsqldb is commented
     *
     * @return The singleton instance of CoursePersistenceStub.
     */
    private static CoursePersistenceStub getInstance() {
        if (instance == null) {
            instance = new CoursePersistenceStub();
        }
        return instance;
    }

    /**
     * Constructs the course graph based on the list of courses.
     */
    private void constructGraph() {
        graph = new CourseGraph();
        for (Course c : courses) {
            graph.addVertex(c.getCourseId());
            for (String s : c.getPrerequisites()) {
                if (!c.getPrerequisites().isEmpty()) {
                    graph.addEdge(c.getCourseId(), s);
                }
            }
        }
    }

    /**
     * Retrieves the course graph.
     *
     * @return The course graph.
     */
    public CourseGraph getGraph() {
        return graph;
    }

    /**
     * Retrieves active courses for the specified email.
     *
     * @param email The email address of the user.
     * @return A set of active courses.
     */
    @Override
    public Set<String> getActiveCourse(String email) {
        return null;
    }

    /**
     * dummy method
     */
    @Override
    public void insertCourse() {}
}
