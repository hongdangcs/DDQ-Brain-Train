package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class FindOperatorGameResultActivity extends AppCompatActivity {

    TextView findOperatorResultTotalScoreTextView, findOperatorResultScoreTextView, findOperatorResultAvgTimeTextView;
    int score, time;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_operator_game_result);

        findOperatorResultAvgTimeTextView = findViewById(R.id.findOperatorResultAvgTimeTextView);
        findOperatorResultTotalScoreTextView = findViewById(R.id.findOperatorResultTotalScoreTextView);
        findOperatorResultScoreTextView = findViewById(R.id.findOperatorResultScoreTextView);

        Intent intent = getIntent();
        score = intent.getIntExtra("score", 0);
        time = intent.getIntExtra("avgTime", 0);
        findOperatorResultScoreTextView.setText("Điểm đạt được: "+ score);
        findOperatorResultAvgTimeTextView.setText("Thời gian trung bình: "+ time);
        findOperatorResultTotalScoreTextView.setText("Tổng điểm: "+ (score + score/time));
    }
}