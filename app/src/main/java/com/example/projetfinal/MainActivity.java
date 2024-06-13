package com.example.projetfinal;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity implements FilterDialogFragment.FilterDialogListener {

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
                showFilterPopup();
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
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.filter_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_filter) {
            showFilterPopup();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void showFilterPopup() {

        LayoutInflater inflater = (LayoutInflater) getSystemService(LAYOUT_INFLATER_SERVICE);
        View popupView = inflater.inflate(R.layout.filter_popup, null);


        int width = LinearLayout.LayoutParams.WRAP_CONTENT;
        int height = LinearLayout.LayoutParams.WRAP_CONTENT;
        boolean focusable = true; // Touche hors popup ferme le popup
        final PopupWindow popupWindow = new PopupWindow(popupView, width, height, focusable);


        popupWindow.showAsDropDown(findViewById(R.id.action_filter));


        Button applyButton = popupView.findViewById(R.id.apply_filter);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CheckBox todoCheckBox = popupView.findViewById(R.id.filter_todo);
                CheckBox inProgressCheckBox = popupView.findViewById(R.id.filter_in_progress);
                CheckBox doneCheckBox = popupView.findViewById(R.id.filter_done);
                CheckBox bugCheckBox = popupView.findViewById(R.id.filter_bug);

                List<String> selectedFilters = new ArrayList<>();
                if (todoCheckBox.isChecked()) selectedFilters.add("To Do");
                if (inProgressCheckBox.isChecked()) selectedFilters.add("In Progress");
                if (doneCheckBox.isChecked()) selectedFilters.add("Done");
                if (bugCheckBox.isChecked()) selectedFilters.add("Bug");

                applyTaskFilter(selectedFilters);
                popupWindow.dismiss();
            }
        });
    }

    private void applyTaskFilter(List<String> filters) {
        List<Task> filteredTasks = new ArrayList<>();
        for (Task task : allTasks) {
            if (filters.contains(task.getStatus())) {
                filteredTasks.add(task);
            }
        }
        TaskAdapter adapter = new TaskAdapter(this, filteredTasks);
        taskListView.setAdapter(adapter);
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

    @Override
    protected void onDestroy() {
        taskDataSource.close();
        super.onDestroy();
    }

    @Override
    public void onFilterApplied(List<String> selectedFilters) {
        applyTaskFilter(selectedFilters);
    }
}
