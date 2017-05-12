package com.example.hubert.app_arbitre;

import android.app.Dialog;
import android.content.DialogInterface;
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

import static android.R.attr.x;
import static com.example.hubert.app_arbitre.R.id.button;

public class MainActivity extends AppCompatActivity
{

    private int goalsTeamA = 0;
    private int goalsTeamB = 0;
    private int foulsTeamA = 0;
    private int foulsTeamB = 0;

    private long timeWhenStopped = 0;
    private boolean stopClicked;
    private Chronometer chronometer;

    private java.lang.String message;
    private java.lang.String message2;




    private static final int DIALOG_ALERT = 10;

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
            if (message == "Êtes vous sur de remettre le temps à 0 ?")
            {
                TextView secondsText = (TextView) findViewById(R.id.hmsTekst);
                timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                int seconds = (int) timeWhenStopped / 1000;
                secondsText.setText(Math.abs(seconds) + " seconds");
                chronometer.stop();
                stopClicked = true;

                chronometer.setBase(SystemClock.elapsedRealtime());
                timeWhenStopped = 0;
                secondsText.setText("0 seconds");

                Button btn = (Button) findViewById(R.id.start_button);
                btn.setEnabled(true);

                Button btn1 = (Button) findViewById(R.id.stop_button);
                btn1.setEnabled(true);
            }

            if (message == "Voulez vous vraiment mettre le temps en pause ?" )
            {
                if (!stopClicked)
                {
                    TextView secondsText = (TextView) findViewById(R.id.hmsTekst);
                    timeWhenStopped = chronometer.getBase() - SystemClock.elapsedRealtime();
                    int seconds = (int) timeWhenStopped / 1000;
                    secondsText.setText( Math.abs(seconds) + " seconds");
                    chronometer.stop();
                    stopClicked = true;

                    Button btn = (Button) findViewById(R.id.start_button);
                    btn.setEnabled(true);

                    Button btn1 = (Button) findViewById(R.id.stop_button);
                    btn1.setEnabled(false);

                }
            }

            if (message == "Voulez vous remettre le score à 0 ?")
            {
                goalsTeamA = 0;
                goalsTeamB = 0;
                foulsTeamA = 0;
                foulsTeamB = 0;
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
            chronometer.setBase(SystemClock.elapsedRealtime() + timeWhenStopped);
            chronometer.start();
            stopClicked = false;

        Button btn = (Button) findViewById(R.id.start_button);
        btn.setEnabled(false);

        Button btn1 = (Button) findViewById(R.id.stop_button);
        btn1.setEnabled(true);
    }

    // the method for when we press the 'stop' button
    public void stopButtonClick(View v)
    {
        message = "Voulez vous vraiment mettre le temps en pause ?";
        message2 = "Le chronomètre continue !";
        showDialog(DIALOG_ALERT);
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
