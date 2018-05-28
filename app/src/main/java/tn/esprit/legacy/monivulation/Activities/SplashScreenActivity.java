package tn.esprit.legacy.monivulation.Activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.v7.app.AppCompatActivity;

import me.wangyuwei.particleview.ParticleView;
import tn.esprit.legacy.monivulation.R;

public class SplashScreenActivity extends AppCompatActivity {

    /**
     * Called when the activity is first created.
     */

    private ParticleView mPv1;

    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.activity_splash_screen);

        mPv1 = findViewById(R.id.pv_1);


        /* New Handler to start the Menu-Activity
         * and close this Splash-Screen after some seconds.*/

        mPv1.postDelayed(new Runnable() {
            @Override
            public void run() {
                mPv1.startAnim();
            }
        }, 200);

        int SPLASH_DISPLAY_LENGTH = 4000;
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(SplashScreenActivity.this, LoginActivity.class);
                SplashScreenActivity.this.startActivity(mainIntent);
                SplashScreenActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);

    }


}