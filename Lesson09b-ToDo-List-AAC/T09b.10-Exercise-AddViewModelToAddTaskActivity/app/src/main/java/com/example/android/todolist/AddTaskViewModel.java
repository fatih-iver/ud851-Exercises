package com.example.android.todolist;

import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.ViewModel;

import com.example.android.todolist.database.AppDatabase;
import com.example.android.todolist.database.TaskEntry;

import java.util.List;

public class AddTaskViewModel extends ViewModel{

    private LiveData<TaskEntry> mTask;

    public LiveData<TaskEntry> getTask() {
        return mTask;
    }

    public AddTaskViewModel(AppDatabase Db, int taskId) {
        mTask = Db.taskDao().loadTaskById(taskId);
    }

}
