package com.zeoharlem.gads.gads2020lb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;

public class MainActivity extends AppCompatActivity {

    private final int SPLASH_SCREEN_TIME    = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        if(Build.VERSION.SDK_INT >= 19){
            getWindow().addFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        else{
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        }
        setContentView(R.layout.activity_main);
        LogoLauncher logoLauncher   = new LogoLauncher();
        logoLauncher.start();
    }

    class LogoLauncher extends Thread{
        @Override
        public void run() {
            try{
                sleep(1000 * SPLASH_SCREEN_TIME);
            }
            catch (InterruptedException e){
                e.printStackTrace();
            }

            Intent intent   = new Intent(MainActivity.this, LeadersBoardActivity.class);
            startActivity(intent);
            MainActivity.this.finish();
        }
    }
}