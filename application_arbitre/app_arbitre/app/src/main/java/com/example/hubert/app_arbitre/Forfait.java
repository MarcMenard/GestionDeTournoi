package com.example.hubert.app_arbitre;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Chronometer;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by Hubert on 18/05/2017.
 */

public class Forfait extends AppCompatActivity
{
    //INITIALISE LES POINTS A 0
    private static int pointTeamC = 0;
    private static int pointTeamD = 0;

    protected static int trueforfait = 0;

    //GET POUR LES BUTS
    public static int GetpointTeamA()
    {
        return pointTeamC;
    }
    public static int GetpointTeamB()
    {
        return pointTeamD;
    }

    //Déclare MessageFinale si il y a eu ou non un forfait
    public static int Gettrueforfait()
    {
        return trueforfait;
    }

    //VA RECHERCHER LE NOM DES DEUX EQUIPES DU PLANNING
    private static String textTeamA = Planning.GettextTeamA();
    private static String textTeamB = Planning.GettextTeamB();

    //ATTRIBUT SERVANT POUR LES MESSAGES DE CONFIRMATION
    private java.lang.String message;
    private java.lang.String message2;



    private static final int DIALOG_ALERT = 10;

    public void onClick(View view) {
        showDialog(DIALOG_ALERT);
    }


    //MéTHODE POUR LES MESSAGES
    @Override
    protected Dialog onCreateDialog(int id)
    {

        switch (id)
        {
            case DIALOG_ALERT:
                // Create out AlterDialog
                AlertDialog.Builder builder = new AlertDialog.Builder(this);
                builder.setMessage (message);
                builder.setCancelable(true);
                builder.setPositiveButton("Oui", new Forfait.OkOnClickListener());
                builder.setNegativeButton("Non, non !", new Forfait.CancelOnClickListener());
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }



    //SI LA REPONSE AU MESSAGE EST D'ANNULER
    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), message2,
                    Toast.LENGTH_LONG).show();
        }
    }



    //MéTHODE DE CONDITION, SI ON AAPUIE SUR "OK" AU MESSAGE
    private final class OkOnClickListener implements
            DialogInterface.OnClickListener {

        public void onClick(DialogInterface dialog, int which)
        {
            //Si la team A déclare Forfait
            if (message == "Cette équipe a donc perdu ?")
            {
                pointTeamC = 0;
                pointTeamD = 4;
                trueforfait = 1;
                startActivity(new Intent(getApplicationContext(), LoginActivity2.class));
            }

            //Si la team B déclare Forfait
            if (message == "Cette équipe déclare Forfait ?")
            {
                pointTeamC = 4;
                pointTeamD = 0;
                trueforfait = 1;
                startActivity(new Intent(getApplicationContext(), LoginActivity2.class));
            }
        }

    }


    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forfait);

        displayTextTeamA(textTeamA);
        displayTextTeamB(textTeamB);
    }



    public void Boutton1(View v)
    {
        message = "Cette équipe a donc perdu ?";
        message2 = "Inchangé";
        showDialog(DIALOG_ALERT);
    }

    public void Boutton2(View v)
    {
        message = "Cette équipe déclare Forfait ?";
        message2 = "Inchangé";
        showDialog(DIALOG_ALERT);
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


}
