
package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class Modifier_tache extends AppCompatActivity {

    private TaskDataSource taskDataSource;
    private Task task;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_modifier_tache);
        ImageView cancelImage = findViewById(R.id.annuler_tache);
        cancelImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Modifier_tache.this, MainActivity.class);
                startActivity(intent);
            }
        });
        Spinner statusSpinner = findViewById(R.id.status_button);
        String[] statuses = {"To Do", "Done", "In Progress", "Bug"};
        int[] colors = {R.color.gray, R.color.green, R.color.blue, R.color.red};

        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, statuses, colors);
        statusSpinner.setAdapter(adapter);

        taskDataSource = new TaskDataSource(this);
        taskDataSource.open();

        Intent intent = getIntent();
        int taskId = intent.getIntExtra("taskId", -1);

        if (taskId != -1) {
            task = taskDataSource.getTaskById(taskId);

            if (task != null) {
                EditText titleEditText = findViewById(R.id.task_title_textview);
                EditText contentEditText = findViewById(R.id.task_content_edittext);

                titleEditText.setText(task.getTitle());
                contentEditText.setText(task.getContent());

                int spinnerPosition = adapter.getPosition(task.getStatus());
                statusSpinner.setSelection(spinnerPosition);

                Button updateButton = findViewById(R.id.add_task_button);
                updateButton.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        String updatedTitle = titleEditText.getText().toString();
                        String updatedContent = contentEditText.getText().toString();
                        String updatedStatus = statusSpinner.getSelectedItem().toString();

                        task.setTitle(updatedTitle);
                        task.setContent(updatedContent);
                        task.setStatus(updatedStatus);

                        taskDataSource.updateTask(task);
                        Toast.makeText(Modifier_tache.this, "Tâche mise à jour", Toast.LENGTH_SHORT).show();
                        finish();
                    }
                });
            } else {
                Toast.makeText(this, "Erreur : Tâche non trouvée", Toast.LENGTH_SHORT).show();
                finish();
            }
        } else {
            Toast.makeText(this, "ID de tâche invalide", Toast.LENGTH_SHORT).show();
            finish();
        }
    }

    @Override
    protected void onDestroy() {
        taskDataSource.close();
        super.onDestroy();
    }
}
