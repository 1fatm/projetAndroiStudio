package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskDataSource taskDataSource;
    private ListView taskListView;
    private List<Task> allTasks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        taskDataSource = new TaskDataSource(this);
        taskDataSource.open();

        taskListView = findViewById(R.id.task_list_view);

        ImageView imagePlus = findViewById(R.id.ajouter_tâche);
        imagePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Ajouter_taches_Activity.class);
                startActivity(intent);
            }
        });

        ImageView filterIcon = findViewById(R.id.action_filter);
        filterIcon.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showFilterDialog();
            }
        });

        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Task selectedTask = (Task) parent.getItemAtPosition(position);
                Intent intent = new Intent(MainActivity.this, Modifier_tache.class);
                intent.putExtra("taskId", selectedTask.getId());
                startActivity(intent);
            }
        });

        loadTaskTitles();
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTaskTitles();
    }

    private void loadTaskTitles() {
        allTasks = taskDataSource.getAllTasks();
        TaskAdapter adapter = new TaskAdapter(this, allTasks);
        taskListView.setAdapter(adapter);
    }

    private void showFilterDialog() {
        DialogFragment filterDialog = new FilterDialogFragment();
        filterDialog.show(getSupportFragmentManager(), "filterDialog");
    }

    public void filterTasks(String status) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (task.getStatus().equals(status)) {
                filteredTasks.add(task);
            }
        }
        TaskAdapter adapter = new TaskAdapter(this, filteredTasks);
        taskListView.setAdapter(adapter);
    }

    @Override
    protected void onDestroy() {
        taskDataSource.close();
        super.onDestroy();
    }
}
