package com.ddq.braintrain;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignInActivity extends AppCompatActivity {
    EditText userName, password;
    Button signin;
    BrainTrainDatabase brainTrainDatabase;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signin);

        userName = findViewById(R.id.userName);
        password = findViewById(R.id.password);
        signin = findViewById(R.id.signInBtn);
        brainTrainDatabase = new BrainTrainDatabase(this);

        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String user = userName.getText().toString();
                String pass = password.getText().toString();

                if (TextUtils.isEmpty(user) || TextUtils.isEmpty(pass)){
                    Toast.makeText(SignInActivity.this, "All fields Required!", Toast.LENGTH_SHORT).show();
                }else {
                    boolean checkUserPass = brainTrainDatabase.checkUserNamePassword(user, pass);
                    if (checkUserPass == true) {
                        Toast.makeText(SignInActivity.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(SignInActivity.this, MainActivity.class);
                        startActivity(intent);
                    } else {
                        Toast.makeText(SignInActivity.this, "Login Failed!", Toast.LENGTH_SHORT).show();
                    }
                }


            }
        });
    }

    public void SignUp(View view) {
        Intent intent = new Intent(this, SignUpActivity.class);
        startActivity(intent);
    }
}