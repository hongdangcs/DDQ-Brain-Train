package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MathActivity extends AppCompatActivity {

    TextView findOperatorScore, compareScore, findOperatorProgress, compareProgress;
    CardView findOperatorCardView, compareCardView;
    ImageView findOperatorCompleted, compareCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel findOperatorModel, compareModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_math);

        findOperatorCardView = findViewById(R.id.findOperatorCardView);
        findOperatorScore = findViewById(R.id.findOperatorScore);
        compareScore = findViewById(R.id.compareScore);
        findOperatorProgress = findViewById(R.id.findOperatorProgress);
        compareProgress = findViewById(R.id.compareProgress);
        compareCardView = findViewById(R.id.compareCardView);
        findOperatorCompleted = findViewById(R.id.findOperatorCompleted);
        compareCompleted = findViewById(R.id.compareComplete);

        brainTrainDatabase = new BrainTrainDatabase(MathActivity.this);
        findOperatorModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 41);
        findOperatorScore.setText("Điểm của bạn: "+ findOperatorModel.getUserScore());
        findOperatorProgress.setText("Đã hoàn thành: "+ ((float)findOperatorModel.getUserScore()/(float)findOperatorModel.getMaxScore())+"%");
        if(findOperatorModel.isCompletedStatus()){
            findOperatorCompleted.setVisibility(View.VISIBLE);
        }

        compareModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 42);
        compareScore.setText("Điểm của bạn: "+ compareModel.getUserScore());
        compareProgress.setText("Đã hoàn thành: "+ ((float)compareModel.getUserScore()/(float)compareModel.getMaxScore())+"%");
        if(compareModel.isCompletedStatus()){
            compareCompleted.setVisibility(View.VISIBLE);
        }

        compareCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, CompareLevelMenuActivity.class);
                startActivity(intent);
            }
        });
        findOperatorCardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MathActivity.this, FindOperatorLevelMenuActivity.class);
                startActivity(intent);
            }
        });
    }
}