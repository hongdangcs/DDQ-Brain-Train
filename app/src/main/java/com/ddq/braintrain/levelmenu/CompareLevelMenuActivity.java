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
import com.ddq.braintrain.gameactivity.CompareGameActivity;
import com.ddq.braintrain.models.CompareModel;

import java.util.List;

public class CompareLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private static List<CompareModel> compareModels;

    public static List<CompareModel> getCompareModels() {
        return compareModels;
    }

    GridLayout compareLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_compare_level_menu);

        compareLevelLayout = findViewById(R.id.compareLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(CompareLevelMenuActivity.this);

        compareModels = new BrainTrainDAO().compareModels(brainTrainDatabase);

        for (int i = 0; i < compareModels.size(); i++) {
            btn = new AppCompatButton(CompareLevelMenuActivity.this);
            btn.setText("" + compareModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(CompareLevelMenuActivity.this, R.drawable.button_shape));
            if (compareModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(CompareLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            compareLevelLayout.addView(btn);
            btn.setOnClickListener(CompareLevelMenuActivity.this);
        }

        //generateButton(compareModels, btn, CompareLevelMenuActivity.this,  compareLevelLayout  );
    }

    /*
        public static void generateButton(List<Object> object, AppCompatButton btn, Context context, GridLayout layout) {
            for (int i = 0; i < object.size(); i++) {
                btn = new AppCompatButton(context);
                btn.setText("" + object.get(i).getLevel());
                btn.setId(i + 1);
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
                params.setMargins(10, 10, 10, 10);
                btn.setLayoutParams(params);
                btn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_shape));
                if (object.get(i).getCompleteStatus() == 1) {
                    btn.setBackgroundDrawable(ContextCompat.getDrawable(context, R.drawable.button_shape_completed));
                }
                layout.addView(btn);
                btn.setOnClickListener((View.OnClickListener) context);
            }
        }
    */
    @Override
    public void onClick(View v) {
        Intent intent = new Intent(CompareLevelMenuActivity.this, CompareGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}