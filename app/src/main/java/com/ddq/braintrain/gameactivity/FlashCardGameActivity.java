package com.ddq.braintrain.gameactivity;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.cardview.widget.CardView;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.R;
import com.ddq.braintrain.levelmenu.FlashCardLevelMenuActivity;
import com.ddq.braintrain.models.FlashCardModel;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class FlashCardGameActivity extends AppCompatActivity  {

    private static List<FlashCardModel> flashCardModels;

    TextView timeTextView, scoreTextView, resultTextView, levelTextView;
    AppCompatButton nextLevelButton, resultButton, playAgainButton;
    GridLayout flashCardGameLayout;
    CardView cardView;
    ImageView image;

    CardView imageCardView1, imageCardView2, imageCardView3, imageCardView4, imageCardView5, imageCardView6,
            imageCardView7, imageCardView8, imageCardView9, imageCardView10,
            imageCardView11, imageCardView12, imageCardView13, imageCardView14, imageCardView15,
            imageCardView16, imageCardView17, imageCardView18, imageCardView19, imageCardView20;

    int level, pair, time, score;
    List<Integer> ID;
    String itemName;
    List<CardView> cardViewList, appearCardViewList;
    List<CardView> clicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_game);

        flashCardModels = FlashCardLevelMenuActivity.getFlashCardModels();

        timeTextView = findViewById(R.id.timeTextView);
        levelTextView = findViewById(R.id.levelTextView);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.resultTextView);
        nextLevelButton = findViewById(R.id.nextLevelButton);
        resultButton = findViewById(R.id.resultButton);
        playAgainButton = findViewById(R.id.playAgainButton);
        flashCardGameLayout = findViewById(R.id.flashCardGameLayout);

 /*       imageCardView1 = findViewById(R.id.imageCardView1);
        imageCardView2 = findViewById(R.id.imageCardView2);
        imageCardView3 = findViewById(R.id.imageCardView3);
        imageCardView4 = findViewById(R.id.imageCardView4);
        imageCardView5 = findViewById(R.id.imageCardView5);
        imageCardView6 = findViewById(R.id.imageCardView6);
        imageCardView7 = findViewById(R.id.imageCardView7);
        imageCardView8 = findViewById(R.id.imageCardView8);
        imageCardView9 = findViewById(R.id.imageCardView9);
        imageCardView10 = findViewById(R.id.imageCardView10);
        imageCardView11 = findViewById(R.id.imageCardView11);
        imageCardView12 = findViewById(R.id.imageCardView12);
        imageCardView13 = findViewById(R.id.imageCardView13);
        imageCardView14 = findViewById(R.id.imageCardView14);
        imageCardView15 = findViewById(R.id.imageCardView15);
        imageCardView16 = findViewById(R.id.imageCardView16);
        imageCardView17 = findViewById(R.id.imageCardView17);
        imageCardView18 = findViewById(R.id.imageCardView18);
        imageCardView19 = findViewById(R.id.imageCardView19);
        imageCardView20 = findViewById(R.id.imageCardView20);*/

        imageCardView1 = new CardView(FlashCardGameActivity.this);
        imageCardView2 = new CardView(FlashCardGameActivity.this);
        imageCardView3 = new CardView(FlashCardGameActivity.this);
        imageCardView4 = new CardView(FlashCardGameActivity.this);
        imageCardView5 = new CardView(FlashCardGameActivity.this);
        imageCardView6 = new CardView(FlashCardGameActivity.this);
        imageCardView7 = new CardView(FlashCardGameActivity.this);
        imageCardView8 = new CardView(FlashCardGameActivity.this);
        imageCardView9 = new CardView(FlashCardGameActivity.this);
        imageCardView10 = new CardView(FlashCardGameActivity.this);
        imageCardView11= new CardView(FlashCardGameActivity.this);
        imageCardView12 = new CardView(FlashCardGameActivity.this);
        imageCardView13 = new CardView(FlashCardGameActivity.this);
        imageCardView14 = new CardView(FlashCardGameActivity.this);
        imageCardView15 = new CardView(FlashCardGameActivity.this);
        imageCardView16 = new CardView(FlashCardGameActivity.this);
        imageCardView17 = new CardView(FlashCardGameActivity.this);
        imageCardView18 = new CardView(FlashCardGameActivity.this);
        imageCardView19 = new CardView(FlashCardGameActivity.this);
        imageCardView20 = new CardView(FlashCardGameActivity.this);

        resultTextView.setVisibility(View.GONE);
        nextLevelButton.setVisibility(View.GONE);
        resultButton.setVisibility(View.GONE);
        playAgainButton.setVisibility(View.GONE);
   /*     imageCardView2.setVisibility(View.GONE);
        imageCardView1.setVisibility(View.GONE);
        imageCardView3.setVisibility(View.GONE);
        imageCardView4.setVisibility(View.GONE);
        imageCardView5.setVisibility(View.GONE);
        imageCardView6.setVisibility(View.GONE);
        imageCardView7.setVisibility(View.GONE);
        imageCardView8.setVisibility(View.GONE);
        imageCardView9.setVisibility(View.GONE);
        imageCardView10.setVisibility(View.GONE);
        imageCardView11.setVisibility(View.GONE);
        imageCardView12.setVisibility(View.GONE);
        imageCardView13.setVisibility(View.GONE);
        imageCardView14.setVisibility(View.GONE);
        imageCardView15.setVisibility(View.GONE);
        imageCardView16.setVisibility(View.GONE);
        imageCardView17.setVisibility(View.GONE);
        imageCardView18.setVisibility(View.GONE);
        imageCardView19.setVisibility(View.GONE);
        imageCardView20.setVisibility(View.GONE);*/

        Intent intent = getIntent();
        level = intent.getIntExtra("level", 0);
        levelTextView.setText("Level: " + level);

        itemName = "animal_image_";
        if (level > 100 && level < 200) {
            level = level - 100;
            itemName = "transportation_item_";
//            row = "complete_status_medium";

        }

        if (level > 1000) {
            level = level - 1000;
            itemName = "transportation_item_";
            //row = "complete_status_hard";

        }

        int pair = flashCardModels.get(level - 1).getPair();
        int time = flashCardModels.get(level - 1).getTime();
        int score = flashCardModels.get(level - 1).getScore();
        cardViewList = new ArrayList<>();
        appearCardViewList = new ArrayList<>();
        clicked = new ArrayList<>();

      cardViewList.add(imageCardView1);
        cardViewList.add(imageCardView2);
        cardViewList.add(imageCardView3);
        cardViewList.add(imageCardView4);
        cardViewList.add(imageCardView5);
        cardViewList.add(imageCardView6);
        cardViewList.add(imageCardView7);
        cardViewList.add(imageCardView8);
        cardViewList.add(imageCardView9);
        cardViewList.add(imageCardView10);
        cardViewList.add(imageCardView11);
        cardViewList.add(imageCardView12);
        cardViewList.add(imageCardView13);
        cardViewList.add(imageCardView14);
        cardViewList.add(imageCardView15);
        cardViewList.add(imageCardView16);
        cardViewList.add(imageCardView17);
        cardViewList.add(imageCardView18);
        cardViewList.add(imageCardView19);
        cardViewList.add(imageCardView20);


        ID = new ArrayList<>();
        for (int i = 1; i < 51; i++) {
            ID.add(i);
        }
        Collections.shuffle(ID);

        for(int i = 0; i< pair; i++){
            cardViewList.get(i).setId(ID.get(i+5));
            cardViewList.get(i+pair).setId(ID.get(i+5));

            appearCardViewList.add(cardViewList.get(i));
            appearCardViewList.add(cardViewList.get(i+pair));
        }

        Collections.shuffle(appearCardViewList);

        for( CardView cardView : appearCardViewList){
            image = new ImageView(FlashCardGameActivity.this);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 280);
            image.setImageResource(getResources().getIdentifier("question_mark_icon", "drawable", getPackageName()));
            params.setMargins(10, 10, 10, 10);
            cardView.setLayoutParams(params);
            cardView.setRadius(70);
            cardView.addView(image);
            flashCardGameLayout.addView(cardView);

            cardView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    cardView.removeAllViews();
                    image = new ImageView(FlashCardGameActivity.this);
                    image.setImageResource(getResources().getIdentifier(itemName + cardView.getId(), "drawable", getPackageName()));
                    cardView.addView(image);
/*
                    if(clicked.isEmpty()){
                        cardView.setClickable(false);
                        clicked.add(cardView);
                    }else{
                        if(cardView.getId() == clicked.get(0).getId()){
                            Toast.makeText(FlashCardGameActivity.this, "Cau tra loi dung", Toast.LENGTH_SHORT).show();
                            cardView.setClickable(false);
                        } else{
                            image = new ImageView(FlashCardGameActivity.this);
                            image.setImageResource(getResources().getIdentifier("question_mark_icon", "drawable", getPackageName()));
                            cardView.removeAllViews();
                           cardView.addView(image);
//                            clicked.get(0).removeAllViews();
//                            clicked.get(0).setClickable(true);
//                            clicked.get(0).addView(image);
//                            clicked.clear();
                        }

                    }*/
                    Log.d(TAG, cardView.getId() + "card view clicked");
                }
            });
        }

/*
        for (CardView card: appearCardViewList){
            card.setOnClickListener(FlashCardGameActivity.this);
        }*/
/*
        imageCardView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCardView1.removeAllViews();
                image = new ImageView(FlashCardGameActivity.this);
                image.setImageResource(getResources().getIdentifier(itemName + imageCardView1.getId(), "drawable", getPackageName()));
                imageCardView1.addView(image);
                Log.d(TAG, imageCardView1.getId() + "card view 1 clicked");
            }
        });

        imageCardView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCardView2.removeAllViews();
                image = new ImageView(FlashCardGameActivity.this);
                image.setImageResource(getResources().getIdentifier(itemName + imageCardView2.getId(), "drawable", getPackageName()));
                imageCardView2.addView(image);
                Log.d(TAG, imageCardView2.getId() + "card view 1 clicked");
            }
        });

        imageCardView3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCardView3.removeAllViews();
                image = new ImageView(FlashCardGameActivity.this);
                image.setImageResource(getResources().getIdentifier(itemName + imageCardView3.getId(), "drawable", getPackageName()));
                imageCardView3.addView(image);
                Log.d(TAG, imageCardView3.getId() + "card view 1 clicked");
            }
        });

        imageCardView4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageCardView4.removeAllViews();
                image = new ImageView(FlashCardGameActivity.this);
                image.setImageResource(getResources().getIdentifier(itemName + imageCardView4.getId(), "drawable", getPackageName()));
                imageCardView4.addView(image);
                Log.d(TAG, imageCardView4.getId() + "card view 1 clicked");
            }
        });*/
    }
/*
        for (int j = 1; j <= pair; j++) {
            generateCardView(j, ID.get(j));
            generateCardView(j, ID.get(j));
        }

        generateGameLayout();

*/
//    }
/*
    public void generateCardView(int imageIndex, int ID) {
        cardView = new CardView(FlashCardGameActivity.this);
        image = new ImageView(FlashCardGameActivity.this);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(230, 280);
        params.setMargins(10, 10, 10, 10);
        cardView.setLayoutParams(params);
        cardView.setRadius(70);
        cardView.setTag(ID);
        // image.setImageResource(getResources().getIdentifier(itemName + ID, "drawable", getPackageName()));
        image.setImageResource(getResources().getIdentifier("question_mark_icon", "drawable", getPackageName()));
        cardView.addView(image);
        cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                image.setImageResource(getResources().getIdentifier(itemName + ID, "drawable", getPackageName()));
                cardView.addView(image);
            }
        });
        cardViewList.add(cardView);

        Log.d(TAG, ID + " " + cardView.getTag());
    }

    public void generateGameLayout() {
        Collections.shuffle(cardViewList);
        for (CardView cardView : cardViewList) {
            flashCardGameLayout.addView(cardView);
        }
    }
*/
}