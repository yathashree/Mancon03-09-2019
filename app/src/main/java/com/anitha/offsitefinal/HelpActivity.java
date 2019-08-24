package com.anitha.offsitefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.webkit.WebView;

import me.anwarshahriar.calligrapher.Calligrapher;

public class HelpActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"arial.ttf",true);


        //Load g4s tutoials
        WebView webView=(WebView)findViewById(R.id.webview);
        webView.loadUrl("https://www.youtube.com/playlist?list=PL-Sigh9mYW10ar9nwVhl7IjANijcYMfwH");


    }
}
