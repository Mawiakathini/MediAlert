package com.example.medialert;

import static com.example.medialert.util.Constants.EMAIL_REGEX;
import static com.example.medialert.util.Constants.authentication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.AuthResult;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Login extends AppCompatActivity {

    private TextInputEditText email, password;
    private static final String TAG = "Login";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        email = findViewById(R.id.email);
        password = findViewById(R.id.password);

    }

    public void openResetPassword(View view) {
        startActivity(new Intent(this, ResetPassword.class));
    }

    public void loginUser(View view) {

        //check fields not empty
        if (email.getText().toString().isEmpty() || password.getText().toString().isEmpty()){
            Toast.makeText(this, "Fields are empty.", Toast.LENGTH_SHORT).show();
        }else{

            //validate email
            Pattern pattern = Pattern.compile(EMAIL_REGEX);
            Matcher matcher = pattern.matcher(email.getText().toString());

            if (!matcher.matches()){
                Toast.makeText(this, "Wrong email format", Toast.LENGTH_SHORT).show();
            }else{

                if (password.getText().toString().length() < 6){
                    Toast.makeText(this, "Password should be 6 character longer.", Toast.LENGTH_SHORT).show();
                }else {

                    //login user
                    String mail = email.getText().toString();
                    String passw = password.getText().toString();

                    authentication.signInWithEmailAndPassword(mail, passw)
                            .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                                @Override
                                public void onComplete(@NonNull Task<AuthResult> task) {

                                    if (task.isSuccessful() && task.isComplete()){

                                        //move to home screen
                                        startActivity(new Intent(getApplicationContext(), HomeScreen.class));
                                        finish();

                                    }
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Login.this, "Failed due to "+e.getMessage(), Toast.LENGTH_SHORT).show();
                                    Log.d(TAG, "onFailure: "+e.getMessage());
                                }
                            });


                }
            }
        }

    }

    public void openRegister(View view) {
        startActivity(new Intent(this, Register.class));
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        if (authentication.getCurrentUser() != null) {
            finish();
        }
    }
}