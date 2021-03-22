package com.example.task2;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;

import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.animation.LinearInterpolator;
import android.widget.Toast;

import com.agrawalsuneet.dotsloader.loaders.LazyLoader;

import java.util.Timer;
import java.util.TimerTask;

public class Splash extends AppCompatActivity {
LazyLoader containerLL;
    private Timer tim;
    int i=0;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        containerLL=(LazyLoader)findViewById(R.id.ll);
        LazyLoader loader = new LazyLoader(this, 30, 20, ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected),
                ContextCompat.getColor(this, R.color.loader_selected));
        loader.setAnimDuration(500);
        loader.setFirstDelayDuration(100);
        loader.setSecondDelayDuration(200);
        loader.setInterpolator(new LinearInterpolator());
        containerLL.addView(loader);
        ConnectivityManager cm =
                (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);

        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        boolean isConnected = activeNetwork != null &&
                activeNetwork.isConnectedOrConnecting();
        if(isConnected==true){

            final long period=70;
            tim=new Timer();
            tim.schedule(new TimerTask() {
                @Override
                public void run() {
                    if(i<70){
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {

                            }
                        });

                        i++;
                    }else{
                        tim.cancel();
                        Intent i1=new Intent(Splash.this,Login.class);
                        startActivity(i1);
                        finish();
                    }
                }
            },0,period);
        }
        else{
            Toast.makeText(Splash.this,"Please Check Your Internet Connection",Toast.LENGTH_LONG).show();
        }
    }
}