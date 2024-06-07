package  com.example.projetfinal;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Spinner;
import androidx.appcompat.app.AppCompatActivity;


public class Ajouter_taches_Activity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ajouter_taches);


        Spinner statusSpinner = findViewById(R.id.status_button);


        String[] statuses = {"Todo", "In Progress", "Done", "Bug"};
        int[] colors = {Color.GRAY, Color.BLUE, Color.GREEN, Color.RED};


        CustomSpinnerAdapter adapter = new CustomSpinnerAdapter(this, statuses, colors);
        statusSpinner.setAdapter(adapter);
        ImageView imagePlus = findViewById(R.id.annuler_tache);

        imagePlus.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Créer un Intent pour démarrer l'autre activité
                Intent intent = new Intent(Ajouter_taches_Activity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}



