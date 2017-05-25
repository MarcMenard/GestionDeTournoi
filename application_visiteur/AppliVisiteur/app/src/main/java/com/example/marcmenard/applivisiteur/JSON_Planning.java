package com.example.marcmenard.applivisiteur;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;


public class JSON_Planning extends AppCompatActivity  {

    private String TAG = JSON_Planning.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private static int urlVPl = 0;

    public static void setUrlPl(int valPl){

        urlVPl = valPl;
    }

    //tableau des url permettant de récuperer les informations de la base de données via la page de script php stockée sur le serveur

    String[] url = {"http://192.168.1.102/get_data_planning.php", "http://192.168.1.102/get_data_planning.php"};

    ArrayList<HashMap<String, String>> Planning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_planning);

        Planning = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetGroupe().execute();

    }

    //On utilise la class Async task pour récupérer le json en faisant une requête HTTP

    private class GetGroupe extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Fenêtre informant l'utilisateur que les données sont en cours de récupération

            pDialog = new ProgressDialog(JSON_Planning.this);
            pDialog.setMessage("recuperation des donnees...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();

            String jsonStr;

            //fais une requête sur un des url du tableau d'url pour récupérer les données de la base de données
            jsonStr = sh.makeServiceCall(url[urlVPl]);

            //affiche la réponse de l'url dans le Log ce qui est très pratique pour savoir si il y a une erreur de connexion entre l'application et le serveur
            Log.e(TAG, "Réponse de l'url: " + jsonStr);

            if (jsonStr != null) {
                try {

                    JSONArray tableau = new JSONArray(jsonStr);

                    // Récupération du tableau JSON (JSON Array)

                    // boucle parcourant le tableau JSON
                    for (int i = 0; i < tableau.length(); i++) {
                        JSONObject r = tableau.getJSONObject(i);

                        String nom1 = r.getString("nom1");
                        String nbbuts1 = r.getString("nbbuts1");
                        String nbbuts2 = r.getString("nbbuts2");
                        String nom2 = r.getString("nom2");
                        String heure = r.getString("heure");
                        String terrain = r.getString("terrain");

                        // hash map des informations d'une équipe
                        HashMap<String, String> result = new HashMap<>();

                        // assignation des clés du hashmap à leur valeur

                        result.put("nom1", nom1);
                        result.put("nbbuts1", nbbuts1);
                        result.put("nbbuts2", nbbuts2);
                        result.put("nom2", nom2);
                        result.put("heure", heure);
                        result.put("terrain", terrain);

                        // ajoute une ligne d'une équipe et ses données respectives à un groupe
                        Planning.add(result);
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

            //ferme la fenêtre de dialogue si elle est ouverte

            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Mets à jour les données JSON analysées dans une ListView
             * */
            ListAdapter adapter = new SimpleAdapter(JSON_Planning.this, Planning, R.layout.tablelayout_item, new String[]{"nom1","nbbuts1","nbbuts2","nom2","heure","terrain"}, new int[]{R.id.nom_equipe1,R.id.score1,R.id.score2,R.id.nom_equipe2,R.id.heure,R.id.terrain});

            lv.setAdapter(adapter);

        }

    }

}

