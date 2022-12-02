package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class LanguageActivity extends AppCompatActivity implements View.OnClickListener {

    TextView completeWordScore, completeWordProgress, findWordScore, findWordProgress, conjunctionScore, conjunctionProgress, sortingCharScore, sortingCharProgress;
    CardView completeWordCardView, findWordCardView, conjunctionCardView, sortingCharCardView;
    ImageView completeWordCompleted, findWordCompleted, conjunctionCompleted, sortingCharCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel completeWordModel, findWordModel, conjunctionModel, sortingCharModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_language);

        completeWordScore = findViewById(R.id.completeWordScore);
        completeWordProgress = findViewById(R.id.completeWordProgress);
        findWordScore = findViewById(R.id.findWordScore);
        findWordProgress = findViewById(R.id.findWordProgress);
        conjunctionScore = findViewById(R.id.conjunctionScore);
        conjunctionProgress = findViewById(R.id.conjunctionProgress);
        sortingCharScore = findViewById(R.id.sortingCharScore);
        sortingCharProgress = findViewById(R.id.sortingCharProgress);
        completeWordCardView = findViewById(R.id.completeWordCardView);
        findWordCardView = findViewById(R.id.findWordCardView);
        conjunctionCardView = findViewById(R.id.conjunctionCardView);
        sortingCharCardView = findViewById(R.id.sortingCharCardView);
        completeWordCompleted = findViewById(R.id.completeWordComplete);
        findWordCompleted = findViewById(R.id.findWordCompleted);
        conjunctionCompleted = findViewById(R.id.conjunctionComplete);
        sortingCharCompleted = findViewById(R.id.sortingCharComplete);

        brainTrainDatabase = new BrainTrainDatabase(LanguageActivity.this);
        completeWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 31);
        completeWordScore.setText("Điểm của bạn: " + completeWordModel.getUserScore());
        completeWordProgress.setText("Đã hoàn thành: " + ((float) completeWordModel.getUserScore() / (float) completeWordModel.getMaxScore()) + "%");
        if (completeWordModel.isCompletedStatus()) {
            completeWordCompleted.setVisibility(View.VISIBLE);
        }

        findWordModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 32);
        findWordScore.setText("Điểm của bạn: " + findWordModel.getUserScore());
        findWordProgress.setText("Đã hoàn thành: " + ((float) findWordModel.getUserScore() / (float) findWordModel.getMaxScore()) + "%");
        if (findWordModel.isCompletedStatus()) {
            findWordCompleted.setVisibility(View.VISIBLE);
        }

        conjunctionModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 33);
        conjunctionScore.setText("Điểm của bạn: " + conjunctionModel.getUserScore());
        conjunctionProgress.setText("Đã hoàn thành: " + ((float) conjunctionModel.getUserScore() / (float) conjunctionModel.getMaxScore()) + "%");
        if (conjunctionModel.isCompletedStatus()) {
            conjunctionCompleted.setVisibility(View.VISIBLE);
        }

        sortingCharModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 34);
        sortingCharScore.setText("Điểm của bạn: " + sortingCharModel.getUserScore());
        sortingCharProgress.setText("Đã hoàn thành: " + ((float) sortingCharModel.getUserScore() / (float) sortingCharModel.getMaxScore()) + "%");
        if (sortingCharModel.isCompletedStatus()) {
            sortingCharCompleted.setVisibility(View.VISIBLE);
        }
        completeWordCardView.setOnClickListener(LanguageActivity.this);
        findWordCardView.setOnClickListener(LanguageActivity.this);
        conjunctionCardView.setOnClickListener(LanguageActivity.this);
        sortingCharCardView.setOnClickListener(LanguageActivity.this);

    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.completeWordCardView:
                intent = new Intent(LanguageActivity.this, CompleteWordGameActivity.class);
                break;

            case R.id.findWordCardView:
                intent = new Intent(LanguageActivity.this, FindWordGameActivity.class);
                break;
            case R.id.conjunctionCardView:
                intent = new Intent(LanguageActivity.this, ConjunctionGameActivity.class);
                break;
            default:
                intent = new Intent(LanguageActivity.this, SortingCharGameActivity.class);
                break;
        }
        startActivity(intent);
    }
}