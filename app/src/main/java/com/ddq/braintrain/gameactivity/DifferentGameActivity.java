package com.ddq.braintrain.gameactivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.ddq.braintrain.R;

public class DifferentGameActivity extends AppCompatActivity {

    ImageView sample;
    TextView textView;
    Button nextimage;
    int imageID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_different_game);

        sample = findViewById(R.id.imageViewSample);
        textView = findViewById(R.id.gameNameTextView);
        nextimage = findViewById(R.id.nextimage);

        imageID = 29;

        Intent intent = getIntent();
        int level = intent.getIntExtra("level", 0);

        sample.setImageResource(getResources().getIdentifier("image"+imageID, "drawable", getPackageName()));

        nextimage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                imageID++;
                sample.setImageResource(getResources().getIdentifier("image"+imageID, "drawable", getPackageName()));
            }
        });

        sample.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getAction() == MotionEvent.ACTION_DOWN){
                    textView.setText("Touch coordinates : " +
                            String.valueOf(event.getX()) + "x" + String.valueOf(event.getY()));
                }
                return true;
            }
        });

    }
}