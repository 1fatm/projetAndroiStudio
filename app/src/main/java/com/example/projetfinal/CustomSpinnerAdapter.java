package  com.example.projetfinal;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class CustomSpinnerAdapter extends BaseAdapter {
    private Context context;
    private String[] statuses;
    private int[] colors;

    public CustomSpinnerAdapter(Context context, String[] statuses, int[] colors) {
        this.context = context;
        this.statuses = statuses;
        this.colors = colors;
    }

    @Override
    public int getCount() {
        return statuses.length;
    }

    @Override
    public Object getItem(int position) {
        return statuses[position];
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_item, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(statuses[position]);
        textView.setTextColor(Color.BLACK);
        return convertView;
    }

    @Override
    public View getDropDownView(int position, View convertView, ViewGroup parent) {
        if (convertView == null) {
            convertView = LayoutInflater.from(context).inflate(android.R.layout.simple_spinner_dropdown_item, parent, false);
        }
        TextView textView = convertView.findViewById(android.R.id.text1);
        textView.setText(statuses[position]);
        textView.setTextColor(colors[position]);
        return convertView;
    }
}
