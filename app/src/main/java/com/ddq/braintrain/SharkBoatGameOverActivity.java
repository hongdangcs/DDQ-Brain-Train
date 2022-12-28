package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ddq.braintrain.gameactivity.SharkBoatGameActivity;
import com.ddq.braintrain.models.SharkBoatModel;

import java.util.List;

public class SharkBoatGameOverActivity extends AppCompatActivity {

    TextView gameOverNoti, sharkBoatScoreTextView;
    Button sharkBoatNextLevelButton, sharkBoatPlayAgainButton;

    int level, score, passScore;
    int isPassed;

    private BrainTrainDatabase brainTrainDatabase;
    private static List<SharkBoatModel> sharkBoatModels;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_boat_game_over);

        gameOverNoti = findViewById(R.id.gameOverNoti);
        sharkBoatScoreTextView = findViewById(R.id.sharkBoatScoreTextView);
        sharkBoatNextLevelButton = findViewById(R.id.sharkBoatNextLevelButton);
        sharkBoatPlayAgainButton = findViewById(R.id.sharkBoatPlayAgainButton);

        sharkBoatNextLevelButton.setVisibility(View.GONE);

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0) - 1;
        score = intent.getIntExtra("score", 0);
        isPassed = intent.getIntExtra("isPassed", 0);
        passScore = intent.getIntExtra("passscore", 0);

        if (isPassed == 1) {
            gameOverNoti.setText("Đã hoàn thành màn chơi");
            sharkBoatNextLevelButton.setVisibility(View.VISIBLE);
        } else {
            gameOverNoti.setText("Chưa vượt qua màn chơi");
        }
        sharkBoatScoreTextView.setText("Điểm: " + score + "/" + passScore);

        Log.d(TAG, "onCreate: Created");


        brainTrainDatabase = new BrainTrainDatabase(SharkBoatGameOverActivity.this);
        sharkBoatModels = new BrainTrainDAO().sharkBoatModels(brainTrainDatabase);

        sharkBoatNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLevelClick(sharkBoatModels.get(level + 1));
            }
        });

        sharkBoatPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                handleLevelClick(sharkBoatModels.get(level));
            }
        });

    }

    public void handleLevelClick(SharkBoatModel model) {
        Intent intent = new Intent(SharkBoatGameOverActivity.this, SharkBoatGameActivity.class);
        intent.putExtra("level", model.getLevel());
        intent.putExtra("score", model.getScore());
        intent.putExtra("shark", model.getNumberOfShark());
        intent.putExtra("boat", model.getNumberOfBoat());
        intent.putExtra("boatpoint", model.getPointPerBoat());
        intent.putExtra("bitecount", model.getAllowableNumberOfBite());
        intent.putExtra("passpoint", model.getLevelPassScore());
        startActivity(intent);
        finish();
    }
}