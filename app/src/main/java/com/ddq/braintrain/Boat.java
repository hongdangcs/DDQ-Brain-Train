package com.ddq.braintrain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;

public class Boat {
    private int x;
    private int y;
    private Bitmap boatImage;
    private int hits;

    public Boat(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        boatImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.boat);
        hits = 0;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(boatImage, x, y, null);
    }

    public void hit() {
        hits++;
    }

    public boolean isDestroyed() {
        return hits >= 5;
    }
}
