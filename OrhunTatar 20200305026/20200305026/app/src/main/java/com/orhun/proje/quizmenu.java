package com.orhun.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class quizmenu extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizmenu);


        Button eminembuton;

        Button menübuton;

        Button generalbuton;



        eminembuton =findViewById(R.id.eminembuton);
        eminembuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent em = new Intent(quizmenu.this,emtupquizactivity.class);
                startActivity(em);
            }


        });

        menübuton =findViewById(R.id.menübuton);
        menübuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent me = new  Intent(quizmenu.this, OrhunTatar.class);
                startActivity(me);
            }
        });


        generalbuton = findViewById(R.id.generalbuton);
        generalbuton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent gen = new Intent(quizmenu.this,generalquizactivity.class);
                startActivity(gen);
            }
        });

    }


}