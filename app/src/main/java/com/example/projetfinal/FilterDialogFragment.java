package com.example.projetfinal;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;
import java.util.ArrayList;
import java.util.List;

public class FilterDialogFragment extends DialogFragment {

    public interface FilterDialogListener {
        void onFilterApplied(List<String> selectedFilters);
    }

    private FilterDialogListener listener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            listener = (FilterDialogListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString()
                    + " must implement FilterDialogListener");
        }
    }

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(requireActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.filter_popup, null);

        CheckBox todoCheckBox = view.findViewById(R.id.filter_todo);
        CheckBox inProgressCheckBox = view.findViewById(R.id.filter_in_progress);
        CheckBox doneCheckBox = view.findViewById(R.id.filter_done);
        CheckBox bugCheckBox = view.findViewById(R.id.filter_bug);

        Button applyButton = view.findViewById(R.id.apply_filter);
        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                List<String> selectedFilters = new ArrayList<>();
                if (todoCheckBox.isChecked()) selectedFilters.add("To Do");
                if (inProgressCheckBox.isChecked()) selectedFilters.add("In Progress");
                if (doneCheckBox.isChecked()) selectedFilters.add("Done");
                if (bugCheckBox.isChecked()) selectedFilters.add("Bug");

                listener.onFilterApplied(selectedFilters);
                dismiss();
            }
        });

        builder.setView(view);
        return builder.create();
    }
}
