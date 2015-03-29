package school.cs492;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.WindowManager;

/**
 * Created by lingxi on 2/21/2015.
 */
public class SplashActivity extends Activity {
    private static long SLEEP_TIME = 5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        //disable title bar and notification bar.
        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_splash);

        //after 5 seconds, launch main activity.
        IntentLauncher launcher = new IntentLauncher();
        launcher.start();

    }

    private class IntentLauncher extends Thread {

        public void run() {
            try {
                //sleeping...
                Thread.sleep(SLEEP_TIME * 1000);
            } catch (Exception e) {
                Log.e("Splash class", e.getMessage());
            }

            //start main activity...
            Intent intent = new Intent(SplashActivity.this, ConfirmationActivity.class);
            SplashActivity.this.startActivity(intent);
            SplashActivity.this.finish();
        }
    }
}