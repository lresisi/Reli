package reli.reliapp.co.il.reli.splashScreen;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseUser;

import reli.reliapp.co.il.reli.R;
import reli.reliapp.co.il.reli.dataStructures.ReliUser;
import reli.reliapp.co.il.reli.login.LoginActivity;
import reli.reliapp.co.il.reli.main.MainActivity;
import reli.reliapp.co.il.reli.sidebar.GuidedTourActivity;

public class SplashScreen extends Activity {

    // Splash screen timer
    private static int SPLASH_TIME_OUT_MILLIS = 1000;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {

                ReliUser user = (ReliUser) (ParseUser.getCurrentUser());

                user.fetchInBackground(new GetCallback<ParseObject>() {
                    @Override
                    public void done(ParseObject parseObject, ParseException e) {

                        MainActivity.user = (ReliUser) parseObject;

                        // TODO - check that it works
                        handleFirstLogin();

                        // This method will be executed once the timer is over
                        Intent intent = new Intent(SplashScreen.this, LoginActivity.class);
                        startActivity(intent);

                        // close this activity
                        finish();
                    }

                    private void handleFirstLogin() {
                        boolean firstRun = getPreferences(Context.MODE_PRIVATE).getBoolean("firstRun", true);
                        if (firstRun) {
                            getPreferences(Context.MODE_PRIVATE).edit().putBoolean("firstRun", false).commit();

                            Intent intent = new Intent(SplashScreen.this, GuidedTourActivity.class);
                            startActivity(intent);
                        }
                    }
                });

            }


        }, SPLASH_TIME_OUT_MILLIS);
    }
}
