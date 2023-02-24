package com.ddq.braintrain;

import static android.content.ContentValues.TAG;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import io.realm.Realm;
import io.realm.mongodb.App;
import io.realm.mongodb.AppConfiguration;

public class SignUpActivity extends AppCompatActivity {
    private Button btnSignUp;
    private EditText userName, password, repeatpassword;
    String appid = "braintrain-hinjk";
    BrainTrainDatabase brainTrainDatabase;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);
        brainTrainDatabase = new BrainTrainDatabase(this);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        repeatpassword = findViewById(R.id.repeatpassword);
        btnSignUp = findViewById(R.id.btnSignUp);

        Realm.init(this);

        App app = new App(new AppConfiguration.Builder(appid).build());

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = userName.getText().toString();
                String pass = password.getText().toString();
                String repeatpass = repeatpassword.getText().toString();

                if(pass.equals(repeatpass)){
                    app.getEmailPassword().registerUserAsync(name, pass, it->{
                        if(it.isSuccess()){
                            Log.d(TAG, "onClick: Register successfully");
                            Toast.makeText(SignUpActivity.this, "Đăng Kí Thành Công!", Toast.LENGTH_SHORT).show();
                        }else{
                            Log.d(TAG, "fail");
                            Toast.makeText(SignUpActivity.this, "Đăng kí thất bại!", Toast.LENGTH_SHORT).show();
                        }
                    });
                }else{
                    Toast.makeText(SignUpActivity.this, "Mật khẩu không trùng nhau!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    public void Out(View view) {
        startActivity(new Intent(SignUpActivity.this, SignInActivity.class));
    }
}