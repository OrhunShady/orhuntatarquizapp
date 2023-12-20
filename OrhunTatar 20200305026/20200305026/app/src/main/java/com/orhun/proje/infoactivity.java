package com.orhun.proje;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class infoactivity extends AppCompatActivity {

    public Button buttonmain;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_infoactivity);
        buttonmain =findViewById(R.id.menü);
        buttonmain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent menu = new Intent(infoactivity.this, OrhunTatar.class);
                startActivity(menu);
            }
        });
    }
}