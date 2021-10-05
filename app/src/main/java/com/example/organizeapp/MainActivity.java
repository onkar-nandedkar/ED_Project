package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.starting_page);
        CardView todo = findViewById(R.id.todocard);
        todo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent todo = new Intent(MainActivity.this,TodoList.class);
                startActivity(todo);
            }
        });
        CardView resource = findViewById(R.id.resource_manager);
        resource.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent link = new Intent(MainActivity.this , ResourceManager.class);
                startActivity(link);
            }
        });
        /*String titles[] = {"To do List","Exam Schedule","Focus Mode","Resource Manager"};
        ListView lv = findViewById(R.id.topics);
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(MainActivity.this,android.R.layout.simple_list_item_1,titles);
        lv.setAdapter(adapter);
        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                if(i == 0){
                }
                else if(i == 3){
                }
            }
        });*/
    }
}