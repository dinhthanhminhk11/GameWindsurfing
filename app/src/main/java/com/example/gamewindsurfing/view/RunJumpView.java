package com.example.gamewindsurfing.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

import com.example.gamewindsurfing.model.Game;
import com.example.gamewindsurfing.view.customview.DroidRunJumpThread;

public class RunJumpView extends SurfaceView implements SurfaceHolder.Callback {

    private DroidRunJumpThread thread;
    private Context context;
    private Game game;
    private SurfaceHolder holder;

    public RunJumpView(Context context, AttributeSet attrs) {
        super(context, attrs);

        holder = getHolder();
        holder.addCallback(this);

        this.context = context;
        game = new Game(context);

        thread = null;
        setFocusable(true);
    }

    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {
        thread.setSurfaceSize(width, height);
    }

    public void surfaceCreated(SurfaceHolder holder) {
        thread.setRunning(true);
        thread.start();
    }

    public void surfaceDestroyed(SurfaceHolder holder) {
        boolean retry = true;
        thread.setRunning(false);
        while (retry) {
            try {
                thread.join();
                retry = false;
            } catch (InterruptedException e) {
            }
        }
        thread = null;
    }

    public boolean onTouchEvent(MotionEvent event) {
        return thread.doTouchEvent(event);
    }

    public DroidRunJumpThread getThread() {
        if (thread == null) {
            thread = new DroidRunJumpThread(holder, context, game);
        }
        return thread;
    }
}
