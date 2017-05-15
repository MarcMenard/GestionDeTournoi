package com.example.hubert.app_arbitre;

import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
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

public class MainActivity extends AppCompatActivity
{
    private boolean synchroniseC = true;
    private boolean synchroniseD = true;

    private static int goalsTeamA = 0;
    private static int goalsTeamB = 0;

    private static String textTeamA = Planning.GettextTeamA();
    private static String textTeamB = Planning.GettextTeamB();


    private long timeWhenStopped = 0;
    private boolean stopClicked = true;
    private Chronometer chronometer;


    private java.lang.String message;
    private java.lang.String message2;

    private static final int DIALOG_ALERT = 10;




    public static int GetgoalsTeamA()
    {
        return goalsTeamA;
    }
    public static int GetgoalsTeamB()
    {
        return goalsTeamB;
    }

    public static String GettextTeamA()
    {
        return textTeamA;
    }
    public static String GettextTeamB()
    {
        return textTeamB;
    }



    public void onClick(View view) {
        showDialog(DIALOG_ALERT);
    }




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


    //Si la réponse au message est d'annuler
    private final class CancelOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which) {
            Toast.makeText(getApplicationContext(), message2,
                    Toast.LENGTH_LONG).show();
        }
    }



    //si la reponse est de confirmer (Choix entre les boutons)
    private final class OkOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which)
        {


            //Change de page a un temps donné !!!!!!!


            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener() {
                public void onChronometerTick(Chronometer chronometer)
                {
                    String currentTime = chronometer.getText().toString();
                    if (currentTime.equals("00:10")) //Mettre le temps que l'on veut
                    {
                        startActivity(new Intent(getApplicationContext(), LoginActivity2.class));
                    }
                }});





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


            if (message == "Lancer le chronomètre ?")
            {
                if (stopClicked)
                {

                    chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
                    chronometer.start();
                    stopClicked = false;

                }

            }


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


            if (message == "Voulez vous remettre le score à 0 ?")
            {
                goalsTeamA = 0;
                goalsTeamB = 0;
                displayGoalsTeamA(goalsTeamA);
                displayGoalsTeamB(goalsTeamB);

            }

            if (message == "Donner un point à cette équipe ?")
            {
                goalsTeamA += 1;
                displayGoalsTeamA(goalsTeamA);
            }

            if (message == "Donner un point à cette Team ?")
            {
                goalsTeamB += 1;
                displayGoalsTeamB(goalsTeamB);
            }

            if (message == "Enlever un point à cette équipe ?")
            {
                goalsTeamA -= 1;
                if (goalsTeamA <= 0)
                {
                    goalsTeamA = 0;
                }
                displayGoalsTeamA(goalsTeamA);
            }

            if (message == "Enlever un point à cette Team ?")
            {
                goalsTeamB -= 1;
                if (goalsTeamB <= 0)
                {
                    goalsTeamB = 0;
                }
                displayGoalsTeamB(goalsTeamB);
            }

        }
    }


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        chronometer = (Chronometer) findViewById(R.id.chronometer);


        while (synchroniseC == true)
        {
            displayTextTeamA(textTeamA);
            synchroniseC = false;
        }

        while (synchroniseD == true)
        {
            displayTextTeamB(textTeamB);
            synchroniseD = false;
        }



    }

    // the method for when we press the 'reset' button
    public void resetButtonClick(View v)
    {
        message = "Êtes vous sur de remettre le temps à 0 ?";
        message2 = "Le chronomètre continue !";
        showDialog(DIALOG_ALERT);
    }


    // the method for when we press the 'start' button
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

    /**
     * Displays the given goals for Team A.
     */
    private void displayGoalsTeamA(int goals)
    {
        TextView goalsView = (TextView) findViewById(R.id.goals_teamA);
        goalsView.setText(String.valueOf(goals));
    }

    /**
     * Displays the given goals for Team B.
     */
    private void displayGoalsTeamB(int goals)
    {
        TextView goalsView = (TextView) findViewById(R.id.goals_teamB);
        goalsView.setText(String.valueOf(goals));
    }




    private void displayTextTeamA(String text)
    {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(text));
    }


    private void displayTextTeamB(String text)
    {
        TextView textView = (TextView) findViewById(R.id.textView2);
        textView.setText(String.valueOf(text));
    }


    /**
     * Displays the given fouls for Team A.
     */


    /**
     * Increases the number of goals the team A.
     */
    public void addGoalForTeamA(View view)
    {
        message = "Donner un point à cette équipe ?";
        message2 = "Point Inchangés";
        showDialog(DIALOG_ALERT);

    }

    /**
     * Increases the number of goals the team B.
     */
    public void addGoalForTeamB(View view)
    {

        message = "Donner un point à cette Team ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    /**
     * Increases the number of fouls the team A.
     */
    public void addFoulForTeamA(View view)
    {
        message = "Enlever un point à cette équipe ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    /**
     * Increases the number of fouls the team B.
     */
    public void addFoulForTeamB(View view)
    {
        message = "Enlever un point à cette Team ?";
        message2 = "Points Inchangés";
        showDialog(DIALOG_ALERT);
    }

    /**
     * Resets the results for both teams back to 0.
     */
    public void clearResults(View view)
    {
        message = "Voulez vous remettre le score à 0 ?";
        message2 = "Les points restent inchangés";
        showDialog(DIALOG_ALERT);
    }

    @Override
    public void onBackPressed() {
        new AlertDialog.Builder(this)
                .setIcon(android.R.drawable.ic_dialog_alert)
                .setTitle("Fermeture de l'activité")
                .setMessage("Êtes vous sur de fermer l'activité ?")
                .setPositiveButton("Oui", new DialogInterface.OnClickListener()
                {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finish();
                    }

                })
                .setNegativeButton("Non, noon !", null)
                .show();

    }

}
