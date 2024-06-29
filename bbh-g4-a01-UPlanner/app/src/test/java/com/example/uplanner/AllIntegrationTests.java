package com.example.uplanner;

import com.example.uplanner.business.integrationTests.StudentLogicIT;
import com.example.uplanner.persistence.StudentPersistenceHSQLDBTest;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;


//integration test suite
@RunWith(Suite.class)
@Suite.SuiteClasses({
        StudentPersistenceHSQLDBTest.class,
        StudentLogicIT.class
})
public class AllIntegrationTests {
}
