package com.example.hubert.isonline;

import android.app.Activity;
import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;

public class MainActivity extends Activity
{
    private static final String TAG = MainActivity.class.getSimpleName();

    private TextView text;

    private final String loginUrl = "http://192.168.1.22/gestion_tournoi/login.php?action=login";

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Groupe = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetGroupe().execute();
        //new DownloadTask().execute("http://192.168.1.22/gestion_tournoi/login.php?action=login");
    }

    private void initComponents()
    {
        text = (TextView)findViewById(R.id.textView);
    }

    ArrayList<HashMap<String, String>> Groupe;

    //On utilise la class Async task pour récupérer le json en faisant une requête HTTP

    private class GetGroupe extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute()
        {
            super.onPreExecute();
        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();

            String jsonStr;

            //fais une requête sur un des url du tableau d'url pour récupérer les données de la base de données
            jsonStr = sh.makeServiceCall(getLis);

            //affiche la réponse de l'url dans le Log ce qui est très pratique pour savoir si il y a une erreur de connexion entre l'application et le serveur
            Log.e(TAG, "Réponse de l'url: " + jsonStr);

            if (jsonStr != null) {
                try {
                    JSONArray tableau = new JSONArray(jsonStr);

                    // Récupération du tableau JSON (JSON Array)

                    // boucle parcourant le tableau JSON
                    for (int i = 0; i < tableau.length(); i++) {
                        JSONObject r = tableau.getJSONObject(i);

                        String nom = r.getString("nom");
                        String points = r.getString("points");

                        // hash map des informations d'une équipe
                        HashMap<String, String> result = new HashMap<>();

                        // assignation des clés du hashmap à leur valeur

                        result.put("nom", nom);
                        result.put("points", points);

                        // ajoute une ligne d'une équipe et ses données respectives à un groupe
                        Groupe.add(result);
                    }

                } catch (final JSONException e) {
                    Log.e(TAG, "Erreur d'analyse du JSON : " + e.getMessage());
                    runOnUiThread(new Runnable() {
                        @Override
                        public void run() {
                            Toast.makeText(getApplicationContext(),
                                    "Erreur d'analyse du JSON : " + e.getMessage(),
                                    Toast.LENGTH_LONG)
                                    .show();
                        }
                    });

                }
            } else {
                Log.e(TAG, "Impossible de récupérer le JSON du serveur.");
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),
                                "Impossible de récupérer le JSON du serveur. Vérifier le LogCat, erreurs possibles!",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result) {
            super.onPostExecute(result);

            /**
             * Mets à jour les données JSON analysées dans une ListView
             * */
            ListAdapter adapter = new SimpleAdapter(MainActivity.this, Groupe, R.layout.list_item, new String[]{"nom","points"}, new int[]{R.id.nom_equipe,R.id.points});

            lv.setAdapter(adapter);

        }

    }

}

    /*private class DownloadTask extends AsyncTask<String, Void, String>
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
            Toast.makeText(MainActivity.this, result, Toast.LENGTH_LONG).show();
        }
    }

    private String downloadContent(String myurl) throws IOException {
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
    }*/
}