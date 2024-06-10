package com.example.projetfinal;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;

public class Ajouter_taches_Activity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;
    private Spinner statusSpinner;
    private TaskDataSource taskDataSource;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_taches);

        taskDataSource = new TaskDataSource(this);
        taskDataSource.open();

        titleEditText = findViewById(R.id.task_title_textview);
        contentEditText = findViewById(R.id.task_content_edittext);
        statusSpinner = findViewById(R.id.status_button);

        String[] statuses = {"Todo", "In Progress", "Done", "Bug"};
        int[] colors = {Color.GRAY, Color.BLUE, Color.GREEN, Color.RED};

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, statuses, colors);
        statusSpinner.setAdapter(adapter);

        Button addButton = findViewById(R.id.add_task_button);
        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addTask();
            }
        });

        ImageView cancelImage = findViewById(R.id.annuler_tache);
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Retourner à l'activité principale
                Intent intent = new Intent(Ajouter_taches_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Définition de la couleur de l'indicateur de statut
        ImageView statusIndicator = findViewById(R.id.status_indicator_add_task);
        statusIndicator.setImageResource(R.drawable.cercle_in_progress); // Utilisez le fichier XML correspondant à la couleur désirée
    }

    private void addTask() {
        String title = titleEditText.getText().toString();
        String content = contentEditText.getText().toString();
        String status = statusSpinner.getSelectedItem().toString();

        taskDataSource.insertTask(title, content, status);
        finish(); // Retourne à l'activité précédente après l'ajout de la tâche
    }

    @Override
    protected void onDestroy() {
        taskDataSource.close();
        super.onDestroy();
    }
}
