package com.example.list;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

// Define the Task as a Room Entity
@Entity(tableName = "tasks")
public class Task {

    // Primary Key for Room Database (automatically generated)
    @PrimaryKey(autoGenerate = true)
    private int id;

    // Task name
    private String taskName;

    // Completion status
    private boolean isCompleted;

    // Constructor
    public Task(String taskName, boolean isCompleted) {
        this.taskName = taskName;
        this.isCompleted = isCompleted;
    }

    // Empty Constructor (Required for Room)
    public Task() {
    }

    // Getters and Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public boolean isCompleted() {
        return isCompleted;
    }

    public void setCompleted(boolean completed) {
        isCompleted = completed;
    }
}