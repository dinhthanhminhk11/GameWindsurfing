package com.example.gamewindsurfing.view.customview;

import android.content.Context;
import android.content.SharedPreferences;
import android.graphics.Canvas;
import android.view.MotionEvent;
import android.view.SurfaceHolder;

import com.example.gamewindsurfing.model.Game;

public class DroidRunJumpThread extends Thread {
    private SurfaceHolder surfaceHolder;
    private boolean run;
    private Game game;

    public DroidRunJumpThread(SurfaceHolder surfaceHolder, Context context, Game game) {
        run = false;
        this.surfaceHolder = surfaceHolder;
        this.game = game;
    }

    public void setSurfaceSize(int width, int height) {
        synchronized (surfaceHolder) {
            game.setScreenSize(width, height);
        }
    }

    public void setRunning(boolean b) {
        run = b;
    }

    @Override
    public void run() {
        // game loop
        while (run) {
            Canvas c = null;
            try {
                c = surfaceHolder.lockCanvas(null);
                synchronized (surfaceHolder) {
                    game.run(c);
                }
            } finally {
                if (c != null) {
                    surfaceHolder.unlockCanvasAndPost(c);
                }
            }
        }
    }

    public boolean doTouchEvent(MotionEvent event) {
        boolean handled = false;

        synchronized (surfaceHolder) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    game.doTouch();
                    handled = true;
                    break;
            }
        }

        return handled;
    }

    public void pause() {
        synchronized (surfaceHolder) {
            game.pause();
            run = false;
        }
    }

    public void resetGame() {
        synchronized (surfaceHolder) {
            game.resetGame();
        }
    }

    public void restoreGame(SharedPreferences savedInstanceState) {
        synchronized (surfaceHolder) {
            game.restore(savedInstanceState);
        }
    }

    public void saveGame(SharedPreferences.Editor editor) {
        synchronized (surfaceHolder) {
            game.save(editor);
        }
    }
}