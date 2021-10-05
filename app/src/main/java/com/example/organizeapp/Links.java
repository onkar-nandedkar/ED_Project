package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class Links extends AppCompatActivity {

    Button add;
    DataStoreLinks db;
    ListView lv;
    ArrayList titles;
    ArrayAdapter adapter;
    int curPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_links);
        add = findViewById(R.id.add);
        lv = findViewById(R.id.lv_links);
        db = new DataStoreLinks(Links.this);
        titles = db.getAllTitles();
        adapter =   new ArrayAdapter(Links.this , android.R.layout.simple_list_item_1 , titles);
        lv.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(Links.this,AddNewLink.class);
                startActivityForResult(intent,1);
                //db.AddTask(task.getText().toString());
                /*lv.refreshDrawableState();
                lv.invalidateViews();
                titles.clear();
                titles.add(db.getAllTitles());
                db = new DataStoreLinks(Links.this);
                titles = db.getAllTitles();
                adapter =   new ArrayAdapter(Links.this , android.R.layout.simple_list_item_1 , titles);
                lv.setAdapter(adapter);
                */
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> adapterView, View view, int i, long l) {
                curPos = i;
                confirm();
                return true;
            }
        });

    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                db.AddLink(data.getStringExtra("link").toString(),data.getStringExtra("rating").toString(),data.getStringExtra("comment").toString(),data.getStringExtra("title").toString());
                lv.refreshDrawableState();
                lv.invalidateViews();
                titles.clear();
                titles.add(db.getAllTitles());
                db = new DataStoreLinks(Links.this);
                titles = db.getAllTitles();
                adapter =   new ArrayAdapter(Links.this , android.R.layout.simple_list_item_1 , titles);
                lv.setAdapter(adapter);
            }
        }
    }
    private void confirm() {
        DialogDelTask dialogDelTask = new DialogDelTask();
        dialogDelTask.show(getSupportFragmentManager(),"Delete Dialog");
    }

    /*public void deleteTask() {

        ArrayList<String> arl = db.getAllTitles();
        db.del_link(arl.get(curPos));
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        lv.refreshDrawableState();
        lv.invalidateViews();
        titles.clear();
        titles.add(db.getAllTitles());
        db = new DataStoreLinks(Links.this);
        titles = db.getAllTitles();
        adapter = new ArrayAdapter(Links.this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);
    }*/
}