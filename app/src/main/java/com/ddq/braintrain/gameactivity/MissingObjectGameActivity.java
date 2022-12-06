package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.MemoryActivity;
import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.MissingObjectLevelMenuActivity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class MissingObjectGameActivity extends AppCompatActivity implements View.OnClickListener {

    TextView missingObjectLevelTextView, missingObjectTimeTextView, missingObjectScoreTextView, missingObjectInforTextView, missingObjectCompleteNotiTextView;
    AppCompatButton missingObjectNextLevelButton, missingObjectResultButton, missingObjectPlayAgainButton;
    GridLayout missingObjectRememberSetGameLayout, missingObjectAnswerSetGameLayout, missingObjectQuestionSetGameLayout;
    CardView cardView;
    ImageView image;
    CountDownTimer timer;
    int level, numberOfCard, hideCard, time, correctAnswer = 0, userScore;
    List<Integer> ID;
    List<CardView> forRememberList, questionList, answerList;

    String itemName, row;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_object_game);

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);

        itemName = "animal_image_";
        row = "complete_status_easy";


        if(level > 100 && level < 200){
            level = level - 100;
            itemName = "transportation_item_";
            row = "complete_status_medium";

        }

        if(level > 1000 ){
            level = level - 1000;
            itemName = "transportation_item_";        row = "complete_status_hard";

        }

        userScore = MemoryActivity.getMissingObjectCurrentScore();

        missingObjectLevelTextView = findViewById(R.id.missingObjectLevelTextView);
        missingObjectTimeTextView = findViewById(R.id.missingObjectTimeTextView);
        missingObjectScoreTextView = findViewById(R.id.missingObjectScoreTextView);
        missingObjectInforTextView = findViewById(R.id.missingObjectInforTextView);
        missingObjectCompleteNotiTextView = findViewById(R.id.missingObjectCompleteNotiTextView);
        missingObjectNextLevelButton = findViewById(R.id.missingObjectNextLevelButton);
        missingObjectPlayAgainButton = findViewById(R.id.missingObjectPlayAgainButton);
        missingObjectResultButton = findViewById(R.id.missingObjectResultButton);
        missingObjectRememberSetGameLayout = findViewById(R.id.missingObjectRememberSetGameLayout);
        missingObjectAnswerSetGameLayout = findViewById(R.id.missingObjectAnswerSetGameLayout);
        missingObjectQuestionSetGameLayout = findViewById(R.id.missingObjectQuestionSetGameLayout);

        missingObjectInforTextView.setVisibility(View.INVISIBLE);
        missingObjectCompleteNotiTextView.setVisibility(View.INVISIBLE);
        missingObjectResultButton.setVisibility(View.GONE);
        missingObjectNextLevelButton.setVisibility(View.GONE);
        missingObjectPlayAgainButton.setVisibility(View.GONE);

        forRememberList = new ArrayList<>();
        questionList = new ArrayList<>();
        answerList = new ArrayList<>();

        numberOfCard = MissingObjectLevelMenuActivity.getMissingObjectModels().get(level - 1).getNumberOfCards();
        hideCard = MissingObjectLevelMenuActivity.getMissingObjectModels().get(level - 1).getHideCard();
        time = MissingObjectLevelMenuActivity.getMissingObjectModels().get(level - 1).getTime();

        missingObjectLevelTextView.setText("Cấp độ: "+ level);

        ID = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            ID.add(i);
        }
        Collections.shuffle(ID);

        String imageTag = "";
        for (int j = 1; j <= numberOfCard + 3; j++) {
            if (j > numberOfCard - hideCard && j <= numberOfCard) {
                imageTag = "correctAns";
            }
            generateCardView(j, ID.get(j), imageTag);
            imageTag = "";
        }

        addQuestionMarkCardView();
        gameStart();

        missingObjectNextLevelButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = getIntent();
                intent.putExtra("level", ++level);
                startActivity(intent);
                finish();
            }
        });
        missingObjectPlayAgainButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(getIntent());
                finish();
            }
        });
        missingObjectResultButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

    public void generateCardView(int imageIndex, int ID, String imageTag) {
        cardView = new CardView(MissingObjectGameActivity.this);
        image = new ImageView(MissingObjectGameActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 280);
        params.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(params);
        cardView.setRadius(70);
        cardView.setTag(imageTag);
        image.setImageResource(getResources().getIdentifier(itemName + ID, "drawable", getPackageName()));
        cardView.addView(image);
        cardView.setOnClickListener(MissingObjectGameActivity.this);
        cardView.setClickable(false);
        if (imageIndex <= numberOfCard - hideCard) {
            questionList.add(cardView);
        }
        if (imageIndex > 0 && imageIndex <= numberOfCard) {
            forRememberList.add(cardView);
        }
        if (imageIndex > numberOfCard - hideCard) {
            answerList.add(cardView);
        }
        Log.d(TAG, ID + " " + imageTag);
    }

    public void addQuestionMarkCardView() {
        for (int i = 0; i < hideCard; i++) {
            generateCardView(0, 0, null);
            questionList.add(cardView);
        }
    }

    public void gameStart() {
        missingObjectInforTextView.setVisibility(View.VISIBLE);
        missingObjectInforTextView.setText("Ghi nhớ các hình phía trên!");
        generateLayout(forRememberList, missingObjectRememberSetGameLayout);
        gameStartTimer();
    }

    public void generateLayout(List<CardView> list, GridLayout grid) {
        grid.removeAllViews();
        Collections.shuffle(list);
        for (int k = 0; k < list.size(); k++) {
            if (list.get(k).getParent() != null) {
                ((ViewGroup) list.get(k).getParent()).removeView(list.get(k));
            }
            grid.addView(list.get(k));
        }
    }

    public void gameStartTimer() {
        timer = new CountDownTimer((long) time * 1000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished / 1000;
                updateTimeLeft(timeLeft);
            }

            @Override
            public void onFinish() {
                gameContinue();
            }
        }.start();
    }

    public void updateTimeLeft(long timeLeft) {
        missingObjectTimeTextView.setText("Bạn còn " + timeLeft + " giây!");
    }

    public void gameContinue() {
        missingObjectInforTextView.setText("Chọn hình đã bị ẩn");
        missingObjectRememberSetGameLayout.removeAllViews();
        generateLayout(questionList, missingObjectQuestionSetGameLayout);
        generateLayout(answerList, missingObjectAnswerSetGameLayout);
        for(int i=0;i<answerList.size(); i++){
            for(CardView cardView : answerList){
                cardView.setClickable(true);
            }
        }
        gamePlayTimer();
    }

    public void gamePlayTimer(){
        timer = new CountDownTimer(60000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                long timeLeft = millisUntilFinished / 1000;
                updateTimeLeft(timeLeft);
            }

            @Override
            public void onFinish() {
                gameFinish();

                missingObjectCompleteNotiTextView.setText("Hết thời gian!");
            }
        }.start();
    }

    public void gameFinish(){
        timer.cancel();
        for(int i=0;i<answerList.size(); i++){
            for(CardView cardView : answerList){
                cardView.setClickable(false);
            }
        }
        missingObjectCompleteNotiTextView.setVisibility(View.VISIBLE);
        missingObjectPlayAgainButton.setVisibility(View.VISIBLE);
        missingObjectResultButton.setVisibility(View.VISIBLE);
    }

    public void gameCompleted(){
        timer.cancel();
        for(int i=0;i<answerList.size(); i++){
            for(CardView cardView : answerList){
                cardView.setClickable(false);
            }
        }
        missingObjectCompleteNotiTextView.setText("Hoàn thành màn chơi!");
        missingObjectCompleteNotiTextView.setVisibility(View.VISIBLE);
        missingObjectResultButton.setVisibility(View.VISIBLE);
        missingObjectNextLevelButton.setVisibility(View.VISIBLE);

        BrainTrainDatabase brainTrainDatabase = new BrainTrainDatabase(MissingObjectGameActivity.this);
        brainTrainDatabase.updateUserScore(11, userScore+MissingObjectLevelMenuActivity.getMissingObjectModels().get(level - 1).getScore());
        brainTrainDatabase.updateCompletedStatus("memory_game_three",row, level);


    }

    @Override
    public void onClick(View v) {
        if(v.getTag().toString().equals("correctAns")){
            //v.setVisibility(View.GONE);
            v.setBackgroundColor(Color.YELLOW);
            Toast.makeText(MissingObjectGameActivity.this, "Câu trả lời Đúng!", Toast.LENGTH_SHORT).show();
            correctAnswer++;
            if(correctAnswer==hideCard){
                gameCompleted();
            }
        } else{
            Toast.makeText(MissingObjectGameActivity.this, "Câu trả lời Sai!", Toast.LENGTH_SHORT).show();
            gameFinish();
            missingObjectCompleteNotiTextView.setText("Bạn đã chọn sai hình!");
        }
    }
}