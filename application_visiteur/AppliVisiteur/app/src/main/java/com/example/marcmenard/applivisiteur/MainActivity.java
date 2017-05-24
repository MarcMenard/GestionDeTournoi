package com.example.marcmenard.applivisiteur;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.content.Intent;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView logo_accueil;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonCL = (Button) findViewById(R.id.buttoncl);
        buttonCL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, accueil_classement.class);
                startActivity(intent);
            }
        });

        Button buttonPL = (Button) findViewById(R.id.buttonpl);
        buttonPL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, JSON_Planning.class);
                startActivity(intent);
            }
        });

        logo_accueil = (ImageView) findViewById(R.id.logo_accueil);

    }
}
