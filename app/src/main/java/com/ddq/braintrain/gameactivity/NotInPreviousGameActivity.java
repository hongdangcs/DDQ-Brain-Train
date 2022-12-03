package com.ddq.braintrain.gameactivity;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.ddq.braintrain.R;

public class NotInPreviousGameActivity extends AppCompatActivity {

    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_not_in_previous_game);

        textView = findViewById(R.id.textView2);
        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);
        textView.setText("Level: " + level);
    }
}