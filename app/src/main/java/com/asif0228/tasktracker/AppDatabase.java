package com.asif0228.tasktracker;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

@Database(entities = {Task.class}, version = 1, exportSchema = false)
public abstract class AppDatabase extends RoomDatabase {
    private static AppDatabase appdb;
    private static String db_name = "taskdb";


    public synchronized static AppDatabase getInstance(Context context){
        if(appdb==null){
            appdb = Room.databaseBuilder(context.getApplicationContext(), AppDatabase.class, db_name)
                    .allowMainThreadQueries()
                    .fallbackToDestructiveMigration()
                    .build();
        }
        return appdb;
    }

    public abstract TaskDao taskDao();
}
