package com.example.logintest;

import android.text.Layout;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.DiffUtil;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import org.w3c.dom.Text;

import java.util.List;

public class EmployeeListAdapter extends ListAdapter<Employee, EmployeeListAdapter.EmployeeViewHolder> {

    public DatabaseHelper mDB;

    protected EmployeeListAdapter(DatabaseHelper db, @NonNull DiffUtil.ItemCallback<Employee> diffCallback) {
        super(diffCallback);
        mDB = db;
    }

    @NonNull
    @Override
    public EmployeeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.recyclerview_employee, parent, false);
        return new EmployeeViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull EmployeeViewHolder holder, int position) {
        Employee current = mDB.searchEmployee(position);
        holder.bind(current);
    }

    @Override
    public int getItemCount(){
        return (int) mDB.count();
    }

    static class EmployeeDiff extends DiffUtil.ItemCallback<Employee>{

        @Override
        public boolean areItemsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem == newItem;
        }

        @Override
        public boolean areContentsTheSame(@NonNull Employee oldItem, @NonNull Employee newItem) {
            return oldItem.geteName().equals((newItem.geteName()));
        }
    }

    class EmployeeViewHolder extends RecyclerView.ViewHolder {

        private Employee emp;
        private final TextView empTextView;

        public EmployeeViewHolder(View itemView) {
            super(itemView);
            empTextView = itemView.findViewById(R.id.txtView);
            empTextView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Log.v("EmployeeViewHolder", "Position: " + getAdapterPosition());
                    mDB.delete(emp.getId());
                    notifyItemRemoved(getAdapterPosition());
                }
            });
        }

        public void bind(Employee employee){
            emp = employee;
            empTextView.setText(emp.geteName());
        }
    }
}
