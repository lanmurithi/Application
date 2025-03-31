package com.example.list;

import android.graphics.Paint;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TaskAdapter extends RecyclerView.Adapter<TaskAdapter.TaskViewHolder> {

    private final List<Task> taskList;
    private OnTaskActionListener listener = null;

    // Constructor
    public TaskAdapter(List<Task> taskList) {
        this.taskList = taskList;
        this.listener = listener;
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.activity_main, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = taskList.get(position);

        // Set task name
        holder.checkBoxTask.setText(task.getTaskName());

        // Set checkbox state and apply strikethrough for completed tasks
        holder.checkBoxTask.setChecked(task.isCompleted());
        if (task.isCompleted()) {
            holder.checkBoxTask.setPaintFlags(holder.checkBoxTask.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        } else {
            holder.checkBoxTask.setPaintFlags(holder.checkBoxTask.getPaintFlags() & ~Paint.STRIKE_THRU_TEXT_FLAG);
        }

        // Handle checkbox state changes
        holder.checkBoxTask.setOnCheckedChangeListener((buttonView, isChecked) -> {
            task.setCompleted(isChecked);
            notifyItemChanged(position);
            if (listener != null) {
                listener.onTaskCompleted(task, isChecked);
            }
        });

        // Handle long-press for editing
        holder.itemView.setOnLongClickListener(v -> {
            if (listener != null) {
                listener.onTaskEdit(task);
            }
            return true;
        });
    }

    @Override
    public int getItemCount() {
        return taskList.size();
    }

    // ViewHolder Class
    static class TaskViewHolder extends RecyclerView.ViewHolder {
        CheckBox checkBoxTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            checkBoxTask = itemView.findViewById(R.id.checkBoxTask);
        }
    }

    // Interface for task actions
    public interface OnTaskActionListener {
        void onTaskEdit(Task task);

        void onTaskCompleted(Task task, boolean isCompleted);
    }
}