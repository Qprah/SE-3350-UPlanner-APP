package com.example.uplanner;

import com.example.uplanner.tests.LoginActivityTest;
import com.example.uplanner.tests.PasswordChangeTest;
import com.example.uplanner.tests.SignOutTest;
import com.example.uplanner.tests.SignupActivityTest;
import com.example.uplanner.tests.ViewCourseHistoryTest;
import com.example.uplanner.tests.ViewProfessorRatingTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;

//System tests suite
@RunWith(Suite.class)
@Suite.SuiteClasses({
        LoginActivityTest.class,
        PasswordChangeTest.class,
        SignOutTest.class,
        SignupActivityTest.class,
        ViewCourseHistoryTest.class,
        ViewProfessorRatingTest.class,
})

public class AllSystemTests {
    // This class remains empty, it is used only as a holder for the above annotations
}