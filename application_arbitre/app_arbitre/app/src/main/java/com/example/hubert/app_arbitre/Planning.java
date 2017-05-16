package com.example.hubert.app_arbitre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import static com.example.hubert.app_arbitre.R.id.text;
import static com.example.hubert.app_arbitre.R.id.textView;




public class Planning extends AppCompatActivity
{
    //CONFIGURATION DES DIFFERENTS TEXTVIEW
    private static String textTeam[] = {"a", "b", "c", "d", "e", "f", "g","h","i","j","k","l"};

    //INITIALISE LE NOM DES DEUX EQUIPES QUI SUIVRONT SUR LES ACTIVITES SUIVANTES
    private static String textTeamA = "Team X";
    private static String textTeamB = "Team Y";

    private int Matcharbitre = 4;

    //GET PERMETTANT AU AUTRES ACTIVITE DE REPRENDRE SES DONNEES (pour les noms des equipes)
    public static String GettextTeamA() {return textTeamA;}
    public static String GettextTeamB() {return textTeamB;}

    //INITIALISE LES TEXTES INFOS
    private static String info = "Arbitré";

    //LA FONCTION PRINCIPALE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_planning);

        //INITIALISE LES CHANGEMENTS DU NOM DES DEUX éQUIPES(VIS A VIS DU DéFAUT DU XML)
        for (int h = 0; h < 12; h++)
        {
            displayTextTeamC(textTeam[h]);
        }

        //INITIALISE LES CHANGEMENTS DES INFOS DU PLANNING
        displayTextinfo(info);

        //TABLEAU POUR LE NOM DES EQUIPE DU PLANNING
       /* android.view.View texxtTeam[]= new android.view.View[11];
        texxtTeam[0] =  findViewById(R.id.textView0);
        texxtTeam[1] =  findViewById(R.id.textView1);
        texxtTeam[2] =  findViewById(R.id.textView2);
        texxtTeam[3] =  findViewById(R.id.textView3);
        texxtTeam[4] =  findViewById(R.id.textView4);
        texxtTeam[5] =  findViewById(R.id.textView5);
        texxtTeam[6] =  findViewById(R.id.textView6);
        texxtTeam[7] =  findViewById(R.id.textView7);
        texxtTeam[8] =  findViewById(R.id.textView8);
        texxtTeam[9] =  findViewById(R.id.textView9);
        texxtTeam[10] = findViewById(R.id.textView10);
        texxtTeam[11] = findViewById(R.id.textView11);*/




        //TABLEAU POUR LES BOUTONS DU PLANNING
        Button[] tab_boutons = new Button[6];
        tab_boutons[0] = (Button) findViewById(R.id.fab0);
        tab_boutons[1] = (Button) findViewById(R.id.fab1);
        tab_boutons[2] = (Button) findViewById(R.id.fab2);
        tab_boutons[3] = (Button) findViewById(R.id.fab3);
        tab_boutons[4] = (Button) findViewById(R.id.fab4);
        tab_boutons[5] = (Button) findViewById(R.id.fab5);



       /* for(int x = 0; x < 6; x++)
        {
           tab_boutons[x].setOnClickListener(new View.OnClickListener()
            {
                textTeamA = textTeam[cpt * 2];
                textTeamB = textTeam[cpt * 2 + 1];
                @Override
                public void onClick(View view)
                {
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
          }); */




            //CHANGE LA VALEUR DU TEXTE DES PROCHAINES ACTIVITE SELON LE BOUTON CHOISI + //ACTIONS DE CHACUN DES BOUTONS --> AMENER AU MAINACTIVITY
            tab_boutons[0].setOnClickListener(new View.OnClickListener()
                {

                    @Override
                    public void onClick(View view)
                    {
                        textTeamA = textTeam[0];
                        textTeamB = textTeam[1];
                        startActivity(new Intent(getApplicationContext(), MainActivity.class));
                    }
                });

            tab_boutons[1].setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    textTeamA = textTeam[2];
                    textTeamB = textTeam[3];
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            tab_boutons[2].setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    textTeamA = textTeam[4];
                    textTeamB = textTeam[5];
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            tab_boutons[3].setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    textTeamA = textTeam[6];
                    textTeamB = textTeam[7];
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            tab_boutons[4].setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    textTeamA = textTeam[8];
                    textTeamB = textTeam[9];
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });

            tab_boutons[5].setOnClickListener(new View.OnClickListener()
            {

                @Override
                public void onClick(View view)
                {
                    textTeamA = textTeam[10];
                    textTeamB = textTeam[11];
                    startActivity(new Intent(getApplicationContext(), MainActivity.class));
                }
            });
        }

    //CHANGE LE TEXTE DES TEXTES INFOS DU PLANNING CHOISI + DESACTIVE LES BOUTONS DU PLANNING CHOISI
    private void displayTextinfo(String text)
    {
        for(int i = 0; i < Matcharbitre ; i++)
        {
            //TABLEAU POUR LES INFOS (A DROITE) DU PLANNING
            android.view.View textinfo[] = new android.view.View[6];
            textinfo[0] = findViewById(R.id.textView50);
            textinfo[1] = findViewById(R.id.textView51);
            textinfo[2] = findViewById(R.id.textView52);
            textinfo[3] = findViewById(R.id.textView53);
            textinfo[4] = findViewById(R.id.textView54);
            textinfo[5] = findViewById(R.id.textView55);

            //TABLEAU POUR LES BOUTONS DU PLANNING
            Button[] tab_boutons = new Button[6];
            tab_boutons[0] = (Button) findViewById(R.id.fab0);
            tab_boutons[1] = (Button) findViewById(R.id.fab1);
            tab_boutons[2] = (Button) findViewById(R.id.fab2);
            tab_boutons[3] = (Button) findViewById(R.id.fab3);
            tab_boutons[4] = (Button) findViewById(R.id.fab4);
            tab_boutons[5] = (Button) findViewById(R.id.fab5);

            //CHANGE LE TEXTE DES TEXTES INFOS DU PLANNING CHOISI
            TextView textView = (TextView) textinfo[i];
            textView.setText(String.valueOf(text));

            //DESACTIVANT LES BOUTONS DU PLANNING CHOISI
            tab_boutons[i].setEnabled(false);
        }
    }



    //VA CHANGER LE NOM DES EQUIPES
    private void displayTextTeamC(String text)
    {
        /*android.view.View texxtTeam[]= new android.view.View[11];
        texxtTeam[0] =  findViewById(R.id.textView0);
        texxtTeam[1] =  findViewById(R.id.textView1);
        texxtTeam[2] =  findViewById(R.id.textView2);
        texxtTeam[3] =  findViewById(R.id.textView3);
        texxtTeam[4] =  findViewById(R.id.textView4);
        texxtTeam[5] =  findViewById(R.id.textView5);
        texxtTeam[6] =  findViewById(R.id.textView6);
        texxtTeam[7] =  findViewById(R.id.textView7);
        texxtTeam[8] =  findViewById(R.id.textView8);
        texxtTeam[9] =  findViewById(R.id.textView9);
        texxtTeam[10] = findViewById(R.id.textView10);
        texxtTeam[11] = findViewById(R.id.textView11);*/

        switch (text)
        {
            case "a":
            TextView TextView0 = (TextView) findViewById(R.id.textView0);
            TextView0.setText(String.valueOf(text));
                break;

            case "b":
            TextView TextView1 = (TextView) findViewById(R.id.textView1);
            TextView1.setText(String.valueOf(text));
                break;

            case "c":
            TextView TextView2 = (TextView) findViewById(R.id.textView2);
            TextView2.setText(String.valueOf(text));
                break;

            case "d":
            TextView TextView3 = (TextView) findViewById(R.id.textView3);
            TextView3.setText(String.valueOf(text));
                break;

            case "e":
            TextView TextView4 = (TextView) findViewById(R.id.textView4);
            TextView4.setText(String.valueOf(text));
                break;

            case "f":
            TextView TextView5 = (TextView) findViewById(R.id.textView5);
            TextView5.setText(String.valueOf(text));
                break;

            case "g":
            TextView TextView6 = (TextView) findViewById(R.id.textView6);
            TextView6.setText(String.valueOf(text));
                break;

            case "h":
            TextView TextView7 = (TextView) findViewById(R.id.textView7);
            TextView7.setText(String.valueOf(text));
                break;

            case "i":
            TextView TextView8 = (TextView) findViewById(R.id.textView8);
            TextView8.setText(String.valueOf(text));
                break;

            case "j":
            TextView TextView9 = (TextView) findViewById(R.id.textView9);
            TextView9.setText(String.valueOf(text));
                break;

            case "k":
            TextView TextView10 = (TextView) findViewById(R.id.textView10);
            TextView10.setText(String.valueOf(text));
                break;

            case "l":
            TextView TextView11 = (TextView) findViewById(R.id.textView11);
            TextView11.setText(String.valueOf(text));
                break;
        }
    }


    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE du TEXTE SELECTIONNE
    private void displayTextTeamA(String text)
    {
        TextView TextView = (TextView) findViewById(R.id.textView8);
        TextView.setText(String.valueOf(text));
    }


    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE du TEXTE SELECTIONNE
    private void displayTextTeamB(String text)
    {
        TextView TextView = (TextView) findViewById(R.id.textView9);
        TextView.setText(String.valueOf(text));
    }

    //SI ON VEUT FAIRE RETOUR, IMPOSSIBLE
    @Override
    public void onBackPressed()
    {

    }
}


//3 variable EXEMPLE : H, U ET B --> U = 2*H  ET B = (2*H)+1

//if tab_boutons[0] else textView0 et text