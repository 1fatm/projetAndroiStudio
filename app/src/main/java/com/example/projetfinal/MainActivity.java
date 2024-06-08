package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import androidx.appcompat.app.AppCompatActivity;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private TaskDataSource taskDataSource;
    private ListView taskListView;

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

        // Écouteur d'événements sur la liste des tâches
        taskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                // Récupérer la tâche sélectionnée
                Task selectedTask = (Task) parent.getItemAtPosition(position);

                // Créer un intent pour ouvrir Modifier_tache avec l'ID de la tâche sélectionnée
                Intent intent = new Intent(MainActivity.this, Modifier_tache.class);
                intent.putExtra("taskId", selectedTask.getId());
                startActivity(intent);
            }
        });

        loadTaskTitles();  // Charger les titres des tâches
    }

    @Override
    protected void onResume() {
        super.onResume();
        loadTaskTitles();
    }

    private void loadTaskTitles() {
        List<Task> tasks = taskDataSource.getAllTasks();
        ArrayAdapter<Task> adapter = new ArrayAdapter<>(this,
                android.R.layout.simple_list_item_1, tasks);
        taskListView.setAdapter(adapter);
        for (Task task : tasks) {
            Log.d("MainActivity", "Task loaded: " + task.getTitle());
        }
    }

    @Override
    protected void onDestroy() {
        taskDataSource.close();
        super.onDestroy();
    }
}
