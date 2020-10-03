package com.njb.todue;

import android.content.Intent;
import android.os.Bundle;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.List;

public class TaskListActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task_list);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(TaskListActivity.this, TaskActivity.class));
            }
        });

        initializeDisplayContent();
    }

    private void initializeDisplayContent() {
        final ListView listTasks = findViewById(R.id.list_tasks);

        List<TaskInfo> tasks = DataManager.getInstance().getTasks();
        ArrayAdapter<TaskInfo> adapterTasks = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, tasks);
        listTasks.setAdapter(adapterTasks);

        listTasks.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int position, long l) {
                Intent intent = new Intent(TaskListActivity.this, TaskActivity.class);
                intent.putExtra(TaskActivity.TASK_POSITION, position);
                startActivity(intent);
            }
        });
    }
}