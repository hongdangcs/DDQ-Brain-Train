package com.ddq.braintrain.gameactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.FindOperatorLevelMenuActivity;
import com.ddq.braintrain.models.SortingCharGameModel;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;
import java.util.Random;

public class SortingCharGameActivity extends AppCompatActivity  {
    private static final int START_TIMER = 20000;
    CountDownTimer timer;
    long timeLeft = START_TIMER, timeRes, totalTimeRes;
    int score = 0, section = 1;
    private String correctWord;
    double averageRes = 0, bonusScore = 0, totalScore = 0;
    private TextView textQuestion, txtQId, txtLanguageTime, txtLanguageScore, txtLanguageCompleteNoti, txtLanguageCompleteGame;
    AppCompatButton nextLevelButton, submitButton;
    private EditText editAnswer;
    private BrainTrainDatabase brainTrainDatabase;
    private static List<SortingCharGameModel> sortingCharGameModels;
    private int level = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sorting_char_game);
        textQuestion = (TextView) findViewById(R.id.txtQuestion);
        txtLanguageTime = (TextView) findViewById(R.id.txtLanguageTime);
        txtLanguageScore = (TextView) findViewById(R.id.txtLanguageScore);
        txtLanguageCompleteNoti = (TextView) findViewById(R.id.txtLanguageCompleteNoti);
        txtLanguageCompleteGame = (TextView) findViewById(R.id.txtLanguageCompleteGame);
        nextLevelButton = findViewById(R.id.nextLevelButton);
        submitButton = findViewById(R.id.submitButton);
        txtQId = (TextView) findViewById(R.id.txtQId);
        editAnswer = findViewById(R.id.editAnswer);
        txtLanguageCompleteGame.setVisibility(View.GONE);
        brainTrainDatabase = new BrainTrainDatabase(SortingCharGameActivity.this);
        sortingCharGameModels = new BrainTrainDAO().sortingCharGameModels(brainTrainDatabase);
        gameStart();

    }

    // Game section:
    public void gameStart() {
        if (level == 10) {
            getAverageTimeRes();
            getBonusScore();
            getTotalScore();
            timeRes = 0;
            totalTimeRes = 0;
            averageRes = 0;
            bonusScore = 0;
            score = 0;
            totalScore = 0;
            gameStop();
        } else {
            level++;
            txtLanguageScore .setText("Điểm: " + timeRes);
            submitButton.setVisibility(View.VISIBLE);
            editAnswer.setVisibility(View.VISIBLE);
            nextLevelButton.setVisibility(View.GONE);
            txtLanguageCompleteNoti.setVisibility(View.GONE);
            for (int i = 0; i < sortingCharGameModels.size(); i++) {
                Collections.shuffle(sortingCharGameModels);
                txtQId.setText("Câu "+level);
                correctWord = sortingCharGameModels.get(i).getWord();
                String word = correctWord;
                word = word.replaceAll(" ", "").trim();
                Random r = new Random();
                word = scramble(r, word);
                word = word.replaceAll(".(?=.)", "$0 ");
                textQuestion.setText(word);
            }
            startTimer();
        }
    }

    public String scramble( Random random, String inputString )
    {
        // Convert your string into a simple char array:
        char a[] = inputString.toCharArray();

        // Scramble the letters using the standard Fisher-Yates shuffle,
        for( int i=0 ; i<a.length ; i++ )
        {
            int j = random.nextInt(a.length);
            // Swap letters
            char temp = a[i]; a[i] = a[j];  a[j] = temp;
        }

        return new String(a);
    }

    public void gameStop() {
        submitButton.setVisibility(View.GONE);
        editAnswer.setVisibility(View.GONE);
        txtLanguageCompleteNoti.setText("Hết 10 câu hỏi của phần " + section);
        txtLanguageCompleteNoti.setVisibility(View.VISIBLE);
        nextLevelButton.setVisibility(View.VISIBLE);
    }

    // Time section:
    public void startTimer() {
        timer = new CountDownTimer(START_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateTimeCounterText();
            }

            @Override
            public void onFinish() {
                timeOver();
            }
        }.start();
    }

    public void updateTimeCounterText() {
        txtLanguageTime.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void getTimeRes() {
        timeRes = 20 - timeLeft;
        long totalTimeRes = 0;
        totalTimeRes += timeRes;
    }

    public void getAverageTimeRes() {
        averageRes = totalTimeRes / 10;
    }

    public void rememberTimer() {
        timer = new CountDownTimer(2500, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                submitButton.setVisibility(View.GONE);
                editAnswer.setVisibility(View.GONE);
                textQuestion.setText(correctWord);
            }

            @Override
            public void onFinish() {
                gameStart();
            }
        }.start();
    }

    // Notification section
    public void timeOver() {
        txtLanguageCompleteNoti.setText("Hết giờ!");
        txtLanguageCompleteNoti.setVisibility(View.VISIBLE);
        rememberTimer();
    }

    // Score section
    public void updateScore() {
        score += 200;
        txtLanguageScore.setText("Điểm: " + score);
    }

    public void getBonusScore() {
        bonusScore = score / averageRes;
    }

    public void getTotalScore() {
        totalScore = score + bonusScore;
    }

    // Submit button handle:
    public void Submit(View view) {
        String userInput = editAnswer.getText().toString().toLowerCase(Locale.ROOT);
        if (userInput.equals(correctWord)){
            timer.cancel();
            getTimeRes();
            updateScore();
            editAnswer.getText().clear();
            gameStart();
        }
        else {
            Toast.makeText(SortingCharGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_LONG).show();
        }
    }

    // Next section handle:
    public void nextSection(View view) {
        if (section <= 4){
            section = section + 1;
            level = 0;
            gameStart();
        } else {
            nextLevelButton.setVisibility(View.GONE);
            txtLanguageCompleteNoti.setVisibility(View.GONE);
            submitButton.setVisibility(View.GONE);
            editAnswer.setVisibility(View.GONE);
            txtLanguageCompleteGame.setVisibility(View.VISIBLE);
        }
    }
}