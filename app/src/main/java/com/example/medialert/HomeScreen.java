package com.example.medialert;

import static com.example.medialert.util.Constants.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;

public class HomeScreen extends AppCompatActivity {
    private static final String TAG = "HomeScreen";
    private Toolbar toolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_screen);
        toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
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

        Log.d(TAG, "onOptionsItemSelected: logout selected "+item.getItemId());

        if (item.getItemId() == R.id.logout){
            Log.d(TAG, "onOptionsItemSelected: logout selected");
            authentication.signOut();
            startActivity(new Intent(HomeScreen.this, Login.class));
            finish();
        }
        
        return super.onOptionsItemSelected(item);
    }
}