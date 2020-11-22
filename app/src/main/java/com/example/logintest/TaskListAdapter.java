package com.example.logintest;

import android.content.Context;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.preference.PreferenceManager;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

public class TaskListAdapter extends ListAdapter<Task, TaskListAdapter.TaskViewHolder> {
    public DatabaseHelper mDB;
    int EmpID;

    protected TaskListAdapter(DatabaseHelper db, @NonNull DiffUtil.ItemCallback<Task> diffCallback, int empID) {
        super(diffCallback);
        this.EmpID = empID;
        mDB = db;
    }

    @Override
    public int getItemCount() {
        return (int) mDB.count();
    }

    @NonNull
    @Override
    public TaskListAdapter.TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_task, parent, false);
        return new TaskListAdapter.TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskListAdapter.TaskViewHolder holder, int position) {
        Task current = mDB.searchTask(position, EmpID);
        holder.bind(current);
    }

    static class TaskDiff extends DiffUtil.ItemCallback<Task> {

        @Override
        public boolean areItemsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Task oldItem, @NonNull Task newItem) {
            return oldItem.getTaskName().equals((newItem.getTaskName()));
        }
    }

    class TaskViewHolder extends RecyclerView.ViewHolder {

        private Task tsk;
        private final TextView taskTextView;

        public TaskViewHolder(View itemView) {
            super(itemView);
            taskTextView = itemView.findViewById(R.id.txtTask);
            taskTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("TaskViewHolder", "Position: " + getAdapterPosition());
                    mDB.delete(tsk.gettId());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public void bind(Task task) {
            tsk = task;
            taskTextView.setText(tsk.getTaskName());
        }
    }
}