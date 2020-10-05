package com.njb.todue;

import android.provider.ContactsContract;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.*;

public class DataManagerTest {

    private static DataManager sDataManager;

    @BeforeClass
    public static void classSetup() {
        sDataManager = DataManager.getInstance();
    }

    @Before
    public void setup() {
        sDataManager= DataManager.getInstance();
        sDataManager.getTasks().clear();
    }

    @Test
    public void createNewTaskTest() {
        final String taskTitle = "title";
        final String taskDescription = "description";
        sDataManager = DataManager.getInstance();

        int taskIndex = sDataManager.createNewTask();
        TaskInfo newTask = sDataManager.getTasks().get(taskIndex);
        newTask.setTitle(taskTitle);
        newTask.setDescription(taskDescription);

        TaskInfo compareTask = sDataManager.getTasks().get(taskIndex);
        assertEquals("title", compareTask.getTitle());
        assertEquals("description", compareTask.getDescription());
    }

    @Test
    public void removeTaskTest()
    {
        sDataManager = DataManager.getInstance();

        int taskIndex = sDataManager.createNewTask();
        TaskInfo newTask1 = sDataManager.getTasks().get(taskIndex);
        newTask1.setTitle("title");
        newTask1.setDescription("description");

        sDataManager.removeTask(taskIndex);

        assertEquals(-1, sDataManager.getTasks().indexOf(newTask1));
    }

    @Test
    public void findTask()
    {
        sDataManager = DataManager.getInstance();

        int taskIndex = sDataManager.createNewTask();
        TaskInfo newTask = sDataManager.getTasks().get(taskIndex);
        newTask.setTitle("title");
        newTask.setDescription("description");

        assertEquals(taskIndex, sDataManager.findTask(newTask));
    }
}