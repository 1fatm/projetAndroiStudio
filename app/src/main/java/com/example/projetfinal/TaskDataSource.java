package com.example.projetfinal;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log; // Ajouter cette ligne
import java.util.ArrayList;
import java.util.List;

public class TaskDataSource {
    private SQLiteDatabase database;
    private TaskDatabaseHelper dbHelper;

    public TaskDataSource(Context context) {
        dbHelper = new TaskDatabaseHelper(context);
    }

    public void open() {
        database = dbHelper.getWritableDatabase();
    }

    public void close() {
        dbHelper.close();
    }

    public void insertTask(String title, String content, String status) {
        ContentValues values = new ContentValues();
        values.put(TaskDatabaseHelper.COLUMN_TITLE, title);
        values.put(TaskDatabaseHelper.COLUMN_CONTENT, content);
        values.put(TaskDatabaseHelper.COLUMN_STATUS, status);
        long insertedId = database.insert(TaskDatabaseHelper.TABLE_TASKS, null, values);
        Log.d("Database", "Inserted task with ID: " + insertedId);
    }

    public void updateTask(int taskId, String title, String content, String status) {
        ContentValues values = new ContentValues();
        values.put(TaskDatabaseHelper.COLUMN_TITLE, title);
        values.put(TaskDatabaseHelper.COLUMN_CONTENT, content);
        values.put(TaskDatabaseHelper.COLUMN_STATUS, status);

        String whereClause = TaskDatabaseHelper.COLUMN_ID + "=?";
        String[] whereArgs = { String.valueOf(taskId) };

        int rowsUpdated = database.update(TaskDatabaseHelper.TABLE_TASKS, values, whereClause, whereArgs);
        Log.d("Database", "Updated task with ID: " + taskId + ". Rows affected: " + rowsUpdated);
    }
    public List<Task> getAllTasks() {
        List<Task> taskList = new ArrayList<>();
        Cursor cursor = database.query(TaskDatabaseHelper.TABLE_TASKS,
                null, null, null, null, null, null);
        cursor.moveToFirst();
        while (!cursor.isAfterLast()) {
            Task task = cursorToTask(cursor);
            taskList.add(task);
            cursor.moveToNext();
        }
        cursor.close();
        return taskList;
    }

    private Task cursorToTask(Cursor cursor) {
        int idIndex = cursor.getColumnIndex(TaskDatabaseHelper.COLUMN_ID);
        int titleIndex = cursor.getColumnIndex(TaskDatabaseHelper.COLUMN_TITLE);
        int contentIndex = cursor.getColumnIndex(TaskDatabaseHelper.COLUMN_CONTENT);
        int statusIndex = cursor.getColumnIndex(TaskDatabaseHelper.COLUMN_STATUS);

        int id = cursor.getInt(idIndex);
        String title = cursor.getString(titleIndex);
        String content = cursor.getString(contentIndex);
        String status = cursor.getString(statusIndex);

        return new Task(id, title, content, status);
    }

    public Task getTaskById(int taskId) {
        Cursor cursor = database.query(TaskDatabaseHelper.TABLE_TASKS,
                new String[]{TaskDatabaseHelper.COLUMN_ID, TaskDatabaseHelper.COLUMN_TITLE, TaskDatabaseHelper.COLUMN_CONTENT, TaskDatabaseHelper.COLUMN_STATUS},
                TaskDatabaseHelper.COLUMN_ID + "=?", new String[]{String.valueOf(taskId)},
                null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
            Task task = cursorToTask(cursor);
            cursor.close();
            return task;
        } else {
            return null;
        }
    }
}
