package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class SharkBoatGameOverActivity extends AppCompatActivity {

    TextView gameOverNoti, sharkBoatScoreTextView;
    Button sharkBoatNextLevelButton, sharkBoatPlayAgainButton;

    int level, score, passScore;
    int isPassed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_boat_game_over);

        gameOverNoti = findViewById(R.id.gameOverNoti);
        sharkBoatScoreTextView = findViewById(R.id.sharkBoatScoreTextView);
        sharkBoatNextLevelButton = findViewById(R.id.sharkBoatNextLevelButton);
        sharkBoatPlayAgainButton = findViewById(R.id.sharkBoatPlayAgainButton);

        sharkBoatNextLevelButton.setVisibility(View.GONE);

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        score = intent.getIntExtra("score", 0);
        isPassed = intent.getIntExtra("isPassed", 0);
        passScore = intent.getIntExtra("passscore", 0);

        if(isPassed==1){
            gameOverNoti.setText("Đã hoàn thành màn chơi");
            sharkBoatNextLevelButton.setVisibility(View.VISIBLE);
        }else{
            gameOverNoti.setText("Chưa vượt qua màn chơi");
        }
        sharkBoatScoreTextView.setText("Điểm: "+ score+"/"+passScore);

        Log.d(TAG, "onCreate: Created");

    }
}