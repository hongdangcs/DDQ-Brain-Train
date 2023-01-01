package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.R;
import com.ddq.braintrain.models.FindOperatorModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Random;

public class FindOperatorGameRoundActivity extends AppCompatActivity implements View.OnClickListener {

    TextView findOperatorRoundLevelTextView, FindOperatorRoundTimeTextView, FindOperatorRoundScoreTextView, FindOperatorRoundCompleteNoticeTextView;
    GridLayout findOperatorRoundGameLayout;
    Button findOperatorRoundNextLevelButton, findOperatorRoundResultButton;

    Button btn, clickedBtn;

    int level, gameLevel = 0, ansOne, score = 0;
    boolean isSelected = false;

    List<Integer> ansList;
    String databaseTable = "";

    BrainTrainDatabase brainTrainDatabase;
    List<FindOperatorModel> findOperatorModels;
    List<Button> buttons;
    List<Button> correctButtons;

    CountDownTimer timer;
    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_operator_game_round);

        findOperatorRoundLevelTextView = findViewById(R.id.findOperatorRoundLevelTextView);
        FindOperatorRoundTimeTextView = findViewById(R.id.FindOperatorRoundTimeTextView);
        FindOperatorRoundScoreTextView = findViewById(R.id.FindOperatorRoundScoreTextView);
        FindOperatorRoundCompleteNoticeTextView = findViewById(R.id.FindOperatorRoundCompleteNoticeTextView);
        findOperatorRoundGameLayout = findViewById(R.id.findOperatorRoundGameLayout);
        findOperatorRoundNextLevelButton = findViewById(R.id.findOperatorRoundNextLevelButton);
        findOperatorRoundResultButton = findViewById(R.id.findOperatorRoundResultButton);

        findOperatorRoundNextLevelButton.setVisibility(View.VISIBLE);
        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);

        if (level == 10) {
            databaseTable = "math_game_two_multiple_of_ten";
        }
        if (level == 100) {
            databaseTable = "math_game_two_multiple_of_hundred";
        }
        if (level == 1000) {
            databaseTable = "math_game_two_multiple_of_thousand";
        }

        brainTrainDatabase = new BrainTrainDatabase(FindOperatorGameRoundActivity.this);
        findOperatorModels = new BrainTrainDAO().findOperatorModels(brainTrainDatabase, databaseTable);

        findOperatorRoundLevelTextView.setText("" + findOperatorModels.get(5).getPoint());

        gameContinue();

        findOperatorRoundNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gameContinue();
            }
        });
    }

    public void gameContinue() {
        findOperatorRoundGameLayout.removeAllViews();
        isSelected = false;
        ansOne = 0;
        clickedBtn = null;
        FindOperatorRoundCompleteNoticeTextView.setVisibility(View.INVISIBLE);
        findOperatorRoundNextLevelButton.setVisibility(View.INVISIBLE);
        buttons = new ArrayList<>();
        correctButtons = new ArrayList<>();
        FindOperatorRoundScoreTextView.setText("Điểm: " + score);

        findOperatorRoundLevelTextView.setText("Màn: " + findOperatorModels.get(gameLevel).getLevel());
        generateAnsList(findOperatorModels.get(gameLevel).getOption(), level);
        Collections.shuffle(buttons);
        for (Button btn : buttons) {
            findOperatorRoundGameLayout.addView(btn);
        }
        startTimer(findOperatorModels.get(gameLevel).getTime());

        gameLevel++;
        Log.d(TAG, "gameContinue: " + gameLevel);
    }
/*
    public void generateAnsList(int options, int level){
        ansList = new ArrayList<>();
        ansList.add(random.nextInt(level-(level/10))+level/10);
        generateButton(ansList.get(0), "correct");
        ansList.add(level-ansList.get(0));
        generateButton(ansList.get(1), "correct");
        for(int i = 0; i<options-2; i++){
            ansList.add(random.nextInt(level-(level/10))+level/10);
        }
    }
*/

    public void generateAnsList(int options, int level) {
        int ans1 = random.nextInt(level - (level / 10)) + level / 10;
        generateButton(ans1, "correct");
        int ans2 = level - ans1;
        generateButton(ans2, "correct");
        for (int i = 0; i < options - 2; i++) {
            generateButton(random.nextInt(level - (level / 10)) + level / 10, "wrong");
        }
    }

    public void generateButton(int number, String ans) {
        btn = new Button(FindOperatorGameRoundActivity.this);
        btn.setText("" + number);
        btn.setTag(number);
        btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorGameRoundActivity.this, R.drawable.round_button));
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(300, 300);
        params.setMargins(10, 10, 10, 10);
        btn.setLayoutParams(params);
        btn.setTextSize(40);
        btn.setOnClickListener(FindOperatorGameRoundActivity.this);
        buttons.add(btn);
        if (ans.equals("correct")) {
            correctButtons.add(btn);
        }

    }

    public void startTimer(int time) {
        timer = new CountDownTimer(time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                FindOperatorRoundTimeTextView.setText("Thời gian: " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                levelFinish();
            }
        }.start();
    }

    public void levelFinish() {
        for (Button btn : buttons) {
            btn.setClickable(false);
        }
        FindOperatorRoundCompleteNoticeTextView.setText("Đã hết thời gian!");
        FindOperatorRoundCompleteNoticeTextView.setVisibility(View.VISIBLE);
        nextButton();
    }

    public void correctAns() {
        FindOperatorRoundCompleteNoticeTextView.setText("Câu trả lời đúng!");
        FindOperatorRoundCompleteNoticeTextView.setVisibility(View.VISIBLE);
        score += findOperatorModels.get(gameLevel - 1).getPoint();
        FindOperatorRoundScoreTextView.setText("Điểm: " + score);
        nextButton();
    }

    public void wrongAns() {
        FindOperatorRoundCompleteNoticeTextView.setText("Câu trả lời sai!");
        FindOperatorRoundCompleteNoticeTextView.setVisibility(View.VISIBLE);
        for (Button btn : correctButtons) {
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorGameRoundActivity.this, R.drawable.round_button_correct));
        }
        nextButton();
    }

    public void nextButton() {
        Log.d(TAG, "nextButton: " + gameLevel);
        if (gameLevel == findOperatorModels.size()) {
            findOperatorRoundNextLevelButton.setVisibility(View.INVISIBLE);
            findOperatorRoundResultButton.setVisibility(View.VISIBLE);
        } else {
            findOperatorRoundNextLevelButton.setVisibility(View.VISIBLE);
        }
    }

    @Override
    public void onClick(View v) {
        if (clickedBtn == null) {
            ansOne = (int) v.getTag();
            isSelected = true;
            v.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorGameRoundActivity.this, R.drawable.round_button_clicked));
            clickedBtn = (Button) v;
        } else {
            if (clickedBtn == v) {
                v.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorGameRoundActivity.this, R.drawable.round_button));
                clickedBtn = null;
            } else {
                timer.cancel();
                v.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorGameRoundActivity.this, R.drawable.round_button_clicked));
                if (ansOne + (int) v.getTag() == level) {
                    correctAns();
                } else wrongAns();
                for (Button btn : buttons) btn.setClickable(false);
            }
        }
    }
}