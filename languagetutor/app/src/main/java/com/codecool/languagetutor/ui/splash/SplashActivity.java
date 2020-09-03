package com.codecool.languagetutor.ui.splash;

import androidx.appcompat.app.AppCompatActivity;
import butterknife.BindView;
import butterknife.ButterKnife;

import android.animation.ValueAnimator;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.ImageView;

import com.codecool.languagetutor.R;
import com.codecool.languagetutor.ui.MainActivity;

public class SplashActivity extends AppCompatActivity {

    private ValueAnimator valueAnimator;
    private Float progress;

    @BindView(R.id.flag1)
    ImageView flag1;
    @BindView(R.id.flag2)
    ImageView flag2;
    @BindView(R.id.flag3)
    ImageView flag3;
    @BindView(R.id.flag4)
    ImageView flag4;
    @BindView(R.id.flag5)
    ImageView flag5;
    @BindView(R.id.flag6)
    ImageView flag6;
    @BindView(R.id.flag7)
    ImageView flag7;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        ButterKnife.bind(this);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent = new Intent(SplashActivity.this, MainActivity.class);
                startActivity(intent);
                finish();
            }
        }, 15000);
    }


    @Override
    public void onAttachedToWindow() {
        super.onAttachedToWindow();
        animate();
    }

    private void animate() {
        valueAnimator = ValueAnimator.ofFloat(0f, 3600f).setDuration(15000);
        valueAnimator.setRepeatMode(ValueAnimator.RESTART);
        valueAnimator.setRepeatCount(ValueAnimator.INFINITE);
        valueAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                progress = (Float) valueAnimator.getAnimatedValue();
                flag1.setRotationX(progress);
                flag2.setRotationY(progress);
                flag3.setRotationX(progress);
                flag3.setRotationY(-progress);
                flag4.setRotationX(progress);
                flag4.setRotationY(-progress);
                flag5.setRotationY(progress);
                flag6.setRotation(progress);
                flag7.setRotation(progress);
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