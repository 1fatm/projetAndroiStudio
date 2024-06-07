package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import androidx.appcompat.app.AppCompatActivity;

public class AjouterTaskActivity extends AppCompatActivity {

    private EditText titleEditText;
    private EditText contentEditText;
    private Button addButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_task);

        titleEditText = findViewById(R.id.title_edittext);
        contentEditText = findViewById(R.id.content_edittext);
        addButton = findViewById(R.id.add_button);

        addButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String title = titleEditText.getText().toString();
                String content = contentEditText.getText().toString();

                Intent resultIntent = new Intent();
                resultIntent.putExtra("task_title", title);
                resultIntent.putExtra("task_content", content);
                setResult(RESULT_OK, resultIntent);
                finish();
            }
        });
    }
}
