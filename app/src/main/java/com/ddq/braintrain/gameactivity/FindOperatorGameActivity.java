package com.ddq.braintrain.gameactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

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
    private int point1, point2, point3;
    private int level;
    private int score = 0;
    private String text;
    ArrayList<Integer>  numbers;
    private int  optionten1, optionhundred2, optionthouasand3;
    private int select1, select2;
    private int  totalSelect;
    private String temp1, temp2;


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
        this.level = level;
        Log.d("level", "number1: " + level);
        gameStart(level);
    }

    public void generate(int level){
        int temp = level + 1;
        textView.setText("Cấp độ: " + temp);
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




        if(level < 15){
            point1 = FindOperatorLevelMenuActivity.getOperatorTenModels().get(level).getPoint();
        }

        if(level < 20){
            point2 = FindOperatorLevelMenuActivity.getOperatorHundredModels().get(level).getPoint();
        }

        if(level < 25){
            point3 = FindOperatorLevelMenuActivity.getOperatorThousandModels().get(level).getPoint();
        }
        Option1.setBackgroundColor(0xFF3a378e);
        Option2.setBackgroundColor(0xFF3a378e);
        Option3.setBackgroundColor(0xFF3a378e);
        Option4.setBackgroundColor(0xFF3a378e);
        Option5.setBackgroundColor(0xFF3a378e);
        Option6.setBackgroundColor(0xFF3a378e);



    }

    public void ClickOption1(View view){
        if(totalSelect == 0){
            Option1.setBackgroundColor(0xFFFFB665);
            select1 = option1;
            temp1 = "option1";

        }

        if(totalSelect == 1){
            Option1.setBackgroundColor(0xFFFFB665);
            select2 = option1;
            temp2 = "option1";
            checkSelect();

        }
        totalSelect = totalSelect + 1;


    }

    public void ClickOption2(View view){
        if(totalSelect == 0){
            Option2.setBackgroundColor(0xFFFFB665);
            select1 = option2;
            temp1 = "option2";
        }

        if(totalSelect == 1){
            Option2.setBackgroundColor(0xFFFFB665);
            select2 = option2;
            temp2 = "option2";
            checkSelect();
        }
        totalSelect = totalSelect + 1;

    }
    public void ClickOption3(View view){
        if(totalSelect == 0){
            Option3.setBackgroundColor(0xFFFFB665);
            select1 = option3;
            temp1 = "option3";

        }

        if(totalSelect == 1){
            Option3.setBackgroundColor(0xFFFFB665);
            select2 = option3;
            temp2 = "option3";
            checkSelect();

        }
        totalSelect = totalSelect + 1;

    }
    public void ClickOption4(View view){
        if(totalSelect == 0){
            Option4.setBackgroundColor(0xFFFFB665);
            select1 = option4;
            temp1 = "option4";


        }

        if(totalSelect == 1){
            Option4.setBackgroundColor(0xFFFFB665);
            select2 = option4;
            temp2 = "option4";
            checkSelect();

        }
        totalSelect = totalSelect + 1;

    }
    public void ClickOption5(View view){
        if(totalSelect == 0){
            Option5.setBackgroundColor(0xFFFFB665);
            select1 = option5;
            temp1 = "option5";

        }
        if(totalSelect == 1){
            Option5.setBackgroundColor(0xFFFFB665);
            select2 = option5;
            temp2 = "option5";
            checkSelect();

        }
        totalSelect = totalSelect + 1;

    }

    public void ClickOption6(View view){
        if(totalSelect == 0){
            Option6.setBackgroundColor(0xFFFFB665);
            select1 = option6;
            temp1 = "option6";

        }

        if(totalSelect == 1){
            Option1.setBackgroundColor(0xFFFFB665);
            select2 = option6;
            temp2 = "option6";
            checkSelect();

        }
        totalSelect = totalSelect + 1;

    }

    public void gameStart(int level1) {
        if(level == 15 && text.equals("ten")){
            level = 0;
            level1 = 0;
            text = "hundred";
        }

        if(level == 20 && text.equals("hundred")){
            level = 0;
            level1 = 0;
            text = "thousand";
        }

        Option6.setVisibility(View.INVISIBLE);
        if(level1 < 15){
            optionten1 = FindOperatorLevelMenuActivity.getOperatorTenModels().get(level1).getOption();
        }

        if(level1 < 20){
            optionhundred2 = FindOperatorLevelMenuActivity.getOperatorHundredModels().get(level1).getOption();
        }

        if(level1 < 25){
            optionthouasand3 = FindOperatorLevelMenuActivity.getOperatorThousandModels().get(level1).getOption();
        }


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
        select1 = select2 = 0;
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

    public void checkSelect(){
        if(select1 + select2 == 10 || select1 + select2 == 100 || select1+select2 == 1000){
            Toast.makeText(FindOperatorGameActivity.this, "Câu trả lời Đúng!", Toast.LENGTH_LONG).show();
            totalSelect = -1;
            pauseTimer();
            level++;
            if(text.equals("ten")){
                score = score + point1;
            }
            if(text.equals("hundred")){
                score = score + point2;
            }

            if(text.equals("thousand")){
                score = score + point3;
            }

            updateScore(score);
            gameStart(level);
        }
        else{
            Toast.makeText(FindOperatorGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
            if(temp1.equals("option1") || temp2.equals("option1")){
                Option1.setBackgroundColor(0xFF3a378e);
            }

            if(temp1.equals("option2") || temp2.equals("option2")){
                Option2.setBackgroundColor(0xFF3a378e);
            }

            if(temp1.equals("option3") || temp2.equals("option3")){
                Option3.setBackgroundColor(0xFF3a378e);
            }

            if(temp1.equals("option4") || temp2.equals("option4")){
                Option4.setBackgroundColor(0xFF3a378e);
            }

            if(temp1.equals("option5") || temp2.equals("option5")){
                Option5.setBackgroundColor(0xFF3a378e);
            }

            if(temp1.equals("option6") || temp2.equals("option6")){
                Option6.setBackgroundColor(0xFF3a378e);
            }

            totalSelect = -1;
            select1 = 0;
            select2 = 0;
//            generate(level);
        }
    }
}