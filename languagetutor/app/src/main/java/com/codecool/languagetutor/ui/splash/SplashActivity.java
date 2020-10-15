package com.codecool.languagetutor.ui.splash;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;

import com.codecool.languagetutor.databinding.ActivitySplashBinding;
import com.codecool.languagetutor.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ActivitySplashBinding binding;
    private ValueAnimator valueAnimator;
    private Float progress;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySplashBinding.inflate(getLayoutInflater());
        View view = binding.getRoot();
        setContentView(view);

        new Handler().postDelayed(() -> {
            Intent intent = new Intent(SplashActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }, 9000);
    }

    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        animate();
    }

    private void animate() {
        valueAnimator = ValueAnimator.ofFloat(0f, 3000f).setDuration(9000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (Float) valueAnimator.getAnimatedValue();
                binding.flag1.setRotationX(progress);
                binding.flag2.setRotationY(progress);
                binding.flag3.setRotationX(progress);
                binding.flag3.setRotationY(-progress);
                binding.flag4.setRotationX(progress);
                binding.flag4.setRotationY(-progress);
                binding.flag5.setRotationY(progress);
                binding.flag6.setRotation(progress);
                binding.flag7.setRotation(progress);
            }
        });
        valueAnimator.start();
    }

    @Override
    public void onDetachedFromWindow() {
        valueAnimator.cancel();
        super.onDetachedFromWindow();
    }
}