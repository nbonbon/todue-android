package com.njb.todue;

import android.os.Bundle;

import androidx.lifecycle.ViewModel;

public class TaskActivityViewModel extends ViewModel {
    public static final String ORIGINAL_TASK_TITLE = "com.njb.todue.ORIGINAL_TASK_TITLE";
    public static final String ORIGINAL_TASK_DESCRIPTION = "com.njb.todue.ORIGINAL_TASK_DESCRIPTION";

    public String mOriginalTaskDescription;
    public String mOriginalTaskTitle;
    public boolean mIsNewlyCreated = true;

    public void saveState(Bundle outState) {
        outState.putString(ORIGINAL_TASK_TITLE, mOriginalTaskTitle);
        outState.putString(ORIGINAL_TASK_DESCRIPTION, mOriginalTaskDescription);
    }

    public void restoreState(Bundle inState) {
        mOriginalTaskTitle = inState.getString(ORIGINAL_TASK_TITLE);
        mOriginalTaskDescription = inState.getString(ORIGINAL_TASK_DESCRIPTION);
    }
}
