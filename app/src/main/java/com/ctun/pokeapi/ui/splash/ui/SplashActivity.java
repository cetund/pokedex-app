package com.ctun.pokeapi.ui.splash.ui;


import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.res.ResourcesCompat;
import androidx.core.splashscreen.SplashScreen;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.View;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.gif.GifDrawable;
import com.bumptech.glide.request.target.ImageViewTarget;
import com.ctun.pokeapi.R;
import com.ctun.pokeapi.databinding.ActivitySplashBinding;
import com.ctun.pokeapi.ui.home.view.Home;

public class SplashActivity extends AppCompatActivity {


    private ActivitySplashBinding binding;
    private SplashScreen splashScreen;
    private boolean keepSplash = true;
    private Handler handler;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        handler=new Handler();
        handler.postDelayed(() -> goHome(),2000);

    }

    private void goHome(){
        startActivity(new Intent(SplashActivity.this, Home.class));
        finish();
    }


}