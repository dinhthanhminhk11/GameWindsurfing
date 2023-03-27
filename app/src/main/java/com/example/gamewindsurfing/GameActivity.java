package com.example.gamewindsurfing;

import android.annotation.SuppressLint;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

import com.example.gamewindsurfing.view.RunJumpView;
import com.example.gamewindsurfing.view.customview.DroidRunJumpThread;

public class GameActivity extends AppCompatActivity {
    public static final String PREFS_NAME = "DRJPrefsFile";
    private RunJumpView drjView;
    private DroidRunJumpThread drjThread;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game);
        drjView = (RunJumpView) findViewById(R.id.droidrunjump);
    }

    @Override
    protected void onPause() {
        super.onPause();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        SharedPreferences.Editor editor = settings.edit();
        drjThread = drjView.getThread();
        if (isFinishing()) {
            drjThread.resetGame();
        } else {
            drjThread.pause();
        }
        drjThread.saveGame(editor);
    }

    @Override
    protected void onResume() {
        super.onResume();
        drjThread = drjView.getThread();
        SharedPreferences settings = getSharedPreferences(PREFS_NAME, 0);
        drjThread.restoreGame(settings);
    }
}