package com.njb.todue;

import android.content.Intent;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.Menu;
import android.view.MenuItem;
import android.widget.EditText;

public class TaskActivity extends AppCompatActivity {
    public static final String TASK_POSITION = "com.njb.todue.TASK_POSITION";
    public static final int POSITION_NOT_SET = -1;
    private TaskInfo mTask;
    private boolean mIsNewTask;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        readDisplayStateValues();

        EditText textTaskTitle = findViewById(R.id.text_task_title);
        EditText textTaskDescription = findViewById(R.id.text_task_description);

        if (!mIsNewTask) {
            displayTask(textTaskTitle, textTaskDescription);
        }
    }

    private void displayTask(EditText textTaskTitle, EditText textTaskDescription) {
        textTaskTitle.setText(mTask.getTitle());
        textTaskDescription.setText(mTask.getDescription());
    }

    private void readDisplayStateValues() {
        Intent intent = getIntent();
        int position = intent.getIntExtra(TASK_POSITION, POSITION_NOT_SET);
        mIsNewTask = (position == POSITION_NOT_SET);

        if (!mIsNewTask) {
            mTask = DataManager.getInstance().getNotes().get(position);
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_task, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}