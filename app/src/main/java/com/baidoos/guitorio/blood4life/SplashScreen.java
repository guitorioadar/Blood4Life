package com.baidoos.guitorio.blood4life;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import com.baidoos.guitorio.blood4life.Profiles.ProfileUsers;

public class SplashScreen extends Activity{

    // Splash screen timer
    private static int SPLASH_TIME_OUT = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);


        new Handler().postDelayed(new Runnable() {

            /*
             * Showing splash screen with a timer. This will be useful when you
             * want to show case your app logo / company
             */

            @Override
            public void run() {
                // This method will be executed once the timer is over
                // Start your app main activity


                /*Intent intentMain = new Intent(SplashScreen.this, MainActivity.class);
                startActivity(intentMain);*/

                SharedPreferences pref = SplashScreen.this.getSharedPreferences("UserPref", 0);

                //Toast.makeText(SplashScreen.this, pref.getString("LoggedIn",null), Toast.LENGTH_SHORT).show();

                if (pref.contains("LoggedIn")){
                    Intent intent = new Intent(SplashScreen.this, ProfileUsers.class);
                    startActivity(intent);
                }else{
                    Intent i = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(i);
                }



                // close this activity
                finish();
            }
        }, SPLASH_TIME_OUT);
    }

}

