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
import com.ddq.braintrain.gameactivity.NotInPreviousGameActivity;
import com.ddq.braintrain.models.NotInPreviousModel;

import java.util.List;

public class NotInPreviousLevelMenuActivity extends AppCompatActivity implements View.OnClickListener {

    private BrainTrainDatabase brainTrainDatabase;
    private List<NotInPreviousModel> notInPreviousModels;
    GridLayout notInPreviousLevelLayout;
    AppCompatButton btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_in_previous_level_menu);

        notInPreviousLevelLayout = findViewById(R.id.notInPreviousLevelLayout);

        brainTrainDatabase = new BrainTrainDatabase(NotInPreviousLevelMenuActivity.this);
        notInPreviousModels = new BrainTrainDAO().notInPreviousModels(brainTrainDatabase);

        for (int i = 0; i < notInPreviousModels.size(); i++) {
            btn = new AppCompatButton(NotInPreviousLevelMenuActivity.this);
            btn.setText("" + notInPreviousModels.get(i).getLevel());
            btn.setId(i + 1);
            LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
                    150,
                    150
            );
            params.setMargins(10, 10, 10, 10);
            btn.setLayoutParams(params);
            btn.setBackgroundDrawable(ContextCompat.getDrawable(NotInPreviousLevelMenuActivity.this, R.drawable.button_shape));
            if (notInPreviousModels.get(i).getCompletedStatus() == 1) {
                btn.setBackgroundDrawable(ContextCompat.getDrawable(NotInPreviousLevelMenuActivity.this, R.drawable.button_shape_completed));
            }
            notInPreviousLevelLayout.addView(btn);
            btn.setOnClickListener(NotInPreviousLevelMenuActivity.this);
        }
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(NotInPreviousLevelMenuActivity.this, NotInPreviousGameActivity.class);
        intent.putExtra("level", v.getId());
        startActivity(intent);
    }
}