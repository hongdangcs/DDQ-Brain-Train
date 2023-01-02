package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.GameResultActivity;
import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.CompareLevelMenuActivity;
import com.ddq.braintrain.models.CompareModel;

import java.util.List;

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


    private BrainTrainDatabase brainTrainDatabase;
    private static List<CompareModel> compareModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_game);


        brainTrainDatabase = new BrainTrainDatabase(CompareGameActivity.this);
        compareModels = new BrainTrainDAO().compareModels(brainTrainDatabase);


        textView = findViewById(R.id.CompareLevelTextView);
        CompareTimeTextView = findViewById(R.id.CompareTimeTextView);
        Expression1 = (Button) findViewById(R.id.Expression1);
        Expression2 = (Button) findViewById(R.id.Expression2);
        CompareCompleteNotiTextView = findViewById(R.id.CompareCompleteNoticeTextView);
        CompareScoreTextView = findViewById(R.id.CompareScoreTextView);
        resultButton = findViewById(R.id.resultButton);
        Intent intent = getIntent();
        level = 1;
        textView.setText("Level: " + level);
        gameStart(level - 1);

        resultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(CompareGameActivity.this, GameResultActivity.class);
                intent1.putExtra("score", score);
                startActivity(intent1);
                finish();
            }
        });

    }

    public void generate(int level) {
        int temp = level + 1;
        textView.setText("Cấp độ: " + temp);
        updateScore(score);
        String ExpressionText1 = compareModels.get(level).getExpression1();
        Expression1.setText(ExpressionText1);
        String ExpressionText2 = compareModels.get(level).getExpression2();
        Expression2.setText(ExpressionText2);
        ExpressionResult1 = compareModels.get(level).getExpressionResult1();
        ExpressionResult2 = compareModels.get(level).getExpressionResult2();
        point = compareModels.get(level).getScore();
        Expression1.setBackgroundDrawable(ContextCompat.getDrawable(CompareGameActivity.this, R.drawable.expression));
        Expression2.setBackgroundDrawable(ContextCompat.getDrawable(CompareGameActivity.this, R.drawable.expression));
    }

    public void ClickExpresion1(View view) {
        if (ExpressionResult1 < ExpressionResult2) {
            Expression1.setBackgroundDrawable(ContextCompat.getDrawable(CompareGameActivity.this, R.drawable.expression_correct));
            count++;
            if (count == 5) {
                pauseTimer();
                timeLeft = timeLeft + 10;
                UpdateTimer();
                count = 0;
            }
            BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(CompareGameActivity.this);
            brainTrainDatabase.updateUserScore(11, score);
            int temp = level - 1;
            brainTrainDatabase.updateCompletedStatus("math_game_one", temp);
            level = level + 1;
            score = score + point;
            if (level == 101) {
                gameEnd();
            } else {
                generate(level - 1);
            }
        } else {
            pauseTimer();
            timeLeft = timeLeft - 2;
            level = level + 1;
            generate(level - 1);
            UpdateTimer();
        }
    }

    public void ClickExpresion2(View view) {
        if (ExpressionResult1 > ExpressionResult2) {
            Expression2.setBackgroundDrawable(ContextCompat.getDrawable(CompareGameActivity.this, R.drawable.expression_correct));
            count++;
            if (count == 5) {
                pauseTimer();
                timeLeft = timeLeft + 10;
                UpdateTimer();
                count = 0;
            }
            BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(CompareGameActivity.this);
            brainTrainDatabase.updateUserScore(11, score);
            int temp = level - 1;
            brainTrainDatabase.updateCompletedStatus("math_game_one", temp);
            level = level + 1;
            score = score + point;
            if (level == 101) {
                gameEnd();
            } else {
                generate(level - 1);
            }
        } else {
            pauseTimer();
            timeLeft = timeLeft - 2;level = level + 1;
            generate(level - 1);
            UpdateTimer();
        }
    }

    public void gameStart(int level1) {
        CompareCompleteNotiTextView.setVisibility(View.GONE);
//        nextLevelButton.setVisibility(View.INVISIBLE);
        resultButton.setVisibility(View.GONE);
        generate(level1);
        startTimer();
    }

    public void UpdateTimer() {
        timer = new CountDownTimer(timeLeft * 1000, 1000) {
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

    public void pauseTimer() {
        timer.cancel();
    }

    public void updateText() {
        CompareTimeTextView.setText("Bạn còn: " + timeLeft + " giây");
    }

    public void updateScore(int score) {
        CompareScoreTextView.setText("Điểm: " + score);

    }

    public void gameStop() {
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

    public void gameEnd() {
        CompareCompleteNotiTextView.setVisibility(View.VISIBLE);
        CompareCompleteNotiTextView.setText("Hoàn Thành");
        resultButton.setVisibility(View.VISIBLE);
        Expression1.setClickable(false);
        Expression2.setClickable(false);

    }

    public void back() {
        finish();
    }


}