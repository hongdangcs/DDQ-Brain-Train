package com.ddq.braintrain.gameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ddq.braintrain.R;

public class GridsHighlightGameActivity extends AppCompatActivity {

    private TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grids_highlight_game);

        textView = findViewById(R.id.gridsHighlight);
        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);
        textView.setText("Level: " + level);
    }
}