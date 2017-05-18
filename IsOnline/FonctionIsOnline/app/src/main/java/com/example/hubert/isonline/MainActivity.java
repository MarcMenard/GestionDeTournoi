package com.example.hubert.isonline;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.Network;
import android.net.NetworkInfo;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;



public class MainActivity extends AppCompatActivity
{
    int exitvalue1;
    String test;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        if (isOnline())
        {
            test = "True";
            displayTextTeamA(test);
        }
        else
        {
            test = "False";
            displayTextTeamA(test);
        }



    }

    public boolean isOnline() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo netInfo = manager.getActiveNetworkInfo();

        if (netInfo == null || !netInfo.isConnected() ||!netInfo.isAvailable()){
            return false;
        }
            return true;
    }

    private boolean isNetworkAvailable() {
        ConnectivityManager manager = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo networkInfo = manager.getActiveNetworkInfo();
        boolean isAvailable = false;
        if (networkInfo != null && networkInfo.isConnected()) {
            // Network is present and connected
            isAvailable = true;
        }
        return isAvailable;
    }

    public void fonctionisonline(View v)
    {
        isOnline();
    }

    private void displayTextTeamA(String text)
    {
        TextView textView = (TextView) findViewById(R.id.textView);
        textView.setText(String.valueOf(text));
    }


}
