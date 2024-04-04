package com.example.medialert;

import static com.example.medialert.util.Constants.authentication;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.medialert.adapter.RemindersAdapter;
import com.example.medialert.model.Reminder;
import com.example.medialert.util.ClickHandler;
import com.example.medialert.util.Constants;
import com.example.medialert.util.Notificationhelper;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.progressindicator.LinearProgressIndicator;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG = "HomeScreen";
    private Toolbar toolbar;

    private RecyclerView reminderRecycler;
    private List<Reminder> reminders = new ArrayList<>();

    private LinearProgressIndicator showLoading;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        reminderRecycler = findViewById(R.id.reminderRecycler);
        showLoading = findViewById(R.id.showLoading);

        fetchReminders();
        reminderRecycler.setLayoutManager(new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false));

    }

    private void fetchReminders() {
        Constants.remindersReference.get()
                .addOnCompleteListener(task -> {
                    if (task.isSuccessful() && task.isComplete()) {

                        reminders.clear();
                        for (DataSnapshot snapshot : task.getResult().getChildren()) {
                            reminders.add(snapshot.getValue(Reminder.class));
                        }

                        runOnUiThread(() -> {

                            if (!reminders.isEmpty()) {
                                reminderRecycler.setAdapter(
                                        new RemindersAdapter(reminders.stream().filter(reminder -> reminder.getUid().equals(authentication.getUid())).collect(Collectors.toList()),
                                                reminder -> {
                                                    //delete
                                                    showLoading.setVisibility(View.VISIBLE);
                                                    reminders.remove(reminder);
                                                    Constants.remindersReference.child(reminder.getId())
                                                            .removeValue((error, ref) -> {

                                                                if (error != null) {
                                                                    Log.d(TAG, "fetchReminders: error " + error.getMessage());
                                                                } else {
                                                                    Log.d(TAG, "fetchReminders: done deleting ");
                                                                    recreate();
                                                                    Notificationhelper.cancelNotification(getApplicationContext());
                                                                    showLoading.setVisibility(View.GONE);
                                                                }
                                                            });
                                                })
                                );

                            }
                        });

                    }

                }).addOnFailureListener(e -> Log.d(TAG, "onCreate: " + e.getMessage()));

    }

    public void addReminder(View view) {
        startActivity(new Intent(this, AddReminder.class));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.homemenu, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        Log.d(TAG, "onOptionsItemSelected: logout selected " + item.getItemId());

        if (item.getItemId() == R.id.logout) {
            Log.d(TAG, "onOptionsItemSelected: logout selected");
            authentication.signOut();
            startActivity(new Intent(HomeScreen.this, Login.class));
            finish();
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    protected void onResume() {
        super.onResume();
        Log.d(TAG, "onResume: resumed");

        reminderRecycler.setAdapter(null);
        reminders.clear();
        fetchReminders();
    }
}