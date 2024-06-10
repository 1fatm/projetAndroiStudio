package com.example.projetfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;
import java.util.List;

public class TaskAdapter extends ArrayAdapter<Task> {
    private List<Task> tasks;
    private Context context;

    public TaskAdapter(Context context, List<Task> tasks) {
        super(context, 0, tasks);
        this.context = context;
        this.tasks = tasks;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View itemView = convertView;
        if (itemView == null) {
            itemView = LayoutInflater.from(getContext()).inflate(R.layout.task_item, parent, false);
        }

        Task currentTask = getItem(position);

        if (currentTask != null) {
            TextView titleTextView = itemView.findViewById(R.id.task_title);
            titleTextView.setText(currentTask.getTitle());

            View statusCircle = itemView.findViewById(R.id.status_circle);

            switch (currentTask.getStatus()) {
                case "To Do":
                    itemView.setBackgroundResource(R.drawable.border_todo);
                    statusCircle.setBackgroundResource(R.drawable.circle_todo);
                    break;
                case "Done":
                    itemView.setBackgroundResource(R.drawable.border_done);
                    statusCircle.setBackgroundResource(R.drawable.cercle_done);
                    break;
                case "In Progress":
                    itemView.setBackgroundResource(R.drawable.border_in_progress);
                    statusCircle.setBackgroundResource(R.drawable.cercle_in_progress);
                    break;
                case "Bug":
                    itemView.setBackgroundResource(R.drawable.border_bug);
                    statusCircle.setBackgroundResource(R.drawable.cercle_bug);
                    break;
                default:
                    itemView.setBackgroundResource(R.drawable.border_task);
                    statusCircle.setBackgroundResource(R.drawable.circle);
                    break;
            }
        }

        return itemView;
    }
}
