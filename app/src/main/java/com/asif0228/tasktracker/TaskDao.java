package com.asif0228.tasktracker;

import androidx.room.ColumnInfo;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import java.util.List;

import static androidx.room.OnConflictStrategy.REPLACE;

@Dao
public interface TaskDao {
    @Query("SELECT * FROM task WHERE isSolved=0 ORDER by `order`")
    List<Task> getAll();

    @Query("SELECT COUNT(taskId) as total,SUM(isSolved) as solved FROM task")
    stat findByIsSolved();

    @Insert(onConflict = REPLACE)
    void insert(Task task);

    @Delete
    void delete(Task task);

    class stat{
        @ColumnInfo(name = "total")
        public int total;
        @ColumnInfo(name = "solved")
        public int solved;
    }
}
