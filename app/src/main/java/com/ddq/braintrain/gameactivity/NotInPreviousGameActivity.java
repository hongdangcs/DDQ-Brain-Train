package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.R;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class NotInPreviousGameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView notInPreviousLevelTextView, notInPreviousInforTextView, notInPreviousTimeTextView, notInPreviousScoreTextView, notInPreviousCompleteNotiTextView;
    AppCompatButton notInPreviousResultButton;
    GridLayout notInPreviousGameLayout;
    CardView cardView;
    ImageView image;
    List<Integer> ID;
    String selected;
    int imageIndex;
    List<CardView> imageList;
    int score = 0;
    int level;

    BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(NotInPreviousGameActivity.this);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_in_previous_game);

        notInPreviousLevelTextView = findViewById(R.id.notInPreviousLevelTextView);
        notInPreviousInforTextView = findViewById(R.id.notInPreviousInforTextView);
        notInPreviousTimeTextView = findViewById(R.id.notInPreviousTimeTextView);
        notInPreviousScoreTextView = findViewById(R.id.notInPreviousScoreTextView);
        notInPreviousCompleteNotiTextView = findViewById(R.id.notInPreviousCompleteNotiTextView);
        notInPreviousResultButton = findViewById(R.id.notInPreviousResultButton);
        notInPreviousGameLayout = findViewById(R.id.notInPreviousGameLayout);
        imageList = new ArrayList<>();
        selected = "";

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        notInPreviousLevelTextView.setText("Cấp độ: " + level);
        notInPreviousInforTextView.setText("Chọn và ghi nhớ một con vật!");
        notInPreviousCompleteNotiTextView.setVisibility(View.INVISIBLE);
        notInPreviousResultButton.setVisibility(View.INVISIBLE);

        ID = new ArrayList<>();
        for (int i = 1; i < 45; i++) {
            ID.add(i);
        }
        Collections.shuffle(ID);
        for (int i = 0; i < ID.size(); i++) {

            Log.d(TAG, "-" + ID.get(i));
        }

        imageIndex = 0;
        for (int j = 0; j < level + 2; j++) {
            generateImage(ID.get(imageIndex));
            imageIndex++;
        }

        generateView();


    }

    public void generateImage(int ID) {
        cardView = new CardView(NotInPreviousGameActivity.this);
        image = new ImageView(NotInPreviousGameActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 280);
        params.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(params);
        cardView.setRadius(70);
        cardView.setTag("picture" + ID + "generate");
        image.setImageResource(getResources().getIdentifier("animal_image" + ID, "drawable", getPackageName()));
        cardView.addView(image);
        cardView.setOnClickListener(NotInPreviousGameActivity.this);
        imageList.add(cardView);
    }

    public void generateView() {
        notInPreviousGameLayout.removeAllViews();
        Collections.shuffle(imageList);
        for (int k = 0; k < imageList.size(); k++) {
            notInPreviousGameLayout.addView(imageList.get(k));
            Log.d(TAG, imageList.get(k).getTag().toString());
        }
    }

    public void disableCardView() {
        for (int l = 0; l < imageList.size(); l++) {
            imageList.get(l).setClickable(false);
        }
    }

    public void gameContinue() {
        generateImage(ID.get(imageIndex));
        imageIndex++;
        checkImageIndex();
        generateView();
    }

    public void checkImageIndex() {

        if (imageIndex == 40) {
            gameFinish();
            notInPreviousInforTextView.setText("Bạn đã hoàn thành trò chơi rất xuất sắc!");
        }
    }

    public void updateScore() {
        score += 500;
        notInPreviousScoreTextView.setText("Điểm của bạn: " + score);
    }

    public void gameFinish() {
        disableCardView();
        score += level * 100 * (imageIndex - 2);
        notInPreviousScoreTextView.setText("Điểm của bạn: " + score);
        notInPreviousResultButton.setVisibility(View.VISIBLE);

        brainTrainDatabase.updateUserScore(12, score);
    }

    @Override
    public void onClick(View v) {

        if (selected.contains(v.getTag().toString())) {
            gameFinish();
            notInPreviousInforTextView.setText("Chọn lựa đã bị trùng!");
            Log.d(TAG, selected + "\n ban da chon " + v.getTag().toString());
        } else {
            String tag = v.getTag().toString();
            selected += tag;
            notInPreviousInforTextView.setText("Chọn một con vật mà bạn chưa chọn trước đó");
            updateScore();
            gameContinue();
            Log.d(TAG, "ban chon " + tag);
        }
    }

}