package com.njb.todue;

import org.junit.Test;
import static org.junit.Assert.*;

public class TaskInfoTest {
    @Test
    public void equalsShouldReturnTrueForTaskThatIsTheSameObject()
    {
        TaskInfo task1 = new TaskInfo("title", "description", true);
        TaskInfo task2 = task1;
        assertTrue(task2.equals(task1));
    }

    @Test
    public void equalsShouldReturnTrueForTaskThatHasTheSameCompareKey()
    {
        TaskInfo task1 = new TaskInfo("title", "description", true);
        TaskInfo task2 = new TaskInfo("title", "description", true);
        assertTrue(task2.equals(task1));
    }

    @Test
    public void equalsShouldReturnFalseForTaskThatDoesNotHaveTheSameCompareKey()
    {
        TaskInfo task1 = new TaskInfo("title", "description", true);
        TaskInfo task2 = new TaskInfo("title2", "description", true);
        assertFalse(task2.equals(task1));
    }
}