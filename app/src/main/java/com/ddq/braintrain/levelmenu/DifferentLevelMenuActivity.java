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
import com.ddq.braintrain.gameactivity.DifferentGameActivity;
import com.ddq.braintrain.models.DifferentModel;

import java.util.List;

public class DifferentLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {


    private BrainTrainDatabase brainTrainDatabase;
    private static List<DifferentModel> differentModels;
    GridLayout differentLevelLayout;
    AppCompatButton btn;

    public static List<DifferentModel> getDifferentModels() {
        return differentModels;
    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_level_menu);

        differentLevelLayout = findViewById(R.id.differentLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(DifferentLevelMenuActivity.this);

        differentModels = new BrainTrainDAO().differentModels(brainTrainDatabase);

        for (int i = 0; i < differentModels.size(); i++) {
            btn = new AppCompatButton(DifferentLevelMenuActivity.this);
            btn.setText("" + differentModels.get(i).getImageID());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(DifferentLevelMenuActivity.this, R.drawable.button_shape));
            if (differentModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(DifferentLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            differentLevelLayout.addView(btn);
            btn.setOnClickListener(DifferentLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(DifferentLevelMenuActivity.this, DifferentGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}