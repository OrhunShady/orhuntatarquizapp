package com.orhun.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class aboutactivity extends AppCompatActivity {

    public Button backbutton1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_aboutactivity);
        backbutton1 = findViewById(R.id.backbutton1);
        backbutton1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent back1 =new Intent(aboutactivity.this, OrhunTatar.class);
                startActivity(back1);
            }
        });

    }
}