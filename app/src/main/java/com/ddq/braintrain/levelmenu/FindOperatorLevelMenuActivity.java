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
import com.ddq.braintrain.gameactivity.FindOperatorGameActivity;
import com.ddq.braintrain.models.FindOperatorModel;

import java.util.List;

public class FindOperatorLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {


    private BrainTrainDatabase brainTrainDatabase;
    private static List<FindOperatorModel> findOperatorModels;
    private static List<FindOperatorModel> findOperatorModels2;
    private static List<FindOperatorModel> findOperatorModels3;

    public static List<FindOperatorModel> getOperatorTenModels() {
        return findOperatorModels;
    }

    public static List<FindOperatorModel> getOperatorHundredModels() {
        return findOperatorModels2;
    }

    public static List<FindOperatorModel> getOperatorThousandModels() {
        return findOperatorModels3;
    }

    GridLayout multipleOfTenLevelLayout, multipleOfHundredLevelLayout, multipleOfThousandLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_operator_level_menu);

        multipleOfTenLevelLayout = findViewById(R.id.multipleOfTenLevelLayout);
        multipleOfHundredLevelLayout = findViewById(R.id.multipleOfHundredLevelLayout);
        multipleOfThousandLevelLayout = findViewById(R.id.multipleOfThousandLevelLayout);


        brainTrainDatabase = new BrainTrainDatabase(FindOperatorLevelMenuActivity.this);
        findOperatorModels = new BrainTrainDAO().findOperatorOfTenModels(brainTrainDatabase);

        for (int i = 0; i < findOperatorModels.size(); i++) {
            btn = new AppCompatButton(FindOperatorLevelMenuActivity.this);
            btn.setText("" + findOperatorModels.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 10);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape));
            if (findOperatorModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            multipleOfTenLevelLayout.addView(btn);
            btn.setOnClickListener(FindOperatorLevelMenuActivity.this);
        }
        findOperatorModels2 = new BrainTrainDAO().findOperatorOfHundredModels(brainTrainDatabase);

        for (int i = 0; i < findOperatorModels2.size(); i++) {
            btn = new AppCompatButton(FindOperatorLevelMenuActivity.this);
            btn.setText("" + findOperatorModels2.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 100);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape));
            if (findOperatorModels2.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            multipleOfHundredLevelLayout.addView(btn);
            btn.setOnClickListener(FindOperatorLevelMenuActivity.this);
        }

        findOperatorModels3 = new BrainTrainDAO().findOperatorOfThousandModels(brainTrainDatabase);

        for (int i = 0; i < findOperatorModels3.size(); i++) {
            btn = new AppCompatButton(FindOperatorLevelMenuActivity.this);
            btn.setText("" + findOperatorModels3.get(i).getLevel());
            btn.setTextSize(26);
            btn.setId(i + 1000);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape));
            if (findOperatorModels3.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(FindOperatorLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            multipleOfThousandLevelLayout.addView(btn);
            btn.setOnClickListener(FindOperatorLevelMenuActivity.this);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(FindOperatorLevelMenuActivity.this, FindOperatorGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}