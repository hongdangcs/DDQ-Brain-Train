package com.ddq.braintrain;


import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.os.CountDownTimer;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Display;
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
    int bitten;

    Context mContext;
    Rect rect;

    Bitmap background = BitmapFactory.decodeResource(getResources(), R.drawable.sea_surface);

    int screenWidth, screenHeight;

    Paint scorePaint = new Paint();
    Paint timePaint = new Paint();
    Paint levelPaint = new Paint();

    List<Shark> sharks = new ArrayList<>();
    List<Boat> boats = new ArrayList<>();
    List<Wave> waves = new ArrayList<>();

    CountDownTimer timer;
    long timeLeft;

    int finishCall = 0;

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

        bitten = 0;


        // Make Game Surface focusable so it can handle events. .
        this.setFocusable(true);

        // Sét callback.
        this.getHolder().addCallback(this);

        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        screenWidth = displayMetrics.widthPixels;
        screenHeight = displayMetrics.heightPixels;
        rect = new Rect(0, 0, screenWidth, screenHeight);

        for (int i = 0; i < sharkNumber; i++) {
            shark = new Shark(getContext(), this, random.nextInt(screenWidth), screenHeight - random.nextInt(screenHeight / 2), random.nextInt(3) + 1, random.nextInt(3) + 1);
            sharks.add(shark);
        }
        for (int i = 0; i < boatNumber; i++) {
            boat = new Boat(getContext(), screenWidth - random.nextInt(screenWidth - 100), random.nextInt(screenHeight / 3));
            boats.add(boat);
        }


        scorePaint.setColor(Color.RED);
        scorePaint.setTextSize(40);
        scorePaint.setTextAlign(Paint.Align.CENTER);


        timePaint.setColor(Color.GREEN);
        timePaint.setTextSize(40);
        timePaint.setTextAlign(Paint.Align.RIGHT);

        levelPaint.setColor(Color.GREEN);
        levelPaint.setTextSize(40);
        levelPaint.setTextAlign(Paint.Align.LEFT);

        timer = new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
            }

            @Override
            public void onFinish() {
                resultActivity();
            }
        }.start();
    }

    public void resultActivity() {
        timer.cancel();

        if (finishCall == 0) {
            finishCall++;
            int currentScore = sharkNumber * boatpoint - (boatpoint / 5) * bitten;

            if (currentScore > score) {
                score = currentScore;
                BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(mContext);
                brainTrainDatabase.updateCell("attention_game_three", "score", score, level);
                if (score >= passpoint) {
                    brainTrainDatabase.updateCell("attention_game_three", "complete_status", 1, level);
                }
            }

            Intent intent = new Intent(mContext, SharkBoatGameOverActivity.class);
            intent.putExtra("score", currentScore);
            intent.putExtra("passscore", passpoint);
            intent.putExtra("level", level);
            intent.putExtra("isPassed", currentScore >= passpoint ? 1 : 0);
            mContext.startActivity(intent);
            ((Activity) mContext).finish();
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

        canvas.drawText("Mạng: " + (bitcount - bitten), screenWidth / 2, 44, scorePaint);
        canvas.drawText("Thời gian: " + timeLeft, screenWidth - 14, 44, timePaint);
        canvas.drawText("Màn: " + level, 14, 44, levelPaint);

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        int touchX = (int) event.getX();
        int touchY = (int) event.getY();
        //if (waves.size() < boatNumber) {
        Wave wave = new Wave(getContext(), touchX - 75, touchY - 75);

        waves.add(wave);
        //}
        try {
            Thread.sleep(300);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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
                    bitten++;
                    if (bitten > bitcount) {
                        resultActivity();
                    }
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

        ((Activity) mContext).finish();
        timer.cancel();
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
