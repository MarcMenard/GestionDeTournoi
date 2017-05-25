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
import java.util.Hashtable;


public class JSON_Classement extends AppCompatActivity  {

    private String TAG = JSON_Classement.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private static int urlV = 0;

    public static void setUrl(int val){

        urlV = val;
    }

    //tableau des url permettant de récuperer les informations de la base de données via la page de script php stockée sur le serveur

    String[] url = {"http://192.168.1.102/get_data_equipe.php?groupe_id=1","http://192.168.1.102/get_data_equipe.php?groupe_id=2","http://192.168.1.102/get_data_equipe.php?groupe_id=3","http://192.168.1.102/get_data_equipe.php?groupe_id=4","http://192.168.1.102/get_data_equipe.php?groupe_id=5","http://192.168.1.102/get_data_equipe.php?groupe_id=6","http://192.168.1.102/get_data_equipe.php?groupe_id=7","http://192.168.1.102/get_data_equipe.php?groupe_id=8"};

    ArrayList<HashMap<String, String>> Groupe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json_classement);

        Groupe = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        new GetGroupe().execute();

    }

    //On utilise la class Async task pour récupérer le json en faisant une requête HTTP

    private class GetGroupe extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute() {
            super.onPreExecute();

            // Fenêtre informant l'utilisateur que les données sont en cours de récupération

            pDialog = new ProgressDialog(JSON_Classement.this);
            pDialog.setMessage("recuperation des donnees...");
            pDialog.setCancelable(false);
            pDialog.show();

        }

        @Override
        protected Void doInBackground(Void... arg0) {

            HttpHandler sh = new HttpHandler();

            String jsonStr;

            //fais une requête sur un des url du tableau d'url pour récupérer les données de la base de données
            jsonStr = sh.makeServiceCall(url[urlV]);

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
                        String jouee = r.getString("jouee");

                        // hash map des informations d'une équipe
                        HashMap<String, String> result = new HashMap<>();

                        // assignation des clés du hashmap à leur valeur

                        result.put("nom", nom);
                        result.put("points", points);
                        result.put("jouee", jouee);

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

            //ferme la fenêtre de dialogue si elle est ouverte

            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Mets à jour les données JSON analysées dans une ListView
             * */
            ListAdapter adapter = new SimpleAdapter(JSON_Classement.this, Groupe, R.layout.list_item, new String[]{"nom","points","jouee"}, new int[]{R.id.nom_equipe,R.id.points,R.id.m_jouer});

            lv.setAdapter(adapter);

        }

    }

}