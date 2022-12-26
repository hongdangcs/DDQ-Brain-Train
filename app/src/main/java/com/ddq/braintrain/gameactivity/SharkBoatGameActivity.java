package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.Window;
import android.view.WindowManager;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ddq.braintrain.GameSurface;
import com.ddq.braintrain.R;
import com.ddq.braintrain.SharkBoatGameView;

public class SharkBoatGameActivity extends AppCompatActivity {

//    TextView textView;
private SharkBoatGameView gameView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
 //       setContentView(R.layout.activity_shark_boat_game);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
//        textView = findViewById(R.id.textView12);
        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);
        int score = intent.getIntExtra("score", 0);
        int shark = intent.getIntExtra("shark", 0);
        int boat = intent.getIntExtra("boat", 0);
        int boatpoint = intent.getIntExtra("boatpoint", 0);
        int bitecount = intent.getIntExtra("bitecount", 0);
        int passpoint = intent.getIntExtra("passpoint", 0);
//        textView.setText("Level: " + level + " " + score + " " + shark + " " + boat + " " + boatpoint + " " + bitecount + " " + passpoint);

 //       gameView = new SharkBoatGameView(this, level,score, shark, boat, boatpoint, bitecount, passpoint);

//        setContentView(gameView);

        this.getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,
                WindowManager.LayoutParams.FLAG_FULLSCREEN);

        // Set No Title
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);

        this.setContentView(new GameSurface(this, level,score, shark, boat, boatpoint, bitecount, passpoint));

    }

    @Override
    public boolean onKeyDown(int keyCode, KeyEvent event) {
        if ((keyCode == KeyEvent.KEYCODE_BACK))
        {
            finish();
            Log.d(TAG                    , "onKeyDown: done");
        }
        return super.onKeyDown(keyCode, event);
    }
}