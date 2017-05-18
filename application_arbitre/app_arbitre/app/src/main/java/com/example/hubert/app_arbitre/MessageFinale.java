package com.example.hubert.app_arbitre;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

/**
 * Created by Hubert on 16/05/2017.
 */

public class MessageFinale extends AppCompatActivity
{
    //ON REPREND LES POITNS DE LA FIN DU MATCH (LOGINACTIVITY2)
    private int goalsTeamA = LoginActivity2.GetgoalsTeamA();
    private int goalsTeamB = LoginActivity2.GetgoalsTeamB();

    //ON RéUTILISE LE NOM DES DEUX éQUIPE DU MATCH (LOGINACTIVITY2)
    private String textTeamA = LoginActivity2.GettextTeamA();
    private String textTeamB = LoginActivity2.GettextTeamB();

    //Pour savoir si il y a eu forfait
    private int forfait = Forfait.Gettrueforfait();

    //Initialise les points
    private int pointTeamA = 0;
    private int pointTeamB = 0;

    //LA FONCTION PRINCIPALE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_messagefinale);

        //lES CONDITIONS POUR ATTRIBUER LES POINTS
        if (goalsTeamA> goalsTeamB)
        {
            pointTeamA = 4;
            pointTeamB = 1;
        }

        if (goalsTeamA< goalsTeamB)
        {
                pointTeamA = 1;
                pointTeamB = 4;
        }

        if (goalsTeamA == goalsTeamB)
        {
            if (forfait != 1)
            {
            pointTeamA = 2;
            pointTeamB = 2;
            }
            else
            {
                pointTeamA = Forfait.GetpointTeamA();
                pointTeamB = Forfait.GetpointTeamB();
            }
        }

        if (goalsTeamA< goalsTeamB)
        {
            pointTeamA = 1;
            pointTeamB = 4;
        }


        //INITIALISE LES CHANGEMENTS DES POINTS(VIS A VIS DE 0) DES DEUX EQUIPES
        displayGoalsTeamA(goalsTeamA);
        displayGoalsTeamB(goalsTeamB);

        //INITIALISE LES CHANGEMENTS DU NOM DES DEUX éQUIPES(VIS A VIS DU DéFAUT)
        displayTextTeamA(textTeamA);
        displayTextTeamB(textTeamB);

        displaypointTeamA(pointTeamA);
        displaypointTeamB(pointTeamB);
    }


    public void retour_login(View v)
    {
        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
    }


    //VA CHANGER LE TEXTE AFFICHé DU NOMBRE DE BUT DE L'EQUIPE A
    private void displayGoalsTeamA(int goals)
    {
        TextView goalsView = (TextView) findViewById(R.id.goals_teamA);
        goalsView.setText(String.valueOf(goals));
    }

    //VA CHANGER LE TEXTE AFFICHé DU NOMBRE DE BUT DE L'EQUIPE B
    private void displayGoalsTeamB(int goals)
    {
        TextView goalsView = (TextView) findViewById(R.id.goals_teamB);
        goalsView.setText(String.valueOf(goals));
    }

    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE A
    private void displayTextTeamA(String text)
    {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(text));
    }

    //VA CHANGER LE NOM DE l'EQUIPE DE L'EQUIPE B
    private void displayTextTeamB(String text)
    {
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(text));
    }


    //VA CHANGER LES POINTS DE l'EQUIPE DE L'EQUIPE A
    private void displaypointTeamA(int text)
    {
        TextView textView = (TextView) findViewById(R.id.point_teamA);
        textView.setText(String.valueOf(text));
    }

    //VA CHANGER LES POINTS DE l'EQUIPE DE L'EQUIPE B
    private void displaypointTeamB(int text)
    {
        TextView textView = (TextView) findViewById(R.id.point_teamB);
        textView.setText(String.valueOf(text));
    }

    //SI ON VEUT FAIRE RETOUR, IMPOSSIBLE
    @Override
    public void onBackPressed()
    {

    }

}