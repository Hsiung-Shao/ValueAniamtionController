package com.example.valueaniamtioncontroller;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.res.Resources;
import android.graphics.Color;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.LinearInterpolator;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    ImageView showImage;
    Button top, left, right, bottom, center;
    float defaultX, defaultY, x, y;
    boolean firstSaveLocation = true;
    int move = 20;

    ValueAnimator animator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        showImage = findViewById(R.id.showImageView);

        top = findViewById(R.id.topButtonPressed);
        left = findViewById(R.id.leftButtonPressed);
        right = findViewById(R.id.rightButtonPressed);
        center = findViewById(R.id.centerButtonPressed);
        bottom = findViewById(R.id.bottomButtonPressed);

        // set imageView
        showImage.setImageResource(R.drawable.item01);

        animator = ValueAnimator.ofFloat(0, 100);
        animator.setDuration(500);
        animator.setInterpolator(new LinearInterpolator());

        top.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstSaveLocation) getImageViewLocation();
                moveImageView(showImage, 0, -1);
            }
        });

        left.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstSaveLocation) getImageViewLocation();
                moveImageView(showImage, -1, 0);
            }
        });

        right.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstSaveLocation) getImageViewLocation();
                moveImageView(showImage, 1, 0);
            }
        });

        center.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (firstSaveLocation) getImageViewLocation();
                showImage.setX(defaultX);
                showImage.setY(defaultY);

            }
        });

        bottom.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(firstSaveLocation) getImageViewLocation();
                moveImageView(showImage, 0, 1);
            }
        });
    }

    public void  getImageViewLocation() {
        if(firstSaveLocation){
            defaultX = showImage.getX();
            defaultY = showImage.getY();
            firstSaveLocation = false;
        }
    }

    public void moveImageView(ImageView view, int moveX, int moveY) {
        final float tempX = view.getX();
        final float tempY = view.getY();

        animator.start();
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float d = (float) valueAnimator.getAnimatedValue();

                view.setX(tempX + d * moveX);
                view.setY(tempY + d * moveY);
            }
        });
    }
}