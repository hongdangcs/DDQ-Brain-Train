package com.ddq.braintrain.gameactivity;

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
import com.ddq.braintrain.levelmenu.FindOperatorLevelMenuActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;

public class FindOperatorGameActivity extends AppCompatActivity {


    private TextView textView, FindOperatorTimeTextView, FindOperatorInforTextView, FindOperatorCompleteNotiTextView, FindOperatorOpportunityTextView, FindOperatorScoreTextView;
    private Button Option1, Option2, Option3, Option4, Option5, Option6;
    AppCompatButton btn, nextLevelButton, resultButton;
    CountDownTimer timer;
    private int  option1, option2, option3, option4, option5, option6;
    long timeLeft;
    private int count;
    private int point;
    private int level;
    private int score = 0;
    private String text;
    ArrayList<Integer>  numbers;
    private int  optionten1, optionhundred2, optionthouasand3;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_operator_game);



        textView = findViewById(R.id.FindOperatorLevelTextView);
        FindOperatorTimeTextView = findViewById(R.id.FindOperatorTimeTextView);
        Option1 = (Button) findViewById(R.id.Option1);
        Option2 = (Button) findViewById(R.id.Option2);
        Option3 = (Button) findViewById(R.id.Option3);
        Option4 = (Button) findViewById(R.id.Option4);
        Option5 = (Button) findViewById(R.id.Option5);
        Option6 = (Button) findViewById(R.id.Option6);
        FindOperatorCompleteNotiTextView = findViewById(R.id.FindOperatorCompleteNoticeTextView);
        FindOperatorScoreTextView = findViewById(R.id.FindOperatorScoreTextView);
        resultButton = findViewById(R.id.resultButton);

        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);
        textView.setText("Level: " + level);
        Log.d("level", "number " + level);
        if (level < 100){
            level = level - 10 ;
            text = "ten";
        }
        else if(level < 1000 && level >= 100){
            level = level - 100;
            text = "hundred";
        }
        else if(level < 10000 && level >= 1000){
            level = level - 1000;
            text = "thousand";
        }
        Log.d("level", "number1: " + level);
        gameStart(level);
    }

    public void generate(int level){
        textView.setText("Cấp độ: " + level);
        updateScore(score);
        option1 = numbers.get(0);
        Option1.setText(Integer.toString(option1));
        option2 = numbers.get(1);
        Option2.setText(Integer.toString(option2));
        option3 = numbers.get(2);
        Option3.setText(Integer.toString(option3));
        option4 = numbers.get(3);
        Option4.setText(Integer.toString(option4));
        option5 = numbers.get(4);
        Option5.setText(Integer.toString(option5));
        if(optionten1 == 6 && text.equals("ten")){
            option6 = numbers.get(5);
            Option6.setText(Integer.toString(option6));
        }
        if(optionhundred2 == 6 && text.equals("hundred")){
            option6 = numbers.get(5);
            Option6.setText(Integer.toString(option6));
        }
        if(optionthouasand3 == 6 && text.equals("thousand")){
            option6 = numbers.get(5);
            Option6.setText(Integer.toString(option6));
        }

//        point = CompareLevelMenuActivity.getCompareModels().get(level).getScore();
//        Expression1.setBackgroundColor(0xFF3a378e);
//        Expression2.setBackgroundColor(0xFF3a378e);
    }

//    public void ClickExpresion1(View view){
//        if(ExpressionResult1 < ExpressionResult2){
//            Expression1.setBackgroundColor(0xFF00FF00);
//            count++;
//            if(count == 5){
//                pauseTimer();
//                timeLeft = timeLeft + 10;
//                UpdateTimer();
//                count = 0;
//            }
//            level = level + 1;
//            score = score + point;
//            generate(level);
//        }
//        else{
//            Expression1.setBackgroundColor(0xFFFF0000);
//            pauseTimer();
//            timeLeft = timeLeft - 2;
//            UpdateTimer();
//        }
//    }
//
//    public void ClickExpresion2(View view){
//        if(ExpressionResult1 > ExpressionResult2){
//            Expression2.setBackgroundColor(0xFF00FF00);
//            count++;
//            if(count == 5){
//                pauseTimer();
//                timeLeft = timeLeft + 10;
//                UpdateTimer();
//                count = 0;
//            }
//            level = level + 1;
//            score = score + point;
//            generate(level);
//        }
//        else{
//            Expression2.setBackgroundColor(0xFFFF0000);
//            pauseTimer();
//            timeLeft = timeLeft - 2;
//            UpdateTimer();
//        }
//    }

    public void gameStart(int level1) {
        Option6.setVisibility(View.INVISIBLE);
        optionten1 = FindOperatorLevelMenuActivity.getOperatorTenModels().get(level1).getOption();
        optionhundred2 = FindOperatorLevelMenuActivity.getOperatorHundredModels().get(level1).getOption();
        optionthouasand3 = FindOperatorLevelMenuActivity.getOperatorThousandModels().get(level1).getOption();
        numbers = new ArrayList();
        Random rand = new Random();
        if(optionten1 == 5 && text.equals("ten")){
            int int_random = rand.nextInt(9) + 1;
            numbers.add(int_random);
            numbers.add(10-int_random);
            for(int i = 0; i < 3; i++){
                int_random = rand.nextInt(9) + 1;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorTenModels().get(level1).getTime();

        }
        if(optionhundred2 == 5 && text.equals("hundred")){
            int int_random = rand.nextInt(81) + 10;
            numbers.add(int_random);
            numbers.add(100-int_random);
            for(int i = 0; i < 3; i++){
                int_random = rand.nextInt(81) + 10;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorHundredModels().get(level1).getTime();

        }

        if(optionten1 == 6 && text.equals("ten")){
            Option6.setVisibility(View.VISIBLE);
            int int_random = rand.nextInt(9) + 1;
            numbers.add(int_random);
            numbers.add(10-int_random);
            for(int i = 0; i < 4; i++){
                int_random = rand.nextInt(9) + 1;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorTenModels().get(level1).getTime();

        }
        if(optionhundred2 == 6 && text.equals("hundred")){
            Option6.setVisibility(View.VISIBLE);
            int int_random = rand.nextInt(81) + 10;
            numbers.add(int_random);
            numbers.add(100-int_random);
            for(int i = 0; i < 4; i++){
                int_random = rand.nextInt(81) + 10;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorHundredModels().get(level1).getTime();

        }
        if(optionthouasand3 == 5 && text.equals("thousand")){
            int int_random = rand.nextInt(801) + 100;
            numbers.add(int_random);
            numbers.add(1000-int_random);
            for(int i = 0; i < 3; i++){
                int_random = rand.nextInt(801) + 100;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorThousandModels().get(level1).getTime();

        }
        if(optionthouasand3 == 6 && text.equals("thousand")){
            Option6.setVisibility(View.VISIBLE);
            int int_random = rand.nextInt(801) + 100;
            numbers.add(int_random);
            numbers.add(1000-int_random);
            for(int i = 0; i < 4; i++){
                int_random = rand.nextInt(801) + 100;
                numbers.add(int_random);
            }
            timeLeft = FindOperatorLevelMenuActivity.getOperatorThousandModels().get(level1).getTime();

        }
        FindOperatorCompleteNotiTextView.setVisibility(View.INVISIBLE);
//        nextLevelButton.setVisibility(View.INVISIBLE);
        resultButton.setVisibility(View.INVISIBLE);
        Collections.shuffle(numbers);
        generate(level1);
        UpdateTimer();
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


//    public void startTimer() {
//        timer = new CountDownTimer(START_TIMER, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeft = millisUntilFinished / 1000;
//                updateText();
//            }
//
//            @Override
//            public void onFinish() {
//                gameStop();
//            }
//        }.start();
//    }

    public void pauseTimer(){
        timer.cancel();
    }

    public void updateText() {
        FindOperatorTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void updateScore(int score){
        FindOperatorScoreTextView.setText("Điểm: "+ score);

    }

    public void gameStop(){
        FindOperatorCompleteNotiTextView.setVisibility(View.VISIBLE);
        resultButton.setVisibility(View.VISIBLE);
//        Expression1.setClickable(false);
//        Expression2.setClickable(false);
    }

    @Override
    public void onBackPressed() {
        pauseTimer();
        finish();
    }
}