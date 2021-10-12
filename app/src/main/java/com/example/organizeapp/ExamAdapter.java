package com.example.organizeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class ExamAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList<Exam> exams;

    Context context;
    public ExamAdapter(Context context, ArrayList<Exam> exams){
        this.context = context;
        this.exams = exams;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return exams.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        convertView = layoutInflater.inflate(R.layout.exam_list_item , null);

        TextView exam = convertView.findViewById(R.id.set_exam);
        TextView time = convertView.findViewById(R.id.set_time);
        TextView date = convertView.findViewById(R.id.set_date);
        exam.setText(exams.get(position).name);
        time.setText(exams.get(position).time);
        date.setText(exams.get(position).date);

        return convertView;
    }
}
