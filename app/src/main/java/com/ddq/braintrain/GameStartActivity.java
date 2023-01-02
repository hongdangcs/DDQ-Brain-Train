package com.ddq.braintrain;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;

public class GameStartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        SharedPreferences sharedPreferences = getSharedPreferences("loginState", MODE_PRIVATE);

        String username = sharedPreferences.getString("username", "");

        Log.d(TAG, "onCreate: " + username);

        if(username.isEmpty()){
            startActivity(new Intent(GameStartActivity.this, SignInActivity.class));
            finish();
        } else{
            startActivity(new Intent(GameStartActivity.this, MainActivity.class));
            finish();
        }
    }
}