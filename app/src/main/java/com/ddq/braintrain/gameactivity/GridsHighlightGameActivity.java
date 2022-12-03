package com.ddq.braintrain.gameactivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.GridsHighlightLevelMenuActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GridsHighlightGameActivity extends AppCompatActivity {

    private TextView textView, gridsHighlightTimeTextView, gridHighlightInforTextView, gridsHighlightCompleteNotiTextView, gridsHighlightOpportunityTextView, gridsHighlightScoreTextView;
    AppCompatButton btn, nextLevelButton, resultButton;
    GridLayout gridsHighlightGameLayout;

    private static final int TRIALS = 12, START_TIMER = 16000;
    CountDownTimer timer;
    long timeLeft = START_TIMER;
    int trials = TRIALS;

    List<AppCompatButton> list;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grids_highlight_game);

        textView = findViewById(R.id.gridsHighlightLevelTextView);
        gridsHighlightGameLayout = findViewById(R.id.gridsHighlightGameLayout);
        gridHighlightInforTextView = findViewById(R.id.gridHighlightInforTextView);
        gridsHighlightCompleteNotiTextView = findViewById(R.id.gridsHighlightCompleteNotiTextView);
        gridsHighlightScoreTextView = findViewById(R.id.gridsHighlightScoreTextView);
        gridsHighlightOpportunityTextView = findViewById(R.id.gridsHighlightOpportunityTextView);
        nextLevelButton = findViewById(R.id.nextLevelButton);
        resultButton = findViewById(R.id.resultButton);
        gridsHighlightTimeTextView = findViewById(R.id.gridsHighlightTimeTextView);


        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);

        gameStart(level);

    }

    public void generateGrid(int level) {

        list = new ArrayList<AppCompatButton>();
        int gridx = GridsHighlightLevelMenuActivity.getHighlightGridsModels().get(level - 1).getGridx();
        int gridy = GridsHighlightLevelMenuActivity.getHighlightGridsModels().get(level - 1).getGridy();
        gridsHighlightGameLayout.setColumnCount(gridx);
        gridsHighlightGameLayout.setRowCount(gridy);

        ArrayList<Integer> numbers = new ArrayList();
        for (int k = 0; k < gridx * gridx; k++) {
            numbers.add(k);
        }
        Collections.shuffle(numbers);

        int[] n = new int[level];
        for (int i = 0; i < level; i++) {
            n[i] = numbers.get(i);
        }
        Arrays.sort(n);
        int j = 0;

        for (int i = 0; i < gridx * gridy; i++) {
            btn = new AppCompatButton(GridsHighlightGameActivity.this);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile));
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
            btn.setLayoutParams(params);

            btn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    pauseTimer();
                    Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời sai!", Toast.LENGTH_LONG).show();
                    updateTrials();
                    updateGridsHighlightOpportunityTextView();
                }
            });

            if (j < level && i == n[j]) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
                btn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        pauseTimer();
                        Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời Đúng!", Toast.LENGTH_LONG).show();
                        startTimer();
                    }
                });
                j++;
            }
            gridsHighlightGameLayout.addView(btn);
            btn.setClickable(false);
            list.add(btn);
            //startTimer();
            //changButtonTimer(btn);
        }
    }

    public void gameStart(int level) {
        textView.setText("Cấp độ: " + level);
        gridsHighlightCompleteNotiTextView.setVisibility(View.INVISIBLE);
        nextLevelButton.setVisibility(View.INVISIBLE);
        resultButton.setVisibility(View.INVISIBLE);
        generateGrid(level);
        changButtonTimer();
    }

    public void changButtonTimer() {
        timer = new CountDownTimer(7000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateText();
            }

            @Override
            public void onFinish() {
                for (AppCompatButton btn : list) {
                    btn.setClickable(true);
                    btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile));
                }
            }
        }.start();
    }

    public void gameContinue() {

    }

    public void updateGridsHighlightOpportunityTextView(){
        gridsHighlightOpportunityTextView.setText("Cơ hội: "+ trials);
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
                updateTrials();
            }
        }.start();
    }

    public void gameStartTimer() {
        timer = new CountDownTimer(10000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateText();
            }

            @Override
            public void onFinish() {

            }
        }.start();
    }

    public void pauseTimer(){
timer.cancel();
    }

    public void updateTrials() {
        if (trials > 0) {
            startTimer();
            trials--;
        } else {
            gameStop();
        }
    }

    public void updateText() {
        gridsHighlightTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void openResultButton() {
        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
        gridsHighlightCompleteNotiTextView.setText("Hết lượt chơi!");
        nextLevelButton.setVisibility(View.GONE);
        resultButton.setVisibility(View.VISIBLE);
    }

    public void gameStop() {
        openResultButton();
        //set all grid not clickable
        for (AppCompatButton btn : list) {
            btn.setClickable(false);
        }
    }

}