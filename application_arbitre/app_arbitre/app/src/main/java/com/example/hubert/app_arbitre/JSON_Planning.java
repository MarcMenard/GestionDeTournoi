package com.example.hubert.app_arbitre;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.HashMap;

import static java.lang.Integer.parseInt;


public class JSON_Planning extends AppCompatActivity  {

    private String TAG = JSON_Planning.class.getSimpleName();

    private ProgressDialog pDialog;
    private ListView lv;
    private static int urlVPl = 0;
    private TextView tv;


    private JSONArray tableau;


    private String id_rencontre;
    private String nom1;
    private String nom2;
    private String idRenc;
    private String idRenc2;
    private String idRenc3;

    /*
    public static void setUrlPl(int valPl)
    {

        urlVPl = valPl;
    }*/

    //tableau des url permettant de récuperer les informations de la base de données via la page de script php stockée sur le serveur

    String[] url = {"http://192.168.1.21/get_data_match.php?id="+ LoginActivity.getIdArbitre(), "http://192.168.1.21/get_data_match.php?id=3"};

    ArrayList<HashMap<String, String>> Planning;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_json__planning);

        Planning = new ArrayList<>();
        lv = (ListView) findViewById(R.id.list);
        tv = (TextView) findViewById(R.id.id_rencontre);
        new GetGroupe().execute();

    }


    //On utilise la class Async task pour récupérer le json en faisant une requête HTTP

    private class GetGroupe extends AsyncTask<Void, Void, Void> {

        @Override
        protected void onPreExecute()
        {
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

                    tableau = new JSONArray(jsonStr);

                    // Récupération du tableau JSON (JSON Array)

                    // boucle parcourant le tableau JSON
                    for (int i = 0; i < tableau.length(); i++) {
                        JSONObject r = tableau.getJSONObject(i);

                        id_rencontre = r.getString("id_rencontre");
                        nom1 = r.getString("nom1");
                        nom2 = r.getString("nom2");
                        String heure = r.getString("heure");
                        String terrain = r.getString("terrain");

                        // hash map des informations d'une équipe
                        HashMap<String, String> result = new HashMap<>();

                        // assignation des clés du hashmap à leur valeur
                        result.put("id_rencontre", id_rencontre);
                        result.put("nom1", nom1);
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
                        public void run()
                        {
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
                                "Impossible de se connecter, vérifier la Wi-fi",
                                Toast.LENGTH_LONG)
                                .show();
                    }
                });

            }

            return null;
        }

        @Override
        protected void onPostExecute(Void result)
        {
            super.onPostExecute(result);

            //ferme la fenêtre de dialogue si elle est ouverte
            if (pDialog.isShowing())
                pDialog.dismiss();
            /**
             * Mets à jour les données JSON analysées dans une ListView
             * */
            ListAdapter adapter = new SimpleAdapter(JSON_Planning.this, Planning, R.layout.list_item, new String[]{"nom1","nom2","heure","terrain","id_rencontre"}, new int[]{R.id.nom_equipe1,R.id.nom_equipe2,R.id.heure,R.id.terrain,R.id.id_rencontre});

            lv.setAdapter(adapter);

            lv.setOnItemClickListener(new AdapterView.OnItemClickListener()
            {

                public void onItemClick(AdapterView<?> parent, View view, int position, long id){


                    String data=(String)parent.getItemAtPosition(position).toString();
                    String[] parts = data.split(",");

                    idRenc = parts[0];
                    idRenc2 = parts[1];
                    idRenc3 = parts[2];

                    for(int i = 0; i < idRenc3.length(); i++)
                    {
                        if(idRenc3.charAt(i) == '=')
                        {
                            idRenc3 = idRenc3.substring(i+1);
                            break;
                        }
                    }

                    for(int i = 0; i < idRenc.length(); i++)
                    {
                        if(idRenc.charAt(i) == '=')
                        {
                            idRenc = idRenc.substring(i+1);
                            break;
                        }
                    }

                    for(int i = 0; i < idRenc2.length(); i++)
                    {
                        if(idRenc2.charAt(i) == '=')
                        {
                            idRenc2 = idRenc2.substring(i+1);
                            break;
                        }
                    }

                    MainActivity.setTextTeamA(idRenc);
                    MainActivity.setTextTeamB(idRenc2);
                    LoginActivity2.setIdRencontre(idRenc3);


                    Intent intent = new Intent(getApplicationContext(), MainActivity.class);

                    startActivity(intent);


                }

            });
        }

    }

}


