package com.anitha.offsitefinal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import me.anwarshahriar.calligrapher.Calligrapher;

public class EPostingSheet extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_eposting_sheet);

        Calligrapher calligrapher=new Calligrapher(this);
        calligrapher.setFont(this,"arial.ttf",true);
    }
}
