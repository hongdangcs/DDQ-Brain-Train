package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MemoryActivity extends AppCompatActivity {

    CardView notInPreviousCardView, missingObjectCardView, gridsHighlightCardView;
    TextView notInPreviousScore, missingObjectScore, gridsHighlightScore, notInPreviousProgress, missingObjectProgress, gridsHighlightProgress;
    ImageView gridsHighlightComplete, missingObjectComplete, notInPreviousComplete;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel notInPreviousModel, gridsHighlightModel, missingObjectModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_memory);

        notInPreviousScore = findViewById(R.id.notInPreviousScore);
        notInPreviousCardView = findViewById(R.id.notInPreviousCardView);
        missingObjectCardView = findViewById(R.id.missingObjectCardView);
        gridsHighlightCardView = findViewById(R.id.gridsHighlightCardView);
        gridsHighlightScore = findViewById(R.id.gridsHighlightScore);
        notInPreviousProgress = findViewById(R.id.notInPreviousProgress);
        missingObjectProgress = findViewById(R.id.missingObjectProgress);
        gridsHighlightProgress = findViewById(R.id.gridsHighlightProgress);
        gridsHighlightComplete = findViewById(R.id.gridsHighlightComplete);
        missingObjectScore = findViewById(R.id.missingObjectScore);
        missingObjectComplete = findViewById(R.id.missingObjectComplete);
        notInPreviousComplete = findViewById(R.id.notInPreviousComplete);

        brainTrainDatabase = new BrainTrainDatabase(MemoryActivity.this);
        gridsHighlightModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 11);
        gridsHighlightScore.setText("Điểm của bạn: "+ gridsHighlightModel.getUserScore());
        gridsHighlightProgress.setText("Đã hoàn thành: "+ ((float)gridsHighlightModel.getUserScore()/(float)gridsHighlightModel.getMaxScore())+"%");
        if(gridsHighlightModel.isCompletedStatus()){
            gridsHighlightProgress.setVisibility(View.GONE);
            gridsHighlightComplete.setVisibility(View.VISIBLE);
        }

        notInPreviousModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 12);
        notInPreviousScore.setText("Điểm của bạn: "+ notInPreviousModel.getUserScore());
        notInPreviousProgress.setText("Đã hoàn thành: "+ ((float)notInPreviousModel.getUserScore()/(float)notInPreviousModel.getMaxScore())+"%");
        if(notInPreviousModel.isCompletedStatus()){
            notInPreviousComplete.setVisibility(View.VISIBLE);
        }

        missingObjectModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 13);
        missingObjectScore.setText("Điểm của bạn: "+ missingObjectModel.getUserScore());
        missingObjectProgress.setText("Đã hoàn thành: "+ ((float)missingObjectModel.getUserScore()/(float)missingObjectModel.getMaxScore())+"%");
        if(missingObjectModel.isCompletedStatus()){
            missingObjectComplete.setVisibility(View.VISIBLE);
        }


    }
}