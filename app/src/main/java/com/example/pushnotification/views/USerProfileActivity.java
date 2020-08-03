package com.example.pushnotification.views;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Toast;

import com.example.pushnotification.R;
import com.example.pushnotification.models.User;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.InstanceIdResult;

import java.util.Objects;

public class USerProfileActivity extends AppCompatActivity {
    private FirebaseAuth firebaseAuth;
    DatabaseReference databaseReference;
    public static final String NODE_USERS = "users";
    private String userToken;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        getUserToken();
        firebaseAuth = FirebaseAuth.getInstance();
        databaseReference = FirebaseDatabase.getInstance().getReference(NODE_USERS);

    }

    private void getUserToken() {
        FirebaseInstanceId.getInstance().getInstanceId()
                .addOnCompleteListener(new OnCompleteListener<InstanceIdResult>() {
                    @Override
                    public void onComplete(@NonNull Task<InstanceIdResult> task) {

                        if (task.isSuccessful()) {
                            userToken = Objects.requireNonNull(task.getResult()).getToken();
                            saveToFireBaseDataBase();
                        }
                    }
                });

    }

    private void saveToFireBaseDataBase() {
        String email = Objects.requireNonNull(firebaseAuth.getCurrentUser()).getEmail();
        User newUser = new User();
        newUser.setUserEmail(email);
        newUser.setUserToken(userToken);
        databaseReference.child(firebaseAuth.getCurrentUser().getUid())
                .setValue(newUser)
        .addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                if (task.isSuccessful()) {
                    Toast.makeText(USerProfileActivity.this, "User Added Successfully",
                            Toast.LENGTH_LONG).show();

                }else {

                }
            }
        });

    }
}
