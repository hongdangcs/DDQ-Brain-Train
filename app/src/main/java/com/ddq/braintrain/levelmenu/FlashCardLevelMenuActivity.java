package com.ddq.braintrain.levelmenu;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import com.ddq.braintrain.BrainTrainDAO;
import com.ddq.braintrain.BrainTrainDatabase;
import com.ddq.braintrain.R;
import com.ddq.braintrain.gameactivity.FlashCardGameActivity;
import com.ddq.braintrain.models.FlashCardModel;

import java.util.List;

public class FlashCardLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private static List<FlashCardModel> flashCardModels;
    GridLayout flashCardEasyLevelLayout, flashCardMediumLevelLayout, flashCardHardLevelLayout;
    AppCompatButton btn;

    public static List<FlashCardModel> getFlashCardModels() {
        return flashCardModels;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flash_card_level_menu);

        flashCardEasyLevelLayout = findViewById(R.id.flashCardEasyLevelLayout);
        flashCardMediumLevelLayout = findViewById(R.id.flashCardMediumLevelLayout);
        flashCardHardLevelLayout = findViewById(R.id.flashCardHardLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(FlashCardLevelMenuActivity.this);

        flashCardModels = new BrainTrainDAO().flashCardModels(brainTrainDatabase);

        for (int i = 0; i < flashCardModels.size(); i++) {
            btn = new AppCompatButton(FlashCardLevelMenuActivity.this);
            btn.setText("" + flashCardModels.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape));
            if (flashCardModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            flashCardEasyLevelLayout.addView(btn);
            btn.setOnClickListener(FlashCardLevelMenuActivity.this);
        }

        for (int i = 0; i < flashCardModels.size(); i++) {
            btn = new AppCompatButton(FlashCardLevelMenuActivity.this);
            btn.setText("" + flashCardModels.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 101);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape));
            if (flashCardModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            flashCardMediumLevelLayout.addView(btn);
            btn.setOnClickListener(FlashCardLevelMenuActivity.this);
        }

        for (int i = 0; i < flashCardModels.size(); i++) {
            btn = new AppCompatButton(FlashCardLevelMenuActivity.this);
            btn.setText("" + flashCardModels.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 1001);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape));
            if (flashCardModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FlashCardLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            flashCardHardLevelLayout.addView(btn);
            btn.setOnClickListener(FlashCardLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(FlashCardLevelMenuActivity.this, FlashCardGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}