package com.example.projetfinal;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;


public class CustomSpinnerAdapter extends ArrayAdapter<String> {
    private Context mContext;
    private String[] mStatuses;
    private int[] mColors;


    public CustomSpinnerAdapter(Context context, String[] statuses, int[] colors) {
        super(context, 0, statuses);
        mContext = context;
        mStatuses = statuses;
        mColors = colors;
    }


    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent);
    }


    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        return createViewFromResource(position, convertView, parent);
    }


    private View createViewFromResource(int position, View convertView, ViewGroup parent) {
        View view = convertView;
        if (view == null) {
            view = LayoutInflater.from(mContext).inflate(R.layout.spinner_item, parent, false);
        }


        TextView textView = view.findViewById(R.id.status_text);
        View colorView = view.findViewById(R.id.status_color);


        textView.setText(mStatuses[position]);
        colorView.setBackgroundColor(mColors[position]);
        textView.setTextColor(mColors[position]);


        return view;
    }
}





