package com.asif0228.tasktracker;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.room.Room;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

public class HomeActivity extends AppCompatActivity {

    AppDatabase db;
    TextView textView_stat;
    TaskAddFragment taskAddFragment;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);
        db = AppDatabase.getInstance(this); // initilize databae
        textView_stat = (TextView) this.findViewById(R.id.textView_stat);
        this.start();
    }

    protected void start(){
        // ======== Get Data from database ============
        TaskDao taskDao = db.taskDao();
        TaskDao.stat task_count = taskDao.findByIsSolved();
//        textView_stat.setText(task_count.length+"");
        textView_stat.setText("Pending: "+(task_count.total-task_count.solved)+", Completed: "+task_count.solved);
        List<Task> tasks = taskDao.getAll();

        this.clearAllFragment(); // Clear all fragment

        // ======== Begin Transaction for Adding Fragments into body layout ===============
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // ======== Add Fragments into body layout ===============
        TaskViewFragment fragment;
        for(int i=0;i<tasks.size();i++) {
            fragment = new TaskViewFragment(this,tasks.get(i));
            ft.add(R.id.linearLayout_body, fragment, "task_"+i);
        }
        ft.commit(); // commit the changes in layout
    }

    public void addTask(View view){
        this.clearAllFragment(); // Clear all fragment
        // ======== Begin Transaction for Adding Fragments into body layout ===============
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // ======== Add Fragments into body layout ===============
        taskAddFragment = new TaskAddFragment(this,null);
        ft.add(R.id.linearLayout_body, taskAddFragment, "add_task");
        ft.commit(); // commit the changes in layout
    }

    public void addTaskSubmit(View v){
        if(taskAddFragment.task==null)
            taskAddFragment.task = new Task(
                    null,
                    taskAddFragment.editText_title.getText().toString(),
                    taskAddFragment.editText_body.getText().toString(),
                    Integer.parseInt(taskAddFragment.spinner_priority.getSelectedItem().toString()),
                    Integer.parseInt(taskAddFragment.editText_order.getText().toString()),
                    false
            );
        else
            taskAddFragment.task = new Task(
                    taskAddFragment.task.getTaskId(),
                    taskAddFragment.editText_title.getText().toString(),
                    taskAddFragment.editText_body.getText().toString(),
                    Integer.parseInt(taskAddFragment.spinner_priority.getSelectedItem().toString()),
                    Integer.parseInt(taskAddFragment.editText_order.getText().toString()),
                    false
            );
        TaskDao taskDao = db.taskDao();
        taskDao.insert(taskAddFragment.task);
        this.start();
    }

    private void clearAllFragment(){
        // ======== Begin Transaction for Adding Fragments into body layout ===============
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();
        for (Fragment fragment : fragmentManager.getFragments()) { // loop through all the fragments added in the linear layout
            ft.remove(fragment);
        }
        ft.commit(); // commit the changes in layout
    }

    public void closeTaskAddEdit(View v){
        this.start();
    }

    public void editTaskMenu(View view,Task task){
        this.clearAllFragment(); // Clear all fragment

        // ======== Begin Transaction for Adding Fragments into body layout ===============
        FragmentManager fragmentManager = getSupportFragmentManager();
        FragmentTransaction ft = fragmentManager.beginTransaction();

        // ======== Add Fragments into body layout ===============
        taskAddFragment = new TaskAddFragment(this,task);
        ft.add(R.id.linearLayout_body, taskAddFragment, "add_task");
        ft.commit(); // commit the changes in layout
    }

    public void finishTask(View view,Task task){
        task.setSolved(true);
        TaskDao taskDao = db.taskDao();
        taskDao.insert(task);
        this.start();
    }
}