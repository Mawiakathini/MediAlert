package com.example.medialert;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.medialert.model.Dose;
import com.example.medialert.model.Duration;
import com.example.medialert.model.Reminder;
import com.example.medialert.model.ReminderTime;
import com.example.medialert.util.Constants;
import com.example.medialert.util.Notificationhelper;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.android.material.textfield.TextInputEditText;

import java.time.LocalTime;

public class AddReminder extends AppCompatActivity {

    private static final String TAG = "AddReminder";
    private TextInputEditText medicineName, dose, duration;
    private RadioGroup frequencyGroup;

    private Spinner doseSpinner, durationSpinner;
    private RadioButton beforeMeal, afterMeal;

    private TimePicker picker;

    private String frequencyStr;

    private LinearProgressIndicator saveReminderProgress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_reminder);

        medicineName = findViewById(R.id.medicineName);
        picker = findViewById(R.id.picker);
        dose = findViewById(R.id.dose);
        frequencyGroup = findViewById(R.id.frequencyGroup);
        doseSpinner = findViewById(R.id.doseSpinner);
        durationSpinner = findViewById(R.id.durationSpinner);
        beforeMeal = findViewById(R.id.beforeMeal);
        afterMeal = findViewById(R.id.afterMeal);
        duration = findViewById(R.id.duration);
        saveReminderProgress = findViewById(R.id.saveReminderProgress);


        //set spnner adapter
        ArrayAdapter<CharSequence> dosesAdapter = ArrayAdapter.createFromResource(this, R.array.medicine_types, android.R.layout.simple_spinner_item);
        dosesAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        doseSpinner.setAdapter(dosesAdapter);

        ArrayAdapter<CharSequence> durationAdapter = ArrayAdapter.createFromResource(this, R.array.duration, android.R.layout.simple_spinner_item);
        durationAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        durationSpinner.setAdapter(durationAdapter);


        frequencyGroup.setOnCheckedChangeListener((group, checkedId) -> {
            if (checkedId == R.id.once)
                frequencyStr = "Once";
            else if (checkedId == R.id.twice) {
                frequencyStr = "Two times";
            } else if (checkedId == R.id.thrice) {
                frequencyStr = "Three times";
            }
        });

    }

    public void saveReminder(View view) {
        //validate details and save reminder
        if (validateFields()) {
            saveReminderProgress.setVisibility(View.VISIBLE);
            String reminderId = Constants.remindersReference.push().getKey();

            String consumeTime;

            if (beforeMeal.isChecked() && afterMeal.isChecked())
                consumeTime = "Before or After meal";
            else if (beforeMeal.isChecked() && !afterMeal.isChecked()) {
                consumeTime = "Before meal";
            } else {
                consumeTime = "After meal";
            }


            Reminder reminder = new Reminder(
                    reminderId,
                    Constants.authentication.getUid(),
                    new ReminderTime(picker.getHour(), picker.getMinute()),
                    medicineName.getText().toString(),
                    new Dose(Long.valueOf(dose.getText().toString()), doseSpinner.getSelectedItem().toString()),
                    consumeTime,
                    frequencyStr,
                    new Duration(Integer.valueOf(duration.getText().toString()), durationSpinner.getSelectedItem().toString())
            );

            Constants.remindersReference.child(reminderId)
                    .setValue(reminder)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful() && task.isComplete()){
                            saveReminderProgress.setVisibility(View.GONE);
                            Notificationhelper.scheduleNotification(getApplicationContext(), reminder);
                            finish();
                        }
                    }).addOnFailureListener(e -> Log.d(TAG, "saveReminder: " + e.getMessage()));

        }
    }

    private boolean validateFields() {

        if (medicineName.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide a medicine name.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (dose.getText().toString().isEmpty()) {
            Toast.makeText(this, "Please provide a dosage.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (doseSpinner.getSelectedItem().toString().equals("--select--")) {
            Toast.makeText(this, "Please select the dosage type.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (!beforeMeal.isChecked() && !afterMeal.isChecked()) {
            Toast.makeText(this, "When do you want to take the medicine.", Toast.LENGTH_SHORT).show();
            return false;
        }
        if (frequencyGroup.getCheckedRadioButtonId() == -1) {
            Toast.makeText(this, "Select a frequency for your medication.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (duration.getText().toString().isEmpty()) {
            Toast.makeText(this, "Provide the duration for the reminder.", Toast.LENGTH_SHORT).show();
            return false;
        }

        if (durationSpinner.getSelectedItem().toString().equals("--select--")) {
            Toast.makeText(this, "Provide the duration type for the reminder.", Toast.LENGTH_SHORT).show();
            return false;
        }


        return true;
    }
}