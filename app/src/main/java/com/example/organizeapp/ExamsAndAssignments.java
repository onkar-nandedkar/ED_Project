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

public class ExamsAndAssignments extends AppCompatActivity implements DialogDelTask.ExampleDialogListener{

    ListView lv;
    ExamAdapter adapter;
    ArrayList<Exam> exams;
    StoreExamDates db;
    int curPos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_exams_and_assignments);
        lv = findViewById(R.id.exams);
        db = new StoreExamDates(ExamsAndAssignments.this);
        exams = db.getAllExams();
        adapter = new ExamAdapter(ExamsAndAssignments.this , exams);
        lv.setAdapter(adapter);
        Button add = findViewById(R.id.add_exam);
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ExamsAndAssignments.this , AddNewExamAssignment.class);
                startActivityForResult(intent , 1);
            }
        });

        lv.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                curPos = position;
                confirm();
                return false;
            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode , int resultCode , Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode == 1){
            if(resultCode == RESULT_OK){
                Exam e;
                e = new Exam(data.getStringExtra("name").toString() ,  data.getStringExtra("date").toString() , data.getStringExtra("time").toString());
                db = new StoreExamDates(ExamsAndAssignments.this);
                db.AddExam(e.name , e.date , e.time);
                lv.refreshDrawableState();
                lv.invalidateViews();
                exams.clear();
                db = new StoreExamDates(ExamsAndAssignments.this);
                exams = db.getAllExams();
                adapter =   new ExamAdapter(ExamsAndAssignments.this , exams);
                lv.setAdapter(adapter);
            }
        }
    }
    private void confirm() {
        DialogDelTask dialogDelTask = new DialogDelTask();
        dialogDelTask.show(getSupportFragmentManager(),"Delete Dialog");
    }

    @Override
    public void deleteTask() {
        ArrayList<Exam> arl = db.getAllExams();
        db.del(arl.get(curPos).name);
        Toast.makeText(getApplicationContext(), "Deleted", Toast.LENGTH_SHORT).show();
        lv.refreshDrawableState();
        lv.invalidateViews();
        exams.clear();
        db = new StoreExamDates(ExamsAndAssignments.this);
        exams = db.getAllExams();
        adapter = new ExamAdapter(ExamsAndAssignments.this, exams);
        lv.setAdapter(adapter);
    }
}