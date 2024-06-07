package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import androidx.appcompat.app.AppCompatActivity;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Initialiser l'ImageView et définir le OnClickListener
        ImageView imagePlus = findViewById(R.id.ajouter_tâche);

        imagePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un Intent pour démarrer l'autre activité
                Intent intent = new Intent(MainActivity.this, Ajouter_taches_Activity.class);
                startActivity(intent);
            }
        });
    }
}
