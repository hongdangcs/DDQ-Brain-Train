package com.ddq.braintrain;

import android.graphics.Canvas;
import android.view.SurfaceHolder;

public class GameThread extends Thread {

    private boolean running;
    private final GameSurface gameSurface;
    private final SurfaceHolder surfaceHolder;

    public GameThread(GameSurface gameSurface, SurfaceHolder surfaceHolder) {
        this.gameSurface = gameSurface;
        this.surfaceHolder = surfaceHolder;
    }

    @Override
    public void run() {

        while (running) {


            Canvas canvas = null;
            try {
                // Get Canvas from Holder and lock it.
                canvas = surfaceHolder.lockCanvas();

                // Synchronized
                synchronized (canvas) {
                    gameSurface.update();
                    gameSurface.draw(canvas);
                }
            } catch (Exception e) {
                // Do nothing.
            } finally {
                if (canvas != null) {
                    // Unlock Canvas.
                    surfaceHolder.unlockCanvasAndPost(canvas);
                }
            }/*
            long now = System.nanoTime() ;
            // Interval to redraw game
            // (Change nanoseconds to milliseconds)
            long waitTime = (now - startTime)/1000000;
            if(waitTime < 10)  {
                waitTime= 10; // Millisecond.
            }
            System.out.print(" Wait Time="+ waitTime);

            try {
                // Sleep.
                this.sleep(waitTime);
            } catch(InterruptedException e)  {

            }
            startTime = System.nanoTime();
            System.out.print(".");*/
            //sleep();

            gameSleep();
        }
    }

    public void setRunning(boolean running) {
        this.running = running;
    }

    public void gameSleep() {
        try {
            Thread.sleep(17);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }


}