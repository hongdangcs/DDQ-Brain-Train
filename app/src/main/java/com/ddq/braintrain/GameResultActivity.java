package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class GameResultActivity extends AppCompatActivity {

    TextView resultScoreTextView, resultBonusScoreTextView, resultTotalScoreTextView;
    Button backToHomeButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_game_result);

        resultScoreTextView = findViewById(R.id.resultScoreTextView);
        backToHomeButton = findViewById(R.id.backToHomeButton);
        resultBonusScoreTextView = findViewById(R.id.resultBonusScoreTextView);
        resultTotalScoreTextView = findViewById(R.id.resultTotalScoreTextView);

        Intent intent = getIntent();
        int score = intent.getIntExtra("score", 0);
        int bonusScore = intent.getIntExtra("bonusScore", 0);
        resultScoreTextView.setText("Điểm của bạn: "+ score);
        resultBonusScoreTextView.setText("Điểm thưởng: "+ bonusScore);
        resultTotalScoreTextView.setText("Điểm thưởng: "+ (score + bonusScore));

        backToHomeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(GameResultActivity.this, MainActivity.class));
                finish();
            }
        });
    }
}