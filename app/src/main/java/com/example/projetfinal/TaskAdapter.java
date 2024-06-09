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

        // Personnalisez l'apparence de chaque élément de la liste ici
        if (currentTask != null) {
            TextView titleTextView = itemView.findViewById(R.id.task_title);
            titleTextView.setText(currentTask.getTitle());

            // Définissez la couleur de la bordure en fonction du statut de la tâche
            if (currentTask.getStatus().equals("To Do")) {
                itemView.setBackgroundResource(R.drawable.border_todo);
            } else if (currentTask.getStatus().equals("Done")) {
                itemView.setBackgroundResource(R.drawable.border_done);
            } else if (currentTask.getStatus().equals("In Progress")) {
                itemView.setBackgroundResource(R.drawable.border_in_progress);
            } else if (currentTask.getStatus().equals("Bug")) {
                itemView.setBackgroundResource(R.drawable.border_bug);
            }
        }

        return itemView;
    }
}
