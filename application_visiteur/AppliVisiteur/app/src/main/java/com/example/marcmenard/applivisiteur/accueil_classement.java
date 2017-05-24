package com.example.marcmenard.applivisiteur;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import java.util.ArrayList;
import java.util.List;


public class accueil_classement extends AppCompatActivity implements AdapterView.OnItemSelectedListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_accueil_classement);

        Spinner spinner = (Spinner) findViewById(R.id.spinner);

        // Spinner click listener
        spinner.setOnItemSelectedListener(this);


        // Spinner Drop down elements
        List<String> items = new ArrayList<String>();
        items.add("Cliquez ici pour choisir votre groupe");
        items.add("Groupe 1");
        items.add("Groupe 2");
        items.add("Groupe 3");
        items.add("Groupe 4");
        items.add("Groupe 5");
        items.add("Groupe 6");
        items.add("Groupe 7");
        items.add("Groupe 8");

        // Crée l'adaptateur pour le filtre
        ArrayAdapter<String> dataAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, items);

        // configure le style à utiliser pour la liste déroulante du filtre
        dataAdapter.setDropDownViewResource(android.R.layout.simple_list_item_multiple_choice);

        // assigner l'adaptateur au filtre
        spinner.setAdapter(dataAdapter);



    }

    @Override
    public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {

        String item = parent.getItemAtPosition(position).toString();

        // switch permettant d'exécuter lancer l'activitée JSON_Classement en fonction du groupe choisi dans le filtre

        switch (position) {
            case 0:

                break;

            case 1:

                JSON_Classement.setUrl(0);
                Intent intent0 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent0);

                break;

            case 2:

                JSON_Classement.setUrl(1);
                Intent intent1 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent1);

                break;

            case 3:

                JSON_Classement.setUrl(2);
                Intent intent2 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent2);

                break;

            case 4:

                JSON_Classement.setUrl(3);
                Intent intent3 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent3);

                break;

            case 5:

                JSON_Classement.setUrl(4);
                Intent intent4 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent4);

                break;

            case 6:

                JSON_Classement.setUrl(5);
                Intent intent5 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent5);

                break;

            case 7:

                JSON_Classement.setUrl(6);
                Intent intent6 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent6);

                break;

            case 8:

                JSON_Classement.setUrl(7);
                Intent intent7 = new Intent(accueil_classement.this, JSON_Classement.class);
                startActivity(intent7);

                break;

        }

    }
    public void onNothingSelected(AdapterView<?> arg0) {
        // TODO Auto-generated method stub
    }


}
