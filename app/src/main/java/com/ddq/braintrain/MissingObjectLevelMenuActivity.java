package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;
import androidx.core.content.ContextCompat;
import androidx.gridlayout.widget.GridLayout;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;

import java.util.List;

public class MissingObjectLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private List<MissingObjectModel> missingObjectModels;
    GridLayout missingObjectEasyLevelLayout, missingObjectMediumLevelLayout, missingObjectHardLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_object_level_menu);

        missingObjectEasyLevelLayout = findViewById(R.id.missingObjectEasyLevelLayout);
        missingObjectMediumLevelLayout = findViewById(R.id.missingObjectMediumLevelLayout);
        missingObjectHardLevelLayout = findViewById(R.id.missingObjectHardLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(MissingObjectLevelMenuActivity.this);

        missingObjectModels = new BrainTrainDAO().missingObjectModels(brainTrainDatabase);

        for (int i = 0; i < missingObjectModels.size(); i++) {
            btn = new AppCompatButton(MissingObjectLevelMenuActivity.this);
            btn.setText("" + missingObjectModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape));
            if (missingObjectModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            missingObjectEasyLevelLayout.addView(btn);
            btn.setOnClickListener(MissingObjectLevelMenuActivity.this);
        }

        for (int i = 0; i < missingObjectModels.size(); i++) {
            btn = new AppCompatButton(MissingObjectLevelMenuActivity.this);
            btn.setText("" + missingObjectModels.get(i).getLevel());
            btn.setId( i + 101);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape));
            if (missingObjectModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            missingObjectMediumLevelLayout.addView(btn);
            btn.setOnClickListener(MissingObjectLevelMenuActivity.this);
        }

        for (int i = 0; i < missingObjectModels.size(); i++) {
            btn = new AppCompatButton(MissingObjectLevelMenuActivity.this);
            btn.setText("" + missingObjectModels.get(i).getLevel());
            btn.setId(i + 1001);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape));
            if (missingObjectModels.get(i).getCompleteStatusEasy() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(MissingObjectLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            missingObjectHardLevelLayout.addView(btn);
            btn.setOnClickListener(MissingObjectLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(MissingObjectLevelMenuActivity.this, MissingObjectGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}