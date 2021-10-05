package com.example.organizeapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResourceManager extends AppCompatActivity implements DialogDelTask.ExampleDialogListener{

    Button add;
    DataStoreLinks db;
    ListView lv;
    ArrayList titles;
    ArrayAdapter adapter;
    int curPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resource_manager);
        add = findViewById(R.id.link_add);
        lv = findViewById(R.id.lv_links);
        db = new DataStoreLinks(ResourceManager.this);
        titles = db.getAllTitles();
        adapter =   new ArrayAdapter(ResourceManager.this , android.R.layout.simple_list_item_1 , titles);
        lv.setAdapter(adapter);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(ResourceManager.this,AddNewLink.class);
                startActivityForResult(intent,1);
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

        lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent show_link = new Intent(ResourceManager.this , ShowLinkInfo.class);
                String[] ar = db.getRecord(i+1);
                Log.e("CHECK VAL DB" , ar[1]);
                show_link.putExtra("rating",ar[1]);
                show_link.putExtra("comment",ar[2]);
                show_link.putExtra("title",ar[0]);
                show_link.putExtra("link",ar[3]);
                startActivity(show_link);
            }
        });
    }

    private void confirm() {
        DialogDelTask dialogDelTask = new DialogDelTask();
        dialogDelTask.show(getSupportFragmentManager(),"Delete Dialog");
    }


    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                String link = data.getStringExtra("link").toString();
                String rating = data.getStringExtra("rating").toString();
                String comment = data.getStringExtra("comment").toString();
                String title = data.getStringExtra("title").toString();
                db.AddLink(link,rating,comment,title);

                lv.refreshDrawableState();
                lv.invalidateViews();
                titles.clear();
                titles.add(db.getAllTitles());
                db = new DataStoreLinks(ResourceManager.this);
                titles = db.getAllTitles();
                adapter =   new ArrayAdapter(ResourceManager.this , android.R.layout.simple_list_item_1 , titles);
                lv.setAdapter(adapter);

            }
        }
    }



    @Override
    public void deleteTask() {
        ArrayList<String> arl = db.getAllTitles();
        db.del_link(arl.get(curPos));
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        lv.refreshDrawableState();
        lv.invalidateViews();
        titles.clear();
        titles.add(db.getAllTitles());
        db = new DataStoreLinks(ResourceManager.this);
        titles = db.getAllTitles();
        adapter = new ArrayAdapter(ResourceManager.this, android.R.layout.simple_list_item_1, titles);
        lv.setAdapter(adapter);
    }
}