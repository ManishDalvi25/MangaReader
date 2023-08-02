package com.anime.otakus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.squareup.picasso.Picasso;

public class MangaView extends AppCompatActivity {

    private String book_name,book_url,image_url,desc,uid;
    private TextView bookname,description;
    private ImageView bookimg;
    private FloatingActionButton loadPdf;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_view);
        Intent i = getIntent();
        book_name = i.getExtras().getString("book_name");
        book_url = i.getExtras().getString("book_url");
        desc = i.getExtras().getString("description");
        image_url = i.getExtras().getString("img_url");

        bookname = (TextView) findViewById(R.id.book_name_id);
        description=(TextView) findViewById(R.id.book_desc_id);
        bookimg = (ImageView)findViewById(R.id.book_img_id);
        loadPdf = findViewById(R.id.loadpdf);

        loadPdf.setOnClickListener(view -> {
            Intent pdfView = new Intent(this, MangaPdfView.class);
            pdfView.putExtra("book_url",book_url);
            startActivity(pdfView);
        });
        //read = (FloatingActionButton)findViewById(R.id.read);
        //download= (FloatingActionButton)findViewById(R.id.download);

        description.setText(desc);
        bookname.setText(book_name);
        Picasso.get().load(image_url).into(bookimg);
    }
}