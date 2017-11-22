package ilovecode.mycustomer;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;


/**
 * Created by Daryl on 21/11/2017.
 */

public class SplashScreen extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        Thread splashThread = new Thread() {

            public void run() {
                try {
                    sleep(2000);
                }  catch(InterruptedException e) {
                    e.printStackTrace();
                } finally {
                    // Launch the MainActivity class
                    Intent intent = new Intent(SplashScreen.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        };
        // To Start the thread
        splashThread.start();
    }
}
