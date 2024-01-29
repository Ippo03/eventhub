package com.example.eventhub.view.Util;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnMultiChoiceClickListener;
import android.util.AttributeSet;
import android.widget.ArrayAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class MultiSpinner extends androidx.appcompat.widget.AppCompatSpinner implements
        OnMultiChoiceClickListener, DialogInterface.OnCancelListener {

    private List<String> items;
    private boolean[] selected;
    private String defaultText;
    private MultiSpinnerListener listener;

    public MultiSpinner(Context context) {
        super(context);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1) {
        super(arg0, arg1);
    }

    public MultiSpinner(Context arg0, AttributeSet arg1, int arg2) {
        super(arg0, arg1, arg2);
    }

    @Override
    public void onClick(DialogInterface dialog, int which, boolean isChecked) {
        if (isChecked)
            selected[which] = true;
        else
            selected[which] = false;
    }

    @Override
    public void onCancel(DialogInterface dialog) {
        updateAdapter();
    }

    public void updateAdapter() {
        StringBuffer spinnerBuffer = new StringBuffer();
        boolean someSelected = false;
        int selectCount = 0;

        for (int i = 0; i < items.size(); i++) {
            if (selected[i]) {
                spinnerBuffer.append(items.get(i));
                spinnerBuffer.append(", ");
                someSelected = true;
                selectCount++;
            }
        }

        String spinnerText;
        if (selectCount == items.size()) {
            spinnerText = defaultText;
        } else if (someSelected) {
            spinnerText = spinnerBuffer.toString();
            if (spinnerText.length() > 2) {
                spinnerText = spinnerText.substring(0, spinnerText.length() - 2);
            }
        } else {
            spinnerText = defaultText;
        }

        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, new String[]{spinnerText});
        setAdapter(adapter);
        listener.onItemsSelected(selected);
    }

    @Override
    public boolean performClick() {
        AlertDialog.Builder builder = new AlertDialog.Builder(getContext());
        builder.setMultiChoiceItems(
                items.toArray(new CharSequence[items.size()]), selected, this);

        // Positive (OK) Button
        builder.setPositiveButton(android.R.string.ok,
                new DialogInterface.OnClickListener() {

                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
        // Negative (Cancel) Button
        builder.setNeutralButton("Clear Selection",
                new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Arrays.fill(selected, true);  // This ensures all values in 'selected' are true
                        onCancel(dialog); // Update the spinner's text and close the dialog
                    }
        });

        builder.setOnCancelListener(this);
        builder.show();
        return true;
    }

    public void setItems(List<String> items, String text,
                         MultiSpinnerListener listener) {
        Collections.sort(items);
        this.items = items;
        this.defaultText = text;
        this.listener = listener;

        // none selected by default
        selected = new boolean[items.size()];
        Arrays.fill(selected, true);  // This ensures all values in 'selected' are true
        // none text on the spinner
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { text });
        setAdapter(adapter);
    }

    public void clearSelection() {
        Arrays.fill(selected, true);  // Set all items to be selected
        defaultText = "All Genres";   // Reset the default text
        ArrayAdapter<String> adapter = new ArrayAdapter<>(getContext(),
                android.R.layout.simple_spinner_item, new String[] { defaultText });
        setAdapter(adapter);
    }

    public String getSelectedItems() {
        int count = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                count++;
            }
        }

        String[] selectedItems = new String[count];
        int j = 0;
        for (int i = 0; i < selected.length; i++) {
            if (selected[i]) {
                selectedItems[j] = items.get(i).trim(); // remove trailing spaces
                j++;
            }
        }
        String selection = String.join(",", selectedItems); // join with comma
        return selection;
    }

    public void setSelectedItems(Set<String> selectedItems) {
        if (selectedItems == null) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            selected[i] = selectedItems.contains(items.get(i));
        }

        updateAdapter();
    }

    public void setSelectedItemsBoolean(boolean[] selectedItems) {
        if (selectedItems == null) {
            return;
        }

        for (int i = 0; i < items.size(); i++) {
            selected[i] = selectedItems[i];
        }

        updateAdapter();
    }

    public interface MultiSpinnerListener {
        public void onItemsSelected(boolean[] selected);
    }
}
