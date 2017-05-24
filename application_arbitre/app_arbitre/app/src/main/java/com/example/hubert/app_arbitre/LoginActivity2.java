package com.example.hubert.app_arbitre;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.annotation.TargetApi;
import android.app.Activity;
import android.app.Dialog;
import android.app.LoaderManager.LoaderCallbacks;
import android.content.CursorLoader;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.Loader;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.net.Uri;
import android.os.AsyncTask;
import android.os.Build;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.NonNull;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.inputmethod.EditorInfo;
import android.widget.ArrayAdapter;
import android.widget.AutoCompleteTextView;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.appindexing.Action;
import com.google.android.gms.appindexing.AppIndex;
import com.google.android.gms.appindexing.Thing;
import com.google.android.gms.common.api.GoogleApiClient;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import static android.Manifest.permission.READ_CONTACTS;

/**
 * A login screen that offers login via email/password.
 */


// LA CLASSE COMMENCE
public class LoginActivity2 extends AppCompatActivity implements LoaderCallbacks<Cursor>
{

    protected int pointTeamA = 0;
    protected int pointTeamB = 0;

//ON REPREND LES POITNS DE LA FIN DU MATCH (MAINACTIVITY)
    private static int goalsTeamA = MainActivity.GetgoalsTeamA();
    private static int goalsTeamB = MainActivity.GetgoalsTeamB();

//ON RéUTILISE LE NOM DES DEUX éQUIPE DU MATCH (MAINACTIVITY)
    private static String textTeamA = MainActivity.GettextTeamA();
    private static String textTeamB = MainActivity.GettextTeamB();

    //Pour savoir si il y a eu forfait
    private int forfait = Forfait.Gettrueforfait();

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

    //ATTRIBUT POUR LE CHRONOMETRE
    private Chronometer chronometer;

//ATTRIBUT SERVANT POUR LES MESSAGES DE CONFIRMATION
    private java.lang.String message;
    private java.lang.String message2;

    private static final int DIALOG_ALERT = 10;



    public void onClick(View view)
    {
        showDialog(DIALOG_ALERT);
    }


    //BOOLEAN SERVANT A AFFICHER UN MESSAGE D'INFORMATION
    private boolean premiermessage = true;


    //MéTHODE POUR LES MESSAGES
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
                AlertDialog dialog = builder.create();
                dialog.show();
        }
        return super.onCreateDialog(id);
    }


    //MéTHODE DE CONDITION, SI ON AAPUIE SUR "OK" AU MESSAGE
    private final class OkOnClickListener implements
            DialogInterface.OnClickListener
    {
        public void onClick(DialogInterface dialog, int which)
        {
            //CHANGE D'ACTIVITé A UN MOMENT DONNé
            //CHANGE D'ACTIVITé A UN MOMENT DONNé
            chronometer.setOnChronometerTickListener(new Chronometer.OnChronometerTickListener()
            {
                public void onChronometerTick(Chronometer chronometer)
                {
                    String currentTime = chronometer.getText().toString();
                    if (currentTime.equals("05:00")) //METTRE LE TEMPS SOUHAITÉ
                    {
                        MainActivity.SetgoalsTeamA(0);
                        MainActivity.SetgoalsTeamB(0);
                        Forfait.Settrueforfait(0);
                        startActivity(new Intent(getApplicationContext(), LoginActivity.class));
                    }
                }
            });


           //LE CHRONOMETRE SE LANCE
            if (message == "Vous avez 5 minutes pour rentrer de nouveau votre mot de passe et votre nom d'utilisateur ansi que de confirmer les scores")
            {
                chronometer.start();
            }

            //+1 POINT à L'éQUIPE A
            if (message == "Donner un point à cette équipe ?")
            {
                goalsTeamA += 1;
                displayGoalsTeamA(goalsTeamA);
            }

            //+1 POINT à L'éQUIPE B
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

        }
    }






    /**
     * Id to identity READ_CONTACTS permission request.
     */
    private static final int REQUEST_READ_CONTACTS = 0;

    /**
     * A dummy authentication store containing known user names and passwords.
     * TODO: remove after connecting to a real authentication system.
     */
    private static final String[] DUMMY_CREDENTIALS = new String[]
            {
            "foo@example.com:hello", "bar@example.com:world"
            };
    /**
     * Keep track of the login task to ensure we can cancel it if requested.
     */
    private UserLoginTask mAuthTask = null;

    // UI references.
    private AutoCompleteTextView mEmailView;
    private EditText mPasswordView;
    private View mProgressView;
    private View mLoginFormView;
    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    private GoogleApiClient client;




    //LA FONCTION PRINCIPALE
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login21);
        // Set up the login form.
        mEmailView = (AutoCompleteTextView) findViewById(R.id.email);
        populateAutoComplete();
        chronometer = (Chronometer) findViewById(R.id.chronometer);


        if (forfait != 1)
        {
            if (goalsTeamA > goalsTeamB)
            {
                pointTeamA = 4;
                pointTeamB = 1;
            }

            if (goalsTeamA < goalsTeamB)
            {
                pointTeamA = 1;
                pointTeamB = 4;
            }

            if (goalsTeamA == goalsTeamB)
            {
                pointTeamA = 2;
                pointTeamB = 2;
            }

            if (goalsTeamA < goalsTeamB)
            {
                pointTeamA = 1;
                pointTeamB = 4;
            }
        }
        else {
            goalsTeamA = 0;
            goalsTeamB = 0;
            pointTeamA = Forfait.GetpointTeamA();
            pointTeamB = Forfait.GetpointTeamB();

            //DÉSACTIVE LES BOUTONS SI FORFAIT
            findViewById(R.id.button).setEnabled(false);
            findViewById(R.id.button2).setEnabled(false);
            findViewById(R.id.button3).setEnabled(false);
            findViewById(R.id.button4).setEnabled(false);

        }



        //INITIALISE LES CHANGEMENTS DES POINTS(VIS A VIS DE 0) DES DEUX EQUIPES
            displayGoalsTeamA(goalsTeamA);
            displayGoalsTeamB(goalsTeamB);

        //INITIALISE LES CHANGEMENTS DU NOM DES DEUX éQUIPES(VIS A VIS DU DéFAUT)
            displayTextTeamA(textTeamA);
            displayTextTeamB(textTeamB);





        //BOUCLE S'ASSURANT QU'UN MESSAGE S'AFFICHE AUTOMATIQUEMENT, APPELLE ENSUITE LE CHRONOMETRE
        while(premiermessage == true)
        {
                message = "Vous avez 5 minutes pour rentrer de nouveau votre mot de passe et votre nom d'utilisateur ansi que de confirmer les scores";
                showDialog(DIALOG_ALERT);
                premiermessage = false;
        }



        mPasswordView = (EditText) findViewById(R.id.password);
        mPasswordView.setOnEditorActionListener(new TextView.OnEditorActionListener()
        {
            @Override
            public boolean onEditorAction(TextView textView, int id, KeyEvent keyEvent)
            {
                if (id == R.id.login || id == EditorInfo.IME_NULL)
                {
                    attemptLogin();
                    return true;
                }
                return false;

            }
        });

//SI LE MDP ET NDC SONT CORRECT, LANCE LA PAGE SUIVANTE APRES CLIQUE DU BOUTON CONNECTION
        Button mEmailSignInButton = (Button) findViewById(R.id.email_sign_in_button);
        mEmailSignInButton.setOnClickListener
                (new OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                    attemptLogin();
                if (mAuthTask != null)
                {
                    startActivity(new Intent(getApplicationContext(), MessageFinale.class));
                }
            }
        });


        mLoginFormView = findViewById(R.id.login_form);
        mProgressView = findViewById(R.id.login_progress);
        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client = new GoogleApiClient.Builder(this).addApi(AppIndex.API).build();
    }


    private void populateAutoComplete()
    {
        if (!mayRequestContacts())
        {
            return;
        }

        getLoaderManager().initLoader(0, null, this);
    }

    private boolean mayRequestContacts()
    {
        if (Build.VERSION.SDK_INT < Build.VERSION_CODES.M)
        {
            return true;
        }
        if (checkSelfPermission(READ_CONTACTS) == PackageManager.PERMISSION_GRANTED)
        {
            return true;
        }

        if (shouldShowRequestPermissionRationale(READ_CONTACTS))
        {
            Snackbar.make(mEmailView, R.string.permission_rationale, Snackbar.LENGTH_INDEFINITE)
                    .setAction(android.R.string.ok, new OnClickListener() {
                        @Override
                        @TargetApi(Build.VERSION_CODES.M)
                        public void onClick(View v) {
                            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
                        }
                    });
        }
        else
        {
            requestPermissions(new String[]{READ_CONTACTS}, REQUEST_READ_CONTACTS);
        }
        return false;
    }

    /**
     * Callback received when a permissions request has been completed.
     */

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions,
                                           @NonNull int[] grantResults)
    {
        if (requestCode == REQUEST_READ_CONTACTS)
        {
            if (grantResults.length == 1 && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
                populateAutoComplete();
            }
        }
    }


    private String email = "";
    private String password = "";

    /**
     * Attempts to sign in or register the account specified by the login form.
     * If there are form errors (invalid email, missing fields, etc.), the
     * errors are presented and no actual login attempt is made.
     */
    private void attemptLogin()
    {
        if (mAuthTask != null)
        {
            return;
        }


        // Reset errors.
        mEmailView.setError(null);
        mPasswordView.setError(null);

        // Store values at the time of the login attempt.
        email = mEmailView.getText().toString();
        password = mPasswordView.getText().toString();
        String loginUrl = "http://192.168.1.22/gestion_tournoi/login.php?username=" + email + "&password=" + password;

        new DownloadTask().execute(loginUrl);

        boolean cancel = false;
        View focusView = null;

        // Check for a valid password, if the user entered one. AFFICHE LES MESSAGE EN CAS D'ERREUR SUR LE MDP
        if (!isPasswordValid(password))
        {
            mPasswordView.setError("Mot de passe incorrect");
            focusView = mPasswordView;
            cancel = true;
        }

        // Check for a valid email address. AFFICHE LES MESSAGE EN CAS D'ERREUR SUR LE COMPTE
        if (TextUtils.isEmpty(email))
        {
            mEmailView.setError(getString(R.string.error_field_required));
            focusView = mEmailView;
            cancel = true;
        } else if (!isEmailValid(email)) {
            mEmailView.setError(getString(R.string.error_invalid_email));
            focusView = mEmailView;
            cancel = true;
        }

        if (cancel)
        {
            // There was an error; don't attempt login and focus the first
            // form field with an error.
            focusView.requestFocus();
        } else {
            // Show a progress spinner, and kick off a background task to
            // perform the user login attempt.
            showProgress(true);
            mAuthTask = new UserLoginTask(email, password);
            mAuthTask.execute((Void) null);
        }
    }

    private static final String TAG = MainActivity.class.getSimpleName();

    private class DownloadTask extends AsyncTask<String, Void, String>
    {

        @Override
        protected String doInBackground(String... params) {

            try {
                return downloadContent(params[0]);

            } catch (IOException e) {
                return "Unable to retrieve data. URL may be invalid.";
            }
        }

        @Override
        protected void onPostExecute(String result)
        {
            if (!result.contains("fail"))
            {
                startActivity(new Intent(getApplicationContext(), MessageFinale.class));
            }

            Toast.makeText(LoginActivity2.this, result, Toast.LENGTH_LONG).show();
        }
    }

    private String downloadContent(String myurl) throws IOException
    {
        InputStream is = null;
        int length = 500;

        try {
            URL url = new URL(myurl);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setReadTimeout(10000 );
            conn.setConnectTimeout(15000 );
            conn.setRequestMethod("GET");
            conn.setDoInput(true);
            conn.connect();
            int response = conn.getResponseCode();
            Log.d(TAG, "The response is: " + response);
            is = conn.getInputStream();

            String contentAsString = convertInputStreamToString(is, length);
            return contentAsString;
        } finally {
            if (is != null) {
                is.close();
            }
        }
    }

    public String convertInputStreamToString(InputStream stream, int length) throws IOException, UnsupportedEncodingException {
        Reader reader = null;
        reader = new InputStreamReader(stream, "UTF-8");
        char[] buffer = new char[length];
        reader.read(buffer);
        return new String(buffer);
    }

    //DéFINIT LES MOTS DE PASSE ET UTILISATEURS
    private boolean isEmailValid(String email)
    {
        //TODO: Replace this with your own logic
        return email.contains("arbitre") && email.length() == 7;
    }

    private boolean isPasswordValid(String password)
    {
        //TODO: Replace this with your own logic
        return password.length() == 4 && password.contains("foot");
    }


    /**
     * Shows the progress UI and hides the login form.
     */
    @TargetApi(Build.VERSION_CODES.HONEYCOMB_MR2)
    private void showProgress(final boolean show)
    {
        // On Honeycomb MR2 we have the ViewPropertyAnimator APIs, which allow
        // for very easy animations. If available, use these APIs to fade-in
        // the progress spinner.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.HONEYCOMB_MR2)
        {
            int shortAnimTime = getResources().getInteger(android.R.integer.config_shortAnimTime);

            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
            mLoginFormView.animate().setDuration(shortAnimTime).alpha(
                    show ? 0 : 1).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
                }
            });

            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mProgressView.animate().setDuration(shortAnimTime).alpha(
                    show ? 1 : 0).setListener(new AnimatorListenerAdapter()
            {
                @Override
                public void onAnimationEnd(Animator animation) {
                    mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
                }
            });
        }
        else
        {
            // The ViewPropertyAnimator APIs are not available, so simply show
            // and hide the relevant UI components.
            mProgressView.setVisibility(show ? View.VISIBLE : View.GONE);
            mLoginFormView.setVisibility(show ? View.GONE : View.VISIBLE);
        }
    }

    @Override
    public Loader<Cursor> onCreateLoader(int i, Bundle bundle)
    {
        return new CursorLoader(this,
                // Retrieve data rows for the device user's 'profile' contact.
                Uri.withAppendedPath(ContactsContract.Profile.CONTENT_URI,
                        ContactsContract.Contacts.Data.CONTENT_DIRECTORY), ProfileQuery.PROJECTION,

                // Select only email addresses.
                ContactsContract.Contacts.Data.MIMETYPE +
                        " = ?", new String[]{ContactsContract.CommonDataKinds.Email
                .CONTENT_ITEM_TYPE},

                // Show primary email addresses first. Note that there won't be
                // a primary email address if the user hasn't specified one.
                ContactsContract.Contacts.Data.IS_PRIMARY + " DESC");
    }


    @Override
    public void onLoadFinished(Loader<Cursor> cursorLoader, Cursor cursor)
    {
        List<String> emails = new ArrayList<>();
        cursor.moveToFirst();
        while (!cursor.isAfterLast())
        {
            emails.add(cursor.getString(ProfileQuery.ADDRESS));
            cursor.moveToNext();
        }
        addEmailsToAutoComplete(emails);
    }


    @Override
    public void onLoaderReset(Loader<Cursor> cursorLoader)
    {

    }

    private void addEmailsToAutoComplete(List<String> emailAddressCollection)
    {
        //Create adapter to tell the AutoCompleteTextView what to show in its dropdown list.
        ArrayAdapter<String> adapter =
                new ArrayAdapter<>(LoginActivity2.this,
                        android.R.layout.simple_dropdown_item_1line, emailAddressCollection);

        mEmailView.setAdapter(adapter);
    }

    /**
     * ATTENTION: This was auto-generated to implement the App Indexing API.
     * See https://g.co/AppIndexing/AndroidStudio for more information.
     */
    public Action getIndexApiAction()
    {
        Thing object = new Thing.Builder()
                .setName("Login Page") // TODO: Define a title for the content shown.
                // TODO: Make sure this auto-generated URL is correct.
                .setUrl(Uri.parse("http://[ENTER-YOUR-URL-HERE]"))
                .build();
        return new Action.Builder(Action.TYPE_VIEW)
                .setObject(object)
                .setActionStatus(Action.STATUS_TYPE_COMPLETED)
                .build();
    }

    @Override
    public void onStart()
    {
        super.onStart();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        client.connect();
        AppIndex.AppIndexApi.start(client, getIndexApiAction());
    }

    @Override
    public void onStop()
    {
        super.onStop();

        // ATTENTION: This was auto-generated to implement the App Indexing API.
        // See https://g.co/AppIndexing/AndroidStudio for more information.
        AppIndex.AppIndexApi.end(client, getIndexApiAction());
        client.disconnect();
    }


    private interface ProfileQuery
    {
        String[] PROJECTION =
                {
                ContactsContract.CommonDataKinds.Email.ADDRESS,
                ContactsContract.CommonDataKinds.Email.IS_PRIMARY,
                };

        int ADDRESS = 0;
        int IS_PRIMARY = 1;
    }

    /**
     * Represents an asynchronous login/registration task used to authenticate
     * the user.
     */
    public class UserLoginTask extends AsyncTask<Void, Void, Boolean>
    {

        private final String mEmail;
        private final String mPassword;

        UserLoginTask(String email, String password) {
            mEmail = email;
            mPassword = password;
        }

        @Override
        protected Boolean doInBackground(Void... params)
        {
            // TODO: attempt authentication against a network service.

            try {
                // Simulate network access.
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                return false;
            }

            for (String credential : DUMMY_CREDENTIALS) {
                String[] pieces = credential.split(":");
                if (pieces[0].equals(mEmail)) {
                    // Account exists, return true if the password matches.
                    return pieces[1].equals(mPassword);
                }
            }

            // TODO: register the new account here.
            return true;
        }

        @Override
        protected void onPostExecute(final Boolean success)
        {
            mAuthTask = null;
            showProgress(false);

            if (success) {
                finish();
            } else {
                mPasswordView.setError(getString(R.string.error_incorrect_password));
                mPasswordView.requestFocus();
            }
        }

        @Override
        protected void onCancelled()
        {
            mAuthTask = null;
            showProgress(false);
        }
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

    //SI ON VEUT FAIRE RETOUR, IMPOSSIBLE
    @Override
    public void onBackPressed()
    {

    }

}

