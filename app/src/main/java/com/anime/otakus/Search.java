package com.anime.otakus;

import android.content.Intent;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.widget.EditText;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;

import java.util.ArrayList;

public class Search extends AppCompatActivity {

    private EditText searchtext;
    private DatabaseReference ref;
    private static int count;
    RecyclerView rv;
    ArrayList<MyMangaData> searchlist =new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        count=0;
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_search);
        Intent i = getIntent();
        String category = i.getExtras().getString("category");
        ref= FirebaseDatabase.getInstance().getReference("Manga").child(category);
        rv = (findViewById(R.id.search_rv));

        searchtext = (EditText)findViewById(R.id.search);

       /* searchtext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchtext.setText("");
            }
        });*/
        search("");
        searchtext.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                if(!s.toString().isEmpty()){
                    search(s.toString());
                }else {
                    search("");
                }

            }
        });
    }

    private void search(String s) {
        Query query = ref.orderByChild("name").startAt(s).endAt(s+"\uf8ff");
        if(count==1){
            ((MyMangaAdapter)rv.getAdapter()).clear();
        }
        query.addChildEventListener(new ChildEventListener() {
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

//        Recyclerview_adapter adapter =new Recyclerview_adapter(rv,Search.this,searchlist);
//        rv.setLayoutManager(new GridLayoutManager(Search.this,2));
//        rv.setAdapter(adapter);
        rv.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        MyMangaAdapter myMangaAdapter =new MyMangaAdapter(searchlist,Search.this);
        rv.setAdapter(myMangaAdapter);
        count=1;
    }
}
