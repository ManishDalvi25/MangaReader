package com.anime.otakus;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.widget.Button;
import android.widget.ImageView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class HomePage extends AppCompatActivity {

    Button search;
    ImageView account;
    ArrayList<String> mNames= new ArrayList<>();
    ArrayList<Integer > mImageUrls = new ArrayList<>();
    RecyclerView categoryRecyclerView;

    static int count = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        search = findViewById(R.id.btn_search);
        account = findViewById(R.id.btn_account);

        account.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, UserAccount.class);
            startActivity(i);
        });



        search.setOnClickListener(view -> {
            Intent i = new Intent(HomePage.this, Search.class);
            i.putExtra("category","All");
            startActivity(i);
        });

        recyclerView1();

        recyclerView2();

        recyclerView3();
    }

    void recyclerView1(){

        categoryRecyclerView =findViewById(R.id.recyclerViewHorizontal);
        initImageBitmaps();


        //DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Manga").child("Action");
        //ArrayList<MyMangaData> actionBooks =new ArrayList<>();

//        ref.addChildEventListener(new ChildEventListener() {
//            @Override
//            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//                MyMangaData mangaData = snapshot.getValue(MyMangaData.class);
//                Log.d("Mangadata",mangaData.getBook_url());
//                ((MyMangaAdapter)categoryRecyclerView.getAdapter()).update(mangaData);
//                //actionBooks.add(mangaData);
//            }
//
//            @Override
//            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onChildRemoved(@NonNull DataSnapshot snapshot) {
//
//            }
//
//            @Override
//            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
//
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });

//        MyMangaData[] myMangaData =new MyMangaData[]{
//                new MyMangaData("The Silence Voice", "2019 film",R.drawable.silence),
//                new MyMangaData("Drifting Home", "2009 film",R.drawable.driftinghome),
//                new MyMangaData("The Cat Return", "2019 film",R.drawable.catreturn),
//                new MyMangaData("The Ocean Waves", "2019 film",R.drawable.ocean),
//                new MyMangaData("A Whisker Away", "2020 film",R.drawable.whishker),
//                new MyMangaData("Bubbles", "2019 film",R.drawable.bubble1),
//
//        };
        categoryRecyclerView.setHasFixedSize(true);
        categoryRecyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        CategoryRecyclerViewAdapter myMangaAdapter =new CategoryRecyclerViewAdapter(HomePage.this,mNames,mImageUrls);
        categoryRecyclerView.setAdapter(myMangaAdapter);

        categoryRecyclerView.postDelayed(new Runnable() {
            @Override
            public void run() {
                // Call smooth scroll
                //if(((MyMangaAdapter)categoryRecyclerView.getAdapter()).getItemCount() != 0){
                categoryRecyclerView.smoothScrollToPosition(count % 5);
                count ++;
                categoryRecyclerView.postDelayed(this,4000);
                Log.d("Delay","count"+count);
                //}
            }
        },3000);
    }

    void recyclerView2(){
        RecyclerView recyclerView=findViewById(R.id.recyclerViewHorizontal2);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Manga").child("Latest");
        ArrayList<MyMangaData> actionBooks =new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MyMangaData mangaData = snapshot.getValue(MyMangaData.class);
                Log.d("Mangadata",mangaData.getBook_url());
                ((MyMangaAdapter)recyclerView.getAdapter()).update(mangaData);
                //actionBooks.add(mangaData);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.HORIZONTAL,false));
        MyMangaAdapter myMangaAdapter =new MyMangaAdapter(actionBooks,HomePage.this);
        recyclerView.setAdapter(myMangaAdapter);
    }

    void recyclerView3(){
        RecyclerView recyclerView=findViewById(R.id.recyclerView);

        DatabaseReference ref= FirebaseDatabase.getInstance().getReference("Manga").child("Popular");
        ArrayList<MyMangaData> actionBooks =new ArrayList<>();

        ref.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {
                MyMangaData mangaData = snapshot.getValue(MyMangaData.class);
                Log.d("Mangadata",mangaData.getBook_url());
                ((MyMangaAdapter)recyclerView.getAdapter()).update(mangaData);
                //actionBooks.add(mangaData);
            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onChildRemoved(@NonNull DataSnapshot snapshot) {

            }

            @Override
            public void onChildMoved(@NonNull DataSnapshot snapshot, @Nullable String previousChildName) {

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));
        MyMangaAdapter myMangaAdapter =new MyMangaAdapter(actionBooks,HomePage.this);
        recyclerView.setAdapter(myMangaAdapter);
    }

    private void initImageBitmaps(){

        mNames.add("ACTION");
        mImageUrls.add(R.drawable.actionimage);

        mNames.add("ADVENTURE");
        mImageUrls.add(R.drawable.adventureimage);

        mNames.add("THRILLER");
        mImageUrls.add(R.drawable.thrillerimage);

        mNames.add("HORROR");
        mImageUrls.add(R.drawable.horrorimage);

        mNames.add("DRAMA");
        mImageUrls.add(R.drawable.dramaimage);

        mNames.add("COMEDY");
        mImageUrls.add(R.drawable.comdeyimage);

//        mNames.add("SELF HELP");
//        mImageUrls.add(R.drawable.selfhelp);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        categoryRecyclerView.removeCallbacks(null);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        return super.onCreateOptionsMenu(menu);
    }
}