package com.ddq.braintrain.gameactivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.CompareLevelMenuActivity;
import com.ddq.braintrain.levelmenu.GridsHighlightLevelMenuActivity;

public class CompareGameActivity extends AppCompatActivity {

    private TextView textView, CompareTimeTextView, CompareInforTextView, CompareCompleteNotiTextView, CompareOpportunityTextView, CompareScoreTextView;
    private Button Expression1, Expression2;
    AppCompatButton btn, nextLevelButton, resultButton;
    CountDownTimer timer;
    int ExpressionResult1, ExpressionResult2;
    private static final int START_TIMER = 61000;
    long timeLeft = START_TIMER;
    private int count;
    private int point;
    private int level;
    private int score = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_game);

        textView = findViewById(R.id.CompareLevelTextView);
        CompareTimeTextView = findViewById(R.id.CompareTimeTextView);
        Expression1 = (Button) findViewById(R.id.Expression1);
        Expression2 = (Button) findViewById(R.id.Expression2);
        CompareCompleteNotiTextView = findViewById(R.id.CompareCompleteNoticeTextView);
        CompareScoreTextView = findViewById(R.id.CompareScoreTextView);
        resultButton = findViewById(R.id.resultButton);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        textView.setText("Level: " + level);
        gameStart(level);

    }

    public void generate(int level){
        textView.setText("Cấp độ: " + level);
        updateScore(score);
        String ExpressionText1 = CompareLevelMenuActivity.getCompareModels().get(level).getExpression1();
        Expression1.setText(ExpressionText1);
        String ExpressionText2 = CompareLevelMenuActivity.getCompareModels().get(level).getExpression2();
        Expression2.setText(ExpressionText2);
        ExpressionResult1 = CompareLevelMenuActivity.getCompareModels().get(level).getExpressionResult1();
        ExpressionResult2 = CompareLevelMenuActivity.getCompareModels().get(level).getExpressionResult2();
        point = CompareLevelMenuActivity.getCompareModels().get(level).getScore();
        Expression1.setBackgroundColor(0xFF3a378e);
        Expression2.setBackgroundColor(0xFF3a378e);
    }

    public void ClickExpresion1(View view){
        if(ExpressionResult1 < ExpressionResult2){
            Expression1.setBackgroundColor(0xFF00FF00);
            count++;
            if(count == 5){
                pauseTimer();
                timeLeft = timeLeft + 10;
                UpdateTimer();
                count = 0;
            }
            level = level + 1;
            score = score + point;
            generate(level);
        }
        else{
            Expression1.setBackgroundColor(0xFFFF0000);
            pauseTimer();
            timeLeft = timeLeft - 2;
            UpdateTimer();
        }
    }

    public void ClickExpresion2(View view){
        if(ExpressionResult1 > ExpressionResult2){
            Expression2.setBackgroundColor(0xFF00FF00);
            count++;
            if(count == 5){
                pauseTimer();
                timeLeft = timeLeft + 10;
                UpdateTimer();
                count = 0;
            }
            level = level + 1;
            score = score + point;
            generate(level);
        }
        else{
            Expression2.setBackgroundColor(0xFFFF0000);
            pauseTimer();
            timeLeft = timeLeft - 2;
            UpdateTimer();
        }
    }

    public void gameStart(int level1) {
        CompareCompleteNotiTextView.setVisibility(View.INVISIBLE);
//        nextLevelButton.setVisibility(View.INVISIBLE);
        resultButton.setVisibility(View.INVISIBLE);
        generate(level1);
        startTimer();
    }

    public void UpdateTimer() {
        timer = new CountDownTimer(timeLeft*1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateText();
            }

            @Override
            public void onFinish() {
                gameStop();
            }
        }.start();
    }


    public void startTimer() {
        timer = new CountDownTimer(START_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateText();
            }

            @Override
            public void onFinish() {
                gameStop();
            }
        }.start();
    }

    public void pauseTimer(){
        timer.cancel();
    }

    public void updateText() {
        CompareTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void updateScore(int score){
        CompareScoreTextView.setText("Điểm: "+ score);

    }

    public void gameStop(){
        CompareCompleteNotiTextView.setVisibility(View.VISIBLE);
        resultButton.setVisibility(View.VISIBLE);
        Expression1.setClickable(false);
        Expression2.setClickable(false);
    }

    @Override
    public void onBackPressed() {
        pauseTimer();
        finish();
    }


}