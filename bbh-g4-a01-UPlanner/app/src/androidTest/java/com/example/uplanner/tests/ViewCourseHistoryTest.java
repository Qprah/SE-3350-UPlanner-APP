package com.example.uplanner.tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.intent.Intents;
import androidx.test.espresso.intent.matcher.IntentMatchers;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.core.app.ActivityScenario;

import com.example.uplanner.R;
import com.example.uplanner.presentation.CourseHistoryActivity;
import com.example.uplanner.presentation.MainPageActivity;
import com.example.uplanner.presentation.WelcomePageActivity;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

@RunWith(AndroidJUnit4.class)
public class ViewCourseHistoryTest {

    @Test
    public void testViewCourseHistory() throws InterruptedException {
        // Launch the WelcomePageActivity
        ActivityScenario<WelcomePageActivity> welcomePageScenario = ActivityScenario.launch(WelcomePageActivity.class);

        // Perform actions to log in
        Espresso.onView(ViewMatchers.withId(R.id.log_in_hyperlink_ek2)).perform(ViewActions.click());

        Intents.init();

        Espresso.onView(ViewMatchers.withId(R.id.input_box_email))
                .perform(ViewActions.typeText("user@myumanitoba.ca"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.input_box_password))
                .perform(ViewActions.typeText("12345678"), ViewActions.closeSoftKeyboard());
        Espresso.onView(ViewMatchers.withId(R.id.log_in_button_ek4)).perform(ViewActions.click());

        // Verify that MainPageActivity is launched
        intended(hasComponent(MainPageActivity.class.getName()));

        // Click on the course history button
        Espresso.onView(ViewMatchers.withId(R.id.course_history_button)).perform(ViewActions.click());

        Thread.sleep(700);

        // Verify that CourseHistoryActivity is launched
        intended(hasComponent(CourseHistoryActivity.class.getName()));

        // Click on the course name
        Espresso.onView(ViewMatchers.withText("INTRODUCTORY COMPUTER SCIENCE 1")).perform(ViewActions.click());

        Thread.sleep(700);
        // Click on the course name
        Espresso.onView(ViewMatchers.withText("INTRODUCTORY COMPUTER SCIENCE 2")).perform(ViewActions.click());

        Thread.sleep(700);

        // Here, you may want to perform further assertions to verify the content or behavior in the CourseHistoryActivity
        // For example, check if a specific view is displayed:
        Espresso.onView(ViewMatchers.withId(R.id.back_space_course_history))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Intents.release();
    }
}
