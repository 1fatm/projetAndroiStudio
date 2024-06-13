
package com.example.projetfinal;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import android.widget.ArrayAdapter;

public class CustomSpinnerAdapter extends ArrayAdapter<String> {

    private Context context;
    private String[] statuses;
    private int[] colors;

    public CustomSpinnerAdapter(@NonNull Context context, String[] statuses, int[] colors) {
        super(context, R.layout.spinner_item, statuses);
        this.context = context;
        this.statuses = statuses;
        this.colors = colors;
    }

    @Override
    public View getDropDownView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        return createView(position, convertView, parent);
    }

    private View createView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(R.layout.spinner_item, parent, false);
        }

        View colorView = convertView.findViewById(R.id.status_color);
        TextView statusTextView = convertView.findViewById(R.id.status_text);

        colorView.setBackgroundTintList(ContextCompat.getColorStateList(context, colors[position]));
        statusTextView.setText(statuses[position]);

        return convertView;
    }
}
