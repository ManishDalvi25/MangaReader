package com.anime.otakus;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.webkit.WebView;
import android.webkit.WebViewClient;


import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

public class MangaPdfView extends AppCompatActivity {

    WebView webView;
    String book_url;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manga_pdf_view);
        Intent i = getIntent();
        book_url = i.getExtras().getString("book_url");
        Log.d("Book url","Book url "+book_url);
        webView = findViewById(R.id.pdfView);
        webView.requestFocus();
        webView.getSettings().setSupportZoom(true);
        webView.getSettings().setJavaScriptEnabled(true);
        webView.setWebViewClient(new WebViewClient());
        try {
            webView.loadUrl("https://docs.google.com/gview?embedded=true&url="+ URLEncoder.encode(book_url, "ISO-8859-1"));
        } catch (UnsupportedEncodingException e) {
            throw new RuntimeException(e);
        }
    }
}