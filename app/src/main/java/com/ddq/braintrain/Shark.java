package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.util.Log;

public class Shark {

    //   public static final float VELOCITY = 0.2f;

    private int movingVectorX;
    private int movingVectorY;


    private final GameSurface gameSurface;

    protected Bitmap image;
    protected int x;
    protected int y;
    int width;
    int height;
    int id;
    int oldX, oldY;
    boolean isCollision;

    public Shark(Context context, GameSurface gameSurface, int x, int y, int movingVectorX, int movingVectorY) {

        this.gameSurface = gameSurface;
        this.x = x;
        this.y = y;
        this.movingVectorX = movingVectorX;
        this.movingVectorY = movingVectorY;
        Bitmap originalImage = BitmapFactory.decodeResource(context.getResources(), R.drawable.shark);
        this.width = originalImage.getWidth();
        this.height = originalImage.getHeight();
        float scaleFactor = 1.2f; // Change this value to adjust the size of the image
        image = Bitmap.createScaledBitmap(originalImage, (int) (width * scaleFactor), (int) (height * scaleFactor), false);
        this.width = image.getWidth();
        this.height = image.getHeight();

        this.oldX = x;
        this.oldY = y;
        this.isCollision = false;
    }

    public void update() {

        /*
        // Current time in nanoseconds
        long now = System.nanoTime();

        // Never once did draw.
        if(lastDrawNanoTime==-1) {
            lastDrawNanoTime= now;
        }
        // Change nanoseconds to milliseconds (1 nanosecond = 1000000 milliseconds).
        int deltaTime = (int) ((now - lastDrawNanoTime)/ 1000000 );

        // Distance moves
        float distance = VELOCITY * deltaTime;

        double movingVectorLength = Math.sqrt(movingVectorX* movingVectorX + movingVectorY*movingVectorY);

        // Calculate the new position of the game character.
        this.x = x +  (int)(distance* movingVectorX / movingVectorLength);
        this.y = y +  (int)(distance* movingVectorY / movingVectorLength);

        // When the game's character touches the edge of the screen, then change direction


         */

        if (isCollision) {
            this.x = oldX;
            this.y = oldY;
            this.movingVectorX = -this.movingVectorX;
            this.movingVectorY = -this.movingVectorY;
            isCollision = false;
        } else {

            this.oldX = x;
            this.oldY = y;

            this.x = x + movingVectorX;
            this.y = y + movingVectorY;

            if (this.x < 0) {
                this.x = 0;
                this.movingVectorX = -this.movingVectorX;
            } else if (this.x > this.gameSurface.getWidth() - width) {
                this.x = this.gameSurface.getWidth() - width;
                this.movingVectorX = -this.movingVectorX;
            }

            if (this.y < 0) {
                this.y = 0;
                this.movingVectorY = -this.movingVectorY;
            } else if (this.y > this.gameSurface.getHeight() - height) {
                this.y = this.gameSurface.getHeight() - height;
                this.movingVectorY = -this.movingVectorY;
            }
        }

    }

    public void draw(Canvas canvas) {
        canvas.drawBitmap(image, x, y, null);
    }

    Rect getCollisionShape() {
        return new Rect(x, y, x + width, y + height);
    }

    void changeDirection() {
        movingVectorX = -movingVectorX;
        movingVectorY = -movingVectorY;
        Log.d(TAG, "changeDirection: Changed!!" + movingVectorX);
    }

    public void setCollision(boolean collision) {
        isCollision = collision;
    }
}
