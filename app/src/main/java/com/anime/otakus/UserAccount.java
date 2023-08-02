package com.anime.otakus;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserAccount extends AppCompatActivity {

    Button logout,back,okPress;
    TextView name,email;
    User user;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_account);
        logout = findViewById(R.id.btn_logout);
        name = findViewById(R.id.user_name);
        email = findViewById(R.id.user_email);
        back = findViewById(R.id.btn_back_account);
        okPress = findViewById(R.id.ok_back);
        SharedPreferences sh = getSharedPreferences("MySharedPref", MODE_PRIVATE);
        String uid = sh.getString("uid", "");
        final FirebaseDatabase database = FirebaseDatabase.getInstance();
        final DatabaseReference ref = database.getReference("Users").child(uid);

        ref.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                user = dataSnapshot.getValue(User.class);
                name.setText(user.getName());
                email.setText(user.getMail_id());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }

        });

        back.setOnClickListener(view -> onBackPressed());

        okPress.setOnClickListener(view -> onBackPressed());

        logout.setOnClickListener(view -> {
            SharedPreferences sharedPreferences = getSharedPreferences("MySharedPref", MODE_PRIVATE);
            SharedPreferences.Editor myEdit = sharedPreferences.edit();
            myEdit.putString("login", "no");
            myEdit.apply();
            Intent intent = new Intent(this,LoginPage.class);
            startActivity(intent);
            finish();
        });
    }
}