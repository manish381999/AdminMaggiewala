package com.tie.adminmaggiewala;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.tie.adminmaggiewala.databinding.ActivitySplashBinding;
import com.tie.adminmaggiewala.ui.Credential.LoginActivity;

public class SplashActivity extends AppCompatActivity {
ActivitySplashBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding=ActivitySplashBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        binding.text1.animate().translationY(-400).setDuration(2700).setStartDelay(2200);
        binding.ivAppLogo.animate().translationX(2000).setDuration(2000).setStartDelay(2900);

//        Animation animation2 = AnimationUtils.loadAnimation(SplashActivity.this, R.anim.animation2);
//        binding.text1.startAnimation(animation2);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        }, 5000);
    }
}