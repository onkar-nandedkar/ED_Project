package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class TodoList extends AppCompatActivity implements DialogDelTask.ExampleDialogListener{


    Button add;
    EditText task;
    DatabaseStore db;
    ListView lv;
    ArrayList tasks;
    //ArrayAdapter adapter;
    TasksAdapter adapter;
    //ArrayList checks;
    int curPos;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_todo_list);
        add = findViewById(R.id.add);
        task = findViewById(R.id.task);
        lv = findViewById(R.id.lv_links);
        db = new DatabaseStore(TodoList.this);
        tasks = db.getAllTasks();
        //adapter =   new ArrayAdapter(TodoList.this , android.R.layout.simple_list_item_1 , tasks);
        adapter = new TasksAdapter(TodoList.this, tasks);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                db.AddTask(task.getText().toString());
                task.setText("");
                Toast.makeText(getApplicationContext(), "Added", Toast.LENGTH_SHORT).show();
                lv.refreshDrawableState();
                lv.invalidateViews();
                tasks.clear();
                tasks.add(db.getAllTasks());
                db = new DatabaseStore(TodoList.this);
                tasks = db.getAllTasks();
                adapter =   new TasksAdapter(TodoList.this , tasks);
                lv.setAdapter(adapter);
                hideKeyboard(TodoList.this);
            }
        });
        lv.setAdapter(adapter);

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                curPos = position;
                confirm();
                return false;
            }
        });

    }

    public static void hideKeyboard(Activity activity) {
        InputMethodManager imm = (InputMethodManager) activity.getSystemService(Activity.INPUT_METHOD_SERVICE);
        //Find the currently focused view, so we can grab the correct window token from it.
        View view = activity.getCurrentFocus();
        //If no view currently has focus, create a new one, just so we can grab a window token from it
        if (view == null) {
            view = new View(activity);
        }
        imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
    }

    private void confirm() {
        DialogDelTask dialogDelTask = new DialogDelTask();
        dialogDelTask.show(getSupportFragmentManager(),"Delete Dialog");
    }

    @Override
    public void deleteTask() {

        ArrayList<String> arl = db.getAllTasks();
        db.del(arl.get(curPos));
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        lv.refreshDrawableState();
        lv.invalidateViews();
        tasks.clear();
        tasks.add(db.getAllTasks());
        db = new DatabaseStore(TodoList.this);
        tasks = db.getAllTasks();
        adapter = new TasksAdapter(TodoList.this, tasks);
        lv.setAdapter(adapter);
    }

}