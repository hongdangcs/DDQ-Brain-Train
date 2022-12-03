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
import com.ddq.braintrain.gameactivity.SharkBoatGameActivity;
import com.ddq.braintrain.models.SharkBoatModel;

import java.util.List;

public class SharkBoatLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private List<SharkBoatModel> sharkBoatModels;
    GridLayout sharkBoatLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shark_boat_level_menu);

        sharkBoatLevelLayout = findViewById(R.id.sharkBoatLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(SharkBoatLevelMenuActivity.this);
        sharkBoatModels = new BrainTrainDAO().sharkBoatModels(brainTrainDatabase);


        for (int i = 0; i < sharkBoatModels.size(); i++) {
            btn = new AppCompatButton(SharkBoatLevelMenuActivity.this);
            btn.setText("" + sharkBoatModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(150, 150);
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(SharkBoatLevelMenuActivity.this, R.drawable.button_shape));
            if (sharkBoatModels.get(i).getCompleteStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(SharkBoatLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            sharkBoatLevelLayout.addView(btn);
            btn.setOnClickListener(SharkBoatLevelMenuActivity.this);
        }

    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(SharkBoatLevelMenuActivity.this, SharkBoatGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}