package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.GameResultActivity;
import com.ddq.braintrain.MemoryActivity;
import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.GridsHighlightLevelMenuActivity;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class GridsHighlightGameActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView textView, gridsHighlightTimeTextView, gridHighlightInforTextView, gridsHighlightCompleteNotiTextView, gridsHighlightScoreTextView;
    AppCompatButton btn, nextLevelButton, resultButton;
    GridLayout gridsHighlightGameLayout;

    private static final int TRIALS = 5, START_TIMER = 16000;
    CountDownTimer timer;
    long timeLeft = START_TIMER;
    int trials = TRIALS, level = 0, trueAns = 0, score;
    int wrong, bonusScore;

    List<AppCompatButton> list;
    int currentTotalScore = MemoryActivity.getGridHighlightTotalScore();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grids_highlight_game);

        textView = findViewById(R.id.gridsHighlightLevelTextView);
        gridsHighlightGameLayout = findViewById(R.id.gridsHighlightGameLayout);
        gridHighlightInforTextView = findViewById(R.id.gridHighlightInforTextView);
        gridsHighlightCompleteNotiTextView = findViewById(R.id.gridsHighlightCompleteNotiTextView);
        gridsHighlightScoreTextView = findViewById(R.id.gridsHighlightScoreTextView);
        nextLevelButton = findViewById(R.id.nextLevelButton);
        resultButton = findViewById(R.id.resultButton);
        gridsHighlightTimeTextView = findViewById(R.id.gridsHighlightTimeTextView);

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        trials = intent.getIntExtra("trial", 0);
        score = intent.getIntExtra("score", 0);
        bonusScore = intent.getIntExtra("bonusScore", 0);

        wrong = 0;

        nextLevelButton.setVisibility(View.GONE);
        resultButton.setVisibility(View.GONE);
        gridsHighlightCompleteNotiTextView.setVisibility(View.GONE);
        textView.setText("Cấp độ: " + level);
        gridsHighlightScoreTextView.setText("Điểm: " + score);

        generateGrid();
        rememberTimer();

        nextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("level", ++level);
                intent.putExtra("trial", trials);
                intent.putExtra("score", score);
                intent.putExtra("bonusScore", (bonusScore + level*100));
                startActivity(intent);
                finish();
            }
        });

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(GridsHighlightGameActivity.this, GameResultActivity.class);
                intent1.putExtra("score", score);
                intent1.putExtra("bonusScore", (bonusScore + level*100));
                startActivity(intent1);
                finish();
            }
        });

    }

    public void generateGrid() {
        list = new ArrayList<AppCompatButton>();
        int gridx = MemoryActivity.getHighlightGridsModels().get(level - 1).getGridx();
        int gridy = MemoryActivity.getHighlightGridsModels().get(level - 1).getGridy();
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
            btn.setTag(false);

            if (j < level && i == n[j]) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
                btn.setTag(true);
                j++;
            }

            btn.setOnClickListener(GridsHighlightGameActivity.this);
            gridsHighlightGameLayout.addView(btn);
            btn.setClickable(false);
            list.add(btn);
        }

    }

    public void rememberTimer() {
        timer = new CountDownTimer(8000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateTimeCounterText();
            }

            @Override
            public void onFinish() {
                gameStart();
            }
        }.start();
    }

    public void updateTimeCounterText() {
        gridsHighlightTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void gameStart() {
        for (AppCompatButton btn : list) {
            btn.setClickable(true);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile));
            gridHighlightInforTextView.setText("Chọn các ô màu vàng đã bị ẩn");
        }
        startTimer();
    }

    public void startTimer() {
        timer = new CountDownTimer(START_TIMER, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                timeLeft = millisUntilFinished / 1000;
                updateTimeCounterText();
            }

            @Override
            public void onFinish() {
                updateTrials();
            }
        }.start();
    }

    public void updateTrials() {
        trials--;
        if(wrong == 1) level--;
        if(wrong >= 2){
            level -= (wrong / 2 + 1);
            if(level < 0){ level = 0;}
        }
        if (trials == 0) {
            gameFinish();
        } else nextLevel();
    }

    public void gameStop() {
        enableStopNoti();
    }

    public void disableGridButton() {
        for (AppCompatButton btn : list) {
            btn.setClickable(false);
        }
    }

    public void gameContinue(){
        enableNextLevelButton();
    }

    public void enableStopNoti() {
        resultButton.setVisibility(View.VISIBLE);
        gridsHighlightCompleteNotiTextView.setText("Hết lượt chơi!");
        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
    }


    public void pauseTimer() {
        timer.cancel();
    }

    public void nextLevel() {
        BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(GridsHighlightGameActivity.this);
        if(level > 0) if(MemoryActivity.getHighlightGridsModels().get(level -1).getCompleteStatus() == 0){
            currentTotalScore+= score;
            brainTrainDatabase.updateUserScore(11, currentTotalScore);

        }
        gridsHighlightScoreTextView.setText("Điểm: " + score);
        disableGridButton();
        enableNextLevelButton();
        updateLevelStatus();
        brainTrainDatabase.updateCompletedStatus("memory_game_one", level);

    }

    public void enableNextLevelButton() {
        nextLevelButton.setVisibility(View.VISIBLE);
        resultButton.setVisibility(View.GONE);
        gridsHighlightCompleteNotiTextView.setText("Hoàn Thành Màn Chơi!");
        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
    }

    public void gameFinish() {
        gridsHighlightScoreTextView.setText("Điểm: " + score);
        disableGridButton();
        nextLevelButton.setVisibility(View.GONE);
        resultButton.setVisibility(View.VISIBLE);
        gridsHighlightCompleteNotiTextView.setText("Hoàn Thành Lượt Chơi!");
        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
    }

    public void updateScore() {
        score += 200;
        Log.d(TAG, "updateScore: update score" + score);
        gridsHighlightScoreTextView.setText("Điểm: " + score);
    }

    public void updateLevelStatus() {

    }

    @Override
    public void onClick(View v) {
        pauseTimer();
        String btnTag = v.getTag().toString();
        if (btnTag.equals("true")) {
            v.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
            updateScore();
            trueAns++;
            if(trueAns == level){
                disableGridButton();
                updateTrials();
            }
        } else {
            Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_SHORT).show();
            v.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight_wrong));
            wrong++;
        }
    }

    @Override
    public void onBackPressed() {
        startActivity(new Intent(GridsHighlightGameActivity.this, MemoryActivity.class));
        finish();
    }

    //
//    public void generateGrid(int level) {
//
//        list = new ArrayList<AppCompatButton>();
//        int gridx = GridsHighlightLevelMenuActivity.getHighlightGridsModels().get(level - 1).getGridx();
//        int gridy = GridsHighlightLevelMenuActivity.getHighlightGridsModels().get(level - 1).getGridy();
//        gridsHighlightGameLayout.setColumnCount(gridx);
//        gridsHighlightGameLayout.setRowCount(gridy);
//
//        ArrayList<Integer> numbers = new ArrayList();
//        for (int k = 0; k < gridx * gridx; k++) {
//            numbers.add(k);
//        }
//        Collections.shuffle(numbers);
//
//        int[] n = new int[level];
//        for (int i = 0; i < level; i++) {
//            n[i] = numbers.get(i);
//        }
//        Arrays.sort(n);
//        int j = 0;
//
//        for (int i = 0; i < gridx * gridy; i++) {
//            btn = new AppCompatButton(GridsHighlightGameActivity.this);
//            btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile));
//            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(100, 100);
//            btn.setLayoutParams(params);
//
//            btn.setTag(false);
///*
//            btn.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    pauseTimer();
//                    Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời sai!", Toast.LENGTH_LONG).show();
//                    updateTrials();
//                    updateGridsHighlightOpportunityTextView();
//                }
//            });
//*/
//            if (j < level && i == n[j]) {
//                btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
//                btn.setTag(true);
//                /*btn.setOnClickListener(new View.OnClickListener() {
//                    @Override
//                    public void onClick(View v) {
//                        pauseTimer();
//                        Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời Đúng!", Toast.LENGTH_LONG).show();
//                        btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
//                        startTimer();
//                    }
//                });*/
//                j++;
//            }
//            btn.setOnClickListener(GridsHighlightGameActivity.this);
//            gridsHighlightGameLayout.addView(btn);
//            btn.setClickable(false);
//            list.add(btn);
//            //startTimer();
//            //changButtonTimer(btn);
//        }
//
//
//    }
//
//    public void gameStart(int level) {
//        textView.setText("Cấp độ: " + level);
//        gridsHighlightCompleteNotiTextView.setVisibility(View.INVISIBLE);
//        nextLevelButton.setVisibility(View.INVISIBLE);
//        resultButton.setVisibility(View.INVISIBLE);
//        generateGrid(level);
//        changButtonTimer();
//    }
//
//    public void changButtonTimer() {
//        timer = new CountDownTimer(7000, 1000) {
//            @Override
//            public void onTick(long millisUntilFinished) {
//                timeLeft = millisUntilFinished / 1000;
//                updateText();
//            }
//
//            @Override
//            public void onFinish() {
//                for (AppCompatButton btn : list) {
//                    btn.setClickable(true);
//                    btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile));
//                    startTimer();
//                    gridHighlightInforTextView.setText("Chọn các ô màu vàng đã bị ẩn");
//                }
//            }
//        }.start();
//    }
//
//    public void gameContinue() {
//
//    }
//
//    public void updateGridsHighlightOpportunityTextView() {
//        gridsHighlightOpportunityTextView.setText("Cơ hội: " + trials);
//    }
//
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
//                updateTrials();
//            }
//        }.start();
//    }
//
//    /*
//        public void gameStartTimer() {
//            timer = new CountDownTimer(10000, 1000) {
//                @Override
//                public void onTick(long millisUntilFinished) {
//                    timeLeft = millisUntilFinished / 1000;
//                    updateText();
//                }
//                @Override
//                public void onFinish() {
//                }
//            }.start();
//        }
//        */
//    public void pauseTimer() {
//        timer.cancel();
//        timer = null;
//    }
//
//    public void updateTrials() {
//        trials--;
//        if(trials>0){
//            startTimer();
//        } else{
//            gameStop();
//        }
//        updateGridsHighlightOpportunityTextView();
//
//    }
//
//    public void updateText() {
//        gridsHighlightTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
//    }
//
//    public void openResultButton() {
//        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
//        gridsHighlightCompleteNotiTextView.setText("Hết lượt chơi!");
//        nextLevelButton.setVisibility(View.GONE);
//        resultButton.setVisibility(View.VISIBLE);
//    }
//
//    public void openNextLevelButton() {
//        gridsHighlightCompleteNotiTextView.setVisibility(View.VISIBLE);
//        gridsHighlightCompleteNotiTextView.setText("Hoàn thành màn chơi");
//        nextLevelButton.setVisibility(View.VISIBLE);
//        resultButton.setVisibility(View.VISIBLE);
//    }
//
//
//    public void disableBtn() {
//        for (AppCompatButton btn : list) {
//            btn.setClickable(false);
//        }
//    }
//
//    public void gameStop() {
//        pauseTimer();
//        openResultButton();
//        //set all grid not clickable
//        disableBtn();
//    }
//
//    public void trueAnsCheck() {
//        if (trueAns == level) {
//            disableBtn();
//            openNextLevelButton();
//        } else {
//            startTimer();
//        }
//    }
//
//    @Override
//    public void onClick(View v) {
//        String btnTag = v.getTag().toString();
//        if (btnTag.equals("false")) {
//            Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời sai!", Toast.LENGTH_LONG).show();
//            pauseTimer();
//            updateTrials();
//            updateGridsHighlightOpportunityTextView();
//        } else {
//            pauseTimer();
//            Toast.makeText(GridsHighlightGameActivity.this, "Câu trả lời Đúng!", Toast.LENGTH_LONG).show();
//            v.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightGameActivity.this, R.drawable.grid_tile_highlight));
//            trueAns++;
//            trueAnsCheck();
//        }
//    }
}