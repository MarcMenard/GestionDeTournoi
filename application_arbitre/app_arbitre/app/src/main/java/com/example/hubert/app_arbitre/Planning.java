package com.example.hubert.app_arbitre;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import static com.example.hubert.app_arbitre.R.id.text;
import static com.example.hubert.app_arbitre.R.id.textView;




public class Planning extends AppCompatActivity
{
    //INITIALISE LE NOM DES DEUX EQUIPES
    private static String textTeamA = "Team X";
    private static String textTeamB =  "Team Y";


    //GET PERMETTANT AU AUTRES ACTIVITE DE REPRENDRE SES DONNEES (pour les noms des equipes)
    public static String GettextTeamA() {return textTeamA;}
    public static String GettextTeamB() {return textTeamB;}


    //LA FONCTION PRINCIPALE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        //INITIALISE LES CHANGEMENTS DU NOM DES DEUX éQUIPES(VIS A VIS DU DéFAUT DU XML)
            displayTextTeamB(textTeamB);
            displayTextTeamA(textTeamA);

        //TABLEAU POUR LES BOUTONS
        Button[] tab_boutons = new Button[6];
        tab_boutons[0] = (Button) findViewById(R.id.fab0);
        tab_boutons[1] = (Button) findViewById(R.id.fab1);
        tab_boutons[2] = (Button) findViewById(R.id.fab2);
        tab_boutons[3] = (Button) findViewById(R.id.fab3);
        tab_boutons[4] = (Button) findViewById(R.id.fab4);
        tab_boutons[5] = (Button) findViewById(R.id.fab5);

        //BOUCLE DESACTIVANT LES BOUTONS
        for(int i = 0; i < 4; i++)
        {
            tab_boutons[i].setEnabled(false);
        }

        //ACTIONS DE CHACUN DES BOUTONS --> AMENER AU MAINACTIVITY
        for(int x = 0; x < 6; x++)
        {
            tab_boutons[x].setOnClickListener(new View.OnClickListener()
            {
                @Override
                public void onClick(View view) {
                    Intent intent = new Intent(view.getContext(), MainActivity.class);
                    Button button = (Button) view;
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }
    }



    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE du TEXTE SELECTIONNE
    private void displayTextTeamA(String text)
    {
        TextView TextView = (TextView) findViewById(R.id.textView13);
        TextView.setText(String.valueOf(text));
    }

    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE du TEXTE SELECTIONNE
    private void displayTextTeamB(String text)
    {
        TextView TextView = (TextView) findViewById(R.id.textView14);
        TextView.setText(String.valueOf(text));
    }

}
