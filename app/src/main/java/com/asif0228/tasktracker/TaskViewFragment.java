package com.asif0228.tasktracker;

import android.graphics.Color;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;


public class TaskViewFragment extends Fragment {

    static HomeActivity homeActivity;
    LinearLayout linearLayout_title;
    LinearLayout linearLayout_body;
    ImageView button_edit;
    Task task;
    String[] colors = {"","FF5630","FFAB00","6554C0","00B8D9","36B37E"};

    public TaskViewFragment(HomeActivity ha,Task t){
        homeActivity = ha;
        task = t; // set task
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_view, container, false);
        ((TextView) view.findViewById(R.id.textView_title)).setText(task.getTitle());
        ((TextView) view.findViewById(R.id.textView_body)).setText(task.getBody());
        linearLayout_title = view.findViewById(R.id.linearLayout_title);
        linearLayout_body = view.findViewById(R.id.linearLayout_body);
        linearLayout_title.setBackgroundColor(Color.parseColor("#"+colors[task.getPriority()]));
        linearLayout_body.setBackgroundColor(Color.parseColor("#AA"+colors[task.getPriority()]));
        button_edit = view.findViewById(R.id.imageView_edit);
        button_edit.setOnClickListener(new Button.OnClickListener() {
            public void onClick(View v) {
                homeActivity.editTaskMenu(v,task);
                //homeActivity.textView_stat.setText(task.getTitle());
            }
        });
        return view;
    }


}