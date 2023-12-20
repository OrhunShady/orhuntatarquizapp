package com.orhun.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class OrhunTatar extends AppCompatActivity {


    public Button buttonstart;
    public Button buttoninfo;
    public Button buttonabout;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        buttonstart = findViewById(R.id.buttonstart);
        buttoninfo = findViewById(R.id.infobutton);
        buttonabout = findViewById(R.id.aboutbutton);

        buttonabout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent about = new Intent(OrhunTatar.this,aboutactivity.class);
                startActivity(about);
            }
        });

        buttoninfo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent info = new Intent(OrhunTatar.this,infoactivity.class);
                startActivity(info);

            }
        });
        buttonstart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent quiz = new Intent(OrhunTatar.this,quizmenu.class);
                startActivity(quiz);
            }

        });

    }

}