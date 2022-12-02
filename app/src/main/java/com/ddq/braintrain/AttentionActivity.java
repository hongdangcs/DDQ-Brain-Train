package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class AttentionActivity extends AppCompatActivity implements View.OnClickListener {

    TextView sharkBoatScore, sharkBoatProgress, flashCardScore, flashCardProgress, differentScore, differentProgress;
    CardView differentCardView, flashCardCardView, sharkBoatCardView;
    ImageView differentCompleted, flashCardCompleted, sharkBoatCompleted;

    private BrainTrainDatabase brainTrainDatabase;
    private ProgressModel differentModel, flashCardModel, sharkBoatModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_attention);

        sharkBoatScore = findViewById(R.id.sharkBoatScore);
        sharkBoatProgress = findViewById(R.id.sharkBoatProgress);
        flashCardScore = findViewById(R.id.flashCardScore);
        flashCardProgress = findViewById(R.id.flashCardProgress);
        differentScore = findViewById(R.id.differentScore);
        differentProgress = findViewById(R.id.differentProgress);
        differentCardView = findViewById(R.id.differentCardView);
        flashCardCardView = findViewById(R.id.flashCardCardView);
        sharkBoatCardView = findViewById(R.id.sharkBoatCardView);
        differentCompleted = findViewById(R.id.differentComplete);
        flashCardCompleted = findViewById(R.id.flashCardComplete);
        sharkBoatCompleted = findViewById(R.id.sharkBoatComplete);

        brainTrainDatabase = new BrainTrainDatabase(AttentionActivity.this);
        differentModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 21);
        differentScore.setText("Điểm của bạn: " + differentModel.getUserScore());
        differentProgress.setText("Đã hoàn thành: " + ((float) differentModel.getUserScore() / (float) differentModel.getMaxScore()) + "%");
        if (differentModel.isCompletedStatus()) {
            differentCompleted.setVisibility(View.VISIBLE);
        }

        flashCardModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 22);
        flashCardScore.setText("Điểm của bạn: " + flashCardModel.getUserScore());
        flashCardProgress.setText("Đã hoàn thành: " + ((float) flashCardModel.getUserScore() / (float) flashCardModel.getMaxScore()) + "%");
        if (flashCardModel.isCompletedStatus()) {
            flashCardCompleted.setVisibility(View.VISIBLE);
        }

        sharkBoatModel = new BrainTrainDAO().getProgressStatus(brainTrainDatabase, 23);
        sharkBoatScore.setText("Điểm của bạn: " + sharkBoatModel.getUserScore());
        sharkBoatProgress.setText("Đã hoàn thành: " + ((float) sharkBoatModel.getUserScore() / (float) sharkBoatModel.getMaxScore()) + "%");
        if (sharkBoatModel.isCompletedStatus()) {
            sharkBoatCompleted.setVisibility(View.VISIBLE);
        }

        differentCardView.setOnClickListener(AttentionActivity.this);
        flashCardCardView.setOnClickListener(AttentionActivity.this);
        sharkBoatCardView.setOnClickListener(AttentionActivity.this);


    }

    @Override
    public void onClick(View v) {
        Intent intent;
        switch (v.getId()) {
            case R.id.differentCardView:
                intent = new Intent(AttentionActivity.this, DifferentLevelMenuActivity.class);
                break;
            case R.id.flashCardCardView:
                intent = new Intent(AttentionActivity.this, FlashCardLevelMenuActivity.class);
                break;
            default:
                intent = new Intent(AttentionActivity.this, SharkBoatLevelMenuActivity.class);
                break;
        }
        startActivity(intent);
    }
}