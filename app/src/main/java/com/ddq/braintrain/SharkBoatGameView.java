package com.ddq.braintrain;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.Rect;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;


public class SharkBoatGameView extends View {

    Context context, mContext;
    int level, score, shark, boat, boatpoint, bitcount, passpoint;
    Bitmap background;
    Point size;
    Display display;
    int screenWidth, screenHeight;
    Rect rect;

    Shark sharkObject;
    Boat boatObject;

    List<Shark> sharks = new ArrayList<>();
    List<Boat> boats = new ArrayList<>();

    Random random = new Random();

    public SharkBoatGameView(Context context, int level, int score, int shark, int boat, int boatpoint, int bitcount, int passpoint) {
        super(context);
        mContext = context;

        this.context = context;
        this.level = level;
        this.score = score;
        this.shark = shark;
        this.boat = boat;
        this.boatpoint = boatpoint;
        this.bitcount = bitcount;
        this.passpoint = passpoint;

        background = BitmapFactory.decodeResource(getResources(), R.drawable.shark_boat_game_background);
        WindowManager windowManager = (WindowManager) mContext.getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics displayMetrics = new DisplayMetrics();
        display.getMetrics(displayMetrics);
        int screenWidth = displayMetrics.widthPixels;
        int screenHeight = displayMetrics.heightPixels;
        rect = new Rect(0, 0, screenWidth, screenHeight);

        for(int i=0; i<shark; i++){
            sharkObject = new Shark(getContext(), random.nextInt(screenWidth-20), random.nextInt(screenHeight-20));
            sharks.add(sharkObject);
        }
        for(int i=0; i<boat; i++){
            boatObject = new Boat(getContext(), random.nextInt(screenWidth-200), random.nextInt(screenHeight-200));
            boats.add(boatObject);
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        Paint paint = new Paint();
        paint.setAlpha(65);
        canvas.drawBitmap(background, null, rect, paint);


        for(Shark shark: sharks){
            shark.draw(canvas);
        }
        for(Boat boat: boats){
            boat.draw(canvas);
        }
    }
}
