package com.ddq.braintrain;


import android.content.Context;
import android.view.SurfaceView;


public class SharkBoatGameView extends SurfaceView implements Runnable {
    public SharkBoatGameView(Context context) {
        super(context);
    }

    @Override
    public void run() {

    }
/*
    Context context, mContext;
    int level, score, shark, boat, boatpoint, bitcount, passpoint;
    Bitmap background;
    Point size;
    Display display;
    int screenWidth, screenHeight;
    Rect rect;
    Boolean isPlaying = true;

    Shark sharkObject;
    Boat boatObject;

    List<Shark> sharks = new ArrayList<>();
    List<Boat> boats = new ArrayList<>();

    Random random = new Random();
    private Timer timer;
    Thread thread;

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

    private void moveShark() {
        for(Shark shark: sharks){
            shark.move();
        }
        invalidate(); // This method will call the onDraw() method of the main activity
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

    @Override
    public void run() {

        while (isPlaying){
            update();
            sleep();
        }
    }

    public void update(){

    }
/*
    public void draw(){
        if (getHolder().getSurface().isValid()) {

            Canvas canvas = getHolder().lockCanvas();
            Paint paint = new Paint();
            paint.setAlpha(65);
            canvas.drawBitmap(background, null, rect, paint);


            for(Shark shark: sharks){
                shark.draw(canvas);
            }
            for(Boat boat: boats){
                boat.draw(canvas);
            }
            getHolder().unlockCanvasAndPost(canvas);

        }

    }
*///
    /*
    public void sleep(){
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void pause(){
        try {
            isPlaying = false;
            thread.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
    public void resume(){
        isPlaying = true;
        thread = new Thread(this);
        thread.start();
    }*/
}
