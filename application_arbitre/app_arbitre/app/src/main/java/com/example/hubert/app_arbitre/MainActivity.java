package com.example.hubert.app_arbitre;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.os.SystemClock;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import static android.R.attr.contextClickable;
import static android.R.attr.x;
import static android.R.id.content;
import static com.example.hubert.app_arbitre.R.id.button;
import static com.example.hubert.app_arbitre.R.id.hmsTekst;

// LA CLASSE COMMENCE
public class MainActivity extends AppCompatActivity
{

    MediaPlayer alarm;

    //INITIALISE LES POINTS A 0
    private static int goalsTeamA = 0;
    private static int goalsTeamB = 0;

    //VA RECHERCHER LE NOM DES DEUX EQUIPES DU PLANNING
    private static String textTeamA = Planning.GettextTeamA();
    private static String textTeamB = Planning.GettextTeamB();


    private long timeWhenStopped = 0;
    private boolean stopClicked = true;

    //ATTRIBUT POUR LE CHRONOMETRE
    private Chronometer chronometer;

    //ATTRIBUT SERVANT POUR LES MESSAGES DE CONFIRMATION
    private java.lang.String message;
    private java.lang.String message2;

    private static final int DIALOG_ALERT = 10;


    //GET PERMETTANT AU AUTRES ACTIVITE DE REPRENDRE SES DONNEES

    //GET POUR LES BUTS
    public static int GetgoalsTeamA()
    {
        return goalsTeamA;
    }
    public static int GetgoalsTeamB()
    {
        return goalsTeamB;
    }

    //GET POUR LES NOMS DES DEUX EQUIPES
    public static String GettextTeamA()
    {
        return textTeamA;
    }
    public static String GettextTeamB()
    {
        return textTeamB;
    }

    public static void SetgoalsTeamA (int goalsTA)
    {
        goalsTeamA = goalsTA;
    }

    public static void SetgoalsTeamB (int goalsTB)
    {
        goalsTeamB = goalsTB;
    }

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
                builder.setPositiveButton("Oui", new OkOnClickListener());
                builder.setNegativeButton("Non, non !", new CancelOnClickListener());
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
            DialogInterface.OnClickListener
    {

        public void onClick(DialogInterface dialog, int which)
        {


            //CHANGE D'ACTIVITé A UN MOMENT DONNé
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
            {
                public void onChronometerTick(Chronometer chronometer)
                {
                    String currentTime = chronometer.getText().toString();
                    if (currentTime.equals("00:10")) //METTRE LE TEMPS SOUHAITé
                    {
                        alarm.start();
                        startActivity(new Intent(getApplicationContext(), LoginActivity2.class));

                    }
                }
            });



            //REINITIALISE LE CHRONOMETRE
            if (message == "Êtes vous sur de remettre le temps à 0 ?")
            {
                TextView secondsText = (TextView) findViewById(hmsTekst);
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                int seconds = (int) timeWhenStopped / 1000;
                secondsText.setText(Math.abs(seconds) + " seconds");
                chronometer.stop();
                stopClicked = true;

                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                secondsText.setText("0 seconds");
            }

            //LE CHRONOMETRE SE LANCE
            if (message == "Lancer le chronomètre ?")
            {
                if (stopClicked)
                {

                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    stopClicked = false;
                }
            }

            //MET EN PAUSE LE CHRONOMETRE
            if (message == "Voulez vous vraiment mettre le temps en pause ?" )
            {
                if (!stopClicked)
                {
                    TextView secondsText = (TextView) findViewById(hmsTekst);
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    secondsText.setText( Math.abs(seconds) + " seconds");
                    chronometer.stop();
                    stopClicked = true;
                }
            }

            //REINITIALISE LE SCORE
            if (message == "Voulez vous remettre le score à 0 ?")
            {
                goalsTeamA = 0;
                goalsTeamB = 0;
                displayGoalsTeamA(goalsTeamA);
                displayGoalsTeamB(goalsTeamB);
            }

            //+1 POINT à L'éQUIPE A
            if (message == "Donner un point à cette équipe ?")
            {
                goalsTeamA += 1;
                displayGoalsTeamA(goalsTeamA);
            }

            //+1 POINT à L'éQUIPE A
            if (message == "Donner un point à cette Team ?")
            {
                goalsTeamB += 1;
                displayGoalsTeamB(goalsTeamB);
            }

            //-1 POINT à L'éQUIPE A
            if (message == "Enlever un point à cette équipe ?")
            {
                goalsTeamA -= 1;
                if (goalsTeamA <= 0)
                {
                    goalsTeamA = 0;
                }
                displayGoalsTeamA(goalsTeamA);
            }

            //-1 POINT à L'éQUIPE B
            if (message == "Enlever un point à cette Team ?")
            {
                goalsTeamB -= 1;
                if (goalsTeamB <= 0)
                {
                    goalsTeamB = 0;
                }
                displayGoalsTeamB(goalsTeamB);
            }

            if (message == "Voulez vous vraiment faire Forfait ?")
            {
                startActivity(new Intent(getApplicationContext(), Forfait.class));
            }
        }
    }

    //LA FONCTION PRINCIPALE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);

        //INITIALISE LES CHANGEMENTS DU NOM DES DEUX éQUIPES(VIS A VIS DU DéFAUT)
            displayTextTeamA(textTeamA);
            displayTextTeamB(textTeamB);

        alarm = MediaPlayer.create(this, R.raw.sound);
    }

    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION REINITIALISANT LE TEMPS A 0 AU CLICK DU BOUTON "RESET"
    public void resetButtonClick(View v)
    {
        message = "Êtes vous sur de remettre le temps à 0 ?";
        message2 = "Le chronomètre continue !";
        showDialog(DIALOG_ALERT);
    }


    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION START OU PAUSE SELON LES CONDITION PREALABLE, AU CLICK DU BOUTON "START/PAUSE"
    public void startButtonClick(View v)
    {
        TextView secondsText = (TextView) findViewById(hmsTekst);
        if (stopClicked)
        {
            message = "Lancer le chronomètre ?";
            message2 = "Donc rester en pause..";
            showDialog(DIALOG_ALERT);
        }

        if (!stopClicked)
        {
            message = "Voulez vous vraiment mettre le temps en pause ?";
            message2 = "Le chronomètre continue !";
            showDialog(DIALOG_ALERT);
        }
    }

    public void forfait(View v)
    {
            message = "Voulez vous vraiment faire Forfait ?";
            message2 = "Le Match Continue !";
            showDialog(DIALOG_ALERT);
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


    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION DONNANT UN POINT A L'EQUIPE A, AU CLICK DU BOUTON
    public void addGoalForTeamA(View view)
    {
        message = "Donner un point à cette équipe ?";
        message2 = "Point Inchangés";
        showDialog(DIALOG_ALERT);

    }

    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION DONNANT UN POINT A L'EQUIPE B, AU CLICK DU BOUTON
    public void addGoalForTeamB(View view)
    {

        message = "Donner un point à cette Team ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION ENLEVANT UN POINT A L'EQUIPE A, AU CLICK DU BOUTON
    public void addFoulForTeamA(View view)
    {
        message = "Enlever un point à cette équipe ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION ENLEVANT UN POINT A L'EQUIPE B, AU CLICK DU BOUTON
    public void addFoulForTeamB(View view)
    {
        message = "Enlever un point à cette Team ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    //LANCE UN MESSAGE ET PERMET D'UTILISER LA FONCTION REINITIALISANT LES SCORES A 0, AU CLICK DU BOUTON
    public void clearResults(View view)
    {
        message = "Voulez vous remettre le score à 0 ?";
        message2 = "Les points restent inchangés";
        showDialog(DIALOG_ALERT);
    }


    //SI ON VEUT FAIRE RETOUR, IMPOSSIBLE
    @Override
    public void onBackPressed()
    {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Fermeture de l'activité")
                .setMessage("Êtes vous sur de revenir au planning ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which)
                    {
                        goalsTeamA = 0;
                        goalsTeamB = 0;
                        startActivity(new Intent(getApplicationContext(), Planning.class));
                    }
                })
                .setNegativeButton("Non, noon !", null)
                .show();
    }

}
