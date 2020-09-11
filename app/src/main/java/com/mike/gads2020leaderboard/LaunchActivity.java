package com.mike.gads2020leaderboard;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;

public class LaunchActivity extends AppCompatActivity {

    Thread splashTread;
    static Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_launch);


        splashTread = new Thread() {
            @Override
            public void run() {
                try {
                    int waited = 0;
                    while (waited < 3500) {
                        sleep(150);
                        waited += 100;
                    }
//                    registerDemo();

                    startActivity(new Intent(LaunchActivity.this, MainActivity.class));
                    finish();
//
//                    startActivity(new Intent(Splashscreen.this, MainActivity.class));
//                    finish();

                } catch (InterruptedException e) {
                } finally {
                }
            }


        };

        splashTread.start();
    }

}