package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;

public class GameCompareActivity extends AppCompatActivity {

    TextView gameCompareLevelTextView, gameCompareTimeTextView, gameCompareScoreTextView, gameCompareNoticeTextView;
    Button expressionOne, expressionTwo, gameCompareResultButton;

    int level = 1;

    String expression1, expression2;
    Random random;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_compare);

        gameCompareLevelTextView = findViewById(R.id.gameCompareLevelTextView);
        gameCompareTimeTextView = findViewById(R.id.gameCompareTimeTextView);
        gameCompareScoreTextView = findViewById(R.id.gameCompareScoreTextView);
        gameCompareNoticeTextView = findViewById(R.id.gameCompareNoticeTextView);
        expressionOne = findViewById(R.id.expressionOne);
        expressionTwo = findViewById(R.id.expressionTwo);
        gameCompareResultButton = findViewById(R.id.gameCompareResultButton);

        gameCompareResultButton.setVisibility(View.GONE);
        gameCompareNoticeTextView.setVisibility(View.GONE);

        random = new Random();

        if(level <6){
            expression1 = randomBound(10, 30) + " + " +randomBound(10, 30);
            expression2 = randomBound(10, 30) + " + " +randomBound(10, 30);
        }


    }

    public int randomBound(int a, int b){
        return random.nextInt(b - a) + a;
    }
}