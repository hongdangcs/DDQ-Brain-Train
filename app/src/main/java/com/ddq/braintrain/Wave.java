package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.Log;

public class Wave {


    private final int x;
    private final int y;
    private final Bitmap waveImage;
    int width, height;
    boolean isAvailable;

    CountDownTimer timer;

    public Wave(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        Bitmap originalImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.wave);
        width = originalImage.getWidth();
        Log.d(TAG, "Wave: " + width);
        height = originalImage.getHeight();
        float scaleFactor = .7f; // Change this value to adjust the size of the image
        waveImage = Bitmap.createScaledBitmap(originalImage, (int) (width * scaleFactor), (int) (height * scaleFactor), false);
        startTimer();
        isAvailable = true;
        width = waveImage.getWidth();
        height = waveImage.getHeight();
        Log.d(TAG, "Wave: after " + width);
    }

    public void startTimer() {
        timer = new CountDownTimer(5000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {

            }

            @Override
            public void onFinish() {
                isAvailable = false;
            }
        }.start();
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(waveImage, x, y, null);
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }


    public boolean isAvailable() {
        return isAvailable;
    }
}
