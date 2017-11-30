package co.hien.fott.android.activities;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.Nullable;

import co.hien.fott.android.R;

/**
 * Created by nguyenvanhien on 11/28/17.
 */

public class SplashActivity extends BaseActivity {
    private final int SPLASH_DISPLAY_LENGTH = 2000;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                Intent feedIntent = new Intent(SplashActivity.this,FeedActivity.class);
                SplashActivity.this.startActivity(feedIntent);
                SplashActivity.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}
