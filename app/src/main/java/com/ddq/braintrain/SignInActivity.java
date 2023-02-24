package com.ddq.braintrain;

import static android.content.ContentValues.TAG;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;
import io.realm.mongodb.Credentials;
import io.realm.mongodb.User;

public class SignInActivity extends AppCompatActivity {
    private static String user;
    String appid = "braintrain-hinjk";

    private static String pass;
    EditText userName, password;
    Button signin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signInBtn);


        Realm.init(this);

        App app = new App(new AppConfiguration.Builder(appid).build());

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user = userName.getText().toString();
                pass = password.getText().toString();
                Credentials credentials = Credentials.emailPassword(user, pass);

                app.loginAsync(credentials, new App.Callback<io.realm.mongodb.User>() {
                    @Override
                    public void onResult(App.Result<User> result) {
                        if(result.isSuccess()){
                            Log.d(TAG, "onResult: Login successfully");
                            SharedPreferences sharedPreferences = getSharedPreferences("loginState", Context.MODE_PRIVATE);
                            SharedPreferences.Editor editor = sharedPreferences.edit();
                            editor.putString("username", user);
                            editor.apply();
                            Toast.makeText(SignInActivity.this, "Đăng Nhập thành công!", Toast.LENGTH_SHORT).show();
                            Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                            startActivity(intent);
                        }
                        else {
                            Log.d(TAG, "onResult: login Fail");
                            Toast.makeText(SignInActivity.this, "Đăng nhập thất bại", Toast.LENGTH_SHORT).show();
                        }
                    }
                });

            }
        });

    }

    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }

}