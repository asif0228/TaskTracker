package com.asif0228.tasktracker;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

public class TaskAddFragment extends Fragment {

    static HomeActivity homeActivity;
    EditText editText_title;
    EditText editText_body;
    Spinner spinner_priority;
    EditText editText_order;
    Button button_edit;
    Button button_finish;
    Task task;


    public TaskAddFragment(HomeActivity ha,Task t) {
        homeActivity = ha;
        task = t;
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_task_add, container, false);
        editText_title = view.findViewById(R.id.editText_title);
        editText_body = view.findViewById(R.id.editText_body);
        spinner_priority = view.findViewById(R.id.spinner_priority);
        editText_order = view.findViewById(R.id.editText_order);
        button_edit = view.findViewById(R.id.button_edit);
        button_finish = view.findViewById(R.id.button_finish);
        if(task!=null) {
            editText_title.setText(task.getTitle());
            editText_body.setText(task.getBody());
            spinner_priority.setSelection(task.getPriority()-1);
            editText_order.setText(task.getOrder()+"");
            button_edit.setText("Change");
            button_finish.setVisibility(View.VISIBLE);
            button_finish.setOnClickListener(new Button.OnClickListener() {
                public void onClick(View v) {
                    homeActivity.finishTask(v,task);
                    //homeActivity.textView_stat.setText(task.getTitle());
                }
            });
        }else{
            button_edit.setText("Add");
            button_finish.setVisibility(View.GONE);
        }
        return view;
    }

    public void addTaskSubmit(View v){ }

    public void closeTaskAddEdit(View v){

    }
}