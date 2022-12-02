package com.ddq.braintrain;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class MissingObjectGameActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_missing_object_game);

        textView = findViewById(R.id.missingTextViewTest);
        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);
        textView.setText("Level: " + level);
    }
}