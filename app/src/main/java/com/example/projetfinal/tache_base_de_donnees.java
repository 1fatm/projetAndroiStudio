import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.util.ArrayList;
import java.util.List;

public class tache_base_de_donnees {
    private TaskDatabaseHelper dbHelper;

    public Taches_bases_de_donnees(Context context) {
        dbHelper = new TaskDatabaseHelper(context);
    }

    public void insertTask(String title, String content, String status) {
        SQLiteDatabase db = dbHelper.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(TaskDatabaseHelper.COLUMN_TITLE, title);
        values.put(TaskDatabaseHelper.COLUMN_CONTENT, content);
        values.put(TaskDatabaseHelper.COLUMN_STATUS, status);
        db.insert(TaskDatabaseHelper.TABLE_TASKS, null, values);
        db.close();
    }

    public List<ListItemTask> getAllTasks() {
        List<ListItemTask> taskList = new ArrayList<>();
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(TaskDatabaseHelper.TABLE_TASKS,
                new String[]{TaskDatabaseHelper.COLUMN_ID, TaskDatabaseHelper.COLUMN_TITLE, TaskDatabaseHelper.COLUMN_CONTENT, TaskDatabaseHelper.COLUMN_STATUS},
                null, null, null, null, null);

        if (cursor != null && cursor.moveToFirst()) {
            do {
                String title = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_TITLE));
                String content = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_CONTENT));
                String status = cursor.getString(cursor.getColumnIndexOrThrow(TaskDatabaseHelper.COLUMN_STATUS));
                ListItemTask task = new ListItemTask(title, content, status);
                taskList.add(task);
            } while (cursor.moveToNext());
            cursor.close();
        }
        db.close();
        return taskList;
    }
}
