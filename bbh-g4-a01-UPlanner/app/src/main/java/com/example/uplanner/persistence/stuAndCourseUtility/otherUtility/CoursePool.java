/**
 * This utility class provides a pool of comp courses available in the application.
 */

package com.example.uplanner.persistence.stuAndCourseUtility.otherUtility;

import com.example.uplanner.objects.Course;

import java.util.ArrayList;
import java.util.List;

public class CoursePool {
    private static List<Course> courses;

    public CoursePool(){
        letsSwim();
    }

    private void letsSwim(){
        courses = new ArrayList<>();
        courses.add(new Course("COMP 1010", "Introductory Computer Science 1", "(Lab required) An introduction to computer programming using a procedural high level language. May not be held with COMP 1011, COMP 1012, or COMP 1013.", ""));
        courses.add(new Course("COMP 1012", "Computer Programming for Scientists and Engineers", "(Lab required) An introduction to computer programming suitable for solving problems in science and engineering. Students will implement algorithms for numerical processing, statistical analysis and matrix operations. May not be held with COMP 1010, COMP 1011, or COMP 1013.", ""));
        courses.add(new Course("COMP 1020", "Introductory Computer Science 2", "(Lab required) More features of a procedural language, elements of programming. May not be held with COMP 1021.", "COMP 1010"));
        courses.add(new Course("COMP 1500", "Computing: Ideas and Innovation", "An introduction to the topics of Computer Science and problem solving. Students will learn concepts in computer programming. May not be used to fulfill computer science requirements in a Computer Science Honours, Joint Honours, Major, General or Minor program. May not be taken once in a declared Computer Science Honours, Joint Honours, Major, General or Minor program. May be used as an elective if taken prior to entry.", ""));
        courses.add(new Course("COMP 1600", "Navigating Your Digital World", "Topics related to digital society such as security, encryption and data storage, issues of social and ethical importance, and current events. May not be used to fulfill computer science requirements in a Computer Science Honours, Joint Honours, Major, General or Minor program. May not be taken once in a declared Computer Science Honours, Joint Honours, Major, General or Minor program. May be used as an elective if taken prior to entry. May not be held with the former COMP 1270.", ""));
        courses.add(new Course("COMP 2080", "Analysis of Algorithms", "Methods of analyzing the time and space requirements of algorithms. Average case and worst case analysis. Models of computation.", ""));
        courses.add(new Course("COMP 2130", "Discrete Mathematics for Computer Science", "An introduction to the set theory, logic, integers, combinatorics and functions for today's computer scientists.", "COMP 1020"));
        courses.add(new Course("COMP 2140", "Data Structures and Algorithms", "Introduction to the representation and manipulation of data structures. Topics will include lists, stacks, queues, trees, and graphs. May not be held with the former COMP 2061.", "COMP 1020"));
        courses.add(new Course("COMP 2160", "Programming Practices", "Introduction to issues involved in real-world computing. Topics will include memory management, debugging, compilation, performance, and good programming practices.", "COMP 1020"));
        courses.add(new Course("COMP 2150", "Object Orientation", "Design and development of object-oriented software. Topics will include inheritance, polymorphism, data abstraction and encapsulation. Examples will be drawn from several programming languages.", "COMP 2140, COMP 2160"));
        courses.add(new Course("COMP 2190", "Introduction to Scientific Computing", "An applied computational course introducing topics such as approximation by polynomials, solution of non-linear equations, linear systems, simulation and computational geometry. May not hold with COMP 2191.", "COMP 1020"));
        courses.add(new Course("COMP 2280", "Introduction to Computer Systems", "Data representation and manipulation, machine-level representation of programs, assembly language programming, and basic computer architecture. Not available to students who have previously completed ECE 3610.", "COMP 2140, COMP 2160"));
        courses.add(new Course("COMP 3010", "Distributed Computing", "An introduction to the development of client server and peer-to-peer systems through web applications, distributed programming models, and distributed algorithms.", "COMP 2150, COMP 2080"));
        courses.add(new Course("COMP 3020", "Human-Computer Interaction 1", "Human-computer interaction: human factors and usability, user-centered design, prototyping, usability evaluation.", "COMP 2150"));
        courses.add(new Course("COMP 3030", "Automata Theory and Formal Languages", "An introduction to automata theory, grammars, formal languages and their applications. Topics: finite automata, regular expressions and their properties; context-free grammars, pushdown automata and properties of context-free languages; Turing machines and their properties.", "COMP 2080"));
        courses.add(new Course("COMP 3040", "Technical Communication in Computer Science", "This course is designed to help students become more effective and confident writers in the context of the computing profession. Students will be introduced to a broad range of written and oral presentation styles used in the computing workplace.", "COMP 2150, COMP 2280, COMP 2080"));
        courses.add(new Course("COMP 3170", "Analysis of Algorithms and Data Structures", "Fundamental algorithms for sorting, searching, storage management, graphs, databases and computational geometry. Correctness and analysis of those algorithms using specific data structures. An introduction to lower bounds and intractability.", "COMP 2080"));
        courses.add(new Course("COMP 3190", "Introduction to Artificial Intelligence", "Principles of artificial intelligence: problem solving, knowledge representation and manipulation; the application of these principles to the solution of 'hard' problems.", "COMP 2150"));
        courses.add(new Course("COMP 3290", "Introduction to Compiler Construction", "Introduction to the standard compiler phases: scanning, parsing, symbol-table management, code generation, and code optimization. The emphasis is on the simpler techniques for compiler construction such as recursive descent.", "COMP 2140, COMP 2280"));
        courses.add(new Course("COMP 3380", "Databases Concepts and Usage", "An introduction to database systems including the relational, hierarchical, network and entity-relationship models with emphasis on the relational model and SQL.", "COMP 2150"));
        courses.add(new Course("COMP 3350", "Software Engineering 1", "Introduction to software engineering. Software life cycle models, system and software requirements analysis, specifications, software design, testing and maintenance, software quality.", "COMP 3020, COMP 3380"));
        courses.add(new Course("COMP 3370", "Computer Organization", "Principles of computer systems architecture, organization and design. Performance, instruction sets, processors, input/output, memory hierarchies.", "COMP 2280"));
        courses.add(new Course("COMP 3430", "Operating Systems", "Operating systems, their design, implementation, and usage. COMP 2160 is recommended for Computer Engineering Students.", "COMP 2280, COMP 2080"));
        courses.add(new Course("COMP 3440", "Programming Language Concepts", "An introduction to major concepts involved in the design of modern programming languages. The imperative, functional, and logical families and differences between them. Facilities for high level data and control structures, modular programming, data typing, and other topics will be covered.", "COMP 2140"));
        courses.add(new Course("COMP 3490", "Computer Graphics 1", "An introductory course in computer graphics including topics such as raster graphics, two and three dimensional transforms, and simple rendering.", "COMP 2150"));
        courses.add(new Course("COMP 4020", "Human-Computer Interaction 2", "Advanced issues in the field of human-computer interaction. Topics will be selected from current research and development issues in the field of HCI.", "COMP 3020"));
        courses.add(new Course("COMP 4050", "Project Management", "Introduction to the issues involved in managing large, complex software projects.", "COMP 3350"));
        courses.add(new Course("COMP 4140", "Introduction to Cryptography and Crypto-systems", "Description and analysis of cryptographic methods used in the authentication and protection of data. Classical cryptosystems and cryptoanalysis, the Advanced Encryption Standard (AES) and Publickey cryptosystems.", "COMP 3170"));
        courses.add(new Course("COMP 4180", "Intelligent Mobile Robotics", "Topics include artificial intelligence, computer vision, human-robot interaction, and multi-robot systems. These abstract components are grounded in the problem of developing a team of intelligent mobile robots. All topics are covered with specific emphasis on applied problems, e.g. real-time performance. May not be held with COMP 4060 when titled “Mobile Robotics.”", "COMP 2160, COMP 3190"));
        courses.add(new Course("COMP 4190", "Artificial Intelligence", "Reasoning with temporal knowledge; causal reasoning; plausible reasoning; nonmonotonic reasoning; abductive reasoning.", "COMP 3190"));
        courses.add(new Course("COMP 4300", "Computer Networks", "This course examines the principles of computer networks, including network architectures, algorithms, protocols, and performance. May not be held with the former COMP 3720 or the former COMP 4720 or ECE 3700.", "COMP 3010, COMP 3430"));
        courses.add(new Course("COMP 4340", "Graph Theory Algorithms 1", "Spanning trees, connectivity, planar graphs, directed graphs, networks, colouring problems and tours are studied and their applications to computer science will be highlighted.", "COMP 3170"));
        courses.add(new Course("COMP 4350", "Software Engineering 2", "Advanced treatment of software development methods. Topics will be selected from requirements gathering, design methodologies, prototyping, software verification and validation.", "COMP 3010, COMP 3350, COMP 3380"));
        courses.add(new Course("COMP 4360", "Machine Learning", "Learning strategies; evaluation of learning; learning in symbolic systems; neural networks, genetic algorithms. May not be held with ECE 4450.", "COMP 3190"));
        courses.add(new Course("COMP 4380", "Database Implementation", "Implementation of modern database systems including query modification/optimization, recovery, concurrency, integrity, and distribution.", "COMP 3010, COMP 3380, COMP 3430"));
        courses.add(new Course("COMP 4420", "Advanced Design and Analysis of Algorithms", "Algorithm design with emphasis on formal techniques in analysis and proof of correctness. Computational geometry, pattern matching, scheduling, numeric algorithms, probabilistic algorithms, approximation algorithms and other topics.", "COMP 3170"));
        courses.add(new Course("COMP 4430", "Operating Systems 2", "Design and implementation of modern operating systems. Detailed analysis of an open source modern operating system and hands-on experience with its kernel and major components.", "COMP 2160, COMP 3430"));
        courses.add(new Course("COMP 4490", "Computer Graphics 2", "Methods in computer graphics including topics such as representation of curves and surfaces, viewing in three dimensions, and colour models.", "COMP 3490"));
        courses.add(new Course("COMP 4510", "Introduction to Parallel Computation", "An overview of the architectures of current parallel processors and the techniques used to program them. Not to be held with ECE 4530.", "COMP 3370, COMP 3430"));
        courses.add(new Course("COMP 4550", "Real-Time Systems", "An introduction to the theory and practice of real-time systems. Topics include the design of real-time systems, scheduling, event based processing, and real-time control. This course may not be held for credit if a student has previously completed both of ECE 4240 and ECE 3760.", "COMP 3430, COMP 3370"));
        courses.add(new Course("COMP 4580", "Computer Security", "(Lab Required) Computer security and information management. This course will examine state-of-the-art knowledge about the issues relevant to data and computer security.", "COMP 3430, COMP 3010"));
        courses.add(new Course("COMP 4620", "Professional Practice in Computer Science", "Background and rationale to view Computer Science in a professional context. Examination of professional ethics, intellectual property, and privacy considerations important to Computer Scientists. May not be held with the former COMP 3620. This course is restricted to students in a Computer Science Major, Honours, or Joint Honours program.", "COMP 3020, COMP 3380"));
        courses.add(new Course("COMP 4690", "Computer Systems and Architecture", "Investigation of today's modern computer architecture and system design concepts, including requirements, specifications, and implementation. Instruction sets, instruction-level parallelism, speculative execution, multi-threaded architectures, memory hierarchy, multiprocessors, storage design and implementation, and interconnection networks.", "COMP 3370"));
        courses.add(new Course("COMP 4710", "Introduction to Data Mining", "Introduction to data mining concepts and their applications.", "COMP 3380"));
        courses.add(new Course("COMP 4740", "Advanced Databases", "Parallel, distributed, object-oriented, object-relational, and XML databases; other emerging database technologies.", "COMP 3380"));
        courses.add(new Course("COMP 4820", "Bioinformatics", "An exploration of bioinformatics problems through the lens of Computer Science. Students will discover novel data structures, algorithmic tools, and techniques used to manage, index, and analyze large amounts of data. May not be held with the former COMP 3820.", "COMP 3170"));
    }

    public List<Course> getCourses() {
        return courses;
    }
}
