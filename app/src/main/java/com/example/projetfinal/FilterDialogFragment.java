package com.example.projetfinal;

import android.app.Dialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AlertDialog;
import androidx.fragment.app.DialogFragment;

public class FilterDialogFragment extends DialogFragment {

    @NonNull
    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
        LayoutInflater inflater = requireActivity().getLayoutInflater();
        View view = inflater.inflate(R.layout.dialog_filter, null);

        Spinner statusSpinner = view.findViewById(R.id.status_spinner);

        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getContext(),
                R.array.status_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusSpinner.setAdapter(adapter);

        builder.setView(view)
                .setTitle("Filter par")
                .setNegativeButton("Annuler", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        FilterDialogFragment.this.getDialog().cancel();
                    }
                })
                .setPositiveButton("Appliquer", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                        String selectedStatus = statusSpinner.getSelectedItem().toString();
                        ((MainActivity) getActivity()).filterTasks(selectedStatus);
                    }
                });
        return builder.create();
    }
}
