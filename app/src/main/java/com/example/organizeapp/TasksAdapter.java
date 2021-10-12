package com.example.organizeapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class TasksAdapter extends BaseAdapter {

    LayoutInflater layoutInflater;
    ArrayList<String> tasks;
    ArrayList<String> ticks;
    Context context;
    public TasksAdapter(Context context, ArrayList<String> tasks){
        this.context = context;
        this.tasks = tasks;
        layoutInflater = LayoutInflater.from(context);
    }
    @Override
    public int getCount() {
        return tasks.size();
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

        LayoutInflater li = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = layoutInflater.inflate(R.layout.task_item , null);
        TextView tv = convertView.findViewById(R.id.task_text);
        tv.setText(tasks.get(position));
        return convertView;
    }
}
