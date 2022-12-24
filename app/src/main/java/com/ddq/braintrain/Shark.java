package com.ddq.braintrain;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;


import java.util.Random;

public class Shark {
    private int x;
    private int y;
    private Bitmap sharkImage;

    public Shark(Context context, int x, int y) {
        this.x = x;
        this.y = y;
        Bitmap originalImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.shark);
        int width = originalImage.getWidth();
        int height = originalImage.getHeight();
        float scaleFactor = 1.2f; // Change this value to adjust the size of the image
        sharkImage = Bitmap.createScaledBitmap(originalImage, (int)(width * scaleFactor), (int)(height * scaleFactor), false);
    }

    public Bitmap getSharkImage() {
        return sharkImage;
    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(sharkImage, x, y, null);
    }

    public void move() {
        Random random = new Random();
        int dx = random.nextInt(5) - 1; // Generate a random number between -1 and 1
        int dy = random.nextInt(5) - 1; // Generate a random number between -1 and 1
        x += dx;
        y += dy;
    }

    Rect getCollisionShape () {
        return new Rect(x, y, x + sharkImage.getWidth(), y + sharkImage.getHeight());
    }
}
