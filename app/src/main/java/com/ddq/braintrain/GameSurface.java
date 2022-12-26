package com.ddq.braintrain;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
import android.view.KeyEvent;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.view.WindowManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class GameSurface extends SurfaceView implements SurfaceHolder.Callback {

    private GameThread gameThread;
    private Shark shark;
    Boat boat;
    Random random = new Random();

    int level, score, sharkNumber, boatNumber, boatpoint, bitcount, passpoint;

    Context mContext;
    Rect rect;

    Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.shark_boat_game_background);

    List<Shark> sharks = new ArrayList<>();
    List<Boat> boats = new ArrayList<>();
    List<Wave> waves = new ArrayList<>();

    int drawCount = 0, updateCount = 0;

    public GameSurface(Context context, int level, int score, int sharkNumber, int boatNumber, int boatpoint, int bitcount, int passpoint) {
        super(context);
        mContext = context;


        this.level = level;
        this.score = score;
        this.sharkNumber = sharkNumber;
        this.boatNumber = boatNumber;
        this.boatpoint = boatpoint;
        this.bitcount = bitcount;
        this.passpoint = passpoint;


        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        rect = new Rect(0, 0, screenWidth, screenHeight);

        for (int i = 0; i < sharkNumber; i++) {
            shark = new Shark(getContext(), this, random.nextInt(screenWidth), screenHeight - random.nextInt(screenHeight / 2), random.nextInt(3) + 1, random.nextInt(3) + 1);
            sharks.add(shark);
        }
        for (int i = 0; i < boatNumber; i++) {
            boat = new Boat(getContext(), screenWidth - random.nextInt(screenWidth - 100), random.nextInt(screenHeight / 3));
            boats.add(boat);
        }
    }

    public void update() {

        for (Shark shark : sharks) {
            shark.update();
        }
        for (Wave wave : waves) {
            if (!wave.isAvailable) {
                waves.remove(wave);
            }
        }
        checkSharkWaveCollision();
        checkSharkBoatCollision();
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);

        //Paint paint = new Paint();
        //paint.setAlpha(100);
        canvas.drawBitmap(background, null, rect, null);
        Log.d(TAG, "draw: Background");

        for (Shark shark : sharks) {
            shark.draw(canvas);
        }
        for (Boat boat : boats) {
            boat.draw(canvas);
        }
        for (Wave wave : waves) {
            wave.draw(canvas);
        }

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        //if (waves.size() < boatNumber) {
            Wave wave = new Wave(getContext(), touchX - 75, touchY - 75);

            waves.add(wave);
        //}
        return true;
    }

    public void checkSharkWaveCollision() {
        for (Shark shark : sharks) {
            for (Wave wave : waves) {
                if (Rect.intersects(shark.getCollisionShape(), wave.getCollisionShape())) {
                    shark.setCollision(true);
                }
            }
        }
    }

    public void checkSharkBoatCollision() {
        for (Shark shark : sharks) {
            for (Boat boat : boats) {
                if (Rect.intersects(shark.getCollisionShape(), boat.getCollisionShape())) {
                    shark.setCollision(true);
                    boat.hit();
                    if (boat.isDestroyed()) {
                        boats.remove(boat);
                    }
                }
            }
        }
    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        this.gameThread = new GameThread(this, holder);
        this.gameThread.setRunning(true);
        this.gameThread.start();
    }

    @Override
    public void surfaceChanged(@NonNull SurfaceHolder holder, int format, int width, int height) {

    }

    // Implements method of SurfaceHolder.Callback
    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {


        try {
            //Thread.sleep(1000);
            this.gameThread.setRunning(false);
            Log.d(TAG, "surfaceDestroyed: Done");
            // Parent thread must wait until the end of GameThread.
            this.gameThread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        /*
        boolean retry = true;
        while (retry) {
            try {
                Thread.sleep(3000);
                this.gameThread.setRunning(false);
                Log.d(TAG, "surfaceDestroyed: Done");
                // Parent thread must wait until the end of GameThread.
                this.gameThread.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            retry = true;
        }*/
    }

}
