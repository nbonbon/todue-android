package com.njb.todue;

import android.content.Intent;
import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProvider;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class TaskActivity extends AppCompatActivity {
    public static final String TASK_POSITION = "com.njb.todue.TASK_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private TaskInfo mTask;
    private boolean mIsNewTask;
    private EditText mTextTaskTitle;
    private EditText mTextTaskDescription;
    private int mTaskPosition;
    private boolean mIsCancelling;
    private TaskActivityViewModel mViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        ViewModelProvider viewModelProvider = new ViewModelProvider(getViewModelStore(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getApplication()));
        mViewModel = viewModelProvider.get(TaskActivityViewModel.class);

        if (mViewModel.mIsNewlyCreated && savedInstanceState != null) {
            mViewModel.restoreState(savedInstanceState);
        }
        mViewModel.mIsNewlyCreated = false;

        readDisplayStateValues();
        saveOriginalTaskValues();

        mTextTaskTitle = findViewById(R.id.text_task_title);
        mTextTaskDescription = findViewById(R.id.text_task_description);

        if (!mIsNewTask) {
            displayTask(mTextTaskTitle, mTextTaskDescription);
        }
    }

    private void saveOriginalTaskValues() {
        if (mIsNewTask) {
            return;
        }

        mViewModel.mOriginalTaskTitle = mTask.getTitle();
        mViewModel.mOriginalTaskDescription = mTask.getDescription();
    }

    private void displayTask(EditText textTaskTitle, EditText textTaskDescription) {
        textTaskTitle.setText(mTask.getTitle());
        textTaskDescription.setText(mTask.getDescription());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(TASK_POSITION, POSITION_NOT_SET);
        mIsNewTask = (position == POSITION_NOT_SET);

        if (mIsNewTask) {
            createNewTask();
        } else {
            mTask = DataManager.getInstance().getTasks().get(position);
        }
    }

    private void createNewTask() {
        DataManager dm = DataManager.getInstance();
        mTaskPosition = dm.createNewTask();
        mTask = dm.getTasks().get(mTaskPosition);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (mIsCancelling) {
            if (mIsNewTask)
            {
                DataManager.getInstance().removeTask(mTaskPosition);
            } else {
               storePreviousTaskValues(); 
            }
        } else {
            saveTask();
        }
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        mViewModel.saveState(outState);
    }

    private void storePreviousTaskValues() {
        mTask.setTitle(mViewModel.mOriginalTaskTitle);
        mTask.setDescription(mViewModel.mOriginalTaskDescription);
    }

    private void saveTask() {
        mTask.setTitle(mTextTaskTitle.getText().toString());
        mTask.setDescription(mTextTaskDescription.getText().toString());
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_send_email) {
            sendEmail();
            return true;
        } else if (id == R.id.action_cancel) {
            mIsCancelling = true;
            finish();
        } else if (id == R.id.action_next) {
            moveNext();
        }

        return super.onOptionsItemSelected(item);
    }

    private void moveNext() {
        saveTask();

        ++mTaskPosition;
        mTask = DataManager.getInstance().getTasks().get(mTaskPosition);

        saveOriginalTaskValues();
        displayTask(mTextTaskTitle, mTextTaskDescription);
    }

    private void sendEmail() {
        String subject = mTextTaskTitle.getText().toString();
        String text = mTextTaskDescription.getText().toString();
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("message/rfc2822");
        intent.putExtra(Intent.EXTRA_SUBJECT, subject);
        intent.putExtra(Intent.EXTRA_TEXT, text);
        startActivity(intent);
    }
}