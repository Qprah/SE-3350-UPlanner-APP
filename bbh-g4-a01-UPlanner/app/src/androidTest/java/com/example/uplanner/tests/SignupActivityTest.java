package com.example.uplanner.tests;

import androidx.test.espresso.Espresso;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.espresso.matcher.ViewMatchers;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.espresso.intent.Intents;
import androidx.test.core.app.ActivityScenario;

import com.example.uplanner.R;
import com.example.uplanner.presentation.MainPageActivity;
import com.example.uplanner.presentation.WelcomePageActivity;

import static androidx.test.espresso.intent.Intents.intended;
import static androidx.test.espresso.intent.matcher.IntentMatchers.hasComponent;

import org.junit.Test;
import org.junit.runner.RunWith;

@RunWith(AndroidJUnit4.class)
public class SignupActivityTest {

    @Test
    public void testSuccessfulSignup() throws InterruptedException {

        ActivityScenario<WelcomePageActivity> welcomePageScenario = ActivityScenario.launch(WelcomePageActivity.class);

        Espresso.onView(ViewMatchers.withId(R.id.new_account_button)).perform(ViewActions.click());

        Intents.init();

        Espresso.onView(ViewMatchers.withId(R.id.input_box_fullname))
                .perform(ViewActions.typeText("Test User"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.input_box_email))
                .perform(ViewActions.typeText("testuser@myumanitoba.ca"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.input_box_password))
                .perform(ViewActions.typeText("12345678"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.input_box_confirm_password))
                .perform(ViewActions.typeText("12345678"), ViewActions.closeSoftKeyboard());


        Espresso.onView(ViewMatchers.withId(R.id.new_account_button)).perform(ViewActions.click());


        intended(hasComponent(MainPageActivity.class.getName()));

        Espresso.onView(ViewMatchers.withId(R.id.user_profile_button))
                .check(ViewAssertions.matches(ViewMatchers.isDisplayed()));

        Espresso.onView(ViewMatchers.withId(R.id.user_profile_button)).perform(ViewActions.click());

        Thread.sleep(1000);

        Intents.release();
    }
}
