package com.example.uplanner;

import com.example.uplanner.business.unitTests.StudentLogicTest;
import com.example.uplanner.objects.CourseTest;
import com.example.uplanner.objects.StudentTest;
import com.example.uplanner.persistence.StudentPersistenceStubTest;
import com.example.uplanner.persistence.stuAndCourseUtility.CourseUtilityTest;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CoursePoolTest;
import com.example.uplanner.persistence.stuAndCourseUtility.otherUtility.CurrentCourseSelectorTest;
import com.example.uplanner.persistence.stuAndCourseUtility.StuUtilityTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//unit tests suite
@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentLogicTest.class,
        StudentTest.class,
        CourseTest.class,
        CoursePoolTest.class,
        CurrentCourseSelectorTest.class,
        CourseUtilityTest.class,
        StuUtilityTest.class,
        StudentPersistenceStubTest.class,
})
public class AllUnitTests {
    // This class remains empty, it is used only as a holder for the above annotations
}