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
import com.ddq.braintrain.gameactivity.GridsHighlightGameActivity;
import com.ddq.braintrain.models.HighlightGridsModel;

import java.util.List;

public class GridsHighlightLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private static BrainTrainDatabase brainTrainDatabase;
    private static List<HighlightGridsModel> highlightGridsModels;

    public static List<HighlightGridsModel> getHighlightGridsModels() {
        return highlightGridsModels;
    }

    GridLayout gridsHighlightLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_grids_highlight_level_menu);
        gridsHighlightLevelLayout = findViewById(R.id.gridsHighlightLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(GridsHighlightLevelMenuActivity.this);
        highlightGridsModels = new BrainTrainDAO().highlightGridsModels(brainTrainDatabase);

        for (int i = 0; i < highlightGridsModels.size(); i++) {
            btn = new AppCompatButton(GridsHighlightLevelMenuActivity.this);
            btn.setText("" + highlightGridsModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightLevelMenuActivity.this, R.drawable.button_shape));
            if (highlightGridsModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(GridsHighlightLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            gridsHighlightLevelLayout.addView(btn);
            btn.setOnClickListener(GridsHighlightLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(GridsHighlightLevelMenuActivity.this, GridsHighlightGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
        finish();
    }
}