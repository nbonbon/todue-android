package com.njb.todue;

import java.util.ArrayList;
import java.util.List;

public class DataManager {
    private static DataManager ourInstance = null;

    private List<TaskInfo> mTasks = new ArrayList<>();

    public static DataManager getInstance() {
        if(ourInstance == null) {
            ourInstance = new DataManager();
            ourInstance.initializeExampleTasks();
        }
        return ourInstance;
    }

    public String getCurrentUserName() {
        return "Jim Wilson";
    }

    public String getCurrentUserEmail() {
        return "jimw@jwhh.com";
    }

    public List<TaskInfo> getTasks() {
        return mTasks;
    }

    public int createNewTask() {
        TaskInfo task = new TaskInfo(null, null);
        mTasks.add(task);
        return mTasks.size() - 1;
    }

    public int findTask(TaskInfo task) {
        for(int index = 0; index < mTasks.size(); index++) {
            if(task.equals(mTasks.get(index)))
                return index;
        }

        return -1;
    }

    public void removeTask(int index) {
        mTasks.remove(index);
    }

    private DataManager() {
    }

    //region Initialization code

    public void initializeExampleTasks() {
        final DataManager dm = getInstance();

         mTasks.add(new TaskInfo("Dynamic intent resolution",
                "Wow, intents allow components to be resolved at runtime"));
        mTasks.add(new TaskInfo("Delegating intents",
                "PendingIntents are powerful; they delegate much more than just a component invocation"));

        mTasks.add(new TaskInfo("Service default threads",
                "Did you know that by default an Android Service will tie up the UI thread?"));
        mTasks.add(new TaskInfo("Long running operations",
                "Foreground Services can be tied to a notification icon"));

        mTasks.add(new TaskInfo("Parameters",
                "Leverage variable-length parameter lists"));
        mTasks.add(new TaskInfo("Anonymous classes",
                "Anonymous classes simplify implementing one-use types"));

        mTasks.add(new TaskInfo("Compiler options",
                "The -jar option isn't compatible with with the -cp option"));
        mTasks.add(new TaskInfo("Serialization",
                "Remember to include SerialVersionUID to assure version compatibility"));
    }
    //endregion

}
