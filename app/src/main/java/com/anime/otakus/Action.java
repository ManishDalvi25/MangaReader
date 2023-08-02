package com.anime.otakus;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Action extends AppCompatActivity {

    TextView categoryText;
    Button backPress, categorySearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.grid_layout);
        categoryText = findViewById(R.id.categorytextView);
        backPress = findViewById(R.id.btn_back);
        categorySearch = findViewById(R.id.btn_category_search);
        categoryText.setText("Action");

        backPress.setOnClickListener(view -> onBackPressed());
        categorySearch.setOnClickListener(view -> {
            Intent i = new Intent(this, Search.class);
            i.putExtra("category","Action");
            startActivity(i);
        });

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Manga/Action");
        final RecyclerView rv=findViewById(R.id.recyclerview_1);

        ArrayList<MyMangaData> fiction =new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                //  String name=dataSnapshot.getKey();
                MyMangaData b1 =dataSnapshot.getValue(MyMangaData.class);
                ((MyMangaAdapter)rv.getAdapter()).update(b1);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

//        Recyclerview_adapter adapter =new Recyclerview_adapter(rv,Thriller.this,fiction);
//        rv.setLayoutManager(new GridLayoutManager(this,2));
//        rv.setAdapter(adapter);

        rv.setHasFixedSize(true);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        MyMangaAdapter myMangaAdapter =new MyMangaAdapter(fiction,this);
        rv.setAdapter(myMangaAdapter);
    }

}

