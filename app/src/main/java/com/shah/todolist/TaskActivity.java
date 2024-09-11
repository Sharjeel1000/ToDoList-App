package com.shah.todolist;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class TaskActivity extends AppCompatActivity {
    private Button save;
    private EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_task);

        save = findViewById(R.id.save);
        editText = findViewById(R.id.editTextText);

        SharedPreferences sharedPreferences = getSharedPreferences("Tasks", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();

        save.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (editText.getText().toString().isEmpty()) {
                    Toast.makeText(TaskActivity.this, "Add Task First", Toast.LENGTH_SHORT).show();
                } else {
                    String note = editText.getText().toString();
                    int key = sharedPreferences.getAll().size();  // Generate a unique key based on current size
                    editor.putString("key" + key, note);
                    editor.apply();
                    Intent intent = new Intent(TaskActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }
}
