package com.asif0228.tasktracker;

import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

import java.io.Serializable;

@Entity(tableName = "task")
public class Task implements Serializable {

    public Task(){}
    public Task(Integer val_taskId,String val_title,String val_body,int val_priority,int val_order,boolean val_isSolved){
        taskId = val_taskId;
        title = val_title;
        body = val_body;
        priority = val_priority;
        order = val_order;
        isSolved = val_isSolved;
    }

    @PrimaryKey(autoGenerate = true)
    private Integer taskId;
    @ColumnInfo(name = "title")
    private String title;
    @ColumnInfo(name = "body")
    private String body;
    @ColumnInfo(name = "priority")
    private int priority;
    @ColumnInfo(name = "order")
    private int order;
    @ColumnInfo(name = "isSolved")
    private boolean isSolved;

    public Integer getTaskId() {
        return taskId;
    }

    public void setTaskId(Integer taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getBody() {
        return body;
    }

    public void setBody(String body) {
        this.body = body;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public int getOrder() {
        return order;
    }

    public void setOrder(int order) {
        this.order = order;
    }

    public boolean isSolved() {
        return isSolved;
    }

    public void setSolved(boolean solved) {
        isSolved = solved;
    }
}
