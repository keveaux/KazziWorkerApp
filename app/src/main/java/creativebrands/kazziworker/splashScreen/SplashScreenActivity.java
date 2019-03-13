package creativebrands.kazziworker.splashScreen;

import android.app.Activity;
import android.content.Intent;
import android.os.Handler;
import android.os.Bundle;

import creativebrands.kazziworker.LoginPackage.LoginActivity;
import creativebrands.kazziworker.R;

public class SplashScreenActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash_screen);

        final Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(SplashScreenActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        }, 1500);
    }
}
