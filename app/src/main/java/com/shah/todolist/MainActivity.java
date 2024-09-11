package com.shah.todolist;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.ArrayList;
import java.util.Map;

public class MainActivity extends AppCompatActivity {
    private Button addNote;
    private ListView listView;
    private ArrayList<String> taskList;
    private ArrayAdapter<String> adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        addNote = findViewById(R.id.button);
        listView = findViewById(R.id.listView);

        taskList = new ArrayList<>();
        adapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, taskList);
        listView.setAdapter(adapter);

        loadTasks();

        addNote.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, TaskActivity.class);
                startActivity(intent);
            }
        });
    }

    private void loadTasks() {
        SharedPreferences sharedPreferences = getSharedPreferences("Tasks", MODE_PRIVATE);
        Map<String, ?> allEntries = sharedPreferences.getAll();
        taskList.clear();
        for (Map.Entry<String, ?> entry : allEntries.entrySet()) {
            taskList.add(entry.getValue().toString());
        }
        adapter.notifyDataSetChanged();
    }
}
