package com.example.hubert.app_arbitre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;

public class Planning extends AppCompatActivity
{

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        Button[] tab_boutons = new Button[6];
        tab_boutons[0] = (Button) findViewById(R.id.fab0);
        tab_boutons[1] = (Button) findViewById(R.id.fab1);
        tab_boutons[2] = (Button) findViewById(R.id.fab2);
        tab_boutons[3] = (Button) findViewById(R.id.fab3);
        tab_boutons[4] = (Button) findViewById(R.id.fab4);
        tab_boutons[5] = (Button) findViewById(R.id.fab5);

        for(int i = 0; i < 4; i++)
        {
            tab_boutons[i].setEnabled(false);
        }

        tab_boutons[0].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        tab_boutons[1].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        tab_boutons[2].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        tab_boutons[3].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        tab_boutons[4].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });

        tab_boutons[5].setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Intent intent = new Intent(view.getContext(),MainActivity.class);
                Button button=(Button) view;
                startActivity(new Intent(getApplicationContext(), MainActivity.class));
            }
        });
    }

}
