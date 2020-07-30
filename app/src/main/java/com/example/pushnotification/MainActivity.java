package com.example.pushnotification;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;

import java.util.Objects;

public class MainActivity extends AppCompatActivity {

    FirebaseAuth firebaseAuth;

    EditText emailEt, passwordEt;
    String email, password;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        emailEt = findViewById(R.id.email_et);
        passwordEt = findViewById(R.id.password_et);

        firebaseAuth = FirebaseAuth.getInstance();


    }



    public void getLoginDetails(View view) {
        email = emailEt.getText().toString();
        password = passwordEt.getText().toString();
         if (email.isEmpty()) {
             emailEt.setText("Field Required");
         }else if (password.isEmpty()) {
             passwordEt.setText("Required Field");
         }else if (password.length() < 6) {
             passwordEt.setText("Minimum Length is: 6 (SIX)");
         }else {
             postUserData(email, password);
         }
    }

    private void postUserData(String em, String pw) {
        firebaseAuth.createUserWithEmailAndPassword(em, pw)
                .addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            goToHomePage();

                        }else if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                            Toast.makeText(MainActivity.this, "User Already Exists", Toast.LENGTH_LONG).show();
//                            goToHomePage();
                        }else {
                            Toast.makeText(MainActivity.this, Objects.requireNonNull(task
                                    .getException()).getMessage(), Toast.LENGTH_LONG).show();
                        }
                    }
                });
    }

    private void goToHomePage() {

    }

    private void sendPushNotification() {


    }



}
