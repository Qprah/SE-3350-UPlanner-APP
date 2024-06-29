/**
 * CourseGraph represents a directed graph of courses and their prerequisite relationships.
 * It provides methods to add courses and prerequisite relationships, as well as to retrieve
 * completed courses and next available courses based on the courses currently being taken.
 * ------------------------------------------------------------------------------------------
 * [The graph is represented using an adjacency list for efficient retrieval of prerequisite
 * information and next available courses. Additionally, a reverse adjacency list is used to
 * facilitate finding completed courses and their connections.]
 * */

package com.example.uplanner.business;

import java.util.*;

public class CourseGraph {
    // Adjacency list representation of the graph
    private final Map<String, Set<String>> adjacencyList;
    // Reverse adjacency list representation of the graph
    private final Map<String, Set<String>> adjacencyListRev;

    // Constructor to initialize the graph
    public CourseGraph() {
        adjacencyList = new HashMap<>();
        adjacencyListRev = new HashMap<>();
    }

    /**
     * Adds a vertex (course) to the graph.
     * @param course The course to be added.
     */
    public void addVertex(String course) {
        adjacencyList.putIfAbsent(course, new HashSet<>());
        adjacencyListRev.putIfAbsent(course, new HashSet<>());
    }

    /**
     * Adds an edge (prerequisite relationship) to the graph.
     * @param course The course to be taken.
     * @param prerequisite The prerequisite course required for 'course'.
     */
    public void addEdge(String course, String prerequisite) {
        Objects.requireNonNull(adjacencyListRev.get(prerequisite)).add(course);
        Objects.requireNonNull(adjacencyList.get(course)).add(prerequisite);
    }

    /**
     * Retrieves all courses that are already completed.
     * @param currentCourses The courses currently being taken.
     * @return A set containing all completed courses.
     */
    public Set<String> getAllDoneCourses(Set<String> currentCourses) {
        Set<String> allDoneCourses = new HashSet<>();
        Set<String> visited = new HashSet<>();
        for (String course : currentCourses) {
            dfs(course, visited, allDoneCourses, currentCourses);
        }
        return allDoneCourses;
    }

    /**
     * Depth-first search (DFS) to traverse the graph and find completed courses.
     * @param course The current course being checked.
     * @param visited Set of visited courses to prevent cycles.
     * @param allDoneCourses Set to store all completed courses.
     * @param currentCourses Set of courses currently being taken.
     */
    private void dfs(String course, Set<String> visited, Set<String> allDoneCourses, Set<String> currentCourses) {
        if (visited.contains(course)) {
            return;
        }
        visited.add(course);
        Set<String> prerequisites = adjacencyList.get(course);
        if (prerequisites != null) {
            for (String prerequisite : prerequisites) {
                dfs(prerequisite, visited, allDoneCourses, currentCourses);
            }
        }
        if (!currentCourses.contains(course)) {
            allDoneCourses.add(course);
        }
    }

    /**
     * Retrieves the next available courses given the current courses being taken.
     * @param currentCourses The courses currently being taken.
     * @return A set containing the next available courses.
     */
    public Set<String> getNextAvailableCourses(Set<String> currentCourses) {
        Set<String> completedOrCurrentCourses = new HashSet<>(currentCourses);
        completedOrCurrentCourses.addAll(getAllDoneCourses(currentCourses));

        Set<String> nextAvailableCourses = new HashSet<>();

        for (String course : completedOrCurrentCourses) {
            Set<String> connectedCourses = adjacencyListRev.getOrDefault(course, Collections.emptySet());
            assert connectedCourses != null;
            for (String connectedCourse : connectedCourses) {
                Set<String> prerequisites = adjacencyList.get(connectedCourse);
                if (prerequisites != null && completedOrCurrentCourses.containsAll(prerequisites)) {
                    nextAvailableCourses.add(connectedCourse);
                }
            }
        }

        // Remove courses already completed or in current courses
        nextAvailableCourses.removeAll(completedOrCurrentCourses);

        return nextAvailableCourses;
    }
}
