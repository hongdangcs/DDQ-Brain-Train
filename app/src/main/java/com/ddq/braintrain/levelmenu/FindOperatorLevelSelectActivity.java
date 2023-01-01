package com.ddq.braintrain.levelmenu;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ddq.braintrain.R;
import com.ddq.braintrain.gameactivity.FindOperatorGameRoundActivity;

public class FindOperatorLevelSelectActivity extends AppCompatActivity {

    Button sumOfTen, sumOfHundred, sumOfThousand;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_find_operator_level_select);

        sumOfTen = findViewById(R.id.sumOfTen);
        sumOfHundred = findViewById(R.id.sumOfHundred);
        sumOfThousand = findViewById(R.id.sumOfThousand);

        sumOfTen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindOperatorLevelSelectActivity.this, FindOperatorGameRoundActivity.class);
                intent.putExtra("level", 10);
                startActivity(intent);
                finish();
            }
        });

        sumOfHundred.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindOperatorLevelSelectActivity.this, FindOperatorGameRoundActivity.class);
                intent.putExtra("level", 100);
                startActivity(intent);
                finish();
            }
        });

        sumOfThousand.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(FindOperatorLevelSelectActivity.this, FindOperatorGameRoundActivity.class);
                intent.putExtra("level", 1000);
                startActivity(intent);
                finish();
            }
        });
    }
}