package com.njb.todue;

import android.content.Context;

import androidx.test.core.app.ActivityScenario;
import androidx.test.espresso.ViewInteraction;
import androidx.test.espresso.action.ViewActions;
import androidx.test.espresso.assertion.ViewAssertions;
import androidx.test.ext.junit.rules.ActivityScenarioRule;
import androidx.test.platform.app.InstrumentationRegistry;
import androidx.test.ext.junit.runners.AndroidJUnit4;
import androidx.test.rule.ActivityTestRule;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;

import static org.junit.Assert.*;

import static androidx.test.espresso.Espresso.*;
import static androidx.test.espresso.action.ViewActions.*;
import static androidx.test.espresso.matcher.ViewMatchers.*;
import static androidx.test.espresso.action.ViewActions.closeSoftKeyboard;
import static androidx.test.espresso.Espresso.pressBack;
import static androidx.test.espresso.assertion.ViewAssertions.*;
import static org.hamcrest.Matchers.*;

@RunWith(AndroidJUnit4.class)
public class TaskCreationTest {
    @Rule
    public ActivityScenarioRule<TaskListActivity> mTaskListActivityActivityTestRule =
            new ActivityScenarioRule<>(TaskListActivity.class);
    private static DataManager sDataManager;

    @BeforeClass
    public static void classSetup() {
        sDataManager = DataManager.getInstance();
    }

    @Test
    public void createNewTask() {
        final String taskTitle = "title";
        final String taskDescription = "description";

        onView(withId(R.id.fab)).perform(click());
        onView(withId(R.id.text_task_title)).perform(typeText(taskTitle))
        .check(matches(withText(containsString(taskTitle))));
        onView(withId(R.id.text_task_description)).perform(typeText(taskDescription),
                closeSoftKeyboard());
        onView(withId(R.id.text_task_description)).check(matches(withText(containsString(taskDescription))));

        pressBack();

        int taskIndex = sDataManager.getTasks().size() - 1;
        TaskInfo task = sDataManager.getTasks().get(taskIndex);

        assertEquals(taskTitle, task.getTitle());
        assertEquals(taskDescription, task.getDescription());
    }
}